package edu.tony.ipa;

import greendroid.app.GDActivity;
import greendroid.widget.ActionBarItem;
import greendroid.widget.ActionBarItem.Type;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class Home extends GDActivity{
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	final int ACTION_BAR_INFO = 0;
        super.onCreate(savedInstanceState);
        
        addActionBarItem(Type.LocateMyself, ACTION_BAR_INFO);
        setActionBarContentView(R.layout.home);
        ImageButton ipaChan = (ImageButton) findViewById(R.id.imageButton1);
        
        
        
        ipaChan.setOnClickListener(new Button.OnClickListener()
        {
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
		// TODO Auto-generated method stub
		return super.onHandleActionBarItemClick(item, position);
	}
}
