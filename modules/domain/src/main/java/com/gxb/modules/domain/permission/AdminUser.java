package com.gxb.modules.domain.permission;

import com.gxb.modules.core.domain.BasicDomain;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

/**
 * @author pffan
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper=true)
public class AdminUser extends BasicDomain {

	private static final long serialVersionUID = -7271891876945138874L;
	
	private Long id;
	private String type;
	private String subType;
	private Integer tenantId;
	private Integer userId;
	private Date createdAt;
	private Date updatedAt;

	private List<TenantRole> roles;

}