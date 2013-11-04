package eu.wrty.eu.android.dnsupdater;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class CONNECTIVITY_CHANGE_BroadcastReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO Auto-generated method stub
		Log.d("DNSU", "service started");
		Intent service = new Intent(context, DnsupdaterService.class);
	    context.startService(service);

	}

}
