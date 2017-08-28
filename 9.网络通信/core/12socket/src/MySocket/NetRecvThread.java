package MySocket;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.util.Arrays;

import android.util.Log;


/**
 * ѭ��������������
 * author:fjw0312@163.com
 * date:2017.4.27
 * */
public class NetRecvThread extends Thread{
	
	private Socket socket;
	private  InputStream in = null;
	public byte[] recvBuf = new byte[1024*4];

	//����
	public NetRecvThread(Socket in_socket) {
		// TODO Auto-generated constructor stub
		this.socket = in_socket;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		super.run();
		try {
			//��ȡ  �������ӿ�
			in = socket.getInputStream();
			//ѭ�� ��������
			while(true){
				if( socket.isClosed() ){ //socket�Ѿ��ر�
					break;
				}
			    //��ȡ  ������
				int recv_num = in.read(recvBuf, 0, recvBuf.length);
			
			}	
						
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Log.w(this.getClass().getName(), "NetRecvThread>>�߳��˳���");
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
			Log.w(this.getClass().getName(), "NetRecvThread>>�߳̽�����");
		}
		
	}



}
