package edu.tony.ipa;

import greendroid.app.GDActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class IPAActivity extends GDActivity {
    /** Called when the activity is first created. */
	//test
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setActionBarContentView(R.layout.home);
        ImageButton ipaChan = (ImageButton) findViewById(R.id.imageButton1);
        
        ipaChan.setOnClickListener(new Button.OnClickListener()
        {
          @Override
          public void onClick(View v)
          { 
        	  	Intent i = new Intent();
      			i.setClass(IPAActivity.this, IPAChan.class);
      			startActivity(i);
          }
        }); 
    }
    
}