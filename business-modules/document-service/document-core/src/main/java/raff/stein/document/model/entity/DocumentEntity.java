package raff.stein.document.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import raff.stein.platformcore.model.audit.entity.BaseDateEntity;

import java.util.List;
import java.util.UUID;

/**
 * Represents a document entity in the system.
 * This entity contains metadata about the document, its versions, access logs, and associated document type.
 * It is used to manage documents within the document service.
 */
@Entity
@Table(name = "document")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DocumentEntity extends BaseDateEntity<UUID> {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    // Relationships

    @OneToMany(mappedBy = "document", cascade = CascadeType.ALL, orphanRemoval = true)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @JsonIgnore
    private List<DocumentVersionEntity> versions;

    @OneToMany(mappedBy = "document", cascade = CascadeType.ALL, orphanRemoval = true)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @JsonIgnore
    private List<DocumentAccessLogEntity> accessLogs;

    @OneToMany(mappedBy = "document", cascade = CascadeType.ALL, orphanRemoval = true)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @JsonIgnore
    private List<DocumentMetadataEntity> metadata;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "document_type_id")
    private DocumentTypeEntity documentType;

    // Fields

    /**
     * The name of the document.
     */
    @Column(nullable = false)
    private String documentName;

    /**
     * The customer ID associated with the document.
     * This field is used to link the document to a specific customer in the system.
     * It is marked as non-nullable to ensure that every document is associated with a customer
     */
    @Column(nullable = false)
    private UUID customerId;


}
