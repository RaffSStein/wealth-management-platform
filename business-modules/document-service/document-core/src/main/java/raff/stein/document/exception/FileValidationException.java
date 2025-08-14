package raff.stein.document.exception;

import raff.stein.platformcore.exception.ErrorCode;
import raff.stein.platformcore.exception.types.GenericException;

import java.util.function.Supplier;

public class FileValidationException extends GenericException {

    public FileValidationException(ErrorCode errorCode, String errorMessage) {
        super(errorCode, errorMessage);
    }

    public static Supplier<FileValidationException> with(String fieldName, String fieldValue) {
        String message = String.format(ErrorCode.DOCUMENT_UPLOAD_VALIDATION_ERROR.getMessageTemplate(), fieldName, fieldValue);
        return () -> new FileValidationException(ErrorCode.DOCUMENT_UPLOAD_VALIDATION_ERROR, message);
    }
}
