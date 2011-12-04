package edu.tony.ipa;

import java.util.ArrayList;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class ShopDetail extends Activity {
	/** Called when the activity is first created. */
	private DB db;
	private ArrayList<JSONObject> result_s;
	private TextView shopname;
	private TextView shopaddr;
	private TextView shopdes;
	private TextView shopphone;
	private TextView shopbranch;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		 super.onCreate(savedInstanceState);
	        setContentView(R.layout.shopdetail);
	        
	        shopname = (TextView)findViewById(R.id.shopname);
	        shopbranch = (TextView)findViewById(R.id.shopbranch);
	        shopaddr = (TextView)findViewById(R.id.shopaddr);
	        shopphone = (TextView)findViewById(R.id.shopphone);
	        shopdes = (TextView)findViewById(R.id.shopdes);
	        
	        
	        db = new DB();
	        Bundle bundle = this.getIntent().getExtras();
	        
	        try{
	        	ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
				nameValuePairs.add(new BasicNameValuePair("ShopID", bundle.getString("ID")));
				
				result_s = db.DataSearch(nameValuePairs,"shop_search");
				
				Log.e("log_act","size="+result_s.size());
				Log.e("r_act",result_s.get(0).getString("shopName"));
				Log.e("r_act",result_s.get(0).getString("branch"));
				Log.e("r_act",result_s.get(0).getString("address"));
				Log.e("r_act",result_s.get(0).getString("phone"));
				Log.e("r_act",result_s.get(0).getString("description"));
				
				shopname.setText(result_s.get(0).getString("shopName"));
				shopbranch.setText(result_s.get(0).getString("branch"));
				shopaddr.setText(result_s.get(0).getString("address"));
				shopdes.setText(result_s.get(0).getString("description"));
				shopphone.setText(result_s.get(0).getString("phone"));
				
				
				

	        }catch(Exception e){
	    		Log.e("log_tag", "Error get data "+e.toString());				
	    		}
	        
	}
}
