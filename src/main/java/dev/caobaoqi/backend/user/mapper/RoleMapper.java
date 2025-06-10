
package dev.caobaoqi.backend.user.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import dev.caobaoqi.backend.model.Role;
import org.apache.ibatis.annotations.Select;

public interface RoleMapper extends BaseMapper<Role> {

	@Select("SELECT backend.tb_role.id AS id,backend.tb_role.code AS code FROM backend.tb_role LEFT JOIN backend.user_role_map urm on backend.tb_role.id = urm.role_id WHERE user_id=#{userId}")
	Role selectRoleByUserId(Long userId);

}
