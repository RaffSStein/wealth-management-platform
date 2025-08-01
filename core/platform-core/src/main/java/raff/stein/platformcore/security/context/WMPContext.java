package raff.stein.platformcore.security.context;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.util.Set;

@ToString
@Builder
@Data
public class WMPContext {

    private final String userId;
    private final String email;
    private final Set<String> roles;
    private final String bankCode;
    private final String correlationId;


}
