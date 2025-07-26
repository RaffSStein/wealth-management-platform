package raff.stein.profiler.model.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

/**
 * Entity representing a permission that can be assigned to users.
 * Each permission has a unique code and a name, and can be associated with multiple user section permissions.
 * This entity is a configuration entity that defines the permissions available in the platform.
 */
@Entity
@Table(name = "permissions")
@Data
@Builder
public class PermissionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false, unique = true)
    private String permissionCode;

    @Column(nullable = false)
    private String permissionName;
}
