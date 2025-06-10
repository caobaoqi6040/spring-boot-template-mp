package dev.caobaoqi.backend.user.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import dev.caobaoqi.backend.model.User;
import dev.caobaoqi.backend.user.mapper.UserMapper;
import dev.caobaoqi.backend.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {


}
