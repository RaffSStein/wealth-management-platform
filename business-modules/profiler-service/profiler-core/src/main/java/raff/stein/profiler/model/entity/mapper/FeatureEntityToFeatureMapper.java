package raff.stein.profiler.model.entity.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import raff.stein.profiler.model.Feature;
import raff.stein.profiler.model.entity.FeatureEntity;
import raff.stein.profiler.model.mapper.common.ProfilerCommonMapperConfiguration;

@Mapper(config = ProfilerCommonMapperConfiguration.class)
public interface FeatureEntityToFeatureMapper {

    FeatureEntityToFeatureMapper MAPPER = Mappers.getMapper(FeatureEntityToFeatureMapper.class);

    Feature toFeature(FeatureEntity entity);
}

