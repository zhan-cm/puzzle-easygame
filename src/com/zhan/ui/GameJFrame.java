package com.zhan.ui;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

public class GameJFrame extends JFrame implements KeyListener, ActionListener {
    //JFrame  界面，窗体
    //规定：GameJFrame这个界面表示的就是游戏的主界面
    //以后跟游戏相关的所有逻辑都放在这个类中

    //创建二维数组
    //目的：用来管理二维数组
    int[][] arr= new int[4][4];
    //记录空白方块在二维数组的位置
    int x=0,y=0;
    //定义一个变量，记录当前图片的路径
    String path = "image\\animal\\animal3\\";

    //定义一个二维数组，存储正确的数组
    int[][] win = {
            {1,2,3,4},
            {5,6,7,8},
            {9,10,11,12},
            {13,14,15,0}
    };

    //定义变量用来记录步数
    int step = 0;
    //创建选项下的条目对象
    JMenuItem replayItem = new JMenuItem("重新游戏");
    JMenuItem reLoginItem = new JMenuItem("重新登录");
    JMenuItem closeItem = new JMenuItem("关闭游戏");

    JMenuItem accountItem = new JMenuItem("公众号");

    public GameJFrame(){
        //初始化界面
        initJFrame();

        //初始化菜单
        initJMenuBar();

        //初始化数据（打乱）
        initData();


        //初始化图片
        initImage();


        //让界面可视化
        this.setVisible(true);
    }


    private void initData() {
        Random r = new Random();
        int[] tempArr = {0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15};

        for (int i = 0; i < tempArr.length; i++) {
            int number = r.nextInt(16);
            int temp = tempArr[i];
            tempArr[i]=tempArr[number];
            tempArr[number]=temp;
        }


        for (int i = 0; i < tempArr.length; i++) {
            if(tempArr[i]==0){
                x=i/4;
                y=i%4;
            }
            arr[i / 4][i % 4] = tempArr[i];


        }
    }

    private void initImage() {
        //清空原来已经出现的图片
        this.getContentPane().removeAll();

        if (victory()) {
            //显示胜利图标
            JLabel winJLabel = new JLabel(new ImageIcon("F:\\code-java\\Jigsaw-puzzle-game\\image\\win.png"));
            winJLabel.setBounds(203,283,197,73);
            this.getContentPane().add(winJLabel);
        }
        JLabel stepCount = new JLabel("步数："+step);
        stepCount.setBounds(50,30,100,20);
        this.getContentPane().add(stepCount);

        //路径分为两种
        //绝对路径：一定从盘符开始  C:\
        //相对路径：不是从盘符开始
        //相对路径相对当前项目而言  aaa\\bbb
        //在当前项目下，去找到aaa文件夹，里面再找bbb文件夹

        //细节：先加载的图片在上方，后加载的图片在下方

        for (int i = 0; i < 4; i++) {
            for (int j = 0;j < 4;j++){
                //获取当前要加载的图片序号
                int num = arr[i][j];
                //创建一个图片ImageIcon的对象
                //ImageIcon icon = new ImageIcon();
                //创建一个JLabel的对象（管理容器）
                JLabel jLabel = new JLabel(new ImageIcon(path+num+".jpg"));
                //指定图片位置
                jLabel.setBounds(105*j+83,105*i+134,105,105);
                //给图片添加边框
                //0-图片突起，1-图片凹入
                jLabel.setBorder(new BevelBorder(BevelBorder.LOWERED));

                //把管理容器添加到界面中
                //this.add(jLabel);
                this.getContentPane().add(jLabel);

            }
        }

        //添加背景图
        JLabel background = new JLabel(new ImageIcon("image\\background.png"));
        background.setBounds(40,40,508,560);
        //把背景图片添加到界面中
        this.getContentPane().add(background);

        //刷新界面
        this.getContentPane().repaint();

    }
    private void initJMenuBar() {
        //创建菜单对象
        JMenuBar jMenuBar = new JMenuBar();

        //创建菜单菜单上的两个选项的对象（功能  关于我们）
        JMenu functionJMenu = new JMenu("功能");
        JMenu aboutJMenu = new JMenu("关于我们");


        //将每个选项下的条目添加在选项当中
        functionJMenu.add(replayItem);
        functionJMenu.add(reLoginItem);
        functionJMenu.add(closeItem);

        aboutJMenu.add(accountItem);

        //给条目绑定事件
        replayItem.addActionListener(this);
        reLoginItem.addActionListener(this);
        closeItem.addActionListener(this);
        accountItem.addActionListener(this);


        //将菜单里面的选项添加到菜单当中
        jMenuBar.add(functionJMenu);
        jMenuBar.add(aboutJMenu);

        //给界面设置菜单
        this.setJMenuBar(jMenuBar);
    }

    private void initJFrame() {
        //设置界面的宽高
        this.setSize(603,680);

        //设置界面的标题
        this.setTitle("拼图单机版 V1.0");

        //设置界面置顶
        this.setAlwaysOnTop(true);

        //设置界面居中
        this.setLocationRelativeTo(null);

        //设置游戏的关闭模式
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        //取消默认的居中位置，只有取消了才会按照xy轴的形式添加组件
        this.setLayout(null);

        //给整个界面添加键盘监听事件
        this.addKeyListener(this);
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        //按下不松时调用
        int code = e.getKeyCode();
        if(code==65){
            //把界面中所有图片删除
            this.getContentPane().removeAll();
            //加载第一张完整图片
            JLabel all = new JLabel(new ImageIcon(path+"all.jpg"));
            all.setBounds(83,134,420,420);
            this.getContentPane().add(all);
            //加载背景图片
            //添加背景图
            JLabel background = new JLabel(new ImageIcon("image\\background.png"));
            background.setBounds(40,40,508,560);
            //把背景图片添加到界面中
            this.getContentPane().add(background);
            //刷新界面
            this.getContentPane().repaint();
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {
        //判断游戏是否胜利，如果胜利，此方法需要直接结束，不能再执行下面的移动代码了
        if (victory()) {
            //
            return;
        }

        //左37 上38 右39 下40
        int code=e.getKeyCode();
        if(code==37){
            System.out.println("左移");
            if(y==3){return;}
            arr[x][y]=arr[x][y+1];
            arr[x][y+1]=0;
            y++;
            step++;
            initImage();
        }else if(code==38){
            System.out.println("上移");
            if(x==3){return;}
            //空白方块下面的数字往上移动
            //x+1,y

            //把空白方块下面的数字赋值给空白方块
            arr[x][y]=arr[x+1][y];
            arr[x+1][y]=0;
            x++;
            step++;
            initImage();
        }else if(code==39){
            System.out.println("右移");
            if(y==0){return;}
            arr[x][y]=arr[x][y-1];
            arr[x][y-1]=0;
            y--;
            step++;
            initImage();
        }else if(code==40){
            System.out.println("下移");
            if(x==0){return;}
            arr[x][y]=arr[x-1][y];
            arr[x-1][y]=0;
            x--;
            step++;
            initImage();
        }else if(code==65){
            initImage();
        }else if(code==87){
            arr = new int[][]{
                    {1,2,3,4},
                    {5,6,7,8},
                    {9,10,11,12},
                    {13,14,15,0}
            };
            initImage();
        }
    }

    //判断data数组中的数据是否跟win数组相同
    //如果全部相同，返回true，反之返回false
    public boolean victory(){
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length; j++) {
                if(arr[i][j]!=win[i][j]){
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //获取当前被点击的条目对象
        Object obj = e.getSource();
        //判断
        if(obj==replayItem){
            System.out.println("重新游戏");
            //计步器清零
            step=0;
            //再次打乱二维数组的数据
            initData();
            //重新加载图片
            initImage();

        } else if (obj == reLoginItem) {
            System.out.println("重新登录");
            //关闭当前游戏界面
            this.setVisible(false);
            //打开登录界面
            new LoginJFrame();

        } else if (obj == closeItem) {
            System.out.println("关闭游戏");
            System.exit(0);
        } else if (obj == accountItem) {
            System.out.println("公众号");

            //创建一个弹窗对象
            JDialog jDialog = new JDialog();
            //创建一个管理图片的容器对象JLabel
            JLabel jLabel = new JLabel(new ImageIcon("image\\about.png"));
            //设置位置和宽高
            jLabel.setBounds(0,0,258,258);
            //把图片添加到弹窗中
            jDialog.getContentPane().add(jLabel);
            //弹窗设置大小
            jDialog.setSize(344,344);
            //弹窗置顶
            jDialog.setAlwaysOnTop(true);
            //让弹窗居中
            jDialog.setLocationRelativeTo(null);
            //弹窗不关闭就无法操作下面的界面
            jDialog.setModal(true);
            //让弹窗显示出来
            jDialog.setVisible(true);

        }
    }
}
