package com.kangshuang.game.snake.util;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

public class ImageUtil {
    /**
     * 输入相对路径获取图片
     */
    public static Image getImage(String imagePath){
        URL url=ImageUtil.class.getClassLoader().getResource(imagePath);
        BufferedImage img=null;
        try {
            assert url != null;
            img= ImageIO.read(url);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return img;
    }

    /**
     * 输入图片及旋转角度，返回旋转后的图片
     */
    public static Image rotateImage(final BufferedImage bufferedImage,final int degree){
        int w=bufferedImage.getWidth();
        int h=bufferedImage.getHeight();
        int type=bufferedImage.getColorModel().getTransparency();//获取图片透明度
        BufferedImage img;
        Graphics2D graphics2d;//获得画笔
        (graphics2d=(img=new BufferedImage(w,h,type)).createGraphics()).setRenderingHint(RenderingHints.KEY_INTERPOLATION,RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        graphics2d.rotate(Math.toRadians(degree),w/2,h/2);
        graphics2d.drawImage(bufferedImage,0,0,null);//从bufferedImage拷贝图片到img，0，0是img坐标
        graphics2d.dispose();
        return img;
    }
}
