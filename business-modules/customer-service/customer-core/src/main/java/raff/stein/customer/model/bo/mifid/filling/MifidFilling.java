package raff.stein.customer.model.bo.mifid.filling;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
@Builder
public class MifidFilling {

    private Long questionnaireId;
    private LocalDate fillingDate;
    private List<MifidResponse> responses;


}
