package com.zhan.ui;

import javax.swing.*;

public class RegisterJFrame extends JFrame {
    //RegisterJFrame 表示登录界面
    //以后所有跟注册相关的代码，都写在这里
    public RegisterJFrame(){
        this.setSize(488,500);


        //设置界面的标题
        this.setTitle("拼图 注册界面");

        //设置界面置顶
        this.setAlwaysOnTop(true);

        //设置界面居中
        this.setLocationRelativeTo(null);

        //设置游戏的关闭模式
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);


        //界面可视化
        this.setVisible(true);
    }
}
