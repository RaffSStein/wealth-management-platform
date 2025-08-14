package raff.stein.document.service.validation.validators;

import raff.stein.document.model.DocumentType;
import raff.stein.document.model.File;
import raff.stein.document.service.validation.FileValidationResult;
import raff.stein.document.service.validation.FileValidator;

public class FileBaseValidator extends FileValidator {

    @Override
    protected void doValidate(File file, FileValidationResult result, DocumentType configuration) {
        // Base validation checks for file and configuration
        // File checks
        if (file == null) {
            result.addError(FileBaseValidator.class.getName(), "File cannot be null");
        } else {
            if(file.getMultipartFile() == null || file.getMultipartFile().isEmpty()) {
                result.addError(FileBaseValidator.class.getName(), "MultipartFile cannot be null or empty");
            }
        }
        // configuration checks
        if (configuration == null) {
            result.addError(FileBaseValidator.class.getName(), "Document type configuration cannot be null");
        } else {
            if (configuration.getMaxFileSize() == null || configuration.getMaxFileSize() <= 0) {
                result.addError(
                        FileBaseValidator.class.getName(),
                        "Document type configuration max file size must be a non null digit greater than zero");
            }
            if (configuration.getAllowedMimeTypes() == null || configuration.getAllowedMimeTypes().isEmpty()) {
                result.addError(
                        FileBaseValidator.class.getName(),
                        "Document type configuration allowed MIME types cannot be null or empty");
            }
            if (configuration.getAllowedOperations() == null || configuration.getAllowedOperations().isEmpty()) {
                result.addError(
                        FileBaseValidator.class.getName(),
                        "Document type configuration allowed operations cannot be null or empty");
            }
        }
    }
}
