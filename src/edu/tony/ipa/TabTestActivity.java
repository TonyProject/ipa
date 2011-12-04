package edu.tony.ipa;

import greendroid.app.GDTabActivity;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import android.app.TabActivity;
import android.content.Context;
import android.content.Intent;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TabHost;
import android.widget.TextView;

public class TabTestActivity extends GDTabActivity{
    /** Called when the activity is first created. */
	private LinearLayout linearLayout1;
	private TextView title;
	private ArrayList<JSONObject> result_a;
	private DB db;
	private List<String> activityDetailList;
	private List<String> activityList;
	private LocationManager locationManager;
	private String provider;
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.head);
        linearLayout1 = (LinearLayout) findViewById(R.id.layout1);
        activityList = new ArrayList<String>();
        activityDetailList = new ArrayList<String>();
        //title = (TextView)findViewById(R.id.LookAroundtitle);
        //linearLayout = (LinearLayout) findViewById(R.id.ttt);
        
      //  locationManager = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
      //  Criteria criteria = new Criteria();
      //  provider = locationManager.getBestProvider(criteria, false);
      //  Location location = locationManager.getLastKnownLocation(provider);
        //locationManager = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
        //Location location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
      //  double b = location.getLongitude();
        //double a = 5.5;
        
      //  double a = location.getLatitude();
        //String.valueOf(a);
        //Log.e("long", String.valueOf(a));
        
        TextView test = (TextView)findViewById(R.id.text1);
        
        //test.setText();
        //linearLayout.addView(R.id.LookAroundtitle);
        //linearLayout.addView(findViewById(R.id.LookAroundtitle));
        TabHost th = getTabHost();
        
        LayoutInflater.from(this).inflate(R.layout.tab, th.getTabContentView(),true);
        /*
        db = new DB();
        try{
            
            ArrayList<JSONObject> result = db.LocSearch(a,b);
    		Log.e("log_loc","size="+result.size());
    		for(int i=0;i<result.size();i++){
    			Log.e("r_loc",result.get(i).getString("name"));
    			Log.e("r_loc","lat "+
    				result.get(i).getJSONObject("geometry").getJSONObject("location").getDouble("lat"));
    			Log.e("r_loc","lng "+
    					result.get(i).getJSONObject("geometry").getJSONObject("location").getDouble("lng"));
    		}
            }
            catch(Exception e){
        		Log.e("log_tag", "Error get data "+e.toString());				
        		}

        getActivity();
        */
        //addBtn(result_a.size(),activityList);
        
        Intent intent = new Intent();
        intent.setClass(TabTestActivity.this,Shop.class);
        
        Intent intent2 = new Intent();
        intent2.setClass(TabTestActivity.this,Honor.class);
        th.addTab(th.newTabSpec("a").setIndicator("商家訊息").setContent(intent));
        th.addTab(th.newTabSpec("b").setIndicator("王者天下").setContent(intent2));
        th.addTab(th.newTabSpec("c").setIndicator("大聲公").setContent(R.id.layout3));
        
        
        //linearLayout.addView(th);
    }
	/*
	 public View createTabContent(String tag){
	     ListView lv = new ListView(this);
	     return lv;
		  
	 }*/
	 public void getActivity(){
	    	try{
	        	ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
				nameValuePairs.add(new BasicNameValuePair("ShopID","100001"));
				

				result_a = db.DataSearch(nameValuePairs,"activity_search");
				
				
				
				Log.e("log_act","size="+result_a.size());
				for(int i=0;i<result_a.size();i++){
					Log.e("r_act",result_a.get(i).getString("activityName"));
					activityList.add(result_a.get(i).getString("activityName"));
					activityDetailList.add(result_a.get(i).getString("description"));
				}
				
				
	        	
	        }catch(Exception e){
	    		Log.e("log_tag", "Error get data "+e.toString());				
	    		}	
	        
	    }
	 
	   public void addBtn(int count,List<String> activityName){
	    	
	    	if(count > 0){
	    		int k;
	    		for(int i = 0; i < count; i++){
	    			Button tempBtn = new Button(this);
	    			tempBtn.setText(activityName.get(i));
	    			tempBtn.setId(i);
	    			//還要寫按Button的動作
	    			tempBtn.setOnClickListener(new View.OnClickListener() {
	    	             public void onClick(View v) {
	    	            	 //int id = v.getId();
	    	            	 //setActivityDetail(id);
	    	             }
	    	         });
	    			
	    			linearLayout1.addView(tempBtn, 300, 100);
	    		}
	    	}
	    	
	    }
}