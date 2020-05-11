package com.kangshuang.game.snake.frame;

import com.kangshuang.game.snake.constant.Constant;

import java.awt.*;
import java.awt.event.*;

public class RankFrame extends Frame{
    public void loadFrame(){
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                dispose();
            }
        });
        this.setTitle("排行榜");
        this.setSize(700,900);
        this.setBackground(Color.black);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
    @Override
    public void paint(Graphics g) {
        g.drawImage(Constant.image.get("ranking"),0,0,null);
        draw(g);
    }

    /**
     * 绘制排行榜
     */
    private void draw(Graphics g){
        g.setFont(new Font("幼圆",Font.PLAIN,24));
        int x=300,y=145,l=Constant.player.length;
        for(int i=l-1;i>=0;i--){
            colorChange(g, l, i);
            g.drawString(Constant.player[i],x,y);
            y+=75;
        }

        y=548;
        for(int i=l-1;i>=0;i--){
            colorChange(g, l, i);
            g.drawString(Constant.player2[i],x,y);
            y+=75;
        }

    }

    private void colorChange(Graphics g, int l, int i) {//对前三名打印字符颜色设置
        if(i==l-1){
            g.setColor(Color.yellow);
        }else if(i==l-2){
            g.setColor(Color.blue);
        }else if(i==l-3){
            g.setColor((Color.RED));
        }else {
            g.setColor(Color.WHITE);
        }
    }


}
