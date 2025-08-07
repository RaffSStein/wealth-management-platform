package raff.stein.user.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserSettings {

    private String language;
    private String theme;
}
