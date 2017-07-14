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
 * 远程监控服务端
 * @author fp
 *
 */
public class Server {
	
	public static void main(String[] args) {
		
		
		try {
			//1.建立服务器监听
			ServerSocket ss = new ServerSocket(10000);
			System.out.println("服务器正在连接中>>>>");
			//2.等待连接
			Socket client = ss.accept();
			System.out.println("服务器连接成功。。。。。。");
			//3.通过socket获取文件输出流
			OutputStream os = client.getOutputStream();
			//4.文件流转换成二进制文件
			DataOutputStream doc = new DataOutputStream(os);
			//5.启动线程
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
		//定义数据输出流
		private DataOutputStream dataOut;
		public ScreenThread(DataOutputStream dataOut){
			this.dataOut = dataOut;
		}
		
		//开始启动线程
		public void run(){
			try {
				//01.定义方法，查询本机操作系统
				Toolkit tk = Toolkit.getDefaultToolkit();
				//02.获取屏幕大小
				Dimension dm = tk.getScreenSize();
				
				//03.获取分辨率,发给客户端
				dataOut.writeDouble(dm.getHeight());
				dataOut.writeDouble(dm.getWidth());
				//04.时时刷新内存，保证时时更新
				dataOut.flush();
				
				//05.设置分享屏幕的大小
				Rectangle rec = new Rectangle(dm);
				//06.创建一个机器人
				Robot robot = new Robot();
				
				while(true){
					BufferedImage bufferedImage = robot.createScreenCapture(rec);
					
					ByteArrayOutputStream baos = new ByteArrayOutputStream();
					//07.图像压缩处理
					JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(baos);
					encoder.encode(bufferedImage);
					//字节传输
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
