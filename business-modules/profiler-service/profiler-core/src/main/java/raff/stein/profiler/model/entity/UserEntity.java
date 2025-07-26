package raff.stein.profiler.model.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

import java.util.Set;
import java.util.UUID;

/**
 * Entity representing a user of the platform. Each user has a unique email, first and last name,
 * and can have multiple permissions for multiple sections.
 */
@Entity
@Table(name = "users")
@Data
@Builder
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<UserSectionPermissionEntity> userSectionPermissions;
}
