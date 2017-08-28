package MySocket;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;


/**
 * socket ��������
 * �������ͻ��� ��������
 * */
public class ServerHAL {

	public ServerHAL() {
		// TODO Auto-generated constructor stub
	}
	
	public static int port = 9090;
	
	public  void netServer(){
		ServerSocket serverSocket = null;  //�����socket
		Socket socket = null;              //�ͻ�������socket
		int n = 0;
		
		try {
			//�½�ServerSocket
			serverSocket = new ServerSocket(port); //	
			
			while(true){
				n++;
				if(n>500) n=0;
				
				//�ȴ�accept ����
				System.out.println("�ȴ�accept��"+n+"������...");
				socket = serverSocket.accept(); 
				
				//����socket ����  ��ʱ 5s 
				socket.setSoTimeout(1000*80);
				
				//socket �շ�
				new NetThread( socket ).start();
			}
			   
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	
	//�����շ�  �߳���
	private class  NetThread extends Thread{
		 private Socket socket;
		 
		//����
		public NetThread(Socket socket) {
			// TODO Auto-generated constructor stub
			this.socket = socket;
		}
	 
		@Override
		public void run() {
			// TODO Auto-generated method stub
			super.run();
			//�鿴 ���ӵĿͻ���ip
			SocketAddress clientAddress = socket.getRemoteSocketAddress();		
			String Ipstr = clientAddress.toString();
			System.out.println("�ͻ��˵�ַ��"+Ipstr+"   ���ӳɹ���");
			
			//�½� ���ݽ����߳�
			new NetRecvThread(socket).start();
			//�½� ���ݷ����߳�
			new NetSendThread(socket).start();	
		}
		
	}

}
