package eu.wrty.eu.android.dnsupdater;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

public class IpUpdate_IS extends IntentService {

	public IpUpdate_IS() {
		super("IpUpdate_IS");
	}
	
	

	@Override
	public void onStart(Intent intent, int startId) {
		// TODO Auto-generated method stub
		super.onStart(intent, startId);
		Toast.makeText(this, "Intent Detected2.", Toast.LENGTH_LONG).show();
	}



	@Override
	protected void onHandleIntent(Intent arg0) {
		// TODO Auto-generated method stub
		Context ctx = this;
		
		Toast.makeText(ctx, "Intent Detected1.", Toast.LENGTH_LONG).show();

	    //optimierung wenn kein setup dann de registriere: android.net.conn.CONNECTIVITY_CHANGE
		//wenn setup ok dann registriere android.net.conn.CONNECTIVITY_CHANGE
		
		//https://wrty.eu/bind/?ip=192.168.0.1&key=
		//ip
		//key		      
		
		Bundle ipextra = arg0.getExtras();
		if (ipextra != null) {
			String ip = ipextra.getString("ip");
			
			SharedPreferences settings = ctx.getSharedPreferences("UserInfo", 0);
			String domain_key = settings.getString("editText_domain_key", "");
			String server = settings.getString("editText_server", "");
			
			if (domain_key == "" || server == ""){
				Toast.makeText(ctx, "Setup incomplete", Toast.LENGTH_LONG).show();
				return;
			}
			
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
			Toast.makeText(ctx, "Bind response: " + response, Toast.LENGTH_LONG).show();			
		}
	}

}
