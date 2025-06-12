package dev.caobaoqi.backend.user.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import dev.caobaoqi.backend.model.Role;
import dev.caobaoqi.backend.model.User;
import dev.caobaoqi.backend.user.mapper.UserMapper;
import dev.caobaoqi.backend.user.service.RoleService;
import dev.caobaoqi.backend.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

	private final RoleService roleService;

	@Override
	public Optional<User> getUserByEmail(String email) {
		User user = this.getOne(Wrappers.<User>lambdaQuery().eq(User::getEmail, email));
		if (user == null) {
			return Optional.empty();
		}
		Role role = roleService.getRoleByUserId(user.getId()).orElseThrow(() -> new UsernameNotFoundException(String.format("user:%s not found", email)));
		user.setRole(role);
		return Optional.of(user);
	}

}
