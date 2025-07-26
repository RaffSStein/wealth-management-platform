package raff.stein.profiler.model.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

/**
 * Entity representing a section in the platform. Each section can have a unique sectionId, a name, and an optional
 * parent section to support hierarchical structures.
 */
@Entity
@Table(name = "fe_sections")
@Data
@Builder
public class SectionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false, unique = true)
    private Long sectionId;
    @Column(nullable = false, unique = true)
    private String sectionName;
    //TODO: add parentSection
//    @Column(nullable = false)
//    private SectionEntity parentSection;

}
