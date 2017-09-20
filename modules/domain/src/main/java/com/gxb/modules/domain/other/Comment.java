package com.gxb.modules.domain.other;

import com.gxb.modules.core.domain.BasicDomain;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Comment extends BasicDomain {
    private static final long serialVersionUID = 2122010659308513978L;

    private Long id;
    private Long userId;
    private Long evaluationId;
    private String content;
    private String status;
    private String from;
    private Date createdAt;
    private Date updatedAt;
    private Long casUserId;
    private Long cmsUserId;
    private Long tenantId;

}