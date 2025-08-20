package raff.stein.customer.controller;

import lombok.RequiredArgsConstructor;
import org.openapitools.api.MifidApi;
import org.openapitools.model.MifidQuestionnaireConfigDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import raff.stein.customer.controller.mapper.MifidConfigDTOToMifidConfig;
import raff.stein.customer.model.bo.mifid.MifidQuestionnaireConfig;
import raff.stein.customer.service.mifid.MifidService;

@RestController
@RequiredArgsConstructor
public class MifidController implements MifidApi {

    private final MifidService mifidService;

    private static final MifidConfigDTOToMifidConfig mifidConfigDTOToMifidConfig = MifidConfigDTOToMifidConfig.MAPPER;

    @Override
    public ResponseEntity<MifidQuestionnaireConfigDTO> getLatestValidMifidQuestionnaireConfig() {
        MifidQuestionnaireConfig mifidConfig = mifidService.getLatestValidMifidQuestionnaireConfig();
        MifidQuestionnaireConfigDTO mifidQuestionnaireConfigDTO = mifidConfigDTOToMifidConfig.toMifidConfigDTO(mifidConfig);
        return ResponseEntity.ok(mifidQuestionnaireConfigDTO);
    }
}
