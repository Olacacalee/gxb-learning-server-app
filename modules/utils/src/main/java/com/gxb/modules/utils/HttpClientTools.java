package com.gxb.modules.utils;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * Created by zhangjinxia on 16/3/1.
 */
public class HttpClientTools {

    public static String get(String url){
        CloseableHttpClient client = HttpClients.createDefault();
        HttpGet get = new HttpGet(url);
//        JSONObj resultJsonObject = new JSONObj();
        StringBuilder entityStringBuilder = new StringBuilder();
        try {
            HttpResponse res = client.execute(get);
            if (res.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                HttpEntity httpEntity=res.getEntity();
                if (httpEntity!=null) {
                    BufferedReader bufferedReader = new BufferedReader
                            (new InputStreamReader(httpEntity.getContent(), "UTF-8"), 1024);
                    String line;
                    while ((line = bufferedReader.readLine()) != null) {
                        entityStringBuilder.append(line);
                    }
//                    Object obj = resultJsonObject.fromObject(entityStringBuilder.toString());
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally{
            if (client != null) {
                try {
                    client.close();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }
        return entityStringBuilder.toString();
    }
}
