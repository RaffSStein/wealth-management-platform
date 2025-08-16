package raff.stein.customer.model.entity.goals.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import raff.stein.customer.model.bo.goals.GoalType;
import raff.stein.customer.model.entity.goals.FinancialGoalTypeEntity;
import raff.stein.customer.model.entity.mapper.CustomerEntityCommonMapperConfig;

@Mapper(config = CustomerEntityCommonMapperConfig.class)
public interface FinancialGoalTypeEntityToGoalType {

    FinancialGoalTypeEntityToGoalType MAPPER = Mappers.getMapper(FinancialGoalTypeEntityToGoalType.class);

    FinancialGoalTypeEntity toFinancialGoalTypeEntity(GoalType goalType);

    GoalType toGoalType(FinancialGoalTypeEntity financialGoalTypeEntity);
}
