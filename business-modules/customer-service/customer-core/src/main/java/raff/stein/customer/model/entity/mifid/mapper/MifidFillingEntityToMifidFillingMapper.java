package raff.stein.customer.model.entity.mifid.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import raff.stein.customer.model.bo.mifid.filling.MifidFilling;
import raff.stein.customer.model.entity.mapper.CustomerEntityCommonMapperConfig;
import raff.stein.customer.model.entity.mifid.MifidFillingEntity;

@Mapper(config = CustomerEntityCommonMapperConfig.class)
public interface MifidFillingEntityToMifidFillingMapper {

    MifidFillingEntityToMifidFillingMapper MAPPER = Mappers.getMapper(MifidFillingEntityToMifidFillingMapper.class);

    // Define mapping methods here
    MifidFilling toMifidFilling(MifidFillingEntity mifidFillingEntity);

    MifidFillingEntity toMifidFillingEntity(MifidFilling mifidFilling);
}
