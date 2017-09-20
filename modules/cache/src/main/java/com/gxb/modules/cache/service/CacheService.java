package com.gxb.modules.cache.service;

import org.hibernate.validator.constraints.NotBlank;

import java.util.Set;

/**
 * Created by jqwang on 15/10/21.
 */
public interface CacheService {

    public   void remove(@NotBlank final String key);

    public  void putObject(@NotBlank final String key, @NotBlank Object fieldVal, Long expireTime);
    public  void putObject(@NotBlank final String key, @NotBlank Object fieldVal);

    public  void putObjectIfAbsent(@NotBlank final String key, @NotBlank Object fieldVal, Long expireTime);
    public  void putObjectIfAbsent(@NotBlank final String key, @NotBlank Object fieldVal);

    public  Object getObject(@NotBlank final String key);

    public  Boolean existsKey(@NotBlank String key);

    public  Long getExpire(@NotBlank String key);

    public  void remove(@NotBlank String[] keys);

    public Integer removePattern(@NotBlank String pattern);

    public Set<String> getKeys(@NotBlank String pattern);

    public Long leftPush(@NotBlank final String key, @NotBlank final Object value, Long expireTime);
    public Long leftPush(@NotBlank final String key, @NotBlank final Object value);

    public Long rightPush(@NotBlank final String key, @NotBlank final Object value, Long expireTime);
    public Long rightPush(@NotBlank final String key, @NotBlank final Object value);

    public Object leftPop(@NotBlank final String key);

    public Object rightPop(@NotBlank final String key);

    public Object indexPop(@NotBlank final String key, @NotBlank final Long index);

    public Long listSize(@NotBlank final String key);

    public void putZSetObject(String qq, Object obj, long score,Long expireTime);
}
