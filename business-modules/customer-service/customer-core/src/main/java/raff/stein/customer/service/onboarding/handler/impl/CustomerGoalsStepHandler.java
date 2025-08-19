package raff.stein.customer.service.onboarding.handler.impl;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import raff.stein.customer.model.entity.customer.enumeration.OnboardingStep;
import raff.stein.customer.service.onboarding.handler.OnboardingStepContext;
import raff.stein.customer.service.onboarding.handler.OnboardingStepHandler;

@Component
@RequiredArgsConstructor
@Slf4j
public class CustomerGoalsStepHandler implements OnboardingStepHandler {

    @Override
    public OnboardingStep getHandledStep() {
        return OnboardingStep.GOALS;
    }

    @Override
    public void handle(@NonNull OnboardingStepContext context) {

    }
}
