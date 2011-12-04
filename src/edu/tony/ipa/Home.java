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

public class Home extends GDActivity{
	private static final int ACTION_BAR_LOCATE = 0;
	private final String[] items = {"7-11","MOS","M"};
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setActionBarContentView(R.layout.home);
        addActionBarItem(Type.Locate, ACTION_BAR_LOCATE);
        
        ImageButton ipaChan = (ImageButton) findViewById(R.id.imageButton1);
        
        ipaChan.setOnClickListener(new Button.OnClickListener()
        {
        	@Override
        	public void onClick(View v)
        	{ 
        		Intent i = new Intent();
      			i.setClass(Home.this, IPAChan.class);
      			startActivity(i);
        	}
        }); 
    }
    
    @Override
    public boolean onHandleActionBarItemClick(ActionBarItem item, int position) {
        switch(item.getItemId()) {
        case ACTION_BAR_LOCATE:
        	//checkin here
        	final AlertDialog.Builder builder = new AlertDialog.Builder(Home.this);
    		builder.setTitle("Check-In!").setItems(items, new DialogInterface.OnClickListener() {
    			public void onClick(DialogInterface dialog, int which) {
    				//checkinTextView.setText(items[which]);
    				Log.i("TEST", items[which]);
    			}
    		});
    		builder.show();
            break;
        default:
            return super.onHandleActionBarItemClick(item, position);
        }
        return true;
    }
}
