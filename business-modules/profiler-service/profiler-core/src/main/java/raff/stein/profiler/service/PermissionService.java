package raff.stein.profiler.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import raff.stein.platformcore.security.context.SecurityContextHolder;
import raff.stein.platformcore.security.context.WMPContext;
import raff.stein.profiler.model.UserPermission;
import raff.stein.profiler.model.entity.UserFeaturePermissionEntity;
import raff.stein.profiler.model.entity.mapper.UserFeaturePermissionToUserPermissionMapper;
import raff.stein.profiler.repository.UserFeaturePermissionRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class PermissionService {

    private final UserFeaturePermissionRepository userFeaturePermissionRepository;
    private static final UserFeaturePermissionToUserPermissionMapper userFeaturePermissionMapper = UserFeaturePermissionToUserPermissionMapper.MAPPER;

    public UserPermission getUserSitemapByClaims() {
        // This method should retrieve user claims and generate a sitemap based on those claims.
        // The implementation will depend on how user claims are stored and accessed.
        final WMPContext context = SecurityContextHolder.getContext();
        if (context == null) {
            // Handle the case where the user context is not available
            log.info("User context is not available.");
            return new UserPermission();
        }
        // Retrieve user permissions based on the user ID from the context
        final String email = context.getEmail();
        final String bankCode = context.getBankCode();
        if (StringUtils.isBlank(email) || StringUtils.isBlank(bankCode)) {
            log.info("User email or bank code is not available in the context.");
            return new UserPermission();
        }
        List<UserFeaturePermissionEntity> userPermissions =
                userFeaturePermissionRepository.findAllByUserEmailAndBankCode(email, bankCode);
        if (userPermissions.isEmpty()) {
            log.info("No user permissions found for email: [{}] and bankCode: [{}]", email, bankCode);
            return new UserPermission();
        }
        return userFeaturePermissionMapper.toUserPermission(email, bankCode, userPermissions);
    }

}
