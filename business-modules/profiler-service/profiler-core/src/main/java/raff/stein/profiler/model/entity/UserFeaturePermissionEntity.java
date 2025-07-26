package raff.stein.profiler.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;
import java.util.UUID;

/**
 * Entity representing the association between a feature and a set of permissions for that feature, for a specific user.
 * This allows fine-grained access control, specifying which permissions a user has within a specific feature of the platform.
 * For each user and feature, it is possible to associate multiple permissions.
 */
@Entity
@Table(name = "user_feature")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserFeaturePermissionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "user_uuid", nullable = false)
    private UUID userUUID;

    @ManyToOne(optional = false)
    @JoinColumn(name = "feature_id")
    private FeatureEntity feature;

    @ManyToMany
    @JoinTable(
            name = "user_feature_permissions",
            joinColumns = @JoinColumn(name = "user_feature_id"),
            inverseJoinColumns = @JoinColumn(name = "permission_id")
    )
    private Set<PermissionEntity> permissions;
}
