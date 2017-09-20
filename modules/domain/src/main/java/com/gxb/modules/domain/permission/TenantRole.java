package com.gxb.modules.domain.permission;

import com.gxb.modules.core.domain.BasicDomain;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TenantRole extends BasicDomain {

	private static final long serialVersionUID = -8551308080903254461L;
	private Long id;
	private Long tenantId;
	private String name;
	private Date createdAt;
	private Date updatedAt;

	private List<PermissionFunction> permissionFunctions;

}