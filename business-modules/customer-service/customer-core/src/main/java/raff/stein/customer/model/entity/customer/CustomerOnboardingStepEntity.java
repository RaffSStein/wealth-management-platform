package raff.stein.customer.model.entity.customer;

import jakarta.persistence.*;
import lombok.*;
import raff.stein.customer.model.entity.customer.enumeration.OnboardingStep;
import raff.stein.platformcore.model.audit.entity.BaseDateEntity;

@Entity
@Table(name = "customer_onboarding_step")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerOnboardingStepEntity extends BaseDateEntity<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "customer_onboarding_id", nullable = false)
    private CustomerOnboardingEntity customerOnboarding;

    @Column(nullable = false, length = 50)
    @Enumerated(EnumType.STRING)
    private OnboardingStep step;

    @Column(nullable = false)
    @Setter
    private String status;

    @Column(length = 1000)
    @Setter
    private String reason;

}
