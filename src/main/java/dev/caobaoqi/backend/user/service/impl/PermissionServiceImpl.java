package dev.caobaoqi.backend.user.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import dev.caobaoqi.backend.model.Permission;
import dev.caobaoqi.backend.user.mapper.PermissionMapper;
import dev.caobaoqi.backend.user.service.PermissionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, Permission> implements PermissionService {

	private final PermissionMapper permissionMapper;

	@Override
	public Optional<List<Permission>> getPermissionsByRoleId(Long roleId) {
		return Optional.ofNullable(permissionMapper.selectPermissionsByRoleId(roleId));
	}
}
