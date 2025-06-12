package dev.caobaoqi.backend.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Builder;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@Data
@Builder
@TableName(value = "tb_permission", schema = "backend")
public class Permission implements Serializable {
	@Serial
	private static final long serialVersionUID = -7779098863897984255L;
	@TableId(type = IdType.AUTO,value = "permission_id")
	private Long id;
	private String code;
}
