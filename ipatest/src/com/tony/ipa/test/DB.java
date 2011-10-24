package com.tony.ipa.test;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

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

public class DB {
	
private JSONArray jArray = null;
private String result = null;
private HttpResponse response=null;


public ArrayList<HashMap<String, String>> DataSearch (ArrayList<NameValuePair> nameValuePairs,String page) {
	ArrayList<HashMap<String, String>> data=new ArrayList<HashMap<String, String>>(); 
	HashMap<String, String> pairs = new HashMap<String, String>();
	//http post
	try{
	    HttpClient httpclient = new DefaultHttpClient();
	    HttpPost httppost = new HttpPost("http://140.112.107.29/"+page+".php");
		httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
		response = httpclient.execute(httppost);
			}catch(Exception e){
			      Log.e("log_tag", "Error in http connection "+e.toString());
			}
	//convert response to string
	try{
		result = EntityUtils.toString(response.getEntity());

		}catch(Exception e){
		      Log.e("log_tag", "Error converting result "+e.toString());
		}
		
	if (result.equals("null")||result.equals("")){
		Log.v("url request", "not found");
		jArray = null;
	}
	else{
		Log.v("url request", "string:"+result);		
			//parse json data
		try{
				JSONArray jArray = new JSONArray(result);
			    /*JSONObject json_data = jArray.getJSONObject(0);
			    for(int i=0;i<jArray.length();i++){
			              JSONObject json_data2 = jArray.getJSONObject(i);
			              //Log.i("log_tag","id: "+json_data2.getString("accountID")
			              //);
			              //test.setText("id: "+json_data.getInt("MovieID"));
			      }*/
				

	

				Log.e("log_tag","length "+jArray.length());
				for (int i = 0; i < jArray.length(); i++) {
					JSONObject j = jArray.optJSONObject(i);
					Iterator it = j.keys();
					while (it.hasNext()) {
						String n = (String) it.next();
						pairs.put(n,j.getString(n));
						Log.e("log_tag", "put in pairs "+n);
					}
					data.add(pairs);
				}
		}catch(JSONException e){
			Log.e("log_tag", "Error parsing data "+e.toString());
		}
	
	}//end else
	return data;
	}//end function

}//end class

