package com.kangshuang.game.snake.frame;

import com.kangshuang.game.snake.constant.Constant;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class BackFrame extends Frame {
    public void loadFrame(){
        this.setTitle("贪吃蛇");
        this.setSize(Constant.GAME_WIDTH,Constant.GAME_HIGH);
        this.setBackground(Color.BLACK);
        this.setLocationRelativeTo(null);
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        this.setVisible(true);



        //new RedrawThread().start();


    }

    /**
     * 不断重绘的线程
     */
    class RedrawThread extends Thread{
        @Override
        public void run() {
            while (true){
                if (Constant.PAUSE){
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }else{
                    repaint();
                    try {
                        Thread.sleep(17);//减小可提升帧数，相应的蛇速度会变快
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();//处于阻塞状态时中断会引起该异常，会将中断标志置为false,此处将标志再次置为true确认中断
                    }
                    if(Thread.currentThread().isInterrupted()){
                        System.out.println("interrupt success");
                        break;
                    }
                }

            }
            Thread.yield();
        }
    }

    /**
     * 防止图片闪烁，双重缓存
     */
    Image backImg=null;

    @Override
    public void update(Graphics g) {
        if(backImg==null){
            backImg=createImage(Constant.GAME_WIDTH,Constant.GAME_HIGH);
        }
        Graphics backg=backImg.getGraphics();
        Color c=backg.getColor();
        backg.setColor(Color.BLACK);
        backg.fillRect(0,0,Constant.GAME_WIDTH,Constant.GAME_HIGH);
        backg.setColor(c);
        paint(backg);
        g.drawImage(backImg,0,0,null);
    }
}