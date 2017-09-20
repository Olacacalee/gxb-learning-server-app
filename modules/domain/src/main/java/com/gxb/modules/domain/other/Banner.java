package com.gxb.modules.domain.other;

import com.gxb.modules.core.domain.BasicDomain;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Banner extends BasicDomain {
    private static final long serialVersionUID = 2122010659308513978L;

    private Long id;
    private String innerName;
    private String category;
    private String title;
    private String subtitle;
    private String url;
    private String buttonText;
    private Integer online;
    private Long position;
    private String status;
    private Long tenantId;
    private String imageUrl;//此字段从asset表中获取

    private Long assetId;


}