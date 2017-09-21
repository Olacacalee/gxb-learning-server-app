package com.gxb.modules.domain.auth;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * time : 15/11/5.
 * auth :
 * desc :
 * tips :
 * 1.
 */
@Data
@NoArgsConstructor
public class AuthToken implements Serializable{

    private Long tokenId;
    private String token;
    private String cid;
    private Long userId;
    private Date createdAt;
    private Date deadTime;
    private List<String> unionId;

}
