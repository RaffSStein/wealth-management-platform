package raff.stein.document.service.validation.validators;

import raff.stein.document.model.DocumentType;
import raff.stein.document.model.File;
import raff.stein.document.service.validation.FileValidationResult;
import raff.stein.document.service.validation.FileValidator;

public class FileBaseValidator extends FileValidator {

    @Override
    protected void doValidate(File file, FileValidationResult result, DocumentType configuration) {
        // Base validation checks for file and configuration
        if(file == null) {
            result.addError(FileBaseValidator.class.getName(), "File cannot be null");
        }
        if(configuration == null) {
            result.addError(FileBaseValidator.class.getName(), "Document type configuration cannot be null");
        }
    }
}
