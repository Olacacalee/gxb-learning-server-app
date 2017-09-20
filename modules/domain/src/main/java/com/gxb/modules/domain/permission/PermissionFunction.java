package com.gxb.modules.domain.permission;

import com.gxb.modules.core.domain.BasicDomain;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PermissionFunction extends BasicDomain {

	private static final long serialVersionUID = -1313640792366907283L;

	private Long id;
	private Integer parentId;
	private String name;
	private String path;
	private String treePath;
	private Integer depth;
	private Integer position;
	private String status;
	private Date createdAt;
	private Date updatedAt;

}
