package dev.caobaoqi.backend.user.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import dev.caobaoqi.backend.model.Permission;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface PermissionMapper extends BaseMapper<Permission> {

	@Select("SELECT p.* FROM backend.tb_permission p INNER JOIN backend.role_permission_map rpm on p.permission_id = rpm.permission_id WHERE role_id = #{roleId}")
	@Results({
		@Result(property = "id", column = "permission_id")
	})
	List<Permission> selectPermissionsByRoleId(Long roleId);

}

