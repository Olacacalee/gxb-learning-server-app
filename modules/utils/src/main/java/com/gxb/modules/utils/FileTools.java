package com.gxb.modules.utils;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

/**
 * 
 * 
 * @author vitem
 * @date 2015年10月21日
 */
public class FileTools {
	
    public static void copyInputStreamToFile(InputStream inputStream, File file) throws IOException {
        FileUtils.copyInputStreamToFile(inputStream, file);
    }

    public static void moveFile(File filePath, File dirPath) throws IOException {
        FileUtils.moveFile(filePath, dirPath);
    }

    public static String getTempDirectoryPath(){
        return FileUtils.getTempDirectoryPath();
    }

}
