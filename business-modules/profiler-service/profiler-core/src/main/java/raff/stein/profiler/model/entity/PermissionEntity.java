package raff.stein.profiler.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import raff.stein.platformcore.model.audit.entity.BaseEntity;

/**
 * Entity representing a permission that can be assigned to users.
 * Each permission has a unique code and a name, and can be associated with multiple user section permissions.
 * This entity is a configuration entity that defines the permissions available in the platform.
 */
@Entity
@Table(name = "permissions")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PermissionEntity extends BaseEntity<Integer> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, unique = true)
    private String permissionCode;

    @Column(nullable = false)
    private String permissionName;
}
