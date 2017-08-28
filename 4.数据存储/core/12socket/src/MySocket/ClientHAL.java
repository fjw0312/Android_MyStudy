package MySocket;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Arrays;

import android.util.Log;



/**
 * socket ����ͻ���
 * */
public class ClientHAL {

	public ClientHAL() {
		// TODO Auto-generated constructor stub
	}
	//����  �շ�buf
	public static byte[] sendBuf = new byte[1024*4]; 
	public static byte[] recvBuf = new byte[1024*4];
	
	//�����շ�����
	public static void netClient(String ip, int port){
		Arrays.fill(recvBuf, (byte)0); //��ս���buf

		
		//�½�socket
		Socket socket = new Socket(); 
		InputStream in = null;
		OutputStream out = null;
		
		
		try {
			//������������ �����ӳ�ʱ
			socket.connect(new InetSocketAddress(ip, port), 500);
		    socket.setSoTimeout(5*1000);  //���ý��ճ�ʱ
		    
		    //��ȡ ����  ��� ��
		    in = socket.getInputStream();
			out = socket.getOutputStream();
			
			//д�� ������
			out.write(sendBuf, 0, sendBuf.length);
			out.flush(); //ˢ�·���
			
		    //��ȡ  ������
			int recv_num = in.read(recvBuf, 0, recvBuf.length);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			Log.w("�������socket IOException err", "close socket");
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
				Log.w("�������socket err", mm);
			}			
		}
		
	}

}
