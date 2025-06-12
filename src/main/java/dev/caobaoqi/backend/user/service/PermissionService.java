package dev.caobaoqi.backend.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import dev.caobaoqi.backend.model.Permission;

import java.util.List;
import java.util.Optional;

public interface PermissionService extends IService<Permission> {
	Optional<List<Permission>> getPermissionsByRoleId(Long roleId);
}
