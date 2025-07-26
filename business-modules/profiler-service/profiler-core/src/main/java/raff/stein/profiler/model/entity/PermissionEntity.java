package raff.stein.profiler.model.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

import java.util.Set;
import java.util.UUID;

/**
 * Entity representing a permission that can be assigned to users. Each permission has a unique code and a name, and can be associated with multiple users.
 */
@Entity
@Table(name = "permissions")
@Data
@Builder
public class PermissionEntity {

    // p
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false, unique = true)
    private String code;

    @Column(nullable = false)
    private String permissionName;

    @ManyToMany(mappedBy = "userPermissions")
    private Set<UserEntity> users;
}
