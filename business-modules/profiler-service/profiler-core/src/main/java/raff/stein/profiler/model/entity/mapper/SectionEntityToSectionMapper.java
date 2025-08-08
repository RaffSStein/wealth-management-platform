package raff.stein.profiler.model.entity.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import raff.stein.profiler.model.Section;
import raff.stein.profiler.model.entity.SectionEntity;
import raff.stein.profiler.model.mapper.common.ProfilerCommonMapperConfiguration;

@Mapper(config = ProfilerCommonMapperConfiguration.class)
public interface SectionEntityToSectionMapper {

    SectionEntityToSectionMapper MAPPER = Mappers.getMapper(SectionEntityToSectionMapper.class);

    Section toSection(SectionEntity entity);
}

