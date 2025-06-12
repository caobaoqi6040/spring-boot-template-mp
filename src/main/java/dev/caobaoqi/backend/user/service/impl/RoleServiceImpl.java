package dev.caobaoqi.backend.user.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import dev.caobaoqi.backend.exception.BusinessException;
import dev.caobaoqi.backend.model.Permission;
import dev.caobaoqi.backend.model.Role;
import dev.caobaoqi.backend.user.mapper.RoleMapper;
import dev.caobaoqi.backend.user.service.PermissionService;
import dev.caobaoqi.backend.user.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {

	private final RoleMapper roleMapper;
	private final PermissionService permissionService;

	@Override
	public Optional<Role> getRoleByUserId(Long userId) {
		Role role = roleMapper.selectRoleByUserId(userId);
		if (role == null) {
			return Optional.empty();
		}
		List<Permission> permissions = permissionService.getPermissionsByRoleId(role.getId())
			.orElseThrow(() -> new BusinessException(String.format("permission not found for roleId %s", role.getId())));
		role.setPermissions(permissions);
		return Optional.of(role);
	}
}
