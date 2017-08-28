package com.example.c013_broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**开机自启动广播接收器
 * */
public class BootBroadcastReceiver extends BroadcastReceiver { 
    static final String action_boot="android.intent.action.BOOT_COMPLETED";  
  
    @Override
    public void onReceive(Context context, Intent intent) { 
    	
    	Toast.makeText(context, "Broadcast>>BOOT", Toast.LENGTH_LONG).show();
   //     if (intent.getAction().equals(action_boot)){  
   //         Intent ootStartIntent=new Intent(context, MainActivity.class);  
   //         context.startActivity(ootStartIntent);  
   //     } 
    }  
} 