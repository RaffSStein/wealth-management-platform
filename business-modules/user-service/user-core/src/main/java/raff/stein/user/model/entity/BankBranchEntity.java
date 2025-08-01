package raff.stein.user.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import raff.stein.platformcore.audit.BaseDateEntity;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

/**
 * Entity representing a bank branch in a wealth management platform.
 * This model is designed to be internationally compatible, supporting both Italian and global banking standards.
 */
@Entity
@Table(name = "bank_branches")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BankBranchEntity extends BaseDateEntity<UUID> {

    /**
     * Unique identifier for the bank branch (UUID).
     */
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    /**
     * List of associations between this bank branch and users (roles per user).
     */
    @OneToMany(mappedBy = "bankBranch", fetch = FetchType.LAZY)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @JsonIgnore
    private Set<BankBranchUserEntity> bankBranchUsers = new HashSet<>();

    /**
     * Bank code (internal or international identifier).
     */
    @Column(nullable = false)
    private String bankCode;

    /**
     * Branch code (internal or international identifier).
     */
    @Column(nullable = false)
    private String branchCode;

    /**
     * SWIFT/BIC code for international identification.
     */
    @Column(nullable = false)
    private String swiftCode;

    /**
     * ISO country code (e.g., "IT", "US", "DE").
     */
    @Column(nullable = false, length = 2)
    private String countryCode;

    /**
     * Type of bank (e.g., retail, private, corporate).
     */
    @Column(nullable = false)
    private String bankType;

    /**
     * Operational status of the branch (e.g., active, closed, under_renovation).
     */
    @Column(nullable = false)
    private String status;

    /**
     * Full description of the bank (e.g., legal name).
     */
    @Column(nullable = false)
    private String bankDescription;

    /**
     * Description of the branch (e.g., branch name or details).
     */
    @Column(nullable = false)
    private String branchDescription;

    /**
     * City where the branch is located.
     */
    @Column(nullable = false)
    private String branchCity;

    /**
     * Full address of the branch.
     */
    @Column(nullable = false)
    private String address;

    /**
     * ZIP or postal code of the branch location.
     */
    @Column(nullable = false)
    private String zipCode;

    /**
     * Phone number for the branch.
     */
    @Column
    private String phoneNumber;

    /**
     * Email contact for the branch.
     */
    @Column
    private String email;



}
