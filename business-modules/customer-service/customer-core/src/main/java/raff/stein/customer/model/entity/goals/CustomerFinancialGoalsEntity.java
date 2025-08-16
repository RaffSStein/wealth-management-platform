package raff.stein.customer.model.entity.goals;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import raff.stein.customer.model.entity.customer.CustomerEntity;
import raff.stein.customer.model.entity.goals.enumeration.GoalTimeline;
import raff.stein.platformcore.model.audit.entity.BaseDateEntity;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "customer_financial_goals")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerFinancialGoalsEntity extends BaseDateEntity<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Relationships

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "goal_type_id", nullable = false)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @JsonIgnore
    private FinancialGoalTypeEntity financialGoalType;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(nullable = false)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @JsonIgnore
    private CustomerEntity customer;

    // Fields

    @Column(nullable = false, length = 100)
    @Enumerated(EnumType.STRING)
    private GoalTimeline goalTimeline;

    @Column(nullable = false, precision = 19, scale = 4)
    private BigDecimal targetAmount;

    @Column(nullable = false)
    private LocalDate targetDate;



}
