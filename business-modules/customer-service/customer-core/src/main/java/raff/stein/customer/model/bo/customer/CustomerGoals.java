package raff.stein.customer.model.bo.customer;

import lombok.Builder;
import lombok.Data;
import raff.stein.customer.model.bo.goals.GoalType;
import raff.stein.customer.model.entity.goals.enumeration.GoalTimeline;

import java.math.BigDecimal;

@Data
@Builder
public class CustomerGoals {

    private GoalType goalType;
    private BigDecimal targetAmount;
    private String notes;
    private GoalTimeline goalTimeline;
}
