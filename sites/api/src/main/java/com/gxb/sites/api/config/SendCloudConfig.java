package com.gxb.sites.api.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.stereotype.Component;

/**
 * @author zhaobin
 * @date 2015年12月30日
 */

@Data
@Component
public class SendCloudConfig {

    @Value("${sendCloud.api.user}")
    private String apiUser;

    @Value("${sendCloud.api.key}")
    private String apiKey;

    @Value("${sendCloud.from}")
    private String from;

    @Value("${sendCloud.url}")
    private String url;

    @Value("${sendCloud.activation.invoke.name}")
    private String registerActivationInvokeName;

    @Value("${sendCloud.activation.click.url}")
    private String activationClickUrl;

    @Value("${sendCloud.resetPwd.invoke.name}")
    private String resetPwdInvokeName;
    @Value("${sendCloud.resetPwd.click.url}")
    private String resetPwdClickUrl;

    @Value("${sendCloud.changeEmail.invoke.name}")
    private String changeEmailInvokeName;
    @Value("${sendCloud.changeEmail.click.url}")
    private String changeEmailClickUrl;

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }
}
