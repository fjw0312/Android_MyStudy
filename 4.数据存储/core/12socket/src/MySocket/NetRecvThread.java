package MySocket;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.util.Arrays;

import android.util.Log;


/**
 * 循环接收网络数据
 * author:fjw0312@163.com
 * date:2017.4.27
 * */
public class NetRecvThread extends Thread{
	
	private Socket socket;
	private  InputStream in = null;
	public byte[] recvBuf = new byte[1024*4];

	//构造
	public NetRecvThread(Socket in_socket) {
		// TODO Auto-generated constructor stub
		this.socket = in_socket;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		super.run();
		try {
			//获取  输入流接口
			in = socket.getInputStream();
			//循环 接收数据
			while(true){
				if( socket.isClosed() ){ //socket已经关闭
					break;
				}
			    //读取  接收流
				int recv_num = in.read(recvBuf, 0, recvBuf.length);
			
			}	
						
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Log.w(this.getClass().getName(), "NetRecvThread>>线程退出！");
		}finally{
			try {
				in.close();
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
			Log.w(this.getClass().getName(), "NetRecvThread>>线程结束！");
		}
		
	}



}
