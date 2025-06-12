package dev.caobaoqi.backend.user.domain.request;

import jakarta.validation.constraints.Email;

/**
 * DTO for {@link dev.caobaoqi.backend.model.User}
 */
public record UserLoginRequestVo(@Email String email, String password) {
}
