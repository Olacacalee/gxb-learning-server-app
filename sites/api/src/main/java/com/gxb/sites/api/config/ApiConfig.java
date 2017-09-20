package com.gxb.sites.api.config;

import com.gxb.modules.utils.IdentitieTools;
import com.gxb.sites.api.other.SystemConfig;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author gtli
 * @date 15/12/3
 */
@Data
@Component
public class ApiConfig {
    public static Map map;
//    @Value("${domain}")
//    private String domain;

//    @Value("${cms.course.url}")
//    private String courseImage;

//    @Value("${lcms.attachmentOrCourseware.link}")
//    private String attachmentOrCoursewareLink;
//
//    @Value("${lcms.video.file.link}")
//    private String videoFileLink;
//
//    @Value("${lcms.video.cover.link}")
//    private String videoCoverLink;
//
//    @Value("${lcms.video.srt.link}")
//    private String videoSrtLink;
//
    @Value("${lcms.course.cover.link}")
    private String courseCoverLink;
//
//    @Value("${lcms.course.introVideo.link}")
//    private String courseIntroVideoLink;
//
//    @Value("${lcms.course.introVideo.cover.link}")
//    private String courseIntroVideoCoverLink;
//
//    @Value("${lcms.course.introVideo.srt.link}")
//    private String courseIntroVideoSrtLink;
//
//    @Value("${lcms.instructor.avatar.link}")
//    private String instructorAvatarLink;
//
//    @Value("${lcms.user.avatar.link}")
//    private String userAvatarLink;
//
//    @Value("${cms.banner.image.url}")
//    private String bannerImageUrl;
//
//    @Value("${split.learn_table.sql.path}")
//    private String saveLearnTableFile;
//
//    @Value("${logs.elasticsearch.host}")
//    private String elasticSearchHost ;
//
//    @Value("${logs.elasticsearch.port}")
//    private String elasticSearchPort ;
//
//    @Value("${logs.elasticsearch.index}")
//    private String elasticSearchIndex ;
//
//    @Value("${logs.elasticsearch.type}")
//    private String elasticSearchType ;
//
//    @Value("${lcms.classAssignment.submission.link}")
//    private String assignmentSubmissionLink;
//
//    @Value("${lcms.video.file.info.url}")
//    private String lcmsVideoFileInfoUrl ;
//
//    @Value("${lcms.message.center.jpush.masterSecret}")
//    private String jpushMasterSecret ;
//
//    @Value("${lcms.message.center.jpush.appkey}")
//    private String jpushAppKey ;

    public String getCourseCoverLink(String type, String id) {
        return courseCoverLink;
    }

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    public String getUserImage() {
        return IdentitieTools.getUrlByType(map, SystemConfig.Type.file.toString());
    }

    public String getUserAvatarLink() {
        return IdentitieTools.getUrlByType(map, SystemConfig.Type.file.toString());
    }
}
