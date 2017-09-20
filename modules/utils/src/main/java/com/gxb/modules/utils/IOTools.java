package com.gxb.modules.utils;

import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * time : 15/11/9.
 * auth :
 * desc :
 * tips :
 * 1.
 */
public class IOTools {

    public static void closeStream(InputStream inputStream) {
        if (inputStream == null) return;
        try {
            inputStream.close();
        } catch (IOException e) {
            ExceptionTools.unchecked(e);
        }
    }

    /**
     * 安全关闭输出流
     */
    public static void closeStream(OutputStream outputStream) {
        if (outputStream == null) return;
        try {
            outputStream.close();
        } catch (IOException e) {
            ExceptionTools.unchecked(e);
        }
    }
    
    public static String read(InputStream inputStream, Integer length) {
        byte[] bytes = new byte[length];
        try {
            IOUtils.readFully(inputStream, bytes);
            return new String(bytes, "utf-8");
        } catch (IOException e) {
           ExceptionTools.unchecked(e);
        }
        return null;
    }
}
