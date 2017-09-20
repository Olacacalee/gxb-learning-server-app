package com.gxb.modules.domain.school;

import com.gxb.modules.core.domain.BasicDomain;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author gtli
 * @date 15/11/4
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class School extends BasicDomain {
	private static final long serialVersionUID = 4394331150956830612L;
	private Long schoolId;
	private Long zoneId;
	private String motto;
	private String name;
	private String category;
	private String validateType;
	private String website;
	private String intro;
	private String desc;
	private String permalink;
	private Long position;
	private String status;
	private Date createdAt;
	private Date updatedAt;
	private String shortname;
	private Long tenantId;
	private String logo;
	private String blackLogo;

	private String entryLevel;

}