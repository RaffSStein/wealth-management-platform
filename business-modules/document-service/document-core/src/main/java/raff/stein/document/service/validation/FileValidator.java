package raff.stein.document.service.validation;

import raff.stein.document.model.File;

public abstract class FileValidator {

    private FileValidator next;

    public FileValidator linkWith(FileValidator next) {
        this.next = next;
        return next;
    }


    public FileValidationResult validate(File file, FileValidationResult result) {
        doValidate(file, result);
        if (next != null) {
            next.validate(file, result);
        }
        return result;
    }

    protected abstract void doValidate(File file, FileValidationResult result);

}
