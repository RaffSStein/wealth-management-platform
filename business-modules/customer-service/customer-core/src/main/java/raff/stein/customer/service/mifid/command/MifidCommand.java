package raff.stein.customer.service.mifid.command;

import jakarta.annotation.Nullable;
import lombok.NonNull;
import org.openapitools.model.MifidFillingDTO;
import raff.stein.customer.model.bo.mifid.filling.MifidFilling;

import java.util.UUID;

public interface MifidCommand {

    MifidActionType getActionType();

    MifidFilling execute(@NonNull UUID customerId, @Nullable MifidFillingDTO dto);
}
