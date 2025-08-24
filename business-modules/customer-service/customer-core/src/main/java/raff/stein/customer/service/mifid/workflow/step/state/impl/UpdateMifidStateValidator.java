package raff.stein.customer.service.mifid.workflow.step.state.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import raff.stein.customer.model.bo.mifid.filling.MifidFilling;
import raff.stein.customer.model.entity.mifid.MifidFillingEntity;
import raff.stein.customer.model.entity.mifid.enumeration.MifidFillingStatus;
import raff.stein.customer.repository.mifid.MifidFillingRepository;
import raff.stein.customer.repository.mifid.MifidQuestionnaireRepository;
import raff.stein.customer.service.mifid.enumeration.MifidActionType;
import raff.stein.customer.service.mifid.workflow.step.state.MifidStateValidator;

import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class UpdateMifidStateValidator extends BaseMifidStateValidator {

    @Override
    public void validateStatus(MifidFillingStatus currentStatus, MifidActionType actionType) {
        // No strict validation for other actions
        if (Objects.requireNonNull(actionType) == MifidActionType.SUBMIT) {
            if (currentStatus != MifidFillingStatus.COMPLETED) {
                throw new IllegalStateException("Cannot submit MIFID unless it is COMPLETED. Current status: " + currentStatus);
            }
        }
    }
}
