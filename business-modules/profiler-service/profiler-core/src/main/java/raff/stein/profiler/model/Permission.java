package raff.stein.profiler.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Data;

/**
 * Entity representing a Permission in the application.
 * Mirrors the structure of PermissionDTO for MapStruct mapping.
 */
@Data
@Builder
public class Permission {

    private Integer id;
    @JsonIgnore
    private String permissionCode;
    private String permissionName;

}
