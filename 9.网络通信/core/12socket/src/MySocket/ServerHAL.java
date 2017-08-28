package MySocket;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;


/**
 * socket 网络服务端
 * 允许多个客户端 连接请求
 * */
public class ServerHAL {

	public ServerHAL() {
		// TODO Auto-generated constructor stub
	}
	
	public static int port = 9090;
	
	public  void netServer(){
		ServerSocket serverSocket = null;  //服务端socket
		Socket socket = null;              //客户端连接socket
		int n = 0;
		
		try {
			//新建ServerSocket
			serverSocket = new ServerSocket(port); //	
			
			while(true){
				n++;
				if(n>500) n=0;
				
				//等待accept 连接
				System.out.println("等待accept第"+n+"个连接...");
				socket = serverSocket.accept(); 
				
				//设置socket 接收  超时 5s 
				socket.setSoTimeout(1000*80);
				
				//socket 收发
				new NetThread( socket ).start();
			}
			   
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	
	//网络收发  线程类
	private class  NetThread extends Thread{
		 private Socket socket;
		 
		//构造
		public NetThread(Socket socket) {
			// TODO Auto-generated constructor stub
			this.socket = socket;
		}
	 
		@Override
		public void run() {
			// TODO Auto-generated method stub
			super.run();
			//查看 连接的客户端ip
			SocketAddress clientAddress = socket.getRemoteSocketAddress();		
			String Ipstr = clientAddress.toString();
			System.out.println("客户端地址："+Ipstr+"   连接成功！");
			
			//新建 数据接收线程
			new NetRecvThread(socket).start();
			//新建 数据发送线程
			new NetSendThread(socket).start();	
		}
		
	}

}
