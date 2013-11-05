package eu.wrty.eu.android.dnsupdater;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import android.app.Application;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

public class IP_UPDATE_BR extends BroadcastReceiver {

	@Override
	public void onReceive(Context arg0, Intent arg1) {
		// TODO Auto-generated method stub
		Toast.makeText(arg0, "Intent Detected.", Toast.LENGTH_LONG).show();

	    //optimierung wenn kein setup dann de registriere: android.net.conn.CONNECTIVITY_CHANGE
		//wenn setup ok dann registriere android.net.conn.CONNECTIVITY_CHANGE
		
		//https://wrty.eu/bind/?ip=192.168.0.1&key=
		//ip
		//key		      
		
		Bundle ipextra = arg1.getExtras();
		if (ipextra != null) {
			String ip = ipextra.getString("ip");
			
			Context ctx = arg0.getApplicationContext();
			SharedPreferences settings = ctx.getSharedPreferences("UserInfo", 0);
			String domain_key = settings.getString("editText_domain_key", "");
			String server = settings.getString("editText_server", "https://wrty.eu/bind/");
			
			
			HttpResponse response = null;
			try {        
			        HttpClient client = new DefaultHttpClient();
			        HttpGet request = new HttpGet();
			        request.setURI(new URI(server + "?ip="+ip+"&key="+domain_key));
			        response = client.execute(request);
			    } catch (URISyntaxException e) {
			        e.printStackTrace();
			    } catch (ClientProtocolException e) {
			        // TODO Auto-generated catch block
			        e.printStackTrace();
			    } catch (IOException e) {
			        // TODO Auto-generated catch block
			        e.printStackTrace();
			    }
			Toast.makeText(arg0, "Bind response: " + response, Toast.LENGTH_SHORT).show();			
		}
	}

}
