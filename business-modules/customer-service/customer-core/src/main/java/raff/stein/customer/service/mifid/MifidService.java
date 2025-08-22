package raff.stein.customer.service.mifid;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.openapitools.model.MifidFillingDTO;
import org.springframework.stereotype.Service;
import raff.stein.customer.controller.mapper.MifidFillingToMifidFillingDTOMapper;
import raff.stein.customer.exception.CustomerException;
import raff.stein.customer.model.bo.mifid.config.MifidQuestionnaireConfig;
import raff.stein.customer.model.bo.mifid.filling.MifidFilling;
import raff.stein.customer.model.entity.mifid.MifidQuestionnaireEntity;
import raff.stein.customer.model.entity.mifid.mapper.MifidQuestionnaireEntityToMifidQuestionnaireMapper;
import raff.stein.customer.repository.mifid.MifidQuestionnaireRepository;
import raff.stein.customer.service.mifid.command.MifidActionType;
import raff.stein.customer.service.mifid.command.MifidCommandDispatcher;

import java.time.LocalDate;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class MifidService {

    private final MifidCommandDispatcher mifidCommandDispatcher;

    private final MifidQuestionnaireRepository mifidQuestionnaireRepository;

    private static final MifidQuestionnaireEntityToMifidQuestionnaireMapper mifidQuestionnaireEntityToMifidQuestionnaireMapper =
            MifidQuestionnaireEntityToMifidQuestionnaireMapper.MAPPER;
    private static final MifidFillingToMifidFillingDTOMapper mifidFillingToMifidFillingDTOMapper =
            MifidFillingToMifidFillingDTOMapper.MAPPER;

    public MifidQuestionnaireConfig getLatestValidMifidQuestionnaireConfig() {
        log.info("Fetching latest valid Mifid questionnaire configuration");
        MifidQuestionnaireEntity mifidQuestionnaireEntity = mifidQuestionnaireRepository.findValidQuestionnaire(LocalDate.now())
                .orElseThrow(() -> new CustomerException("No valid Mifid questionnaire configuration found for today date: " + LocalDate.now()));

        return mifidQuestionnaireEntityToMifidQuestionnaireMapper.toMifidQuestionnaireConfig(mifidQuestionnaireEntity);
    }

    public MifidFilling handleFilling(
            MifidActionType actionType,
            UUID customerId,
            MifidFillingDTO dto) {
        if (actionType == null) {
            throw new IllegalArgumentException("Action type must not be null");
        }
        return mifidCommandDispatcher.dispatch(
                actionType,
                customerId,
                mifidFillingToMifidFillingDTOMapper.toMifidFilling(dto));
    }

}
