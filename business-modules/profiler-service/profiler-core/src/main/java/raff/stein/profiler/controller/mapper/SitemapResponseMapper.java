package raff.stein.profiler.controller.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.openapitools.model.SitemapResponseDTO;
import raff.stein.profiler.model.Section;
import raff.stein.profiler.model.entity.mapper.PermissionEntityToPermissionMapper;
import raff.stein.profiler.model.mapper.FeatureDTOToFeatureMapper;
import raff.stein.profiler.model.mapper.SectionDTOToSectionMapper;
import raff.stein.profiler.model.mapper.common.CommonMapperConfiguration;

import java.util.List;

@Mapper(
        uses = {
                FeatureDTOToFeatureMapper.class,
                SectionDTOToSectionMapper.class,
                PermissionEntityToPermissionMapper.class},
        config = CommonMapperConfiguration.class)
public interface SitemapResponseMapper {

    SitemapResponseMapper MAPPER = Mappers.getMapper(SitemapResponseMapper.class);

    @Mapping(target = "application", source = "application")
    @Mapping(target = "sections", source = "sections")
    SitemapResponseDTO toDto(String application, List<Section> sections);

}
