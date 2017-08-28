package MySocket;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.util.List;

import android.util.Log;



/**
 * ѭ��������������
 * author:fjw0312@163.com
 * date:2017.4.27
 * */
public class NetSendThread extends Thread{

	private Socket socket;
	private  OutputStream out = null;
	public static int delayTime = 200;
	public byte[] sendBuf = new byte[1024*4]; 

	//����
	public NetSendThread(Socket out_socket) {
		// TODO Auto-generated constructor stub
		this.socket = out_socket;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		super.run();
		try {
			//��ȡ  �������ӿ�
			out = socket.getOutputStream();

			
			//ѭ�� ��������
			while(true){
				
				if( socket.isClosed() ){ //socket�Ѿ��ر�
					break;
				}
				
				//д�� ������
				out.write(sendBuf, 0, sendBuf.length);
				out.flush(); //ˢ�·���
				
				//�ʵ���ʱ
				try{
					Thread.sleep(delayTime);
				}catch(Exception e){
					
				}
			}	
				
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Log.w(this.getClass().getName(), "NetSendThread>>�쳣�׳���");
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
			Log.w(this.getClass().getName(), "NetSendThread>>�߳̽�����");
		}
	}

}
