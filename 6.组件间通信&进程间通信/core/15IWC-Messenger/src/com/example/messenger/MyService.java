package com.example.messenger;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.util.Log;

public class MyService extends Service{

	public MyService() {
		// TODO Auto-generated constructor stub
	}
	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		Log.i("MyService->onCreate","into");
	}


	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		// TODO Auto-generated method stub
		Log.i("MyService->onStartCommand","into");
		return super.onStartCommand(intent, flags, startId);
	}


	@Override
	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method stub
		Log.i("MyService->onBind","into");
		return messenger.getBinder();
	}
	
	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		Log.i("MyService->onDestroy","into");
	}
	
	//����һ�� Messenger	
	private Handler myHandler = new Handler(){
		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			super.handleMessage(msg);
			switch(msg.what){
			case 1:  //��ȡ��Messenger ��������Message
				Log.i("MyService->myHandler","into>> get Messenger.send-1");
				GetMessenger = msg.replyTo;
				break;
			case 2: //��ȡ��Messenger ��������Message  ��ͨ����õ���һ��Messenger ���ͻ���Message
				Log.i("MyService->myHandler","into>> get Messenger.send-2");
				GetMessenger = msg.replyTo;
				//���� GetMessenger.send   ����Activity��Messenger
				Message newMsg = new Message();
				newMsg.what = 3;
				try {
					GetMessenger.send(newMsg);
				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			default: break;
			}
		}
	};
	public Messenger messenger = new Messenger(myHandler);  //��Service�˵�Messenger
	public Messenger GetMessenger = null;   //���յ���Messenger���ڻط���Ϣ
}
