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
public class Tenant extends BasicDomain {

	private static final long serialVersionUID = 4394331150956830612L;
	private Long id;
	private String name;
	private String tenantType;
	private Integer position;
	private String status;
	private Date createdAt;
	private Date updatedAt;
	private String zoneId;
	private String relatedIntro;
	private String relatedIntroTitle;
	private String frontendTemplate;
	private String focusColor;
	private String buttonColor;
	private String jingcaiTitle;
	private String photoTitle;
	private String teacherTitle;
	private String newsTitle;
	private String entryLevel;//all,login,tenant
	private String loginType;//"",cas,all
	private String oauthType;//cas
	private String oauthLink;//link
	private String aboutUs;
	private Integer verifyType;

}