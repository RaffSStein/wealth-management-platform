package raff.stein.user.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserBranchRoles {

    private String bankCode;
    private String role;

}
