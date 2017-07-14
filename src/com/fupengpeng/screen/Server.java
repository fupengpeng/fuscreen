package com.fupengpeng.screen;

import java.awt.AWTException;
import java.awt.Dimension;
import java.awt.HeadlessException;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;


/**
 * Զ�̼�ط����
 * @author fp
 *
 */
public class Server {
	
	public static void main(String[] args) {
		
		
		try {
			//1.��������������
			ServerSocket ss = new ServerSocket(10000);
			System.out.println("����������������>>>>");
			//2.�ȴ�����
			Socket client = ss.accept();
			System.out.println("���������ӳɹ�������������");
			//3.ͨ��socket��ȡ�ļ������
			OutputStream os = client.getOutputStream();
			//4.�ļ���ת���ɶ������ļ�
			DataOutputStream doc = new DataOutputStream(os);
			//5.�����߳�
//			ScreenThread screenThread = new ScreenThread(doc);
			//6.
//			screenThread.start();
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	/**
	 * 
	 * @author fp
	 *
	 */
	class ScreenThread extends Thread{
		//�������������
		private DataOutputStream dataOut;
		public ScreenThread(DataOutputStream dataOut){
			this.dataOut = dataOut;
		}
		
		//��ʼ�����߳�
		public void run(){
			try {
				//01.���巽������ѯ��������ϵͳ
				Toolkit tk = Toolkit.getDefaultToolkit();
				//02.��ȡ��Ļ��С
				Dimension dm = tk.getScreenSize();
				
				//03.��ȡ�ֱ���,�����ͻ���
				dataOut.writeDouble(dm.getHeight());
				dataOut.writeDouble(dm.getWidth());
				//04.ʱʱˢ���ڴ棬��֤ʱʱ����
				dataOut.flush();
				
				//05.���÷�����Ļ�Ĵ�С
				Rectangle rec = new Rectangle(dm);
				//06.����һ��������
				Robot robot = new Robot();
				
				while(true){
					BufferedImage bufferedImage = robot.createScreenCapture(rec);
					
					ByteArrayOutputStream baos = new ByteArrayOutputStream();
					//07.ͼ��ѹ������
					JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(baos);
					encoder.encode(bufferedImage);
					//�ֽڴ���
					byte [] data = baos.toByteArray();
					dataOut.writeInt(data.length);
					dataOut.write(data);
					dataOut.flush();
					try {
						Thread.sleep(0);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}
				
				
			} catch (HeadlessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (AWTException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
	
}
