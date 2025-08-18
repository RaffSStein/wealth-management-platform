package raff.stein.customer.service.update.visitor.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import raff.stein.customer.model.bo.customer.Customer;
import raff.stein.customer.model.bo.customer.CustomerFinancials;
import raff.stein.customer.model.entity.customer.CustomerFinancialEntity;
import raff.stein.customer.model.entity.customer.mapper.CustomerFinancialsToCustomerFinancialEntityMapper;
import raff.stein.customer.model.entity.financial.FinancialTypeEntity;
import raff.stein.customer.repository.CustomerFinancialsRepository;
import raff.stein.customer.repository.FinancialTypeRepository;
import raff.stein.customer.service.update.visitor.CustomerVisitor;
import raff.stein.customer.service.update.visitor.TypeKey;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class CustomerFinancialsVisitor implements CustomerVisitor<Collection<CustomerFinancials>> {

    private final CustomerFinancialsRepository customerFinancialsRepository;
    private final FinancialTypeRepository financialTypeRepository;

    private static final CustomerFinancialsToCustomerFinancialEntityMapper customerFinancialsToCustomerFinancialEntityMapper =
            CustomerFinancialsToCustomerFinancialEntityMapper.MAPPER;

    @Override
    public TypeKey getPayloadType() {
        return TypeKey.of(Collection.class, CustomerFinancials.class, null);
    }

    @Override
    public Customer visit(Customer customer, Collection<CustomerFinancials> payload) {
        UUID customerId = customer.getId();

        // 1. fetch existing financial entities for the customer
        List<CustomerFinancialEntity> existingEntities = customerFinancialsRepository.findByCustomerId(customerId);

        // 2. fetch financial types from the repository
        List<String> financialTypeNames = payload.stream()
                .map(CustomerFinancials::getFinancialType)
                .filter(type -> type != null && type.getName() != null && !type.getName().isEmpty())
                .map(type -> type.getName().toUpperCase())
                .distinct()
                .toList();
        List<FinancialTypeEntity> financialTypeEntities = financialTypeRepository.findAllByNameIn(financialTypeNames);

        // 3. map existing entities by financial type id
        Map<Integer, CustomerFinancialEntity> existingByTypeId =
                existingEntities.stream()
                        .filter(e -> e.getFinancialTypeId() != null)
                        .collect(Collectors.toMap(CustomerFinancialEntity::getFinancialTypeId, Function.identity()));

        // 4. map new payload to entities by financial type id
        Map<Integer, CustomerFinancialEntity> newByTypeId =
                payload.stream()
                        .map(customerFinancials -> {
                            CustomerFinancialEntity entity = customerFinancialsToCustomerFinancialEntityMapper.toCustomerFinancialsEntity(customerFinancials);
                            FinancialTypeEntity financialType = financialTypeEntities.stream()
                                    .filter(type -> type.getName().equalsIgnoreCase(customerFinancials.getFinancialType().getName()))
                                    .findFirst()
                                    .orElseThrow(() -> new IllegalArgumentException("Financial type not found: " + customerFinancials.getFinancialType().getName()));
                            entity.setFinancialTypeId(financialType.getId());
                            entity.setCustomerId(customerId);
                            return entity;
                        })
                        .filter(e -> e.getFinancialTypeId() != null)
                        .collect(Collectors.toMap(CustomerFinancialEntity::getFinancialTypeId, Function.identity()));

        List<CustomerFinancialEntity> newEntities = new ArrayList<>();

        // 5. Update & Insert
        for (Map.Entry<Integer, CustomerFinancialEntity> entry : newByTypeId.entrySet()) {
            Integer typeId = entry.getKey();
            CustomerFinancialEntity newEntity = entry.getValue();

            if (existingByTypeId.containsKey(typeId)) {
                CustomerFinancialEntity existing = existingByTypeId.get(typeId);
                existing.updateFrom(newEntity);
                CustomerFinancialEntity updated = customerFinancialsRepository.save(existing);
                newEntities.add(updated);
            } else {
                CustomerFinancialEntity inserted = customerFinancialsRepository.save(newEntity);
                newEntities.add(inserted);
            }
        }

        // 6. Delete the obsolete ones
        for (Map.Entry<Integer, CustomerFinancialEntity> entry : existingByTypeId.entrySet()) {
            if (!newByTypeId.containsKey(entry.getKey())) {
                customerFinancialsRepository.delete(entry.getValue());
            }
        }

        // 7. update customer data
        customer.setCustomerFinancials(
                newEntities.stream()
                        .map(customerFinancialsToCustomerFinancialEntityMapper::toCustomerFinancials)
                        .toList());
        return customer;
    }
}
