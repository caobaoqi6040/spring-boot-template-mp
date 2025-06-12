package dev.caobaoqi.backend.user.domain;

import dev.caobaoqi.backend.model.User;
import dev.caobaoqi.backend.user.domain.request.UserLoginRequestVo;
import dev.caobaoqi.backend.user.domain.request.UserRegisterVo;
import dev.caobaoqi.backend.user.domain.response.UserInfoVo;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserStruct {
    User toEntity(UserInfoVo userInfoVo);

    UserInfoVo toUserInfoVo(User user);

    User toEntity(UserRegisterVo userRegisterVo);

    UserRegisterVo toUserRegisterVo(User user);

    User toEntity(UserLoginRequestVo userLoginRequestVo);

    UserLoginRequestVo toUserLoginRequestVo(User user);
}
