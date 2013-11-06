package eu.wrty.eu.android.dnsupdater;

import android.app.Application;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Messenger;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

public class WIFI_CHANGE_BR extends BroadcastReceiver {

	

    
	@Override
	public void onReceive(final Context context, Intent intent) {
		
	    Handler handler = new Handler() {
	    	
	        @Override
	        public void handleMessage(Message msg) {
	                Bundle reply = msg.getData();
	                	// do whatever with the bundle here
	                
	                String response = reply.getString("response", "none");
	                Toast.makeText(context, response, Toast.LENGTH_SHORT).show();
	                }
	    };	
		
		// TODO Auto-generated method stub
		Log.d("DNSU", "service started");
//		Intent service = new Intent(context, DnsupdaterService.class);
//	    context.startService(service);

	  //optimierung
//		load data and then compare with last execution time
//		int timestamp_last_run = sharedPref.getInt("TIMESTAMP", -1);
//		String key = sharedPref.getString("KEY", "");
//		String url = sharedPref.getString("URL", "");
		
		
		//wenn wlan dann sonst raus
		int conn = NetworkUtil.getConnectivityStatus(context);
		if (conn == NetworkUtil.TYPE_WIFI){
			Log.d("DNSU", "wifi enabled");
			String ip = NetworkUtil.getIpAddr(context);
			Log.d("DNSU", "wifi ip: " + ip);

			//ip an server senden
			//intent erzeugen und mit ip bestücken					
			Intent IpUpdateIS = new Intent(context.getApplicationContext(), IpUpdate_IS.class);
			IpUpdateIS.putExtra("ip", ip);
			
			if (intent.getExtras() != null){
				Messenger messenger = (Messenger) intent.getExtras().get("messenger");
				if (messenger == null) messenger = new Messenger(handler);
				IpUpdateIS.putExtra("messenger", messenger);
			}
				
			context.getApplicationContext().startService(IpUpdateIS);
		}	    
	    
	    
	}
	


}
