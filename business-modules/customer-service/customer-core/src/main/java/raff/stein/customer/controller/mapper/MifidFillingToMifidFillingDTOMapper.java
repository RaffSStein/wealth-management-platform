package raff.stein.customer.controller.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.openapitools.model.MifidFillingDTO;
import raff.stein.customer.model.bo.mifid.filling.MifidFilling;

@Mapper(config = CustomerControllerCommonMapperConfig.class)
public interface MifidFillingToMifidFillingDTOMapper {

    MifidFillingToMifidFillingDTOMapper MAPPER = Mappers.getMapper(MifidFillingToMifidFillingDTOMapper.class);

    MifidFillingDTO toMifidFillingDTO(MifidFilling mifidFilling);

    MifidFilling toMifidFilling(MifidFillingDTO mifidFillingDTO);
}
