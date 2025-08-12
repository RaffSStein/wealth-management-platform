package raff.stein.customer.model.entity.mifid;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import raff.stein.platformcore.model.audit.entity.BaseDateEntity;

import java.time.LocalDate;

@Entity
@Table(name = "mifid_response")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MifidResponseEntity extends BaseDateEntity<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Relationships

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "answer_option_id")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @JsonIgnore
    private MifidAnswerOptionEntity answerOption;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "question_id")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @JsonIgnore
    private MifidQuestionEntity question;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "filling_id")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @JsonIgnore
    private MifidFillingEntity filling;

    // Fields

    @Column(name = "free_text", length = 1000)
    private String freeText;

    @Column(name = "numeric_value")
    private Double numericValue;

    @Column(name = "date_value")
    private LocalDate dateValue;

}
