package raff.stein.platformcore.exception;

import lombok.Getter;
import org.openapitools.model.ErrorCategory;

@Getter
public enum ErrorCode {
    UNSPECIFIED_ERROR("Unspecified error", ErrorCategory.GENERIC_ERROR),
    REQUEST_VALIDATION_ERROR("For field [%s] value [%s] is not valid", ErrorCategory.VALIDATION_ERROR),
    OBJECT_NOT_FOUND("Object with id [%s] not found", ErrorCategory.NOT_FOUND_ERROR),
    ACCESS_DENIED("Access denied for user [%s] to object with id [%s]", ErrorCategory.AUTHORIZATION_ERROR),
    AUTHENTICATION_ERROR("Authentication failed for user [%s]", ErrorCategory.AUTHENTICATION_ERROR),
    MISSING_HEADER_ERROR("Missing required header [%s]", ErrorCategory.VALIDATION_ERROR),
    OPTIMISTIC_LOCKING_ERROR("The resource has been modified by another transaction. Please retry", ErrorCategory.VALIDATION_ERROR),
    NOT_IMPLEMENTED_ERROR("This feature [%s] is not implemented yet", ErrorCategory.GENERIC_ERROR);


    private final String messageTemplate;
    private final ErrorCategory errorCategory;

    ErrorCode(String messageTemplate, ErrorCategory errorCategory) {
        this.messageTemplate = messageTemplate;
        this.errorCategory = errorCategory;
    }
}
