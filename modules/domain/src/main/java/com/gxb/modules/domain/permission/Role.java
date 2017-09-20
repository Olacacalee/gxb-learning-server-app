package com.gxb.modules.domain.permission;

import com.gxb.modules.core.domain.BasicDomain;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author pffan
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper=true)
public class Role extends BasicDomain {

	private static final long serialVersionUID = -7271891876945138874L;
	
	private Long roleId;
	private String name;
	private Date createdAt;
	private Date updatedAt;

}