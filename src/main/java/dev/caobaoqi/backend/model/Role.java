package dev.caobaoqi.backend.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@Data
@TableName(value = "tb_role", schema = "backend")
public class Role implements Serializable {
	@Serial
	private static final long serialVersionUID = 1976738543072227434L;
	private Long id;
	private String code;
	@TableField(exist = false)
	private List<Permission> permissions;
}
