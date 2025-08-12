package raff.stein.document.exception;

import raff.stein.platformcore.exception.ErrorCode;
import raff.stein.platformcore.exception.types.badrequest.RequestValidationException;

public class FileValidationException extends RequestValidationException {

    public FileValidationException(ErrorCode errorCode, String errorMessage) {
        super(errorCode, errorMessage);
    }
}
