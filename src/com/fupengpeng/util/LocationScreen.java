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
 * ���ؼ��
 * @author fp
 *
 */
public class LocationScreen {
	public static void main(String[] args) {
		System.out.println("ͬѧ�ǣ���������������");
		//1.�����Ի���ѯ���Ƿ����ӿ��ƶԷ�����
		int choice = JOptionPane.showConfirmDialog(
				null, 
				"������ƶԷ����ԣ�",
				"java xueyuan",
				JOptionPane.YES_NO_OPTION);
		//��������no
		if(choice == JOptionPane.NO_OPTION){
			return;
		}else{
			//2.������ǣ�����Ҫ���ӵĵ���ip��ַ�Ͷ˿ںţ����ƺţ�
			JOptionPane.showInputDialog(
					"������Ҫ���ӵķ�����ip��ַ",
					"127.0.0.1");
			//3.��ʼ�����ڣ����տ�������
			JFrame jFrame = 
					new JFrame("javaѧԺ��Զ�̿���--������");
			//4.���ô��ڴ�С
			jFrame.setSize(600, 600);
			//5.���ô��ڿɼ�
			jFrame.setVisible(true);
			//6.���ô��ھ���
			jFrame.setLocationRelativeTo(null);
			//7.���ô����ö�
			jFrame.setAlwaysOnTop(true);
			//8.����˳���ťʱ���رճ���
			jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			
			
			
			try {
				//9.���巽������ѯ��������ϵͳ
				Toolkit tk = Toolkit.getDefaultToolkit();
				//10.��ȡ��Ļ��С
				Dimension dm = tk.getScreenSize();
				//11.����Ҫ��ʾ������
				JLabel imagelJLabe = new JLabel();
				jFrame.add(imagelJLabe);
				
		
				while (true) {//��ͣ�Ľ�ͼ���γ���Ƶ
					//12.��ȡ�Է�����ָ�������򣬾���
					Rectangle rec = new Rectangle(
							jFrame.getWidth(), 
							0, 
							(int)dm.getWidth()-jFrame.getWidth(), //��Ļ��ȼ�ȥ������
							(int)dm.getHeight()-jFrame.getHeight());
					
					//13.������Ļ��ȣ�������������
					Robot robot = new Robot();//����һ�������ˣ�  �����쳣��ݼ� alt+shift+z
					BufferedImage bufImg = robot.createScreenCapture(rec);//������Ļ
					imagelJLabe.setIcon(new ImageIcon(bufImg));//�������Ļ��ӵ���Ҫ��ʾ������
					
					
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
