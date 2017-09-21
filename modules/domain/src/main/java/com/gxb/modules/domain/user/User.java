package com.gxb.modules.domain.user;

import com.gxb.modules.core.domain.BasicDomain;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @author lixing
 * @date 2017年9月22日
 */

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class User extends BasicDomain {

    private static final long serialVersionUID = 4394331150956830612L;

    private Long userId;
    private String username;
    private String password; //密码,
    private String name;
    private String gender;
    private String mobile;
    private String uuid;
}
