package raff.stein.profiler.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import raff.stein.platformcore.model.audit.entity.BaseEntity;

/**
 * Entity representing a feature of the platform, such as a specific functionality or action
 * (e.g., EXPORT, EDIT_PROFILE). Each feature is associated with a section of the platform.
 * This entity is used for fine-grained permission management, allowing the platform to control
 * access to individual features within sections.
 */
@Entity
@Table(name = "features")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FeatureEntity extends BaseEntity<Integer> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String featureCode; // e.g.: "EXPORT", "EDIT_PROFILE"

    @Column(nullable = false)
    private String featureName;

    @ManyToOne(optional = false)
    @JoinColumn(name = "section_id")
    private SectionEntity section;
}
