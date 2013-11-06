package eu.wrty.eu.android.dnsupdater;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;



import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.util.Log;
import android.widget.Toast;

public class IpUpdate_IS extends IntentService {

	public IpUpdate_IS() {
		super("IpUpdate_IS");
	}
	

	@Override
	public void onStart(Intent intent, int startId) {
		// TODO Auto-generated method stub
		super.onStart(intent, startId);
		//Toast.makeText(this, "Intent Detected2.", Toast.LENGTH_LONG).show();
	}



	@Override
	protected void onHandleIntent(Intent arg0) {
		// TODO Auto-generated method stub
		Context ctx = this;
		
	    //optimierung wenn kein setup dann de registriere: android.net.conn.CONNECTIVITY_CHANGE
		//wenn setup ok dann registriere android.net.conn.CONNECTIVITY_CHANGE
		
		//https://wrty.eu/bind/?ip=192.168.0.1&key=
		//ip
		//key		      
		
		Bundle extras = arg0.getExtras();
		if (extras != null) {
			String ip = extras.getString("ip");
			
			Messenger messenger = (Messenger) extras.get("messenger");
			Message msg = Message.obtain();

			
			SharedPreferences settings = ctx.getSharedPreferences("UserInfo", 0);
			String domain_key = settings.getString("editText_domain_key", "");
			String server = settings.getString("editText_server", "");
			
			if (domain_key == "" || server == ""){
				//Toast.makeText(ctx, "Setup incomplete", Toast.LENGTH_LONG).show();
				return;
			}
			
			
			String https_url = server + "?ip=" + ip + "&key=" + domain_key;
			URL url;
			try {

				url = new URL(https_url);
				HttpsURLConnection con = (HttpsURLConnection)url.openConnection();
	
				if(con!=null){
					try {
							BufferedReader br = 
								new BufferedReader(
								new InputStreamReader(con.getInputStream()));
	
							String input;
							String output = "";
							while ((input = br.readLine()) != null){
								//System.out.println(input);
								output += input;
							}
							br.close();
							
							Bundle data = new Bundle();
							data.putString("response", output);
							msg.setData(data); //put the data here
							try {
							    messenger.send(msg);
							} catch (RemoteException e) {
							    Log.i("error", "error");
							}
						} catch (IOException e) {
							e.printStackTrace();
						}
				}

			} catch (MalformedURLException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
