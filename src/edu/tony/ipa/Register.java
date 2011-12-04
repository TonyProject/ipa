package edu.tony.ipa;

import greendroid.app.GDActivity;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class Register extends Activity {

    private TextView warning = null;
    private EditText accountID = null;
    private EditText password = null;
    private EditText firstName = null;
    private EditText lastName = null;
    private EditText email = null;
    private EditText phone = null;
    private Boolean verify = true;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.register);
	}
	 
	public void doneOnClick(View v){
		warning=(TextView) findViewById(R.id.errortext);
		accountID=(EditText) findViewById(R.id.text2);
		password=(EditText) findViewById(R.id.text3);
		firstName=(EditText) findViewById(R.id.text4);
		lastName=(EditText) findViewById(R.id.text5);
		email=(EditText) findViewById(R.id.text6);
		phone=(EditText) findViewById(R.id.text7);
		
		if(accountID.getText().equals("") || accountID.length() < 4){
			warning.setText("username must type at least 4 character");
			verify = false;
		}
		if(password.getText().equals("") || password.length() < 6){
			warning.setText("password must type at least 6 character");
			verify = false;
		}
		if(firstName.getText().equals("")){
			warning.setText("firstName must type");
			verify = false;
		}
		if(lastName.getText().equals("")){
			warning.setText("lastName must type");
			verify = false;
		}
		if(email.getText().equals("")){
			warning.setText("email must type");
			verify = false;
		}
		if(phone.getText().equals("")){
			warning.setText("phone must type");
			verify = false;
		}
		
		if(verify == true){
			//加資料進DB
			
			Intent i = new Intent();
	     	i.setClass(Register.this, IPAActivity.class);
	     	startActivity(i);
		}
		
		
		
	}
}
