package raff.stein.customer.service.mifid.workflow.step.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import raff.stein.customer.service.mifid.enumeration.MifidActionType;
import raff.stein.customer.service.mifid.workflow.MifidWorkflow;
import raff.stein.customer.service.mifid.workflow.step.MifidWorkflowStep;

import java.util.List;

@Component
@RequiredArgsConstructor
public class SubmitMifidWorkflow implements MifidWorkflow {

    //TODO: implement

    @Override
    public MifidActionType getActionType() {
        return MifidActionType.SUBMIT;
    }

    @Override
    public List<MifidWorkflowStep> preHooks() {
        return List.of();
    }

    @Override
    public MifidWorkflowStep command() {
        return null;
    }

    @Override
    public List<MifidWorkflowStep> postHooks() {
        return List.of();
    }
}
