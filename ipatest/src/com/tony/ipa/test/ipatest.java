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

	
	DB db = new DB();
	//找附近店家資訊
	try{
			double a =25.019943, b=121.542353;
			ArrayList<JSONObject> result = db.LocSearch(a,b);
			Log.e("log_loc","size="+result.size());
			for(int i=0;i<result.size();i++)
				Log.e("r_loc",result.get(i).getString("name"));
		
	
	//找優惠資訊(activity)
			ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
			nameValuePairs.add(new BasicNameValuePair("ShopID","100000"));

			ArrayList<JSONObject> result_a = db.DataSearch(nameValuePairs,"activity_search");
			Log.e("log_act","size="+result_a.size());
			for(int i=0;i<result_a.size();i++){
				Log.e("r_act",result_a.get(i).getString("activityName"));
			}
			}
		catch(Exception e){
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
				}
			*/
		
		

} //end oncreate
} //end class