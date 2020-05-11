package com.kangshuang.game.snake.frame;

import com.kangshuang.game.snake.constant.Constant;
import com.kangshuang.game.snake.main.Food;
import com.kangshuang.game.snake.main.Ranking;
import com.kangshuang.game.snake.main.Snake;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

public class RunFrame extends BackFrame{
    public Snake snake=new Snake(56,126);
    public Food food=new Food();
    private Buttle buttle=new Buttle();
    Image background = Constant.image.get("background");
    Image gameover=Constant.image.get("fail");
    RedrawThread redrawThread=new RedrawThread();
    private boolean end=false,pass=false;
    private int count=0, r=0;//这两个参数用于生成随机消息，count表示repaint次数，r记录第一次生成的随机数


    @Override
    public void loadFrame() {
        super.loadFrame();

        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                snake.keyPressed(e);
                if(e.getKeyCode()==KeyEvent.VK_SPACE){
                    Constant.PAUSE=!Constant.PAUSE;
                }
            }
        });
        addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                buttle.mouseAction(e);
            }
        });
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                mouseAction(e);
            }
        });
        redrawThread.start();
    }

    @Override
    public void paint(Graphics g) {
        g.drawImage(background,0,0,null);
        if(!end){
            if(snake.live){
                snake.draw(g);
                if(food.live){
                    food.draw(g);
                    food.eaten(snake);
                }else {
                    food=new Food();
                    if(!food.optimization(snake)){
                        //redrawThread.interrupt();
                        finalScore(g);
                        end();
                        end=true;
                        pass=true;
                    }
                }
            } else {
                //redrawThread.interrupt();
                end();
                end=true;
            }

            if(snake.getLength()==9||snake.getLength()==29||snake.getLength()==89){
                notice(g);
            }
        }

        if(pass){
            finalScore(g);
        }else if(end){
            g.drawImage(gameover,(background.getWidth(null)-gameover.getWidth(null))/2,(background.getHeight(null)-gameover.getHeight(null))/2,null);
        }
        drawLength(g);
        drawScore(g);
        buttle.draw(g);
        if(count>0){
            drawMessage(g);
            count--;
        }
    }

    /**
     * 点击“...”按钮显示消息
     */
    private void drawMessage(Graphics g) {
        if(count==60){//count==60表示此时点击了按钮
            Random random=new Random();
            r=random.nextInt(100);
        }
        g.setFont(new Font("幼圆",Font.PLAIN,24));
        g.setColor(Color.RED);
        if(r<30){
            g.drawString(Constant.MESSAGE1,30,500);
        }else if(r < 40){
            g.drawString(Constant.MESSAGE2,300,400);
        }else if(r < 50){
            g.drawString(Constant.MESSAGE3,100,500);
        }else if(r < 60){
            g.drawString(Constant.MESSAGE4,840,500);
        }else if(r < 65){
            g.drawString(Constant.MESSAGE5,640,440);
        }else if(r < 86){
            g.drawString(Constant.MESSAGE6,740,350);
        }else if(r>=90&&r<96){
            g.drawString(Constant.MESSAGE7,340,550);
        }else if(r == 96){
            g.drawString(Constant.MESSAGE8,80,300);
        }else if(r == 97){
            g.drawString(Constant.MESSAGE9,300,100);
        }else if(r == 98){
            g.drawString(Constant.MESSAGE10,200,200);
        }else if(r == 99){
            g.drawString(Constant.MESSAGE11,30,350);
        }

    }

    /**
     * 绘制分数和加速提示等
     */
    private void drawScore(Graphics g) {
        g.setFont(new Font("Courier New",Font.BOLD,40));
        g.setColor(Color.blue);
        g.drawString("SCORE:"+snake.score,30,650);
    }
    private void drawLength(Graphics g) {
        g.setFont(new Font("Courier New",Font.PLAIN,30));
        g.setColor(Color.RED);
        g.drawString(String.valueOf(snake.getLength()),490,645);
    }
    private void notice(Graphics g) {
        g.setFont(new Font("Courier New",Font.BOLD,30));
        g.setColor(Color.RED);
        g.drawString("speed will up!!!",380,350);
    }
    private void finalScore(Graphics g) {
        g.setFont(new Font("Courier New",Font.PLAIN,80));
        g.setColor(Color.RED);
        g.drawString("VICTORY!",340,350);
    }

    /**
     * 游戏结算
     */
    private void end(){
        Constant.PAUSE=true;
        if(Ranking.analyzeScore(snake.getScore(),true)){
            String s=JOptionPane.showInputDialog("恭喜你打破了记录,请输入你的ID");
            while (s!=null&&(s.equals("")||s.contains("#")||s.contains("$")||s.contains(" ")||s.length()>8)){
                if(s.equals("")||s.contains("#")||s.contains("$")||s.contains(" ")){
                    s=JOptionPane.showInputDialog("请不要输入空格或'#'和'$'，否则将保存失败");
                }else {
                    s=JOptionPane.showInputDialog("输入ID过长，请输入长度小于9的字符串！");
                }
            }

            if(s!=null){
                s=s.trim();
                if(Constant.FLAG1||Constant.FLAG2||Constant.FLAG3){
                    s=s+" $挂B$";
                }
                Ranking.addPlayer(snake.getScore(),s);
            }

        }
        Constant.PAUSE=false;
    }
    /**
     * 鼠标点击动作
     */

    private void mouseAction(MouseEvent evt){
        int x=evt.getX();
        int y=evt.getY();
        if(x>550&&x<605&&y>600&&y<655){
            Constant.PAUSE=!Constant.PAUSE;
        }else if(x>630&&x<750&&y>600&&y<655){
            Constant.PAUSE=true;
            int result=JOptionPane.showConfirmDialog(null, "是否返回主菜单");
            if(result==0){
                this.dispose();
                new MainFrame().loadFrame();
            }
            Constant.PAUSE=!Constant.PAUSE;
        }else if(x>775&&x<900&&y>600&&y<655){
            this.dispose();
            Constant.PAUSE=false;
            new RunFrame().loadFrame();
        }else if(x>920&&x<980&&y>600&&y<655){
            Constant.PAUSE=true;
            int result=JOptionPane.showConfirmDialog(null, "是否退出游戏");
            if(result==0)System.exit(0);
            Constant.PAUSE=!Constant.PAUSE;
        }else if(x>980&&x<1010&&y>600&&y<655){
            if(count==0){
                count=60;//消息持续刷新60次
            }
        }
    }

}
