package raff.stein.customer.service.mifid;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import raff.stein.customer.model.bo.mifid.MifidQuestionnaireConfig;

@Service
@RequiredArgsConstructor
@Slf4j
public class MifidService {

    public MifidQuestionnaireConfig getLatestValidMifidQuestionnaireConfig() {
        //TODO
        return MifidQuestionnaireConfig.builder().build();
    }

}
