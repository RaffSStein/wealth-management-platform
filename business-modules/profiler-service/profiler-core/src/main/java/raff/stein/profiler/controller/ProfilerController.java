package raff.stein.profiler.controller;

import lombok.RequiredArgsConstructor;
import org.openapitools.api.PermissionsApi;
import org.openapitools.model.UserPermissionDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import raff.stein.profiler.controller.mapper.UserPermissionDTOMapper;
import raff.stein.profiler.model.UserPermission;
import raff.stein.profiler.service.PermissionService;

@RestController
@RequiredArgsConstructor
public class ProfilerController implements PermissionsApi {

    private final PermissionService permissionService;
    private static final UserPermissionDTOMapper userPermissionMapper = UserPermissionDTOMapper.MAPPER;


    @Override
    public ResponseEntity<UserPermissionDTO> getUserSitemapByClaims() {
        // get user claims and generate sitemap
        final UserPermission userPermission = permissionService.getUserSitemapByClaims();
        final UserPermissionDTO userPermissionDTO = userPermissionMapper.toUserPermissionDTO(userPermission);
        return ResponseEntity.ok(userPermissionDTO);
    }
}
