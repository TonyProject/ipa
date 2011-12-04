package edu.tony.ipa;

import greendroid.app.GDActivity;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Checkin extends GDActivity {

	private Button checkinBtn;
	private TextView checkinView;

    
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setActionBarContentView(R.layout.checkin);
	     
	    checkinBtn = (Button) findViewById(R.id.button1);
	    checkinView = (TextView) findViewById(R.id.checkinText);
	    
	    final String[] items = {"7-11","MOS","KFC"};
	    final AlertDialog.Builder builder = new AlertDialog.Builder(Checkin.this);
	    
	    checkinBtn.setOnClickListener(new Button.OnClickListener()
        {
        	public void onClick(View v)
        	{ 
//        		builder.setTitle("½Ð¿ï¾Ü©±®a").setItems(items, new DialogInterface.OnClickListener() {
//        			
//        			public void onClick(DialogInterface dialog, int which) {
//        				checkinView.setText(items[which]);
//        				
//        			}
//        		});
        		checkinView.setText("1234");
        	}
        }); 
	    
	  
	}
	

	
}
