package raff.stein.customer.service.mifid.command;

import org.springframework.stereotype.Component;
import raff.stein.customer.model.bo.mifid.filling.MifidFilling;

import java.util.EnumMap;
import java.util.List;
import java.util.UUID;

@Component
public class MifidCommandDispatcher {

    private final EnumMap<MifidActionType, MifidCommand> commandMap;

    public MifidCommandDispatcher(List<MifidCommand> commands) {
        this.commandMap = new EnumMap<>(MifidActionType.class);
        commands.forEach(command -> this.commandMap.put(command.getActionType(), command));
    }

    public MifidFilling dispatch(
            MifidActionType actionType,
            UUID customerId,
            MifidFilling dtmifidFilling) {

        MifidCommand command = commandMap.get(actionType);
        if (command == null) {
            throw new UnsupportedOperationException("Unsupported action type: " + actionType);
        }
        return command.execute(customerId, dtmifidFilling);
    }
}
