package dev.caobaoqi.backend.user.domain.response;

import dev.caobaoqi.backend.model.User;

import java.time.LocalDateTime;

/**
 * DTO for {@link User}
 */
public record UserInfoVo(String username, String email, LocalDateTime createTime, LocalDateTime updateTime, RoleInfoVo role) {
}
