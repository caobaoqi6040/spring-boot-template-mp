package dev.caobaoqi.backend.user.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import dev.caobaoqi.backend.model.Permission;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface PermissionMapper extends BaseMapper<Permission> {

	@Select("SELECT backend.tb_permission.id AS id,backend.tb_permission.code AS code FROM backend.tb_permission LEFT JOIN backend.role_permission_map rpm on backend.tb_permission.id = rpm.permission_id WHERE role_id=#{roleId}")
	List<Permission> selectPermissionByRoleId(Long roleId);

}

