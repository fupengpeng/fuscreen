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
 * �ͻ���
 * @author fp
 *
 */
public class Client {
	
	public static void main(String[] args) {
		
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
			String input = JOptionPane.showInputDialog(
					"������Ҫ���ӵķ�����ip��ַ",
					"127.0.0.1:0000001");
			//3.��ȡ������ip��ַ
			String host =  input.substring(0, input.indexOf(":"));
			//4.��ȡ�������˿ں�
			String post = input.substring(input.indexOf(":"+1));
			System.out.println("������ip:"
					+host+"    �������˿ںţ�"+post);
			
			try {
				//5.���ӷ�����
				Socket client = new Socket(host, Integer.parseInt(post));
				//6.
				DataInputStream dis = new DataInputStream(client.getInputStream());
				//7.�������
				JFrame jFrame = new JFrame();
				jFrame.setDefaultCloseOperation(3);
				jFrame.setTitle("Զ��������");
				jFrame.setSize(1024,768);
				//8����ȡ�������ֱ��ʣ�����չ���������
				double height = dis.readDouble();
				double width = dis.readDouble();
				//9.��ȡ��Ļ��С
				Dimension ds = new Dimension((int)width, (int)height);
				//10.������Ļ��С
				jFrame.setSize(ds);
				//11.������������չʾ���������
				JLabel backImage = new JLabel();//�������
				JPanel panel = new JPanel();
				//12.�ֱ��ʲ����䣬��Ҫ����������
				JScrollPane scrollPane = new JScrollPane(panel);
				panel.setLayout(new FlowLayout());
				
				panel.add(backImage);//����ͼƬ��ӵ�panel
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
