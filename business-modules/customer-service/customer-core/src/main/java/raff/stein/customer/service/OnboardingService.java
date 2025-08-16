package raff.stein.customer.service;


import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import raff.stein.customer.model.entity.customer.CustomerEntity;
import raff.stein.customer.model.entity.customer.CustomerOnboardingEntity;
import raff.stein.customer.model.entity.customer.CustomerOnboardingStepEntity;
import raff.stein.customer.model.entity.customer.enumeration.OnboardingStatus;
import raff.stein.customer.model.entity.customer.enumeration.OnboardingStep;
import raff.stein.customer.repository.CustomerOnboardingRepository;
import raff.stein.customer.repository.CustomerOnboardingStepRepository;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class OnboardingService {

    private final CustomerOnboardingRepository customerOnboardingRepository;
    private final CustomerOnboardingStepRepository customerOnboardingStepRepository;

    @Transactional
    public void startOnboardingProcess(CustomerEntity savedCustomerEntity) {
        log.info("Starting onboarding process for customer with ID [{}].", savedCustomerEntity.getId());
        // check if the customer already has an onboarding process instance active
        Optional<CustomerOnboardingEntity> existingOnboardingOptional =
                customerOnboardingRepository.findByCustomerIdAndIsValidTrue(savedCustomerEntity.getId());
        existingOnboardingOptional.ifPresent(onb -> {
            log.warn("Customer with ID [{}] already has an active onboarding process. Disabling the old one", savedCustomerEntity.getId());
            // if the customer already has an active onboarding process, disable it
            onb.setValid(false);
        });

        final CustomerOnboardingEntity onboardingEntity = CustomerOnboardingEntity.builder()
                .customer(savedCustomerEntity)
                .onboardingStatus(OnboardingStatus.IN_PROGRESS)
                .reason("Onboarding process initiated")
                .isValid(true)
                .build();
        customerOnboardingRepository.save(onboardingEntity);
        // create the first step of the onboarding process
        final CustomerOnboardingStepEntity firstStep = CustomerOnboardingStepEntity.builder()
                .step(OnboardingStep.INIT)
                .status("DONE")
                .reason("Onboarding process initiated")
                .customerOnboarding(onboardingEntity)
                .build();
        customerOnboardingStepRepository.save(firstStep);
    }

    public void updatedDocumentOnboardingStep(UUID customerId, UUID fileId, @NonNull Boolean isValid) {
        // if the file is valid, check or create the document step for the status
        // get the actual customer onboarding instance
        Optional<CustomerOnboardingEntity> customerOnboardingOptional =
                customerOnboardingRepository.findByCustomerIdAndIsValidTrue(customerId);
        if (customerOnboardingOptional.isEmpty()) {
            log.warn("No valid customer onboarding found for customer ID: [{}]. Skipping update.", customerId);
            return;
        }
        CustomerOnboardingEntity customerOnboarding = customerOnboardingOptional.get();
        Optional<CustomerOnboardingStepEntity> existingDocumentStepOptional =
                customerOnboardingStepRepository.findByCustomerOnboardingAndStep(
                        customerOnboarding,
                        OnboardingStep.DOCUMENTS);
        // Prepare the reason and status based on the validation result
        final String reason = isValid ?
                String.format("File with ID: [%s] has been validated successfully.", fileId.toString()) :
                String.format("File with ID: [%s] has been rejected.", fileId.toString());
        final String status = isValid ? "DONE" : "REJECTED";
        // Check if the document onboarding step already exists
        // If it exists, update the status to VALIDATED
        if (existingDocumentStepOptional.isPresent()) {
            log.info("Document onboarding step already exists for customer ID: [{}]. Updating status.", customerId);
            // Update the existing step
            CustomerOnboardingStepEntity existingStep = existingDocumentStepOptional.get();
            String appendedReason = existingStep.getReason().concat(";").concat(reason);
            existingStep.setStatus(status);
            existingStep.setReason(appendedReason);
            customerOnboardingStepRepository.updateStepStatusAndReason(
                    customerOnboarding,
                    existingStep.getStep(),
                    status,
                    appendedReason);
            log.info("Updated document onboarding step with ID: [{}], for customer ID: [{}] with file ID: [{}].",
                    existingStep.getId(),
                    customerId,
                    fileId);
        } else {
            // If it does not exist, create a new document onboarding step
            log.info("Creating new document onboarding step for customer ID: [{}].", customerId);
            // Create a new document onboarding step
            CustomerOnboardingStepEntity newDocumentStep = CustomerOnboardingStepEntity.builder()
                    .customerOnboarding(customerOnboarding)
                    .step(OnboardingStep.DOCUMENTS)
                    .status(status)
                    .reason(reason)
                    .build();
            customerOnboardingStepRepository.save(newDocumentStep);
        }
    }
}
