package eu.wrty.eu.android.dnsupdater;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;

public class WIFI_CHANGE_BR extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
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
			Intent IP_Update_BR = new Intent();
			IP_Update_BR.setAction("eu.wrty.android.intent.action.DNS_UPDATE");
			context.sendBroadcast(IP_Update_BR);			
		}	    
	    
	    
	}

}
