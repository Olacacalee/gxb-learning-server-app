package com.gxb.modules.domain.other;

import com.gxb.modules.core.domain.BasicDomain;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper=true)
public class CalendarEvent extends BasicDomain {
	
	private static final long serialVersionUID = 4086913723610891532L;
    private Long calendarEventId;
    private Long contextId;
    private String contextType;
    private Long contentId;
    private String contentType;
    private String title;
    private String desc;
    private String locationName;
    private String locationAddress;
    private Date startAt;
    private Date endAt;
    private Date deletedAt;
    private Date allDayDate;
    private Long userId;
    private String status;
    private Boolean all_day;
    private Date createdAt;
    private Date updatedAt;
    
}