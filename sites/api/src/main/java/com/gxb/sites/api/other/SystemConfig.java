package com.gxb.sites.api.other;

import com.gxb.modules.core.domain.BasicDomain;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class SystemConfig extends BasicDomain {

    private static final long serialVersionUID = -5143671032402424639L;

    private Long id;
    private String configType;
    private String configValue;
    private Integer status;

    public enum Type{
        video, file, image, xcsp, course
    }
}