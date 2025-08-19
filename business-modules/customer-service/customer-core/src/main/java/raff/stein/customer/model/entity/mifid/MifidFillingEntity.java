package raff.stein.customer.model.entity.mifid;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import raff.stein.customer.model.entity.customer.CustomerEntity;
import raff.stein.platformcore.model.audit.entity.BaseDateEntity;

import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "mifid_filling")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MifidFillingEntity extends BaseDateEntity<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Relationships

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "customer_id", nullable = false)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @JsonIgnore
    private CustomerEntity customer;

    @OneToMany(mappedBy = "filling", cascade = CascadeType.ALL, orphanRemoval = true)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @JsonIgnore
    private Set<MifidResponseEntity> responses;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "questionnaire_id", nullable = false)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @JsonIgnore
    private MifidQuestionnaireEntity questionnaire;

    // Fields

    @Column(name = "filling_date", nullable = false)
    private LocalDate fillingDate;

    @Column(name = "calculated_risk_profile", length = 100)
    private String calculatedRiskProfile;



}
