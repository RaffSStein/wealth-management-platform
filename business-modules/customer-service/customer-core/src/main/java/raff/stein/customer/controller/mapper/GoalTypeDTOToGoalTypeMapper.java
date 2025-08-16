package raff.stein.customer.controller.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.openapitools.model.GoalTypeDTO;
import raff.stein.customer.model.bo.goals.GoalType;

@Mapper(config = CustomerControllerCommonMapperConfig.class)
public interface GoalTypeDTOToGoalTypeMapper {

    GoalTypeDTOToGoalTypeMapper MAPPER = Mappers.getMapper(GoalTypeDTOToGoalTypeMapper.class);

    GoalTypeDTO toGoalTypeDTO(GoalType goalType);

    GoalType toGoalType(GoalTypeDTO goalTypeDTO);
}
