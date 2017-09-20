package com.gxb.modules.domain.other;

import com.gxb.modules.core.domain.BasicDomain;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class FileUploadSet extends BasicDomain {

    private static final long serialVersionUID = -5143671032402424639L;

    private Long uploadSetId;
    private String type;
    private String url;
    private Integer status;

    public enum Type{
        video, file, image, xcsp, course
    }
}