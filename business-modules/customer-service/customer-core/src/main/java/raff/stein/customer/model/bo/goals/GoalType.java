package raff.stein.customer.model.bo.goals;

import lombok.Builder;
import lombok.Data;
import raff.stein.customer.model.entity.goals.enumeration.GoalTypeEnum;

@Data
@Builder
public class GoalType {

    private String name;
    private GoalTypeEnum type;
    private String description;
}
