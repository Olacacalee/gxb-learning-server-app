package com.gxb.modules.utils;



import com.github.cage.Cage;
import com.github.cage.GCage;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * 验证码生成器
 */
public class ValidateCode {
    // 图片的宽度。
    private int width = 80;
    // 图片的高度。
    private int height = 38;
    // 验证码字符个数
    private int codeCount = 4;
    // 验证码干扰线数
    private int lineCount = 100;
    // 验证码
    private String code = null;
    // 验证码图片Buffer
    private BufferedImage buffImg = null;

    private char[] codeSequence = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'J', 'K', 'L', 'M', 'N', 'P', 'Q', 'R', 'S', 'T', 'U', 'V',
            'W', 'X', 'Y', 'Z', '2', '3', '4', '5', '6', '7', '8', '9' };

    public ValidateCode(String code) {
        this.createCode(code);
    }

    /**
     *
     * @param width
     *            图片宽
     * @param height
     *            图片高
     */
    public ValidateCode(int width, int height,String code) {
        this.width = width;
        this.height = height;
        this.createCode(code);
    }

    /**
     *
     * @param width
     *            图片宽
     * @param height
     *            图片高
     * @param codeCount
     *            字符个数
     * @param lineCount
     *            干扰线条数
     */
    public ValidateCode(int width, int height, int codeCount, int lineCount,String code) {
        this.width = width;
        this.height = height;
        this.codeCount = codeCount;
        this.lineCount = lineCount;
        this.createCode(code);
    }

    public void createCode(String code) {
        Cage cage = new GCage();
        BufferedImage bi = cage.drawImage(code);
        this.buffImg = bi;
    }

    public void write(String path) throws IOException {
        OutputStream sos = new FileOutputStream(path);
        this.write(sos);
    }

    public void write(OutputStream sos) throws IOException {
        ImageIO.write(buffImg, "png", sos);
        sos.close();
    }

    public BufferedImage getBuffImg() {
        return buffImg;
    }

    public String getCode() {
        return code;
    }

}