package raff.stein.customer.service.update.visitor.impl;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import raff.stein.customer.model.bo.customer.Customer;
import raff.stein.customer.model.bo.customer.CustomerGoals;
import raff.stein.customer.model.entity.customer.CustomerFinancialGoalsEntity;
import raff.stein.customer.model.entity.customer.mapper.CustomerGoalsToCustomerGoalsEntityMapper;
import raff.stein.customer.model.entity.goals.GoalTypeEntity;
import raff.stein.customer.repository.CustomerGoalsRepository;
import raff.stein.customer.repository.GoalTypeRepository;
import raff.stein.customer.service.update.visitor.CustomerVisitor;
import raff.stein.customer.service.update.visitor.TypeKey;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class CustomerGoalsVisitor implements CustomerVisitor<Collection<CustomerGoals>> {

    private final CustomerGoalsRepository customerGoalsRepository;
    private final GoalTypeRepository goalTypeRepository;

    private static final CustomerGoalsToCustomerGoalsEntityMapper customerGoalsToCustomerGoalsEntityMapper =
            CustomerGoalsToCustomerGoalsEntityMapper.MAPPER;

    @Override
    public Customer visit(Customer customer, @NonNull Collection<CustomerGoals> payload) {
        UUID customerId = customer.getId();

        // 1. fetch existing goals for the customer
        List<CustomerFinancialGoalsEntity> existingGoals = customerGoalsRepository.findByCustomerId(customerId);

        //FIXME: goals and financials may be more than 1 for the same goal type, so we need to handle that case and the update of existing values

        // 2. fetch goal types from the repository
        List<String> goalTypeNames = payload.stream()
                .map(CustomerGoals::getGoalType)
                .filter(type -> type != null && type.getName() != null && !type.getName().isEmpty())
                .map(type -> type.getName().toUpperCase())
                .distinct()
                .toList();
        List<GoalTypeEntity> goalTypeEntities = goalTypeRepository.findAllByNameIn(goalTypeNames);

        // 3. map existing goals by goal type id
        Map<Integer, CustomerFinancialGoalsEntity> existingByTypeId =
                existingGoals.stream()
                        .filter(e -> e.getGoalTypeId() != null)
                        .collect(Collectors.toMap(CustomerFinancialGoalsEntity::getGoalTypeId, Function.identity()));

        // 4. map new payload to entities by goal type id
        Map<Integer, CustomerFinancialGoalsEntity> newByTypeId =
                payload.stream()
                        .map(customerGoals -> {
                            CustomerFinancialGoalsEntity entity = customerGoalsToCustomerGoalsEntityMapper.toCustomerGoalsEntity(customerGoals);
                            GoalTypeEntity goalType = goalTypeEntities.stream()
                                    .filter(type -> type.getName().equalsIgnoreCase(customerGoals.getGoalType().getName()))
                                    .findFirst()
                                    .orElseThrow(() -> new IllegalArgumentException("Goal type not found: " + customerGoals.getGoalType().getName()));
                            entity.setCustomerId(customerId);
                            entity.setGoalTypeId(goalType.getId());
                            return entity;
                        })
                        .filter(e -> e.getGoalTypeId() != null)
                        .collect(Collectors.toMap(CustomerFinancialGoalsEntity::getGoalTypeId, Function.identity()));

        List<CustomerFinancialGoalsEntity> newEntities = new ArrayList<>();

        // 5. Update & insert
        for (Map.Entry<Integer, CustomerFinancialGoalsEntity> entry : newByTypeId.entrySet()) {
            Integer goalTypeId = entry.getKey();
            CustomerFinancialGoalsEntity newEntity = entry.getValue();

            if (existingByTypeId.containsKey(goalTypeId)) {
                // Update existing entity
                CustomerFinancialGoalsEntity existingEntity = existingByTypeId.get(goalTypeId);
                existingEntity.updateFrom(newEntity);
                CustomerFinancialGoalsEntity updated = customerGoalsRepository.save(existingEntity);
                newEntities.add(updated);
            } else {
                // Insert new entity
                CustomerFinancialGoalsEntity savedEntity = customerGoalsRepository.save(newEntity);
                newEntities.add(savedEntity);
            }
        }

        // 6. Delete obsolete goals
        for (Map.Entry<Integer, CustomerFinancialGoalsEntity> entry : existingByTypeId.entrySet()) {
            if (!newByTypeId.containsKey(entry.getKey())) {
                // Delete the obsolete goal
                customerGoalsRepository.delete(entry.getValue());
            }
        }

        // 7. Update the customer with the new goals
        customer.setCustomerGoals(
                newEntities.stream()
                        .map(customerGoalsToCustomerGoalsEntityMapper::toCustomerGoals)
                        .toList());

        return customer;
    }

    @Override
    public TypeKey getPayloadType() {
        return TypeKey.of(Collection.class, CustomerGoals.class, null);
    }
}
