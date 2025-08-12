package raff.stein.document.service.validation;

import raff.stein.document.service.validation.validators.FileBaseValidator;
import raff.stein.document.service.validation.validators.FileExtensionValidator;
import raff.stein.document.service.validation.validators.FileSizeValidator;

public class FileValidatorProvider {

    private FileValidatorProvider() {
        // to prevent instantiation
    }

    public static FileValidator getBasicFileValidator() {
        return new FileBaseValidator()
                .linkWith(new FileSizeValidator())
                .linkWith(new FileExtensionValidator());
    }
}
