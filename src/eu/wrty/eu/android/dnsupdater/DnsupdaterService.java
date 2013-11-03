package eu.wrty.eu.android.dnsupdater;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.IBinder;
import android.preference.PreferenceManager;
import android.util.Log;

public class DnsupdaterService extends Service {

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		// TODO do something useful
		
		Context context = this;
		
		//load data and then compare with last execution time
		SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(context);
		
		//https://wrty.eu/bind/?ip=192.168.0.1&key=
		//ip
		//key

		int timestamp_last_run = sharedPref.getInt("TIMESTAMP", -1);
		String key = sharedPref.getString("KEY", "");
		String url = sharedPref.getString("URL", "");
		
		
		int conn = NetworkUtil.getConnectivityStatus(context);
		if (conn == NetworkUtil.TYPE_WIFI){
			Log.d("DNSU", "wifi enabled");
			String ip = NetworkUtil.getIpAddr(context);
			Log.d("DNSU", "wifi ip: " + ip);

		}
		
		return Service.START_NOT_STICKY;
	}

	//wenn kein setup vorhanden de-registriere von broadcast receiver
	
	
	
	
	
	@Override
	public IBinder onBind(Intent intent) {
		// TODO for communication return IBinder implementation
		return null;
	}
}
