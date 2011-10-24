package com.tony.ipa.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


import android.app.Activity;
import android.net.ParseException;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ipatest extends Activity {

EditText acc,pass;
Button send;
TextView status;
@Override
public void onCreate(Bundle savedInstanceState) {
	
	super.onCreate(savedInstanceState);
	setContentView(R.layout.main);
//取得輸入資料
	acc=(EditText)findViewById(R.id.account);
	pass=(EditText)findViewById(R.id.pass);
	send=(Button)findViewById(R.id.send);
	status=(TextView)findViewById(R.id.status2);

	send.setOnClickListener(new OnClickListener() {
		public void onClick(View v){
			String account=acc.getText().toString(),
			password=pass.getText().toString();

			ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();

			nameValuePairs.add(new BasicNameValuePair("AccID",account));
			//nameValuePairs.add(new BasicNameValuePair("AccPass",password));
			
			DB db = new DB();
			try{
				ArrayList<HashMap<String, String>> result = db.DataSearch(nameValuePairs, "ipa_search");
				for(int i=0;i<result.size();i++){
					status.append(result.get(i).toString()+" ");
				}
				
			}catch(Exception e){
				Log.e("log_tag", "Error get data "+e.toString());				
			}
			
			//parse json data
			/*try{
			      JSONObject json_data = json_result.getJSONObject(0);
			      //test = (TextView) findViewById(R.id.textView1);
			      for(int i=0;i<json_result.length();i++){
			              JSONObject json_data2 = json_result.getJSONObject(i);
			              Log.i("log_tag","id: "+json_data2.getString("accountID")
			              );
			              //test.setText("id: "+json_data.getInt("MovieID"));
			      }
			}catch(JSONException e){
			      Log.e("log_tag", "Error parsing data "+e.toString());
				}*/
			

		}
	}); //end onclicklistener

} //end oncreate
} //end class