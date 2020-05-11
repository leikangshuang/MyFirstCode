package com.kangshuang.game.snake.frame;

import com.kangshuang.game.snake.constant.Constant;
import com.kangshuang.game.snake.main.Ranking;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MainFrame extends BackFrame{

    private Buttle buttle=new Buttle();
    private RedrawThread redrawThread=new RedrawThread();
    private RankFrame rankFrame=null;
    private HelpFrame helpFrame=null;

    @Override
    public void loadFrame() {
        super.loadFrame();
        redrawThread.start();



        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {

               keyPressAction(e);
            }


        });
        addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                buttle.mainMouseAction(e);
            }
        });
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {

                mouseAction(e);

            }

        });

    }

    @Override
    public void paint(Graphics g) {
        g.drawImage(Constant.image.get("main_view"),0,0,null);
        buttle.mainDraw(g);
        drawOpen(g);
    }

    /**
     * 显示作弊码是否生效
     */

    public void drawOpen(Graphics g){
        if(Constant.FLAG1) {
            g.setFont(new Font("幼圆", Font.PLAIN, 24));
            g.setColor(Color.red);
            g.drawString("无敌    on", 20,60 );
        }
        if(Constant.FLAG3) {
            g.setFont(new Font("幼圆", Font.PLAIN, 24));
            g.setColor(Color.red);
            g.drawString("锁速    on", 20,90 );
        }
        if(Constant.FLAG2) {
            g.setFont(new Font("幼圆", Font.PLAIN, 24));
            g.setColor(Color.red);
            g.drawString("自动移动on", 20,120 );
        }

    }

    /**
     * 键盘点击事件
     */
    private void keyPressAction(KeyEvent evt) {
        if (evt.getKeyCode() == KeyEvent.VK_F4) {//点击f4则打开输入操作码的界面
            String s = JOptionPane.showInputDialog("");
            if (s != null) {
                if (s.contains("-god")) Constant.FLAG1 = !Constant.FLAG1;
                if (s.contains("-move")) Constant.FLAG2 = !Constant.FLAG2;
                if (s.contains("-slow")) Constant.FLAG3 = !Constant.FLAG3;
                if ("-reset1".equals(s)) {
                    int result = JOptionPane.showConfirmDialog(null, "重置后不能恢复，是否确定重置！");
                    if (result == 0) Ranking.reset(Ranking.filename);
                    Constant.player = Ranking.inital(Ranking.filename);
                }
                if ("-reset2".equals(s)) {
                    int result = JOptionPane.showConfirmDialog(null, "重置后不能恢复，是否确定重置！");
                    Ranking.reset(Ranking.filename2);
                    Constant.player2 = Ranking.inital(Ranking.filename2);
                }
                if ("-reset-all".equals(s)) {
                    Ranking.reset(Ranking.filename);
                    Ranking.reset(Ranking.filename2);
                    Constant.player = Ranking.inital(Ranking.filename);
                    Constant.player2 = Ranking.inital(Ranking.filename2);
                }
            }
        }

    }

    /**
     * 鼠标点击动作
     */

    private void mouseAction(MouseEvent evt) {
        int x = evt.getX();
        int y = evt.getY();
        if (x > 448 && x < 575 && y > 470 && y < 510) {
            this.dispose();
            Constant.PAUSE = false;
            new RunFrame().loadFrame();
        } else if (x > 448 && x < 575 && y > 510 && y < 550) {
            this.dispose();
            Constant.PAUSE = false;
            new DoubleRunFrame().loadFrame();

        } else if (x > 448 && x < 575 && y > 550 && y < 605) {
            if (helpFrame != null) {//如果已经打开过帮助界面，再次点击帮助则关闭已经打开的帮助界面，而是重新打开一个
                helpFrame.dispose();
            }
            helpFrame = new HelpFrame();
            helpFrame.loadFrame();
        } else if (x > 448 && x < 575 && y > 605 && y < 640) {
            if (rankFrame != null) {
                rankFrame.dispose();
            }
            rankFrame = new RankFrame();
            rankFrame.loadFrame();
        }
    }
    public static void main(String[] args) {
        new MainFrame().loadFrame();
        Constant.player=Ranking.inital(Ranking.filename);
        Constant.player2=Ranking.inital(Ranking.filename2);
    }
}
