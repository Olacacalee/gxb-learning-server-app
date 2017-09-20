package com.gxb.modules.domain.permission;

import com.gxb.modules.core.domain.BasicDomain;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 
 * @author gtli
 * @date 2015年10月14日
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper=true)
public class UserInfo extends BasicDomain {
	private static final long serialVersionUID = -1731176100303771933L;
	
	private String resetPasswordToken;
    private Date resetPasswordSentAt;
    private Date rememberCreatedAt;
    private Integer signInCount;
    private Date currentSignInAt;
    private Date lastSignInAt;
    private String currentSignInIp;
    private String lastSignInIp;
    private String confirmationToken;
    private String unconfirmedEmail;
    private Integer failedAttempts;
    private String unlockToken;
}
