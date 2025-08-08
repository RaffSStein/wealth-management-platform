package raff.stein.profiler.model.entity.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import raff.stein.profiler.model.UserPermission;
import raff.stein.profiler.model.entity.FeatureEntity;
import raff.stein.profiler.model.entity.UserFeaturePermissionEntity;
import raff.stein.profiler.model.mapper.common.ProfilerCommonMapperConfiguration;

import java.util.List;

@Mapper(config = ProfilerCommonMapperConfiguration.class, uses = {PermissionEntityToPermissionMapper.class})
public interface UserFeaturePermissionToUserPermissionMapper {

    UserFeaturePermissionToUserPermissionMapper MAPPER = Mappers.getMapper(UserFeaturePermissionToUserPermissionMapper.class);

    // Mapping from entity to model
    default UserPermission toUserPermission(
            String userEmail,
            String bankCode,
            List<UserFeaturePermissionEntity> userFeaturePermissionEntities) {
        UserPermission.UserPermissionBuilder builder = UserPermission.builder()
                .userEmail(userEmail)
                .bankCode(bankCode);
        // Group by section
        List<UserPermission.SectionPermission> sectionPermissions = userFeaturePermissionEntities.stream()
                .map(UserFeaturePermissionEntity::getFeature)
                .map(FeatureEntity::getSection)
                .distinct()
                .map(section -> UserPermission.SectionPermission.builder()
                        .sectionCode(section.getSectionCode())
                        .sectionName(section.getSectionName())
                        .featurePermissions(
                                userFeaturePermissionEntities.stream()
                                        .filter(ufe -> ufe.getFeature().getSection().equals(section))
                                        .map(ufe -> UserPermission.FeaturePermission.builder()
                                                .featureCode(ufe.getFeature().getFeatureCode())
                                                .featureName(ufe.getFeature().getFeatureName())
                                                .permissions(ufe.getPermissions().stream().map(PermissionEntityToPermissionMapper.MAPPER::toPermission).toList())
                                                .build())
                                        .toList()
                        )
                        .build())
                .toList();
        builder.sectionPermissions(sectionPermissions);
        return builder.build();
    }


    // Mapping from model to entity (not always needed, but for completeness)
    // ...potresti aggiungere qui i metodi inversi se necessario...
}
