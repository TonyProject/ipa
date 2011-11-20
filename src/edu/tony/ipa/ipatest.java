package edu.tony.ipa;

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
			//放google place的資料
			ArrayList<JSONObject> result = db.LocSearch(a,b);
			//要和db對照的資料
			ArrayList<NameValuePair> location = new ArrayList<NameValuePair>();
			ArrayList<JSONObject> shop_loc = new ArrayList<JSONObject>();
			Log.e("log_loc","size="+result.size());
			String name;
			Double lat,lng;
			for(int i=0;i<result.size();i++){
				name = result.get(i).getString("name");
				lat = result.get(i).getJSONObject("geometry").getJSONObject("location").getDouble("lat");
				lng = result.get(i).getJSONObject("geometry").getJSONObject("location").getDouble("lng");
				Log.e("r_loc",name);
				Log.e("r_loc","lat "+ lat);
				Log.e("r_loc","lng "+ lng);
				
				//查詢地點是否是和我們合作的商家
				//location.add(new BasicNameValuePair("Lat",lat.toString()));
				location.add(new BasicNameValuePair("Lng",lng.toString()));
				location.add(new BasicNameValuePair("Lat",lat.toString()));
				shop_loc = db.DataSearch(location,"shop_loc_search");
				for(int j=0;j<shop_loc.size();j++){
					Log.e("shop_id",shop_loc.get(j).getString("shopID"));
				}
			}
	
	//找優惠資訊(activity)
			ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
			nameValuePairs.add(new BasicNameValuePair("ShopID","1000"));

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

		
		

} //end oncreate
} //end class