package raff.stein.profiler.model.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.openapitools.model.PermissionDTO;
import raff.stein.profiler.model.Permission;
import raff.stein.profiler.model.mapper.common.CommonMapperConfiguration;

@Mapper(config = CommonMapperConfiguration.class)
public interface PermissionDTOToPermissionMapper {

    PermissionDTOToPermissionMapper MAPPER = Mappers.getMapper(PermissionDTOToPermissionMapper.class);

    Permission toPermission(PermissionDTO permissionDTO);

    PermissionDTO toPermissionDTO(Permission permission);
}
