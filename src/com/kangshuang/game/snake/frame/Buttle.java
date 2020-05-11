package com.kangshuang.game.snake.frame;

import com.kangshuang.game.snake.constant.Constant;

import java.awt.*;
import java.awt.event.MouseEvent;

/**
 * 响应鼠标移动到按钮上
 */
public class Buttle  {
    private int x;
    private int y;
    private String s="233333333333";
    private boolean visible =false;//是否显示按钮框
    private boolean option=false;//游戏界面的按钮框选择

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public String getS() {
        return s;
    }

    public void setS(String s) {
        this.s = s;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public boolean isOption() {
        return option;
    }

    public void setOption(boolean option) {
        this.option = option;
    }

    public void mouseAction(MouseEvent evt){
        int x,y;

        x=evt.getX();
        y=evt.getY();

        if(x>550&&x<605&&y>600&&y<655){
            visible =true;
            option=false;
            this.x=538;
            this.y=610;
        }else if(x>630&&x<750&&y>600&&y<655){
            option=true;
            visible =true;
            this.x=623;
            this.y=610;

        }else if(x>775&&x<900&&y>600&&y<655){
            option=true;
            visible =true;
            this.x=770;
            this.y=610;


        }else if(x>920&&x<980&&y>600&&y<655){
            option=false;
            visible =true;
            this.x=905;
            this.y=610;


        }else {
            visible =false;
        }
    }
    //主界面按钮响应
    public void mainMouseAction(MouseEvent evt){
        int x=evt.getX();
        int y=evt.getY();
        if(x>448&&x<575&&y>470&&y<510){
            visible =true;
            this.x=402;
            this.y=450;

        }else if(x>448&&x<575&&y>510&&y<550){
            visible =true;
            this.x=402;
            this.y=495;


        }else if(x>448&&x<575&&y>550&&y<605){
            visible =true;
            this.x=402;
            this.y=545;

        }else if(x>448&&x<575&&y>605&&y<640){
            visible =true;
            this.x=402;
            this.y=595;

        }else {
            visible =false;
        }
    }
    public void draw(Graphics g){
        if(visible&&!option){
            g.drawImage(Constant.image.get("frame1"),x,y,null);
        }else if(visible){
            g.drawImage(Constant.image.get("frame2"),x,y,null);
        }
    }
    public void mainDraw(Graphics g){
        if(visible){
            g.drawImage(Constant.image.get("frame"),x,y,null);
        }
    }


}
