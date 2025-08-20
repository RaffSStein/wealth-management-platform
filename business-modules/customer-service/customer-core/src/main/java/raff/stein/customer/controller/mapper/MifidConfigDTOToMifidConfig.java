package raff.stein.customer.controller.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.openapitools.model.MifidQuestionnaireConfigDTO;
import raff.stein.customer.model.bo.mifid.MifidQuestionnaireConfig;

@Mapper(config = CustomerControllerCommonMapperConfig.class)
public interface MifidConfigDTOToMifidConfig {

    MifidConfigDTOToMifidConfig MAPPER = Mappers.getMapper(MifidConfigDTOToMifidConfig.class);

    MifidQuestionnaireConfig toMifidConfig(MifidQuestionnaireConfigDTO mifidQuestionnaireConfigDTO);

    MifidQuestionnaireConfigDTO toMifidConfigDTO(MifidQuestionnaireConfig mifidQuestionnaireConfig);

}
