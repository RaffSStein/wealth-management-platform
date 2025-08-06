package raff.stein.profiler.model.entity.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import raff.stein.profiler.model.Permission;
import raff.stein.profiler.model.entity.PermissionEntity;
import raff.stein.profiler.model.mapper.common.ProfilerCommonMapperConfiguration;

@Mapper(config = ProfilerCommonMapperConfiguration.class)
public interface PermissionEntityToPermissionMapper {

    PermissionEntityToPermissionMapper MAPPER = Mappers.getMapper(PermissionEntityToPermissionMapper.class);

    Permission toPermission(PermissionEntity entity);
}

