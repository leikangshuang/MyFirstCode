package com.kangshuang.game.snake.main;

import com.kangshuang.game.snake.constant.Constant;

import java.awt.*;
import java.util.Random;


public class Food extends SnakeObject {
    private int SCORE=Constant.FOOD_PRIZE1;
    private boolean coffin =false;//判定当前食物是否为棺材
    public Food(){
        this.live=true;
        this.img= Constant.image.get("food");
        this.width=img.getWidth(null);
        this.height=img.getHeight(null);
        Random random=new Random();
        int x=random.nextInt(21);
        int y=random.nextInt(12);
        this.x=x*48+8;
        this.y=y*48+30;
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(img,x,y,null);
    }
    public void eaten(Snake snake){
        if(snake.getRectangle().intersects(this.getRectangle())&&live&&snake.live){
            this.live=false;
            if(!coffin){
                snake.setLength(snake.getLength()+1);
                if(!Constant.FLAG2){
                    if(snake.getLength()==2){
                        snake.speedUp();
                    }
                    if(snake.getLength()==10){
                        snake.speedUp();
                    }
                    if(!Constant.FLAG3){
                        if(snake.getLength()==30) {
                            snake.speedUp();
                        }
                        if(snake.getLength()==90) {
                            snake.speedUp();
                        }
                    }

                }
                snake.score+=SCORE*snake.getLength();
            }else {
                if(!Constant.FLAG1){
                    snake.live=false;
                    snake.score+=SCORE*snake.getLength();
                }else {
                    snake.score+=SCORE*snake.getLength();
                }

            }

        }
    }

    /**
     * 对生成的食物进行优化,无法优化说明游戏已通关
     */
    public boolean optimization(Snake snake){
        if(snake.getLength()>240){
            return false;//蛇长度达到240则不再生成食物,游戏通关
        }
        //保证食物不会生成在蛇身上
        int length=snake.bodyPoints.size()-1;
        int count=0;
        Random random=new Random();

        for(int i=length;i>=0;i-=snake.getNum()){
            Point p=snake.bodyPoints.get(i);

            if(getRectangle(p).intersects(this.getRectangle())&&snake.live){
                int x=random.nextInt(21);
                int y=random.nextInt(12);
                this.x=x*48+8;
                this.y=y*48+30;
                i=length+snake.getNum();
                count++;
                if (count>50){
                    return false;//50次没能找到生成食物的合适位置则不生成食物，游戏通关
                }
            }
        }
        //随机生成食物种类
        int l=snake.getLength();
        int r=random.nextInt(200);
        if(l>11&&l<28&&r<2){
            this.coffin=true;
            this.img= Constant.image.get("coffin");
            SCORE=-9999;
        }else if(l==r&&l>10){
            this.img= Constant.image.get("food0");
            SCORE=Constant.FOOD_PRIZE4;
        }else if(l>15&&r>170){
            this.img= Constant.image.get("food2");
            SCORE=Constant.FOOD_PRIZE3;
        }else if(l>6&&r>125){
            this.img= Constant.image.get("food1");
            SCORE=Constant.FOOD_PRIZE2;
        }

        return true;
    }
    public boolean optimization(Snake snake,Snake snake2){
        int l=snake.getLength();
        int l2=snake2.getLength();
        if((l>50&&l2>50)||l>91||l2>91){
            return false;
        }
        Random random=new Random();
        //随机生成食物种类

        int r=random.nextInt(100);
        if((l>10||l2>10)&&(l<28&&l2<28)&&r<2){
            this.coffin=true;
            this.img= Constant.image.get("coffin");
            SCORE=-9999;
        }else if((l==r||l2==r)&&l>20&&l2>20){
            this.img= Constant.image.get("food0");
            SCORE=Constant.FOOD_PRIZE4;
        }else if((l>15||l2>15)&&r>90){
            this.img= Constant.image.get("food2");
            SCORE=Constant.FOOD_PRIZE3;
        }else if((l>6||l2>6)&&r>70){
            this.img= Constant.image.get("food1");
            SCORE=Constant.FOOD_PRIZE2;
        }

        return true;
    }

}
