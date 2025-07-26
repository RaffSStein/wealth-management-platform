package raff.stein.profiler.model.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

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
public class SectionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false, unique = true)
    private Long sectionCode;
    @Column(nullable = false, unique = true)
    private String sectionName;

}
