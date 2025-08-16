package raff.stein.customer.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import raff.stein.customer.model.bo.goals.GoalType;
import raff.stein.customer.model.entity.goals.mapper.FinancialGoalTypeEntityToGoalType;
import raff.stein.customer.repository.GoalTypeRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class GoalsService {

    private final GoalTypeRepository goalTypeRepository;

    private static final FinancialGoalTypeEntityToGoalType financialGoalTypeEntityToGoalTypeMapper = FinancialGoalTypeEntityToGoalType.MAPPER;

    public List<GoalType> getAllGoalTypes() {
        log.debug("Retrieving all goal types");
        return goalTypeRepository.findAll()
                .stream()
                .map(financialGoalTypeEntityToGoalTypeMapper::toGoalType)
                .toList();
    }
}
