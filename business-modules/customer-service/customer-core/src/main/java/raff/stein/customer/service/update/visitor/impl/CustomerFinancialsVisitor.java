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

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.UUID;
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


        // 3. map existing entities by financial type
        Map<Integer, CustomerFinancialEntity> existingByType =
                existingEntities.stream()
                        .filter(e -> e.getFinancialTypeId() != null)
                        .collect(Collectors.toMap(CustomerFinancialEntity::getFinancialTypeId, Function.identity()));

        // 3. map new payload to entities by financial type
        Map<Integer, CustomerFinancialEntity> newByType =
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

        // 4. Update & Insert
        for (Map.Entry<Integer, CustomerFinancialEntity> entry : newByType.entrySet()) {
            Integer typeId = entry.getKey();
            CustomerFinancialEntity newEntity = entry.getValue();

            if (existingByType.containsKey(typeId)) {
                CustomerFinancialEntity existing = existingByType.get(typeId);
                existing.updateFrom(newEntity);
                customerFinancialsRepository.save(existing);
            } else {
                customerFinancialsRepository.save(newEntity);
            }
        }

        // 5. Delete the obsolete ones
        for (Map.Entry<Integer, CustomerFinancialEntity> entry : existingByType.entrySet()) {
            if (!newByType.containsKey(entry.getKey())) {
                customerFinancialsRepository.delete(entry.getValue());
            }
        }

        // 6. update customer data
        customer.setCustomerFinancials((List<CustomerFinancials>) payload);
        return customer;
    }
}
