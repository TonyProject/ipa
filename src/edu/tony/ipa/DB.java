package edu.tony.ipa;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpRequestFactory;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.HttpResponseException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
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
	
private String result = null;
private HttpResponse response=null;

private static final String PLACES_SEARCH_URL =  "https://maps.googleapis.com/maps/api/place/search/json?";
private static final String API_KEY = "AIzaSyCm-5HSgkhLKgWjXV6OgbhpyqJaRxN--JA";

/*******
 * 
 * @param latitude , longitude
 * @return nearby places
 */
 public ArrayList<JSONObject> LocSearch(double latitude,double longitude) throws Exception {
	 //ArrayList<HashMap<String, String>> data=new ArrayList<HashMap<String, String>>(); 
	 ArrayList<JSONObject> data=new ArrayList<JSONObject>();
	 //HashMap<String, String> pairs = new HashMap<String, String>();
	 try{
		HttpClient httpclient = new DefaultHttpClient();
		HttpGet httpget = new HttpGet(PLACES_SEARCH_URL+"location="+latitude+","+longitude+
				"&radius=500&sensor=false&key="+API_KEY);
		response = httpclient.execute(httpget);		
		}catch(Exception e){
				Log.e("log_tag", "Error in http connection "+e.toString());
				}
	 //convert response to string
	try{
		result = EntityUtils.toString(response.getEntity());
		Log.e("url request", "string:"+result);	
			}catch(Exception e){
			      Log.e("log_tag", "Error converting result "+e.toString());
			}
	//parse json data
	try{
		JSONObject json_data = new JSONObject(result);
		String status=json_data.getString("status");
		if("OK".equals(status)){
			//Log.e("log_tag", "ok");
			JSONArray jArray = json_data.getJSONArray("results");
			//Log.e("log_tag","length "+jArray.length());
			for (int i = 0; i < jArray.length(); i++) {
					JSONObject j = jArray.optJSONObject(i);
					//Log.e("log_tag",j.getString("name"));
					data.add(j);
					}
		}
		else if("ZERO_RESULTS".equals(status)){
			Log.e("log_tag", "ZERO_RESULTS");
		}
		else{
			Log.e("log_tag", status);
		}
			}catch(JSONException e){
				Log.e("log_tag", "Error parsing data "+e.toString());
			}

	 return data;
 }
 
/*******
 * 
 * @param nameValuePairs
 * @param page 
 * 		accountcheck :  AccID , AccPass
 * 		ipa_search	 :	AccID
 * 		mycoupon_search	: AccID
 * 		myhonor_search	: IpaID
 * 		closet_search	: IpaID , type
 *      shop_search		: ShopID		// shop info
 *      activity_search	: ShopID		
 *      shop_loc_search : Lat,Lng      
 *		king_search 	:				//List all the kings			
 *      shop_search		: ShopID		
 *      activity_search	: ShopID		 
 *      shop_loc_search : Lat,Lng        
 *		king_search 	:				
 *		checkin			: IpaID,Lat,Lng,ShopID 
 *		register		: AccID,AccPass,FirstName,LastName,Email,Phone,BDay	
 *		speak_add		: AccID,Lat,Lng,Message 
 *		friend_search	: Lat,Lng      //who checked in this location (in 1 day)
 *		speak_search	: Lat,Lng		//who spoke in this location (in 1 day)
 *		honor_search	: HonorID		//who get this honor
 *		like			: IpaID			//like this person
 * @return ArrayList<JSONObject>
 */

public ArrayList<JSONObject> DataSearch (ArrayList<NameValuePair> nameValuePairs,String page) {
	ArrayList<JSONObject> data=new ArrayList<JSONObject>(); 
	//HashMap<String, String> pairs = new HashMap<String, String>();
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
		
			
	//parse json data
	try{
		Log.e("url request", "string:"+result.toString());
		JSONArray jArray = new JSONArray(result);
		JSONObject jOb = jArray.optJSONObject(0);
		//Log.e("log_tag","length "+jArray.length());
		//Log.e("log_tag",jArray.optJSONObject(0).toString());
		if("false".equals(jOb.get("output")))
			Log.e("log_tag","false");
		else{
			Log.e("Log_tag","true");
			if(jOb.has("result")){
			jArray = jOb.getJSONArray("result");
			//Log.e("log_tag",jArray.toString());
			for (int i = 0; i < jArray.length(); i++) {
				JSONObject j = jArray.optJSONObject(i);
				data.add(j);
			}
			}
		}
	}catch(JSONException e){
		Log.e("log_tag", "Error parsing data "+e.toString());
	}

	return data;
	}//end function

}//end class

