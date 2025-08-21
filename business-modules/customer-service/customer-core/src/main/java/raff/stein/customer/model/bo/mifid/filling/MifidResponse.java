package raff.stein.customer.model.bo.mifid.filling;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class MifidResponse {

    private Integer questionId;
    private Integer answerOptionId;
    private String freeText;
    private Double numericValue;
    private LocalDate dateValue;

}

