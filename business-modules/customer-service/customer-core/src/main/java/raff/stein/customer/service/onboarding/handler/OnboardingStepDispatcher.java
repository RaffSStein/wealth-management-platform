package raff.stein.customer.service.onboarding.handler;

import org.springframework.stereotype.Component;
import raff.stein.customer.model.entity.customer.enumeration.OnboardingStep;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class OnboardingStepDispatcher {

    private final Map<OnboardingStep, OnboardingStepHandler> handlerMap;

    public OnboardingStepDispatcher(List<OnboardingStepHandler> stepHandlersList) {
        this.handlerMap = stepHandlersList.stream()
                .collect(Collectors.toMap(OnboardingStepHandler::getHandledStep, Function.identity()));
    }

    public void dispatch(OnboardingStep onboardingStep, OnboardingStepContext context) {
        OnboardingStepHandler handler = handlerMap.get(onboardingStep);
        if (handler == null) {
            throw new IllegalStateException("No handler found for step: " + onboardingStep);
        }
        handler.handle(context);
    }
}
