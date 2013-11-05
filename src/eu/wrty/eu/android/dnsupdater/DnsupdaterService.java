package eu.wrty.eu.android.dnsupdater;

import android.R.string;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.IBinder;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.Toast;

public class DnsupdaterService extends Service {

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		// TODO do something useful
		
		Context context = this;
		Bundle myextras = intent.getExtras();
		
		if (myextras.getBoolean("testbutton")) {
			Toast.makeText(getBaseContext(),"test wird gestartet", Toast.LENGTH_SHORT).show();
		} else {
			//durch wlan an/aus ausgelöst
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
