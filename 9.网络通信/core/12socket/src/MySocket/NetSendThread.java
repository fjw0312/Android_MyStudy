package MySocket;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.util.List;

import android.util.Log;



/**
 * 循环发送网络数据
 * author:fjw0312@163.com
 * date:2017.4.27
 * */
public class NetSendThread extends Thread{

	private Socket socket;
	private  OutputStream out = null;
	public static int delayTime = 200;
	public byte[] sendBuf = new byte[1024*4]; 

	//构造
	public NetSendThread(Socket out_socket) {
		// TODO Auto-generated constructor stub
		this.socket = out_socket;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		super.run();
		try {
			//获取  输入流接口
			out = socket.getOutputStream();

			
			//循环 接收数据
			while(true){
				
				if( socket.isClosed() ){ //socket已经关闭
					break;
				}
				
				//写入 发送流
				out.write(sendBuf, 0, sendBuf.length);
				out.flush(); //刷新发送
				
				//适当延时
				try{
					Thread.sleep(delayTime);
				}catch(Exception e){
					
				}
			}	
				
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Log.w(this.getClass().getName(), "NetSendThread>>异常抛出！");
		}finally{
			try {
				out.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				socket.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Log.w(this.getClass().getName(), "NetSendThread>>线程结束！");
		}
	}

}
