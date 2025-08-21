package raff.stein.customer.service.mifid;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import raff.stein.customer.exception.CustomerException;
import raff.stein.customer.model.bo.mifid.MifidQuestionnaireConfig;
import raff.stein.customer.model.entity.mifid.MifidQuestionnaireEntity;
import raff.stein.customer.model.entity.mifid.mapper.MifidQuestionnaireEntityToMifidQuestionnaireMapper;
import raff.stein.customer.repository.MifidQuestionnaireRepository;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
@Slf4j
public class MifidService {

    private final MifidQuestionnaireRepository mifidQuestionnaireRepository;

    private static final MifidQuestionnaireEntityToMifidQuestionnaireMapper mifidQuestionnaireEntityToMifidQuestionnaireMapper =
            MifidQuestionnaireEntityToMifidQuestionnaireMapper.MAPPER;

    public MifidQuestionnaireConfig getLatestValidMifidQuestionnaireConfig() {
        log.info("Fetching latest valid Mifid questionnaire configuration");
        MifidQuestionnaireEntity mifidQuestionnaireEntity = mifidQuestionnaireRepository.findValidQuestionnaire(LocalDate.now())
                .orElseThrow(() -> new CustomerException("No valid Mifid questionnaire configuration found for today date: " + LocalDate.now()));

        return mifidQuestionnaireEntityToMifidQuestionnaireMapper.toMifidQuestionnaireConfig(mifidQuestionnaireEntity);
    }

}
