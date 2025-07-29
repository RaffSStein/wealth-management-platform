package raff.stein.profiler.controller.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.openapitools.model.UserPermissionDTO;
import raff.stein.profiler.model.UserPermission;
import raff.stein.profiler.model.entity.mapper.PermissionEntityToPermissionMapper;
import raff.stein.profiler.model.mapper.FeatureDTOToFeatureMapper;
import raff.stein.profiler.model.mapper.SectionDTOToSectionMapper;
import raff.stein.profiler.model.mapper.common.CommonMapperConfiguration;

@Mapper(uses = {
        FeatureDTOToFeatureMapper.class,
        SectionDTOToSectionMapper.class,
        PermissionEntityToPermissionMapper.class},
        config = CommonMapperConfiguration.class)
public interface UserPermissionDTOMapper {

    UserPermissionDTOMapper MAPPER = Mappers.getMapper(UserPermissionDTOMapper.class);

    UserPermissionDTO toUserPermissionDTO(UserPermission userPermission);

    UserPermission toUserPermission(UserPermissionDTO userPermissionDTO);
}
