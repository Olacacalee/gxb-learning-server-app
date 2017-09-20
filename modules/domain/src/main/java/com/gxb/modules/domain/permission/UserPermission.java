package com.gxb.modules.domain.permission;

import com.gxb.modules.core.domain.BasicDomain;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 
 * @author gtli
 * @date 2015年10月14日
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper=true)
public class UserPermission extends BasicDomain {
	private static final long serialVersionUID = 6131318005504190291L;
	
	private Long id;
    private String name;
    private String subject_name;
    private String action;
    private String desc;
}
