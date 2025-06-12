package dev.caobaoqi.backend.user.controller;

import dev.caobaoqi.backend.user.domain.UserStruct;
import dev.caobaoqi.backend.user.domain.response.UserInfoVo;
import dev.caobaoqi.backend.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
public class UserController {

	private final UserService userService;
	private final UserStruct userStruct;

	@GetMapping
	public ResponseEntity<List<UserInfoVo>> listUserInfo() {
		List<UserInfoVo> vos = userService.list().stream()
			.map(userStruct::toUserInfoVo)
			.toList();
		return ResponseEntity.ok(vos);
	}

}
