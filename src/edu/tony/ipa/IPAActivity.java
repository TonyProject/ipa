package edu.tony.ipa;

import greendroid.app.GDActivity;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class IPAActivity extends GDActivity {
    /** Called when the activity is first created. */
	//test
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setActionBarContentView(R.layout.main);
        
        /*//For testing
        Intent i = new Intent();
		i.setClass(IPAActivity.this, IPAChan.class);
		startActivity(i);*/
    }
}