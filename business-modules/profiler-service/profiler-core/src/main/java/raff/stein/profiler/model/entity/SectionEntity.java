package raff.stein.profiler.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

/**
 * Entity representing a section in the platform. Each section can have a unique sectionId, a name, and an optional
 * parent section to support hierarchical structures.
 * A section can have multiple user permissions associated with it, allowing for fine-grained access control.
 * This entity is a configuration entity that defines the structure of the platform's sections.
 */
@Entity
@Table(name = "sections")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SectionEntity {

    @Id
    @GeneratedValue
    @Column(columnDefinition = "uuid DEFAULT gen_random_uuid()")
    private UUID id;

    @Column(nullable = false)
    private String application;

    @Column(nullable = false, unique = true)
    private String sectionCode;

    @Column(nullable = false, unique = true)
    private String sectionName;

    @OneToMany(mappedBy = "section", fetch = FetchType.EAGER)
    @OrderBy("featureName ASC")
    private List<FeatureEntity> features;

}
