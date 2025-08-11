package raff.stein.customer.model.entity.goals;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import raff.stein.customer.model.entity.customer.CustomerEntity;
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

    @Column(nullable = false)
    private String goalType;

    @Column(nullable = false, precision = 19, scale = 4)
    private BigDecimal targetAmount;

    @Column(nullable = false)
    private LocalDate targetDate;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(nullable = false)
    private CustomerEntity customer;

}
