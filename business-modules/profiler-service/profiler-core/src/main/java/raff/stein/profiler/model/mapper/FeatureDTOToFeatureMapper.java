package raff.stein.profiler.model.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.openapitools.model.FeatureDTO;
import raff.stein.profiler.model.Feature;
import raff.stein.profiler.model.mapper.common.CommonMapperConfiguration;

@Mapper(config = CommonMapperConfiguration.class)
public interface FeatureDTOToFeatureMapper {

    FeatureDTOToFeatureMapper MAPPER = Mappers.getMapper(FeatureDTOToFeatureMapper.class);

    Feature toFeature(FeatureDTO featureDTO);

    FeatureDTO toFeatureDTO(Feature feature);
}
