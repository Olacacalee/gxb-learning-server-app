package com.gxb.modules.domain.permission;

import com.gxb.modules.core.domain.BasicDomain;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

/**
 * 
 * @author gtli
 * @date 2015年10月14日
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper=true)
public class UserAdmin extends BasicDomain {
	private static final long serialVersionUID = -7271891541145138874L;
	
	private int id;
	private String type;
	private String sub_type;
	private int tenant_id;
	private int user_id;
	private Date created_at;
	private Date updated_at;

	// 权限
	private List<UserPermission> permissions;

}