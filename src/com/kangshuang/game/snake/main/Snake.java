package com.kangshuang.game.snake.main;

import com.kangshuang.game.snake.constant.Constant;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.LinkedList;
import java.util.List;

public class Snake extends SnakeObject{
    private BufferedImage IMG_SNAKE_HEAD=(BufferedImage) Constant.image.get("snake_head");
    private int speed;
    private int length;
    private int num;
    public  List<Point> bodyPoints=new LinkedList<>();
    public int score=0;
    public BufferedImage newImgSnakeHead;
    private boolean up,down,left,right=true,click=false;//click用于记录当前帧是否有按键按下，如果按下设为true，在蛇move后设为flase
    /**
     * 举个栗子：当蛇向上移动，如果快速按左然后按下（速度太快蛇没有真正向左移动），
     * 而原本的算法会按照修改后的方向判定蛇能否移动的方向，这将会导致蛇向下移动从而吃到身体
     * 这里设置四个boolean，在按键第一次点击（click判断）时记录蛇的原始移动方向，之后的判定方向以这次记录为准
     */
    private boolean upBuffer,downBuffer,leftBuffer,rightBuffer;

    public Snake(int x, int y) {
        this.live=true;
        this.x=x;
        this.y=y;
        this.img=Constant.image.get("snake_body");
        this.width=img.getWidth(null);
        this.height=img.getHeight(null);
        if(Constant.FLAG2){
            speed = 48;
        }else {
            this.speed=1;
        }
        num=width/speed;
        this.length=1;
        newImgSnakeHead=IMG_SNAKE_HEAD;
    }
    public Snake(int x, int y,String s) {//为双人模式的蛇加的构造方法，改变初始移动方向和图片
        this.live=true;
        this.x=x;
        this.y=y;
        this.img=Constant.image.get(s);
        this.width=img.getWidth(null);
        this.height=img.getHeight(null);
        if(Constant.FLAG2){
            speed = 48;
        }else {
            this.speed=1;
        }
        num=width/speed;
        this.length=1;
        this.left=true;
        this.right=false;
        IMG_SNAKE_HEAD=(BufferedImage) Constant.image.get("snake_head2");
        newImgSnakeHead=IMG_SNAKE_HEAD;
    }

    public boolean isUp() {
        return up;
    }

    public void setUp(boolean up) {
        this.up = up;
    }

    public boolean isDown() {
        return down;
    }

    public void setDown(boolean down) {
        this.down = down;
    }

    public boolean isLeft() {
        return left;
    }

    public void setLeft(boolean left) {
        this.left = left;
    }

    public boolean isRight() {
        return right;
    }

    public void setRight(boolean right) {
        this.right = right;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }
    /**
     * 接收按键按下事件（设置click和Buffer原因见上方变量声明处）
     */
    public void keyPressed(KeyEvent e){
        if(!click){
            upBuffer=up;
            downBuffer=down;
            leftBuffer=left;
            rightBuffer=right;
        }
        click=true;
        switch (e.getKeyCode()){
            case KeyEvent.VK_UP:
                if(!downBuffer){
                    up=true;
                    down=false;
                    left=false;
                    right=false;
                    click=true;
                    newImgSnakeHead=IMG_SNAKE_HEAD;
                }
                break;
            case KeyEvent.VK_DOWN:
                if(!upBuffer){
                    down=true;
                    up=false;
                    left=false;
                    right=false;
                    newImgSnakeHead=IMG_SNAKE_HEAD;
                }
                break;
            case KeyEvent.VK_LEFT:
                if(!rightBuffer){
                    left=true;
                    down=false;
                    up=false;
                    right=false;
                    newImgSnakeHead=IMG_SNAKE_HEAD;
                }
                break;
            case KeyEvent.VK_RIGHT:
                if(!leftBuffer){
                    right=true;
                    down=false;
                    left=false;
                    up=false;
                    newImgSnakeHead=IMG_SNAKE_HEAD;
                }
                break;


        }
    }
    //双人模式，原始蛇的方向键改为WASD
    public void keyPressed2(KeyEvent e){
        if(!click){
            upBuffer=up;
            downBuffer=down;
            leftBuffer=left;
            rightBuffer=right;
        }
        click=true;
        switch (e.getKeyCode()){
            case KeyEvent.VK_W:
                if(!downBuffer){
                    up=true;
                    down=false;
                    left=false;
                    right=false;
                    newImgSnakeHead=IMG_SNAKE_HEAD;//此处可按情况旋转蛇头
                }
                break;
            case KeyEvent.VK_S:
                if(!upBuffer){
                    down=true;
                    up=false;
                    left=false;
                    right=false;
                    newImgSnakeHead=IMG_SNAKE_HEAD;
                }
                break;
            case KeyEvent.VK_A:
                if(!rightBuffer){
                    left=true;
                    down=false;
                    up=false;
                    right=false;
                    newImgSnakeHead=IMG_SNAKE_HEAD;
                }
                break;
            case KeyEvent.VK_D:
                if(!leftBuffer){
                    right=true;
                    down=false;
                    left=false;
                    up=false;
                    newImgSnakeHead=IMG_SNAKE_HEAD;
                }
                break;


        }
    }

    /**
     * 移动
     */
    public void move(){
        if(!Constant.FLAG2){
            if(up)y-=speed;
            else if(down)y+=speed;
            else if(left)x-=speed;
            else if(right)x+=speed;
        }else {
            //开启自动移动后将按以下轨迹移动（此算法对蛇的初始位置和速度有一定要求）
            if(right&&x==968){
                right=false;
                down=true;
            }else if(down&&((y-30)%48)==0&&x==968){
                down=false;
                left=true;
            }else if(left&&x==56&&y!=558){
                left=false;
                down=true;
            }else if(down&&x==56&&((y-30)%48)==0){
                down=false;
                right=true;
            }else if(left&&x==8){
                left=false;
                up=true;
            }else if(up&&y==30){
                up=false;
                right=true;
            }
            if(up)y-=speed;
            else if(down)y+=speed;
            else if(left)x-=speed;
            else if(right)x+=speed;
        }

    }

    /**
     * 绘制
     */
    public void draw(Graphics g){
        if (!Constant.FLAG1) {
            outOfBounds();
            if(length!=1){
                eatBody();
            }
        }
        if(x<-1000||x>2000||y>1600||y<-1000){
            live=false;
        }
        bodyPoints.add(new Point(x,y));
        if(bodyPoints.size()>(this.length)*num){
            bodyPoints.remove(0);
        }
        g.drawImage(newImgSnakeHead,x,y,null);
        drawBody(g);
        move();
        click=false;
    }

    /**
     * 绘制身体
     */
    private void drawBody(Graphics g) {
        int length=bodyPoints.size()-1;
        for(int i=length;i>=0;i-=num){
            Point p=bodyPoints.get(i);
            g.drawImage(img,p.x,p.y,null);
        }
    }

    /**
     * 判断是否吃到蛇身
     */
    private void eatBody() {
        for(Point point1:bodyPoints){
            for(Point point2:bodyPoints){
                if (point1.equals(point2) && point1 != point2) {
                    this.live = false;
                    break;
                }
            }
        }
    }

    /**
     * 判断是否超出边界
     */
    private void outOfBounds(){
        boolean xOut=(x<8||x>968);
        boolean yOut=(y<30||y>566);//视情况而定
        if(xOut||yOut){
            live=false;
        }
    }

    public void speedUp(){
        speed *= 2;//速度增加两倍，蛇的轨迹点要均匀的减去一半
        num=width/speed;
        for(int i=bodyPoints.size()-2;i>=0;i-=2){
            bodyPoints.remove(i);
        }
    }



















}
