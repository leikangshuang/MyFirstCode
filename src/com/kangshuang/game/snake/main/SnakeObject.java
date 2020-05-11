package com.kangshuang.game.snake.main;

import java.awt.*;

public abstract class SnakeObject {
    int x;
    int y;
    Image img;
    int width;
    int height;
    public boolean live;

    public abstract  void draw(Graphics g);

    public Rectangle getRectangle(){
        return new Rectangle(x,y,width,height);
    }
    public Rectangle getRectangle(Point point){
        return new Rectangle(point.x,point.y,48,48);
    }
}
