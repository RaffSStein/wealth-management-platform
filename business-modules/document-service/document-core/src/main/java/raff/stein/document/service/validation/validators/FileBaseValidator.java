package raff.stein.document.service.validation.validators;

import raff.stein.document.model.File;
import raff.stein.document.service.validation.FileValidationResult;
import raff.stein.document.service.validation.FileValidator;

public class FileBaseValidator extends FileValidator {

    @Override
    protected void doValidate(File file, FileValidationResult result) {
        if(file == null) {
            result.addError(FileBaseValidator.class.getName(), "File cannot be null");
        }
    }
}
