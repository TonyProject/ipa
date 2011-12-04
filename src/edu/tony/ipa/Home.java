package edu.tony.ipa;

import greendroid.app.GDActivity;
import greendroid.widget.ActionBarItem;
import greendroid.widget.ActionBarItem.Type;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class Home extends GDActivity{
	
	private TextView checkinTextView;
//	private final String[] items = {"7-11","MOS","M"};
//    private final AlertDialog.Builder builder = new AlertDialog.Builder(this);

    @Override
    public void onCreate(Bundle savedInstanceState) {
    	final int ACTION_BAR_INFO = 0;
        super.onCreate(savedInstanceState);
        
        addActionBarItem(Type.LocateMyself, ACTION_BAR_INFO);
        setActionBarContentView(R.layout.home);
        ImageButton ipaChan = (ImageButton) findViewById(R.id.imageButton1);
        ImageButton lookBtn = (ImageButton) findViewById(R.id.imageButton2);
        checkinTextView=(TextView) findViewById(R.id.checkinText);
        
        
        
        
        ipaChan.setOnClickListener(new Button.OnClickListener()
        {
        	public void onClick(View v)
        	{ 
        		Intent i = new Intent();
      			i.setClass(Home.this, IPAChan.class);
      			startActivity(i);
        	}
        }); 
        
        lookBtn.setOnClickListener(new Button.OnClickListener()
        {
        	public void onClick(View v)
        	{ 
        		Intent j = new Intent();
      			j.setClass(Home.this, Checkin.class);
      			startActivity(j);
        	}
        }); 
   
    }
    
 
    

	@Override
	public boolean onHandleActionBarItemClick(ActionBarItem item, int position) { 
		//actionbar's onclick
		switch(item.getItemId()){
		case 0:
			//checkin here
			
			final String[] items = {"7-11","MOS","M"};
			final AlertDialog.Builder builder = new AlertDialog.Builder(this);
			builder.setTitle("½Ð¿ï¾Ü©±®a").setItems(items, new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int which) {
					checkinTextView.setText(items[which]);
					
				}
			});
			AlertDialog ad = builder.create();
			ad.show();
			break;
		default:
			return super.onHandleActionBarItemClick(item, position);
		}
		return true;
	}
    

}
