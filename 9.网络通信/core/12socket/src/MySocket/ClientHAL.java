package MySocket;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Arrays;

import android.util.Log;



/**
 * socket 网络客户端
 * */
public class ClientHAL {

	public ClientHAL() {
		// TODO Auto-generated constructor stub
	}
	//定义  收发buf
	public static byte[] sendBuf = new byte[1024*4]; 
	public static byte[] recvBuf = new byte[1024*4];
	
	//网络收发数据
	public static void netClient(String ip, int port){
		Arrays.fill(recvBuf, (byte)0); //清空接收buf

		
		//新建socket
		Socket socket = new Socket(); 
		InputStream in = null;
		OutputStream out = null;
		
		
		try {
			//设置网络连接 及连接超时
			socket.connect(new InetSocketAddress(ip, port), 500);
		    socket.setSoTimeout(5*1000);  //设置接收超时
		    
		    //获取 输入  输出 流
		    in = socket.getInputStream();
			out = socket.getOutputStream();
			
			//写入 发送流
			out.write(sendBuf, 0, sendBuf.length);
			out.flush(); //刷新发送
			
		    //读取  接收流
			int recv_num = in.read(recvBuf, 0, recvBuf.length);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			Log.w("网络出错！socket IOException err", "close socket");
			e.printStackTrace();
		}finally
		{
			try
			{
				in.close();
			} catch (Exception e)
			{
				e.printStackTrace();
			}
			
			try
			{
				out.close();
			} catch (Throwable e)
			{
				e.printStackTrace();
			}
			
			try
			{
				socket.close();
			} catch (Throwable e)
			{
				String mm = e.getMessage().toString();
				Log.w("网络出错！socket err", mm);
			}			
		}
		
	}

}
