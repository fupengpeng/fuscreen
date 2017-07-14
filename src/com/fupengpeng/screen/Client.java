package com.fupengpeng.screen;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

/**
 * 客户端
 * @author fp
 *
 */
public class Client {
	
	public static void main(String[] args) {
		
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
			String input = JOptionPane.showInputDialog(
					"请输入要链接的服务器ip地址",
					"127.0.0.1:0000001");
			//3.获取服务器ip地址
			String host =  input.substring(0, input.indexOf(":"));
			//4.获取服务器端口号
			String post = input.substring(input.indexOf(":"+1));
			System.out.println("服务器ip:"
					+host+"    服务器端口号："+post);
			
			try {
				//5.连接服务器
				Socket client = new Socket(host, Integer.parseInt(post));
				//6.
				DataInputStream dis = new DataInputStream(client.getInputStream());
				//7.创建面板
				JFrame jFrame = new JFrame();
				jFrame.setDefaultCloseOperation(3);
				jFrame.setTitle("远程桌面监控");
				jFrame.setSize(1024,768);
				//8。读取服务器分辨率，用于展现在面板中
				double height = dis.readDouble();
				double width = dis.readDouble();
				//9.获取屏幕大小
				Dimension ds = new Dimension((int)width, (int)height);
				//10.设置屏幕大小
				jFrame.setSize(ds);
				//11.将服务器内容展示在面板里面
				JLabel backImage = new JLabel();//创建面板
				JPanel panel = new JPanel();
				//12.分辨率不适配，需要滚动条调整
				JScrollPane scrollPane = new JScrollPane(panel);
				panel.setLayout(new FlowLayout());
				
				panel.add(backImage);//背景图片添加到panel
				jFrame.add(scrollPane);
				jFrame.setAlwaysOnTop(true);
				jFrame.setVisible(true);
				
				while(true){
					int len = dis.readInt();//
					byte[] imageData = new byte[len];
					dis.readFully(imageData);
					ImageIcon image = new ImageIcon(imageData);
					backImage.setIcon(image);
					jFrame.repaint();
					
				}
				
				
				
				
				
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (UnknownHostException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			
		}
		
		
	}
	
}
