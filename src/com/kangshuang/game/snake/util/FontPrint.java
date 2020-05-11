package com.kangshuang.game.snake.util;

import java.awt.*;

/**
 * 打印当前系统的字体名称
 */
public class FontPrint {
    public static void main(String[] args) {
        GraphicsEnvironment e = GraphicsEnvironment.getLocalGraphicsEnvironment(); //返回本地 GraphicsEnvironment 。
        String [] forName = e.getAvailableFontFamilyNames(); //返回包含在此所有字体系列名称的数组， GraphicsEnvironment本地化为默认的语言环境，如返回 Locale.getDefault() 。
        //逐行输出
        for (String s : forName) System.out.println(s);
    }
}
