package raff.stein.identity.model;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class AuthenticatedUser {

    private String userId;
    private String username;
    private String email;
    private List<String> roles;
    private String companyCode;
    private boolean isImpersonated;
}
