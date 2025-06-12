
package dev.caobaoqi.backend.user.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import dev.caobaoqi.backend.model.Role;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

public interface RoleMapper extends BaseMapper<Role> {

	@Select("SELECT r.* FROM backend.tb_role r INNER JOIN backend.user_role_map urm on r.role_id = urm.role_id WHERE user_id = #{userId} LIMIT  1")
	@Results({
		@Result(property = "id", column = "role_id")
	})
	Role selectRoleByUserId(Long userId);

}
