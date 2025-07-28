package raff.stein.profiler.model.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.openapitools.model.SectionDTO;
import raff.stein.profiler.model.Section;
import raff.stein.profiler.model.mapper.common.CommonMapperConfiguration;

@Mapper(uses = CommonMapperConfiguration.class)
public interface SectionDTOToSectionMapper {

    SectionDTOToSectionMapper MAPPER = Mappers.getMapper(SectionDTOToSectionMapper.class);

    Section toSection(SectionDTO sectionDTO);

    SectionDTO toSectionDTO(Section section);

}
