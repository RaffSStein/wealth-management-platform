package raff.stein.customer.model.entity.goals;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import raff.stein.customer.model.entity.goals.enumeration.GoalTimeline;
import raff.stein.platformcore.model.audit.entity.BaseEntity;

@Entity
@Table(name = "goal_types")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FinancialGoalTypeEntity extends BaseEntity<Integer> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    // Name of the goal type (e.g. HOME,EDUCATION,RETIREMENT,TRAVEL,BUSINESS,FAMILY_PLANNING,WEALTH_BUILDING,HEALTHCARE,OTHER
    @Column(nullable = false, length = 100)
    private String name;

    @Column(nullable = false, length = 100)
    @Enumerated(EnumType.STRING)
    private GoalTimeline goalTimeline;

    @Column(length = 500)
    private String description;
}
