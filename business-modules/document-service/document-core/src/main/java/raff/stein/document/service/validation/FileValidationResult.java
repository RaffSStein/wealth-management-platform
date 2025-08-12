package raff.stein.document.service.validation;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@Builder
public class FileValidationResult {

    private final List<ValidationError> errors = new ArrayList<>();

    public void addError(String validator, String message) {
        errors.add(new ValidationError(validator, message));
    }

    public boolean isValid() {
        return errors.isEmpty();
    }


    public record ValidationError(String validator, String message) {}
}
