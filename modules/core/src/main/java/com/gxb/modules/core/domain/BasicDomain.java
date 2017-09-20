package com.gxb.modules.core.domain;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
 * Domain基础类
 * @author lh
 * @date 2013-11-18
 */
@Data
@EqualsAndHashCode
@JsonInclude(Include.NON_EMPTY)
@JsonAutoDetect(fieldVisibility=Visibility.PROTECTED_AND_PUBLIC)
public abstract class BasicDomain implements Serializable {
	private static final long serialVersionUID = -3905527698553456808L;

//    protected Integer position;
//    protected String status;
    protected Integer deleteFlag;
    protected Date createdAt;
    protected Date updatedAt;
}
