package edu.tony.ipa;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import greendroid.app.GDActivity;
import greendroid.widget.ActionBarItem;
import greendroid.widget.ActionBarItem.Type;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class Home extends GDActivity{


	private LocationManager locationManager;
	private String provider;
	private ArrayList<JSONObject> result;
	private DB db;
	


    @Override
    public void onCreate(Bundle savedInstanceState) {
    	final int ACTION_BAR_INFO = 0;
        super.onCreate(savedInstanceState);
        
        addActionBarItem(Type.LocateMyself, ACTION_BAR_INFO);
        setActionBarContentView(R.layout.home);

        
        ImageButton ipaChan = (ImageButton) findViewById(R.id.imageButton1);
        ImageButton lookBtn = (ImageButton) findViewById(R.id.imageButton2);
        ImageButton hotnewsBtn = (ImageButton) findViewById(R.id.imageButton3);
        
        
        
        
        ipaChan.setOnClickListener(new Button.OnClickListener()
        {
        	public void onClick(View v)
        	{ 
        		Intent i = new Intent();
      			i.setClass(Home.this, IPAChan.class);
      			startActivity(i);
        	}
        }); 
        
        lookBtn.setOnClickListener(new Button.OnClickListener()
        {
        	public void onClick(View v)
        	{ 
        		Intent j = new Intent();
      			j.setClass(Home.this, LookAround.class);
      			startActivity(j);
        	}
        }); 
        
        hotnewsBtn.setOnClickListener(new Button.OnClickListener()
        {
        	public void onClick(View v)
        	{ 
        		Intent j = new Intent();
      			j.setClass(Home.this, TabTestActivity.class);
      			startActivity(j);
        	}
        });
   
    }
     
    

	@Override
	public boolean onHandleActionBarItemClick(ActionBarItem item, int position) { 
		//actionbar's onclick
		switch(item.getItemId()){
		case 0:
			//****checkin here
			
			//自己的經緯度
			locationManager = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
			Criteria criteria = new Criteria();
			provider = locationManager.getBestProvider(criteria, false);
			Location location = locationManager.getLastKnownLocation(provider);
			double b = location.getLongitude();
			double a = location.getLatitude();
			Log.e("longitude", String.valueOf(b));
			Log.e("latitude", String.valueOf(a));
			result = new ArrayList<JSONObject>();
			db = new DB();
			//end自己的經緯度
			
			//Google's shop
			final String[] items = {};
			final List<String> Gshops = new ArrayList<String>();
			try{
				result = db.LocSearch(a,b);
				
				for(int i = 0; i < result.size(); i++){
					Gshops.add(i, result.get(i).getString("name"));
				}
			}
			catch(Exception e){
				Log.e("log_tag", "Error get data "+e.toString());				
			}
			//end Google's shop
			
			
			//建立下拉式選單 by Google's shop
			final AlertDialog.Builder builder = new AlertDialog.Builder(this);
			builder.setTitle("請選擇店家").setItems(Gshops.toArray(items), new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int which) {
					
 				    //選擇店家後，要去DB裡找有沒有這個店家
					String shopGet = Gshops.get(which);
					Double lat,lng;
					ArrayList<NameValuePair> location = new ArrayList<NameValuePair>();
					ArrayList<JSONObject> shop_loc = new ArrayList<JSONObject>();
					Log.e("check", shopGet);
					for(int i = 0; i < result.size(); i++){
						try {
							if(result.get(i).getString("name").equals(shopGet)){
								lat = result.get(i).getJSONObject("geometry").getJSONObject("location").getDouble("lat");
								lng = result.get(i).getJSONObject("geometry").getJSONObject("location").getDouble("lng");
								
								location.add(new BasicNameValuePair("Lng",lng.toString()));
								location.add(new BasicNameValuePair("Lat",lat.toString()));
								shop_loc = db.DataSearch(location,"shop_loc_search");
								
								for(int j=0;j<shop_loc.size();j++){
									Log.e("shop_id",shop_loc.get(j).getString("shopID"));
								}
							}
						}catch(Exception e){
							Log.e("log_tag", "Error get data "+e.toString());				
						}	
					}
					
					//----新增至checkin table----

					//用accountID找ipaID
					SharedPreferences settings = getSharedPreferences("Account", 0);
			        String user = settings.getString("username", "nouser");
			        Log.e("user", user);
			        try{
			        	ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
			        	nameValuePairs.add(new BasicNameValuePair("AccID",user));

			        	ArrayList<JSONObject> result_a = db.DataSearch(nameValuePairs,"ipa_search");
						Log.e("log_act","size="+result_a.size());
						for(int i=0;i<result_a.size();i++){
							Log.e("r_act",result_a.get(i).getString("ipaID"));
						}
					}
			        catch(Exception e){
			        	Log.e("log_tag", "Error get data "+e.toString());				
			        }	
					
				}
			});
			AlertDialog ad = builder.create();
			ad.show();
			break;
		default:
			return super.onHandleActionBarItemClick(item, position);
		}
		return true;
	}
}
    


