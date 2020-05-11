package com.kangshuang.game.snake.constant;

import com.kangshuang.game.snake.util.ImageUtil;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class Constant {
    public static final int GAME_WIDTH=1020;
    public static final int GAME_HIGH=662;
    public static final String IMG_PATH= "com/kangshuang/game/snake/img/";
    public static final int FOOD_PRIZE1=100;
    public static final int FOOD_PRIZE2=300;
    public static final int FOOD_PRIZE3=1000;
    public static final int FOOD_PRIZE4=23333;
    public static final String MESSAGE1="别点我啦，我什么也不会告诉你";
    public static final String MESSAGE2="空格键也可以暂停游戏";
    public static final String MESSAGE3="按键无效可能是因为没有切换到英文输入法";
    public static final String MESSAGE4="QAQ";
    public static final String MESSAGE5="不要点我啦！(￣^￣)";
    public static final String MESSAGE6=" (￣o￣) . z Z";
    public static final String MESSAGE7="主界面点击f4可输入神秘代码哦";
    public static final String MESSAGE8="听说-slow可以不再加速";
    public static final String MESSAGE9="想看看电脑玩游戏?试试-move吧";
    public static final String MESSAGE10="-reset1可以重置单人游戏排行榜";
    public static final String MESSAGE11="我是不会告诉你-god可以无敌的";
    public static String[] player=new String[5];
    public static String[] player2=new String[5];
    public static boolean PAUSE=false;
    public static boolean FLAG1=false;//无敌
    public static boolean FLAG2=false;//自动移动
    public static boolean FLAG3=false;//锁定速度


    public static Map<String, Image> image=new HashMap<>();//获取图片并存储在HaspMap中，使用时调用get()方法
    static {
        image.put("snake_body", ImageUtil.getImage(Constant.IMG_PATH+"snake_head0.png"));
        image.put("food", ImageUtil.getImage(Constant.IMG_PATH+"food.png"));
        image.put("snake_head", ImageUtil.getImage(Constant.IMG_PATH+"snake_head0.png"));
        image.put("background", ImageUtil.getImage(Constant.IMG_PATH+"background.png"));
        image.put("fail", ImageUtil.getImage(Constant.IMG_PATH+"fail.png"));
        image.put("food0", ImageUtil.getImage(Constant.IMG_PATH+"food0.png"));
        image.put("food1", ImageUtil.getImage(Constant.IMG_PATH+"food1.png"));
        image.put("food2", ImageUtil.getImage(Constant.IMG_PATH+"food2.png"));
        image.put("coffin", ImageUtil.getImage(Constant.IMG_PATH+"coffin.png"));
        image.put("main_view", ImageUtil.getImage(Constant.IMG_PATH+"main_view.png"));
        image.put("snake_body2", ImageUtil.getImage(Constant.IMG_PATH+"snake_head.png"));
        image.put("snake_head2", ImageUtil.getImage(Constant.IMG_PATH+"snake_head.png"));
        image.put("frame", ImageUtil.getImage(Constant.IMG_PATH+"frame.png"));
        image.put("ranking", ImageUtil.getImage(Constant.IMG_PATH+"ranking.png"));
        image.put("frame1", ImageUtil.getImage(Constant.IMG_PATH+"frame1.png"));
        image.put("frame2", ImageUtil.getImage(Constant.IMG_PATH+"frame2.png"));
        image.put("help", ImageUtil.getImage(Constant.IMG_PATH+"help.png"));
    }
}
