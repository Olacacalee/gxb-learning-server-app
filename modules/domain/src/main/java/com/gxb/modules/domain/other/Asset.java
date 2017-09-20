package com.gxb.modules.domain.other;

import com.gxb.modules.core.domain.BasicDomain;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Asset extends BasicDomain {
    private static final long serialVersionUID = 2122010659308513978L;

    private Long assetId;
    private Long viewableId;
    private String viewableType;
    private String title;
    private Integer attachmentWidth;
    private Integer attachmentHeight;
    private Integer attachmentFileSize;
    private String attachmentContentType;
    private String attachmentFileName;
    private Date attachmentUpdatedAt;
    private String link;
    private String alt;
    private String type;
    private Integer position;
    private String status;
    private String uuid;

    public enum ViewableType {
        User, Submission, School
    }

    public enum Type {
        Avatar, AssignmentAttachment, InstitutionTopWhiteBadgeImage, InstitutionTopBlackBadgeImage, Instructor, InstructorImage
    }

}