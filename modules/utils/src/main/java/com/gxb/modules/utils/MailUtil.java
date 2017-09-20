package com.gxb.modules.utils;

import com.gxb.modules.json.JSONObj;
import org.apache.commons.lang3.text.StrBuilder;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhaobin
 * @date 2015年12月31日
 */


public class MailUtil {

    private static final Logger LOG = LoggerFactory.getLogger(MailUtil.class);

    /**
     * 根据模版发送邮件
     */
    public static boolean snedTemplateMail(
            String template_invoke_name, String api_user, String api_key, String url,
            String emailTo, String subject, String fromName,
            String emailFrom, String paramJson)
            throws Exception {

        HttpClient httpclient = new DefaultHttpClient();
        StrBuilder strBuilder = new StrBuilder();
        strBuilder.append("{\"to\": [\"").append(emailTo).append("\"]");
        if (!StringTools.isBlank(paramJson)) {
            strBuilder.append(",\"sub\":").append(paramJson);
        }
        strBuilder.append("}");
        // {"to": ["to1@sendcloud.org", "to2@sendcloud.org"], "sub" : { "%name%"
        // : ["约翰", "林肯"]} }
        HttpPost httpost = new HttpPost(url);
        List<BasicNameValuePair> nvps = new ArrayList<>();
        nvps.add(new BasicNameValuePair("template_invoke_name",
                template_invoke_name)); // 邮件模板名称//KaikebaActivity//KaikebaNewsletter//agZftD8KBjOjlyrZ
        nvps.add(new BasicNameValuePair("api_user", api_user));// congchen_test_j1UumR
        nvps.add(new BasicNameValuePair("from", emailFrom));
        nvps.add(new BasicNameValuePair("mail_from", emailFrom));
        nvps.add(new BasicNameValuePair("api_key", api_key));// oF519MyI5CxvQTGB
        nvps.add(new BasicNameValuePair("fromname", fromName));
        nvps.add(new BasicNameValuePair("subject", subject));
        nvps.add(new BasicNameValuePair("substitution_vars", strBuilder
                .toString()));
        httpost.setEntity(new UrlEncodedFormEntity(nvps, "UTF-8"));
        HttpResponse response = httpclient.execute(httpost);

        int statusCode = response.getStatusLine().getStatusCode();
        if (statusCode == HttpStatus.SC_OK) {
            String returnMessage = EntityUtils.toString(response.getEntity());
            JSONObj object = JSONObj.fromObject(returnMessage);
            if ("success".equals(object.getString("message"))) {
                return true;
            } else {
                LOG.error("send mail error [ email : {}, returnMessage : {}]",
                        new String[]{emailTo, returnMessage});
                return false;
            }
        } else {
            LOG.error("send mail error [ email : {}, return code : {}",
                    new String[]{emailTo, String.valueOf(statusCode)});
            return false;
        }
    }
}

