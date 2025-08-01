package raff.stein.profiler.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserPermission {

    private String userEmail;
    private String bankCode;
    private List<SectionPermission> sectionPermissions;

    @Data
    @Builder
    public static class SectionPermission {
        private String sectionCode;
        private String sectionName;
        private List<FeaturePermission> featurePermissions;
    }

    @Data
    @Builder
    public static class FeaturePermission {
        private String featureCode;
        private String featureName;
        private List<Permission> permissions;
    }
}


