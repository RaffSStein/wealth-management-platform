package raff.stein.user.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import raff.stein.platformcore.audit.BaseDateEntity;

import java.util.UUID;

/**
 * Entity representing the association between a user and a bank branch, including the user's role within that branch.
 * Each user can operate for multiple bank branches, with a specific role for each association.
 */
@Entity
@Table(name = "bank_branch_users")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BankBranchUserEntity extends BaseDateEntity<UUID> {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    /**
     * The user associated with this bank branch role.
     */
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @JsonIgnore
    private UserEntity user;

    /**
     * The bank branch where the user operates.
     */
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "bank_branch_id", nullable = false)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @JsonIgnore
    private BankBranchEntity bankBranch;

    /**
     * The role of the user within the bank branch (e.g., SUPERVISOR, HANDLER, MANAGER, AUDITOR, etc.).
     */
    @Column(nullable = false, length = 30)
    private String role;

    /**
     * Additional notes or description for this association (optional).
     */
    @Column
    private String notes;
}
