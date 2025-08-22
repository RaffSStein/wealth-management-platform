package raff.stein.customer.service.mifid.command.impl;

import jakarta.annotation.Nullable;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.openapitools.model.MifidFillingDTO;
import org.springframework.stereotype.Component;
import raff.stein.customer.model.bo.mifid.filling.MifidFilling;
import raff.stein.customer.service.mifid.command.MifidActionType;
import raff.stein.customer.service.mifid.command.MifidCommand;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class UpdateMifidCommand implements MifidCommand {

    @Override
    public MifidActionType getActionType() {
        return MifidActionType.UPDATE;
    }

    @Override
    public MifidFilling execute(@NonNull UUID customerId, @Nullable MifidFillingDTO dto) {
        return null;
    }
}
