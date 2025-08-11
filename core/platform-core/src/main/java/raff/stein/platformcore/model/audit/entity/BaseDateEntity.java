package raff.stein.platformcore.model.audit.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.OffsetDateTime;
import java.time.ZonedDateTime;

@MappedSuperclass
@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString(of = {"creationDate"})
public abstract class BaseDateEntity<I> extends BaseEntity<I> {

    //TODO: entityListener for changes
    // history table for changes
    // createdBy, updatedBy, deletedBy fields
    // soft delete?
    // tracing: correlationId, traceId, spanId ?

    /** entity creation date populated on @PrePersist. */
    @Column(name = "creation_date", updatable = false, nullable = false)
    @CreationTimestamp
    private OffsetDateTime creationDate;

    /** entity modification date populated on @PreUpdate. */
    @Column(name = "last_update_date", nullable = false)
    @UpdateTimestamp
    private OffsetDateTime lastUpdateDate;

    /** let hibernate manage optimistic locking. */
    @Version
    private Long version;

    /**
     * Inits the insert date.
     */
    @PrePersist
    public void initInsertDate() {
        this.creationDate = ZonedDateTime.now().toOffsetDateTime();
        this.lastUpdateDate = this.creationDate;
    }

    /**
     * Inits the last update.
     */
    @PreUpdate
    public void initLastUpdate() {
        this.lastUpdateDate = ZonedDateTime.now().toOffsetDateTime();
    }

}
