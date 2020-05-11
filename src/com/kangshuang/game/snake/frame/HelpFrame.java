package com.kangshuang.game.snake.frame;

import com.kangshuang.game.snake.constant.Constant;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class HelpFrame extends Frame{
    public void loadFrame(){
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                dispose();
            }
        });
        this.setTitle("帮助");
        this.setSize(1020,662);
        this.setBackground(Color.black);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
    @Override
    public void paint(Graphics g) {
        g.drawImage(Constant.image.get("help"),0,0,null);
    }

}
