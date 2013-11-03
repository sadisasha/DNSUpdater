package eu.wrty.eu.android.dnsupdater;

import android.os.Bundle;
import android.app.Activity;
import android.content.SharedPreferences;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {

	Button b1;
	EditText editText_domain_key;
	EditText editText_server;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        editText_domain_key = (EditText) findViewById(R.id.editText_domain_key);        
        editText_domain_key.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {
              // you can call or do what you want with your EditText here
	        	SharedPreferences settings = getSharedPreferences("UserInfo", 0);
	        	SharedPreferences.Editor editor = settings.edit();
	        	editor.putString("editText_domain_key",editText_domain_key.getText().toString());
	        	editor.commit();
            }
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            public void onTextChanged(CharSequence s, int start, int before, int count) {}
         });        
        
        editText_server = (EditText) findViewById(R.id.editText_server);        
        editText_server.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {
              // you can call or do what you want with your EditText here
	        	SharedPreferences settings = getSharedPreferences("UserInfo", 0);
	        	SharedPreferences.Editor editor = settings.edit();
	        	editor.putString("editText_server",editText_server.getText().toString());
	        	editor.commit();
            }
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            public void onTextChanged(CharSequence s, int start, int before, int count) {}
         });
        
        
        b1 = (Button) findViewById(R.id.test_button);
        b1.setOnClickListener(new OnClickListener()
	        {
		        public void onClick(View v)
		        {
		        	//start server dienst und empfange hier result vom server dienst bzw server sendet spezielel braodcast
			        Toast msg = Toast.makeText(getBaseContext(),
"start server dienst und empfange hier result vom server dienst bzw server sendet spezielel braodcast", Toast.LENGTH_SHORT);
			        msg.show();
				}
			});
        }

    //wenn setup complett registriere braodcast receiver

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
}
