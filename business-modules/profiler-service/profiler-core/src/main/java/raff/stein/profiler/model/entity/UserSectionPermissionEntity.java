package raff.stein.profiler.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

/**
 * Entity representing the association between a user, a section, and a set of permissions for that section.
 * This allows fine-grained access control, specifying which permissions a user has within a specific section of the platform.
 */
@Entity
@Table(name = "user_section_permissions")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserSectionPermissionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @ManyToOne(optional = false)
    @JoinColumn(name = "section_id")
    private SectionEntity section;

    @ManyToOne(optional = false)
    @JoinColumn(name = "permission_id")
    private PermissionEntity permission;
}
