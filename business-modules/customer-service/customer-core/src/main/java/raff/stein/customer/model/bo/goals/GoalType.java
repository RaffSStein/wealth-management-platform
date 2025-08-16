package raff.stein.customer.model.bo.goals;

import lombok.Builder;
import lombok.Data;
import raff.stein.customer.model.entity.goals.enumeration.GoalTimeline;

@Data
@Builder
public class GoalType {

    private String name;
    private GoalType goalType;
    private GoalTimeline goalTimeline;
    private String description;
}
