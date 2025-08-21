package raff.stein.customer.controller;

import lombok.RequiredArgsConstructor;
import org.openapitools.api.MifidApi;
import org.openapitools.model.MifidFillingDTO;
import org.openapitools.model.MifidQuestionnaireConfigDTO;
import org.openapitools.model.MifidRiskProfileResponseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import raff.stein.customer.controller.mapper.MifidConfigDTOToMifidConfig;
import raff.stein.customer.model.bo.mifid.config.MifidQuestionnaireConfig;
import raff.stein.customer.service.mifid.MifidService;

import java.util.UUID;

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

    @Override
    public ResponseEntity<MifidFillingDTO> getMifidFillingForCustomer(
            UUID customerId,
            Long fillingId) {
        return null;
    }

    @Override
    public ResponseEntity<MifidFillingDTO> saveMifidFillingForCustomer(
            UUID customerId,
            MifidFillingDTO mifidFillingDTO) {
        return null;
    }

    @Override
    public ResponseEntity<MifidFillingDTO> updateMifidFillingForCustomer(
            UUID customerId,
            Long fillingId,
            MifidFillingDTO mifidFillingDTO) {
        return null;
    }

    @Override
    public ResponseEntity<MifidRiskProfileResponseDTO> submitQuestionnaire(
            UUID customerId,
            MifidFillingDTO mifidFillingDTO) {
        return null;
    }
}
