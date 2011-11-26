package edu.tony.ipa;

import greendroid.app.GDActivity;

import java.util.ArrayList;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class IPAActivity extends GDActivity {
    /** Called when the activity is first created. */
	
	private EditText username=null;  
    private EditText password=null;
    private TextView warning=null;
    private Boolean verify = false;
    private String user = null;
    private String userpass = null;
    
	
	//test
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setActionBarContentView(R.layout.main);
        
        username=(EditText) findViewById(R.id.editText1);
        password=(EditText) findViewById(R.id.editText2);
        warning=(TextView) findViewById(R.id.text1); 
        
        SharedPreferences settings = getSharedPreferences("Account", 0);
        user = settings.getString("username", "nouser");
        userpass = settings.getString("password", "nopass");
        
        if(!user.equals("nouser")){
        	username.setText(user);
        	if(!userpass.equals("nopass")){
            	password.setText(userpass);
//            	verify = true; //之前已經登入過了
            }
            else{
            	password.setText("");
            }
        }
        else{
        	username.setText("");
        }
        
        
        
        
//        //For testing
//        Intent i = new Intent();
//		i.setClass(IPAActivity.this, Home.class);
//		startActivity(i);
    }
    
    public void loginOnClick(View v){
    	
    	ArrayList<JSONObject> result_a = null;
    	
    	if(username.length() != 0 && password.length() != 0){
    		
    		if(username.length() < 4){
    			warning.setText("username at least 4 characters");
    		}
    		else{
    			
    			if(password.length() < 6){
    				warning.setText("password at least 6 characters");
    			}
    			else{
    				
    				if(username.getText().toString().equals(user) && password.getText().toString().equals(userpass)){
    					verify = true;//之前已經登入過，因此可確定已註冊過!
    				}
    				
    				//帳號密碼格式都對
    		    	if(verify == false){ //第一次登入
    		    		
    		    		//確認此人已經註冊
    		        	DB db = new DB();
    		        	try{
    		        		
    		        	  	ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
    		        	  	Log.e("test", username.getText().toString());
    		        	  	
    		        		nameValuePairs.add(new BasicNameValuePair("AccID",username.getText().toString()));
    		        		nameValuePairs.add(new BasicNameValuePair("AccPass",password.getText().toString()));
    		        		result_a = db.DataSearch(nameValuePairs,"accountcheck");
    		    			
    		        		
    		        		Log.i("log_act","size="+result_a.size());
    		    			for(int i=0;i<result_a.size();i++){
    		    				Log.e("r_act",result_a.get(i).getString("email"));
    		    			}
    		    		}
    		    		catch(Exception e){
    		    		Log.e("log_tag", "Error get data "+e.toString());				
    		    		}
    		        	
    		        	if(result_a.size() == 0){
    		        		warning.setText("you have to register first");
    		        	}
    		        	else{
    		        		//儲存帳號密碼
        		        	SharedPreferences settings = getSharedPreferences("Account", 0);
        		        	SharedPreferences.Editor editor = settings.edit();
        		        	editor.putString("username", username.getText().toString());
        		        	editor.putString("password", password.getText().toString());
        		        	editor.commit();
        		        	verify = true;
    		        	}
    		        	
    		        	
    		        	
    		    	}
    		    	else{
    		    		Intent i = new Intent();
        	        	i.setClass(IPAActivity.this, Home.class);
        	        	startActivity(i);
    		    	}
    				
    			}
    		}
    		
    	}
    	else{
    		warning.setText("plz type username and password");
    	}	
    }
    
    public void registerOnClick(View v){
    	Intent i = new Intent();
    	i.setClass(IPAActivity.this, Register.class);
    	startActivity(i);
    }
    
 
    
}