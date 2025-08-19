package raff.stein.customer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import raff.stein.customer.model.entity.customer.CustomerOnboardingEntity;
import raff.stein.customer.model.entity.customer.CustomerOnboardingStepEntity;
import raff.stein.customer.model.entity.customer.enumeration.OnboardingStep;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface CustomerOnboardingStepRepository extends JpaRepository<CustomerOnboardingStepEntity, Long> {

    Optional<CustomerOnboardingStepEntity> findByCustomerOnboardingAndStep(
            CustomerOnboardingEntity customerOnboarding,
            OnboardingStep step);

    @Modifying
    @Transactional
    @Query("""
                UPDATE CustomerOnboardingStepEntity e
                SET e.status = :status,
                    e.reason = :reason
                WHERE e.customerOnboarding = :customerOnboarding
                  AND e.step = :step
            """)
    int updateStepStatusAndReason(
            @Param("customerOnboarding") CustomerOnboardingEntity customerOnboarding,
            @Param("step") OnboardingStep step,
            @Param("status") String status,
            @Param("reason") String reason);

    Optional<CustomerOnboardingStepEntity> findTopByCustomerOnboarding_CustomerIdAndCustomerOnboarding_IsValidTrueOrderByStepOrderDesc(UUID customerId);

}
