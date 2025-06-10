package dev.caobaoqi.backend.user.domain.response;

import dev.caobaoqi.backend.model.Role;

import java.util.List;

/**
 * DTO for {@link Role}
 */
public record RoleInfoVo(String code, List<PermissionInfoVo> permissions) {
}
