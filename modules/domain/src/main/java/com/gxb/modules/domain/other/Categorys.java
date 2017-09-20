package com.gxb.modules.domain.other;


import com.gxb.modules.core.domain.BasicDomain;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotBlank;

import java.util.List;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Categorys extends BasicDomain {
    private static final long serialVersionUID = 4432998946322981095L;
    private Long categoryId;
    private Long parentId;
    @NotBlank
    private String categoryName;
    private String shortName;
    private String description;
    private Integer position;
    private String status;
    private Integer deleteFlag;
    private Long tenantId;
    private Long userId;
    private Long editorId;

    private Long classTenantId;//从关联的class中获取的租户id


    private List<Categorys> childList;

    private Integer classCount=0;

}
