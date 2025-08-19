package raff.stein.customer.service.onboarding.handler;

import lombok.NonNull;
import org.springframework.stereotype.Component;
import raff.stein.customer.model.entity.customer.CustomerOnboardingStepEntity;
import raff.stein.customer.model.entity.customer.enumeration.OnboardingStep;
import raff.stein.customer.repository.CustomerOnboardingStepRepository;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class OnboardingStepDispatcher {

    private final Map<OnboardingStep, OnboardingStepHandler> handlerMap;
    private final CustomerOnboardingStepRepository customerOnboardingStepRepository;

    public OnboardingStepDispatcher(
            List<OnboardingStepHandler> stepHandlersList,
            CustomerOnboardingStepRepository customerOnboardingStepRepository) {
        this.handlerMap = stepHandlersList.stream()
                .collect(Collectors.toMap(OnboardingStepHandler::getHandledStep, Function.identity()));
        this.customerOnboardingStepRepository = customerOnboardingStepRepository;
    }

    public void dispatch(OnboardingStep actualStep, @NonNull OnboardingStepContext context) {
        OnboardingStepHandler handler = handlerMap.get(actualStep);
        if (handler == null) {
            throw new IllegalStateException("No handler found for step: " + actualStep);
        }
        final UUID customerId = context.getCustomerId();
        if (customerId == null) {
            throw new IllegalArgumentException("Customer ID must not be null");
        }
        // check if this handler can proceed, based on the provided step and the step from DB
        // only for step which are not the first step (INIT)
        if (actualStep != OnboardingStep.INIT) {
            Optional<CustomerOnboardingStepEntity> onboardingOptional =
                    customerOnboardingStepRepository.findTopByCustomerOnboarding_CustomerIdAndCustomerOnboarding_IsValidTrueOrderByStepOrderDesc(customerId);
            onboardingOptional.ifPresentOrElse(o -> {
                        final OnboardingStep previousStep = o.getStep();
                        if (previousStep.getOrder() >= actualStep.getOrder()) {
                            throw new IllegalStateException("Cannot proceed to step " + actualStep + " from step " + previousStep);
                        }
                    },
                    () -> {
                        throw new IllegalStateException("No active of valid onboarding instance found for customer " + customerId);
                    });
        }
        handler.handle(context);
    }
}
