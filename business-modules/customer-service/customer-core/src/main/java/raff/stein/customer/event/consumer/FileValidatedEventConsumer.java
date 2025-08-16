package raff.stein.customer.event.consumer;

import io.cloudevents.CloudEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.openapitools.model.FileValidatedEvent;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import raff.stein.customer.model.entity.customer.CustomerOnboardingEntity;
import raff.stein.customer.model.entity.customer.CustomerOnboardingStepEntity;
import raff.stein.customer.model.entity.customer.enumeration.OnboardingStep;
import raff.stein.customer.repository.CustomerOnboardingRepository;
import raff.stein.customer.repository.CustomerOnboardingStepRepository;
import raff.stein.customer.repository.CustomerRepository;
import raff.stein.platformcore.messaging.consumer.WMPBaseEventConsumer;

import java.util.Optional;
import java.util.UUID;

@Slf4j
@Component
@RequiredArgsConstructor
@ConditionalOnProperty(name = "kafka.topics.document-service.file-validated.enabled", havingValue = "true")
public class FileValidatedEventConsumer extends WMPBaseEventConsumer {

    private final CustomerRepository customerRepository;
    private final CustomerOnboardingRepository customerOnboardingRepository;
    private final CustomerOnboardingStepRepository customerOnboardingStepRepository;

    @KafkaListener(
            topics = "${kafka.topics.document-service.file-validated.name}",
            containerFactory = "kafkaListenerFactory",
            groupId = "${kafka.topics.document-service.file-validated.groupId}")
    public void consume(CloudEvent cloudEvent) {
        withEventPayload(
                cloudEvent,
                FileValidatedEvent.class,
                payload -> processFileValidatedEvent(payload, cloudEvent.getId()));
    }

    private void processFileValidatedEvent(FileValidatedEvent fileValidatedEvent, String eventId) {
        log.info("Processing FileValidatedEvent with eventId: [{}]", eventId);
        final UUID customerId = fileValidatedEvent.getCustomerId();
        if (customerId == null) {
            log.warn("FileValidatedEvent with eventId: [{}] has no customer ID. Skipping processing.", eventId);
            return;
        }
        // Check if the customer exists
        if (!customerRepository.existsById(customerId)) {
            log.warn("Customer with ID: [{}] does not exist. Skipping processing for eventId: [{}]", customerId, eventId);
            return;
        }
        final Boolean isValid = fileValidatedEvent.getIsValid();
        if (isValid == null) {
            log.warn("FileValidatedEvent with eventId: [{}] has no validation result. Skipping processing.", eventId);
            return;
        }
        if (isValid) {
            log.info("File with ID [{}] is valid for customer with ID: [{}]. Proceeding with onboarding step update.",
                    fileValidatedEvent.getFileId(),
                    customerId);
        } else {
            log.warn("File with ID [{}] is not valid for customer with ID: [{}]. Proceeding with onboarding step update.",
                    fileValidatedEvent.getFileId(),
                    customerId);
        }
        // proceed with file validation logic
        updatedDocumentOnboardingStep(
                customerId,
                fileValidatedEvent.getFileId(),
                isValid);

        log.info("FileValidatedEvent with eventId: [{}] processed successfully", eventId);
        // Additional processing logic can be added here
    }

    private void updatedDocumentOnboardingStep(UUID customerId, UUID fileId, Boolean isValid) {
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
