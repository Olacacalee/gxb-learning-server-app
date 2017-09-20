package com.gxb.sites.api.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.stereotype.Component;

/**
 * @author zhaobin
 * @date 2015年12月25日
 */

@Data
@Component
public class MobileValidateCodeConfig {

    @Value("${triggerMessage.userCode}")
    private String triggerUserCode;

    @Value("${triggerMessage.userPass}")
    private String triggerUserPass;

    @Value("${triggerMessage.channel}")
    private String triggerChannel;

    @Value("${triggerMessage.url}")
    private String triggerUrl;

    @Value("${notifyMessage.userCode}")
    private String notifyUserCode;

    @Value("${notifyMessage.userPass}")
    private String notifyUserPass;

    @Value("${notifyMessage.channel}")
    private String notifyChannel;

    @Value("${notifyMessage.url}")
    private String notifyUrl;

    @Value("${voiceMessage.userCode}")
    private String voiceUserCode;


    @Value("${voiceMessage.userPass}")
    private String voiceUserPass;

    @Value("${voiceMessage.channel}")
    private String voiceChannel;


    @Value("${voiceMessage.amount}")
    private String voiceAmount;

    @Value("${voiceMessage.url}")
    private String voiceUrl;

    @Value("${voiceMessage.templateID}")
    private String voiceTemplateID;

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }
}
