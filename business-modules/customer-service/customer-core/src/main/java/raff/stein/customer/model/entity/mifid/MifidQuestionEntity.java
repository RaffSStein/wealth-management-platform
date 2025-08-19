package raff.stein.customer.model.entity.mifid;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import raff.stein.platformcore.model.audit.entity.BaseDateEntity;

import java.util.Set;

@Entity
@Table(name = "mifid_question")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MifidQuestionEntity extends BaseDateEntity<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Relationships

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "section_id", nullable = false)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @JsonIgnore
    private MifidSectionEntity section;

    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL, orphanRemoval = true)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @JsonIgnore
    private Set<MifidAnswerOptionEntity> answerOptions;

    @OneToOne(mappedBy = "question", cascade = CascadeType.ALL, orphanRemoval = true)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @JsonIgnore
    private MifidResponseEntity response;

    // Fields

    @Column(nullable = false, length = 1000)
    private String text;

    @Column(nullable = false)
    private String questionType;

    @Column(nullable = false)
    private Boolean isRequired;

    @Column
    private Integer orderIndex;


}
