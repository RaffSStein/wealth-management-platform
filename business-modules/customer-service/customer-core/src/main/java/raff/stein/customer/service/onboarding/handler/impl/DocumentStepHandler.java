package raff.stein.customer.service.onboarding.handler.impl;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import raff.stein.customer.model.entity.customer.CustomerOnboardingEntity;
import raff.stein.customer.model.entity.customer.CustomerOnboardingStepEntity;
import raff.stein.customer.model.entity.customer.enumeration.OnboardingStatus;
import raff.stein.customer.model.entity.customer.enumeration.OnboardingStep;
import raff.stein.customer.repository.CustomerOnboardingRepository;
import raff.stein.customer.repository.CustomerOnboardingStepRepository;
import raff.stein.customer.service.onboarding.handler.OnboardingStepContext;
import raff.stein.customer.service.onboarding.handler.OnboardingStepHandler;

import java.util.Optional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
@Slf4j
public class DocumentStepHandler implements OnboardingStepHandler {

    private final CustomerOnboardingRepository customerOnboardingRepository;
    private final CustomerOnboardingStepRepository customerOnboardingStepRepository;

    @Override
    public OnboardingStep getHandledStep() {
        return OnboardingStep.DOCUMENTS;
    }

    @Override
    public void handle(@NonNull OnboardingStepContext context) {
        UUID customerId = context.getCustomerId();
        UUID fileId = (UUID) context.getMetadata("fileId");
        Boolean isValid = (Boolean) context.getMetadata("isValid");

        log.info("Handling document step for customer ID: [{}] with file ID: [{}]. Valid: [{}]", customerId, fileId, isValid);

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
        final String reason = Boolean.TRUE.equals(isValid) ?
                String.format("File with ID: [%s] has been validated successfully.", fileId.toString()) :
                String.format("File with ID: [%s] has been rejected.", fileId.toString());
        final String status = Boolean.TRUE.equals(isValid) ? "DONE" : "REJECTED";
        // Check if the document onboarding step already exists
        // If it exists, update the status and reason with the new outcome
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
                    .customerOnboardingId(customerOnboarding.getId())
                    .step(OnboardingStep.DOCUMENTS)
                    .stepOrder(OnboardingStep.DOCUMENTS.getOrder())
                    .status(status)
                    .reason(reason)
                    .build();
            customerOnboardingStepRepository.save(newDocumentStep);
        }
        // now update the onboarding status
        if (Boolean.TRUE.equals(isValid)) {
            log.info("File with ID: [{}] is valid. Proceeding to next onboarding step for customer ID: [{}].", fileId, customerId);
            // Proceed to the next step in the onboarding process
            customerOnboarding.setOnboardingStatus(OnboardingStatus.IN_PROGRESS);
            customerOnboarding.setReason("Document validated successfully");
        } else {
            log.warn("File with ID: [{}] is not valid. Marking onboarding as failed for customer ID: [{}].", fileId, customerId);
            // Mark the onboarding as failed
            customerOnboarding.setOnboardingStatus(OnboardingStatus.FAILED);
            customerOnboarding.setReason("Document validation failed");
        }
    }
}
