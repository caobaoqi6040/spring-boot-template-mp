package dev.caobaoqi.backend.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import dev.caobaoqi.backend.model.User;

import java.util.Optional;

public interface UserService extends IService<User> {
	Optional<User> getUserByEmail(String email);
}
