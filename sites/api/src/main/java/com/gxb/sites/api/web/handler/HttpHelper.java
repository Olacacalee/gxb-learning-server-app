package com.gxb.sites.api.web.handler;

import javax.servlet.ServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;

/**
 * Created with antnest-platform
 * User: chenyuan
 * Date: 12/24/14
 * Time: 10:39 AM
 */
public class HttpHelper {

    /**
     * 获取请求Body
     *
     * @param request
     * @return
     */
    public static String getBodyString(ServletRequest request) {
        StringBuilder sb = new StringBuilder();
        InputStream inputStream = null;
        BufferedReader reader = null;


        try {
            ByteBuffer buffer = ByteBuffer.allocate(request.getContentLength());
            ReadableByteChannel channels = Channels.newChannel(request.getInputStream());
            channels.read(buffer);
            return new String(buffer.array());
//            inputStream = request.getInputStream();
//            reader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
//            String line = "";
//            while ((line = reader.readLine()) != null) {
//                sb.append(line);
//            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return null;
    }

}