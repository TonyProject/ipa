package edu.tony.ipa;

import greendroid.app.GDActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class Register extends GDActivity {

    private TextView warning = null;
    private EditText accountID = null;
    private EditText password = null;
    private EditText firstName = null;
    private EditText lastName = null;
    private EditText email = null;
    private EditText phone = null;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setActionBarContentView(R.layout.register);
	}
	 
	public void doneOnClick(View v){
		warning=(TextView) findViewById(R.id.errortext);
		
		
		
		Intent i = new Intent();
     	i.setClass(Register.this, IPAActivity.class);
     	startActivity(i);
	}
}
