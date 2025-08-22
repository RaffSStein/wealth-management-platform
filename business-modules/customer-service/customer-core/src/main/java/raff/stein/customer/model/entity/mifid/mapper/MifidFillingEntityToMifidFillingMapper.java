package raff.stein.customer.model.entity.mifid.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import raff.stein.customer.model.bo.mifid.filling.MifidFilling;
import raff.stein.customer.model.entity.mapper.CustomerEntityCommonMapperConfig;
import raff.stein.customer.model.entity.mifid.MifidFillingEntity;

@Mapper(
        config = CustomerEntityCommonMapperConfig.class,
        uses = {MifidResponseEntityToMifidResponseMapper.class})
public interface MifidFillingEntityToMifidFillingMapper {

    MifidFillingEntityToMifidFillingMapper MAPPER = Mappers.getMapper(MifidFillingEntityToMifidFillingMapper.class);

    // Define mapping methods here
    MifidFilling toMifidFilling(MifidFillingEntity mifidFillingEntity);

    @Mapping(target = "responses", source = "responses", defaultExpression = "java(new HashSet<>())")
    @Mapping(target = "responses.filling", ignore = true)
    @Mapping(target = "calculatedRiskProfile", source = "customerRiskProfile.calculatedRiskProfile")
    MifidFillingEntity toMifidFillingEntity(MifidFilling mifidFilling);
}
