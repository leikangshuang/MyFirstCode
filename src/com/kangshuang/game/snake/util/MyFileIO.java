package com.kangshuang.game.snake.util;

import java.io.*;

/**
 * 不知道如何将两个文件隐藏所以对内容进行了简单的加密，感觉效果还行
 */
public class MyFileIO {

        public void set(String filename,String str) throws IOException {
            byte[] b=str.getBytes();
            for(int i=0;i<b.length;i++){//简单的加密
                b[i]+=(byte) i;
            }
            FileOutputStream outputStream=new FileOutputStream(filename);
            outputStream.write(b);
            outputStream.close();
        }

        public String load(String filename) throws Exception{

            FileInputStream fileInputStream=new FileInputStream(filename);
            int b;
            int i=0;
            byte[] bytes=new byte[1024];
            while ((b=fileInputStream.read())!=-1){
                b=(byte)b-(byte)i;//解密
                bytes[i]=(byte) b;
                i++;
            }
            String str=new String(bytes,0,i);
            fileInputStream.close();

            return str;
        }



}
