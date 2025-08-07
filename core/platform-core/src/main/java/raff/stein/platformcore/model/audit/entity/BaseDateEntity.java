package raff.stein.platformcore.model.audit.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;

@MappedSuperclass
@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString(of = {"creationDate"})
public abstract class BaseDateEntity<I> extends BaseEntity<I> {

    //TODO: entityListener for changes
    // history table for changes
    // createdBy, updatedBy, deletedBy fields
    // soft dalete?
    // tracign: correlationId, traceId, spanId ?

    /** entity creation date populated on @PrePersist. */
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "creation_date", updatable = false, nullable = false)
    @CreationTimestamp
    private Date creationDate;

    /** entity modification date populated on @PreUpdate. */
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "last_update_date", nullable = false)
    @UpdateTimestamp
    private Date lastUpdateDate;

    /** let hibernate manage optimistic locking. */
    @Version
    private Long version;

    /**
     * Inits the insert date.
     */
    @PrePersist
    public void initInsertDate() {
        this.creationDate = new Date();
        this.lastUpdateDate = this.creationDate;
    }

    /**
     * Inits the last update.
     */
    @PreUpdate
    public void initLastUpdate() {
        this.lastUpdateDate = new Date();
    }

}
