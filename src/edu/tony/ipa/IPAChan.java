package edu.tony.ipa;

import java.util.ArrayList;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

public class IPAChan extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    	DB db = new DB();
    	ArrayList<NameValuePair> ipa = new ArrayList<NameValuePair>();
    		ipa.add(new BasicNameValuePair("AccID","irene3119"));
    		ArrayList<JSONObject> result_a = db.DataSearch(ipa,"ipa_search");
    		Log.i("TEST", "go go go"+result_a.toString());
    		for(int i=0;i<result_a.size();i++){
    			try {
	                Log.i("r_act",result_a.get(i).getString("ipaID"));
                } catch (JSONException e) {
	                // TODO Auto-generated catch block
	                e.printStackTrace();
                }
    		}
    }
}
