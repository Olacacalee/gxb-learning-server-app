package com.gxb.modules.domain.other;

import com.gxb.modules.core.domain.BasicDomain;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import java.util.Date;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper=true)
public class Announcement extends BasicDomain {
	
	private static final long serialVersionUID = 4086913723610891532L;
    private Long announcementId;
    private Integer contextId;
    private String contextType;
    private Long userId;
    private Long editorId;
    @NotBlank
    @Length(min = 1, max = 32)
    private String title;
    @NotBlank
    private String body;
    private String permalink;
    private Integer position;
    private String status;
    private Date createdAt;
    private Date updatedAt;

    private String name;
    
}