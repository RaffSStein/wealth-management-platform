package raff.stein.customer.model.bo.financial;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FinancialType {

    private String name;
    private raff.stein.customer.model.entity.financial.enumeration.FinancialType type;
    private String description;
}
