package raff.stein.profiler.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import raff.stein.platformcore.model.audit.entity.BaseEntity;

import java.util.List;

/**
 * Entity representing a section in the platform. Each section can have a unique sectionId, a name, and an optional
 * parent section to support hierarchical structures.
 * A section can have multiple user permissions associated with it, allowing for fine-grained access control.
 * This entity is a configuration entity that defines the structure of the platform's sections.
 */
@Entity
@Table(name = "sections")
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SectionEntity extends BaseEntity<Integer> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String application;

    @Column(nullable = false, unique = true)
    private String sectionCode;

    @Column(nullable = false, unique = true)
    private String sectionName;

    @OneToMany(mappedBy = "section", fetch = FetchType.EAGER)
    @OrderBy("featureName ASC")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @JsonIgnore
    private List<FeatureEntity> features;

}
