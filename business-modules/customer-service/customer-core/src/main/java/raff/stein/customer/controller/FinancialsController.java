package raff.stein.customer.controller;

import lombok.RequiredArgsConstructor;
import org.openapitools.api.FinancialApi;
import org.openapitools.model.FinancialTypeDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import raff.stein.customer.service.FinancialsService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class FinancialsController implements FinancialApi {

    private final FinancialsService financialsService;

    @Override
    public ResponseEntity<List<FinancialTypeDTO>> getAllFinancialTypes() {
        return null;
    }
}
