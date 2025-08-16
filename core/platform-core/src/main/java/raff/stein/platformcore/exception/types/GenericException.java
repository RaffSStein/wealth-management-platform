package raff.stein.platformcore.exception.types;

import lombok.Getter;
import raff.stein.platformcore.exception.ErrorCode;

@Getter
public abstract class GenericException extends RuntimeException {

    private final ErrorCode errorCode;
    private final String errorMessage;

    public GenericException(ErrorCode errorCode, String errorMessage) {
        super(errorMessage);
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    public String getErrorCodeAsString() {
        if(errorCode == null) {
            return "UNKNOWN_ERROR";
        }
        return String.valueOf(errorCode);
    }
}
