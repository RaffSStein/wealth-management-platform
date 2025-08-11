package raff.stein.customer.model.entity.financial;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import raff.stein.customer.model.entity.customer.CustomerEntity;
import raff.stein.platformcore.model.audit.entity.BaseDateEntity;

import java.math.BigDecimal;

@Entity
@Table(name = "customer_financials")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerFinancialsEntity extends BaseDateEntity<Integer> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    // Relationships

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "customer_id", nullable = false)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @JsonIgnore
    private CustomerEntity customer;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "financial_type_id", nullable = false)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @JsonIgnore
    private CustomerFinancialTypeEntity financialType;

    // Fields

    @Column(nullable = false, precision = 19, scale = 4)
    private BigDecimal amount;

    @Column(length = 500)
    private String description;



}
