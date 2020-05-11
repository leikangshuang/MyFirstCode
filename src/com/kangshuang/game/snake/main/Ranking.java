package com.kangshuang.game.snake.main;

import com.kangshuang.game.snake.constant.Constant;
import com.kangshuang.game.snake.util.MyFileIO;

import javax.swing.*;
import java.io.IOException;

public class Ranking {
    public static final String filename="configuration";
    public static final String filename2="configuration2";
    private MyFileIO myFileIO=new MyFileIO();


    /**
     * 读取文件中的排行榜数据并转化为字符串数组返回，如果不存在文件则新建一个并且初始化数据
     */
    public static String[] inital(String filename) {
        MyFileIO myFileIO=new MyFileIO();
        String strs = null;
        try {
            strs = myFileIO.load(filename);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "配置文件读写异常！");
            e.printStackTrace();
        }
        if (strs == null) {
            try {
                StringBuffer sb = new StringBuffer();
                for (int i = 0; i < Constant.player.length; i++) {
                    sb.append(10000 * (i + 1)).append(" computer").append("###");
                }
                myFileIO.set(filename, sb.toString());
                return sb.toString().split("###");
            } catch (IOException e) {
                JOptionPane.showMessageDialog(null, "配置文件读写异常！");
                e.printStackTrace();
            }
        }
        assert strs != null;
        return strs.split("###");
    }

    /**
     * 分析是否打破记录
     */
    public static boolean analyzeScore(int score,boolean option){
        String[] ns;
        if(option){
            ns=Constant.player[0].split(" ");
        }else {
            ns=Constant.player2[0].split(" ");
        }

        return score > Integer.parseInt(ns[0]);
    }

    /**
     * 根据传入的名字及分数（已分析打破了记录）更新排行榜
     */

    public static void addPlayer(int score,String name){
        MyFileIO myFileIO=new MyFileIO();
        String[] ns;
        String namescore=score+" "+name;
        Constant.player[0]=namescore;
        for(int i=0;i<Constant.player.length-1;i++){//从小到大遍历比较分数，如果新分数大于旧分数则两者替换位置（冒泡排序）
            ns=Constant.player[i+1].split(" ");
            if(score>Integer.parseInt(ns[0])){
                Constant.player[i]=Constant.player[i+1];
                Constant.player[i+1]=namescore;
            }else {
                break;
            }
        }
        StringBuffer sb=new StringBuffer();

        for (int i=0;i<Constant.player.length;i++){
            sb.append(Constant.player[i]).append("###");
        }
        try {
            myFileIO.set(filename,sb.toString());
        } catch (IOException e) {

            JOptionPane.showMessageDialog(null,"文件写入异常！");
            e.printStackTrace();
        }
    }

    /**
     * 输入排行榜文件路径，重置该文件
     */
    public static void reset(String filename){
        MyFileIO myFileIO=new MyFileIO();
        try {
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < Constant.player.length; i++) {
                sb.append(10000 * (i + 1)).append(" computer").append("###");
            }
            myFileIO.set(filename, sb.toString());
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "配置文件重置失败！");
            e.printStackTrace();
        }
    }
    public static void addPlayer2(int score,String name){
        MyFileIO myFileIO=new MyFileIO();
        String[] ns;
        String namescore=score+" "+name;
        Constant.player2[0]=namescore;
        for(int i=0;i<Constant.player2.length-1;i++){
            ns=Constant.player2[i+1].split(" ");
            if(score>Integer.parseInt(ns[0])){
                Constant.player2[i]=Constant.player2[i+1];
                Constant.player2[i+1]=namescore;
            }else {
                break;
            }
        }
        StringBuffer sb=new StringBuffer();
        for (int i=0;i<Constant.player2.length;i++){
            sb.append(Constant.player2[i]).append("###");
        }
        try {
            myFileIO.set(filename2,sb.toString());
        } catch (IOException e) {

            JOptionPane.showMessageDialog(null,"文件写入异常！");
            e.printStackTrace();
        }
    }



}
