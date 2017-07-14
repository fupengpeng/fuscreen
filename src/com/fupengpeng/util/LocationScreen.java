package com.fupengpeng.util;

import java.awt.AWTException;
import java.awt.Dimension;
import java.awt.HeadlessException;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

/**
 * 本地监控
 * @author fp
 *
 */
public class LocationScreen {
	public static void main(String[] args) {
		System.out.println("同学们？？？？？？？？");
		//1.弹出对话框，询问是否连接控制对方电脑
		int choice = JOptionPane.showConfirmDialog(
				null, 
				"请求控制对方电脑？",
				"java xueyuan",
				JOptionPane.YES_NO_OPTION);
		//如果点击了no
		if(choice == JOptionPane.NO_OPTION){
			return;
		}else{
			//2.点击了是，输入要连接的电脑ip地址和端口号（门牌号）
			JOptionPane.showInputDialog(
					"请输入要链接的服务器ip地址",
					"127.0.0.1");
			//3.初始化窗口，接收控制请求
			JFrame jFrame = 
					new JFrame("java学院，远程控制--付朋朋");
			//4.设置窗口大小
			jFrame.setSize(600, 600);
			//5.设置窗口可见
			jFrame.setVisible(true);
			//6.设置窗口居中
			jFrame.setLocationRelativeTo(null);
			//7.设置窗口置顶
			jFrame.setAlwaysOnTop(true);
			//8.点击退出按钮时，关闭程序
			jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			
			
			
			try {
				//9.定义方法，查询本机操作系统
				Toolkit tk = Toolkit.getDefaultToolkit();
				//10.获取屏幕大小
				Dimension dm = tk.getScreenSize();
				//11.设置要显示的区域
				JLabel imagelJLabe = new JLabel();
				jFrame.add(imagelJLabe);
				
		
				while (true) {//不停的截图，形成视频
					//12.获取对方电脑指定的区域，矩形
					Rectangle rec = new Rectangle(
							jFrame.getWidth(), 
							0, 
							(int)dm.getWidth()-jFrame.getWidth(), //屏幕宽度减去弹框宽度
							(int)dm.getHeight()-jFrame.getHeight());
					
					//13.捕获屏幕宽度（机器人来捕获）
					Robot robot = new Robot();//创建一个机器人，  捕获异常快捷键 alt+shift+z
					BufferedImage bufImg = robot.createScreenCapture(rec);//捕获屏幕
					imagelJLabe.setIcon(new ImageIcon(bufImg));//捕获的屏幕添加到所要显示的区域
					
					
				}
				
				
			} catch (HeadlessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (AWTException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			
		}
	}
}
