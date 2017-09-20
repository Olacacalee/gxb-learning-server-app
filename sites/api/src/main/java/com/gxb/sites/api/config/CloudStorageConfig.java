/**
 *  Copyright (c)  2014-2020 Kaikeba, Inc.
 *  All rights reserved.
 *
 *  This software is the confidential and proprietary information of Kaikeba, 
 *  Inc. ("Confidential Information"). You shall not
 *  disclose such Confidential Information and shall use it only in
 *  accordance with the terms of the license agreement you entered into with Kaikeba.
 */
package com.gxb.sites.api.config;

import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.stereotype.Component;

/**
 * 
 * @author sunninghai
 * @date 2015年5月25日
 */
@Data
@Component
public class CloudStorageConfig {

	@Value("${cloudStorage.access_key}")
	private String accessKey;

	@Value("${cloudStorage.secret_key}")
	private String secretKey;

	@Value("${cloudStorage.end_point}")
	private String endPoint;

	@Value("${cloudStorage.default_bucket}")
	private String defaultBucket;

	@NotEmpty
	@Value("${cloudStorage.default_bucket_domain}")
	private String defaultBucketDomain;

	@Value("${cloudStorage.image_bucket}")
	private String imageBucket;

	@Value("${cloudStorage.image_bucket_domain}")
	private String imageBucketDomain;

	@Value("${cloudStorage.video_bucket}")
	private String videoBucket;

	@Value("${cloudStorage.video_bucket_domain}")
	private String videoBucketDomain;

	@Value("${cloudStorage.video_promotional}")
	private String videoPromotional;

	@Value("${cloudStorage.video_promotional_domain}")
	private String videoPromotionalDomain;

	@Bean
	public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
		return new PropertySourcesPlaceholderConfigurer();
	}
}
