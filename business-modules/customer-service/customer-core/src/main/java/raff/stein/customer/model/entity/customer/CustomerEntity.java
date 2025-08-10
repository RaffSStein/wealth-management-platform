package raff.stein.customer.model.entity.customer;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import raff.stein.customer.model.entity.customer.enumeration.CustomerType;
import raff.stein.customer.model.entity.customer.enumeration.Gender;
import raff.stein.customer.model.entity.customer.enumeration.OnboardingStatus;
import raff.stein.platformcore.model.audit.entity.BaseDateEntity;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "customers")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerEntity extends BaseDateEntity<UUID> {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private CustomerType customerType;

    @Column
    private String firstName; // Only for individual

    @Column
    private String lastName; // Only for individual

    @Column
    private LocalDate dateOfBirth;

    @Enumerated(EnumType.STRING)
    @Column
    private Gender gender;

    @Column(length = 2)
    private String nationality; // ISO 3166-1 alpha-2

    @Column
    private String companyName; // Only for corporate

    @Column(nullable = false)
    private String taxId;

    @Column(nullable = false)
    private String email;

    @Column
    private String phoneNumber;

    @Column
    private String addressLine1;

    @Column
    private String addressLine2; // Optional

    @Column
    private String city;

    @Column
    private String zipCode;

    @Column(length = 2)
    private String country; // ISO 3166-1 alpha-2

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private OnboardingStatus onboardingStatus;

}
