package edu.tony.ipa;

import java.util.ArrayList;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import greendroid.app.GDActivity;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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
    private EditText bDate = null;
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
		bDate=(EditText) findViewById(R.id.text8);
		
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
		if(bDate.getText().equals("")){
			warning.setText("birthday must type");
			verify = false;
		}
		
		if(verify == true){
			//加資料進DB
			
			DB db = new DB();
			Boolean accexist = false;
			try{
				ArrayList<NameValuePair> acconameValuePairs = new ArrayList<NameValuePair>();
				acconameValuePairs.add(new BasicNameValuePair("AccID",accountID.getText().toString()));
				ArrayList<JSONObject> result_acc = db.DataSearch(acconameValuePairs,"ipa_search");
				if(result_acc.size() != 0){
					accexist = true;
					warning.setText("此帳號已有人註冊");
				}
				
				if(accexist == false){
					ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
					nameValuePairs.add(new BasicNameValuePair("AccID",accountID.getText().toString()));
					nameValuePairs.add(new BasicNameValuePair("AccPass",password.getText().toString()));
					nameValuePairs.add(new BasicNameValuePair("FirstName",firstName.getText().toString()));
					nameValuePairs.add(new BasicNameValuePair("LastName",lastName.getText().toString()));
					nameValuePairs.add(new BasicNameValuePair("Email",email.getText().toString()));
					nameValuePairs.add(new BasicNameValuePair("Phone",phone.getText().toString()));
					nameValuePairs.add(new BasicNameValuePair("BDay",bDate.getText().toString()));

					ArrayList<JSONObject> result_a = db.DataSearch(nameValuePairs,"register");
					
					Log.e("log_act","size="+result_a.size());
					Intent i = new Intent();
			     	i.setClass(Register.this, IPAActivity.class);
			     	startActivity(i);
				}
			}
			catch(Exception e){
			Log.e("log_tag", "Error get data "+e.toString());				
			}	
			
			
			
		}
		
		
		
	}
}
