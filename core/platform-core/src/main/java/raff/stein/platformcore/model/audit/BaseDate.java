package raff.stein.platformcore.model.audit;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

/**
 * Base class for audit fields (creation and update dates) for DTOs and models.
 * These fields are populated from the entity but should not be used to overwrite entity values on mapping.
 */
@Getter
@Setter
@ToString(of = {"creationDate", "lastUpdateDate"})
public abstract class BaseDate {
    /** Entity creation date. */
    private Date creationDate;
    /** Entity last update date. */
    private Date lastUpdateDate;
}
