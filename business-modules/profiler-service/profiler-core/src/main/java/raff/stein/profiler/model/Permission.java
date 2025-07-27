package raff.stein.profiler.model;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

/**
 * Entity representing a Permission in the application.
 * Mirrors the structure of PermissionDTO for MapStruct mapping.
 */
@Data
@Builder
public class Permission {

    private UUID id;
    private String permissionCode;
    private String permissionName;

}
