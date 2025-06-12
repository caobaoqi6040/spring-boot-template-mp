package dev.caobaoqi.backend.user.domain.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

/**
 * DTO for {@link dev.caobaoqi.backend.model.User}
 */
public record UserRegisterVo(
	@NotNull(message = "用户名不能为空") @Size(message = "用户名不符合格式", min = 6, max = 12) String username,
	@Email(message = "邮箱不符合格式") String email,
	@Size(message = "密码不符合格式", min = 6, max = 18) String password) {
}
