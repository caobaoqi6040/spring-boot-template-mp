package dev.caobaoqi.backend.model;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;

@Data
@TableName(value = "tb_user", schema = "backend")
@EqualsAndHashCode(callSuper = true)
public class User extends BaseEntity {
	@Serial
	private static final long serialVersionUID = -4397710465184505447L;
	@TableId(type = IdType.AUTO)
	private Long id;
	private String username;
	private String email;
	private String password;
	@TableLogic
	private Boolean enable;
	@TableField(exist = false)
	private Role role;
}
