package dev.caobaoqi.backend.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import dev.caobaoqi.backend.model.Role;

import java.util.Optional;

public interface RoleService extends IService<Role> {
	Optional<Role> getRoleByUserId(Long userId);
}
