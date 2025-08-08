package raff.stein.profiler.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import raff.stein.platformcore.model.audit.entity.BaseDateEntity;

import java.util.Set;
import java.util.UUID;

/**
 * Entity representing the association between a feature and a set of permissions for that feature, for a specific user.
 * This allows fine-grained access control, specifying which permissions a user has within a specific feature of the platform.
 * Each user can manage multiple banks.
 * For each bank-feature pair, it is possible to associate multiple permissions.
 */
@Entity
@Table(name = "user_feature")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserFeaturePermissionEntity extends BaseDateEntity<UUID> {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "user_email", nullable = false)
    private String userEmail;

    @Column(name = "bank_code", nullable = false)
    private String bankCode;

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
