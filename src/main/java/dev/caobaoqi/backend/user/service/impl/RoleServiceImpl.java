package dev.caobaoqi.backend.user.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import dev.caobaoqi.backend.model.Role;
import dev.caobaoqi.backend.user.mapper.RoleMapper;
import dev.caobaoqi.backend.user.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {


}
