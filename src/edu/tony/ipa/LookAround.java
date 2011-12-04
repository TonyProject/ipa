package edu.tony.ipa;

import android.app.Activity;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ZoomControls;
import java.util.List;


import edu.tony.ipa.R;
import greendroid.app.GDMapActivity;

import com.google.android.maps.MapActivity;
import com.google.android.maps.MapView;
import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapController;
import com.google.android.maps.MyLocationOverlay;
import com.google.android.maps.Overlay;

import com.google.android.maps.ItemizedOverlay;
import com.google.android.maps.OverlayItem;
import android.graphics.drawable.Drawable;
import android.content.Context;
import java.util.ArrayList;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import android.content.DialogInterface;
import android.app.AlertDialog;


public class LookAround extends GDMapActivity {
    /** Called when the activity is first created. */
	private LocationManager locationManager;
	private String provider;
	
    LinearLayout linearLayout;
	MapView mapView;
	MapController mapController;
	ZoomControls mZoom;
	@SuppressWarnings("deprecation")	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setActionBarContentView(R.layout.look_around);
        
        linearLayout = (LinearLayout) findViewById(R.id.zoomview);
        mapView = (MapView) findViewById(R.id.mapview);
        mZoom = (ZoomControls) mapView.getZoomControls();
        linearLayout.addView(mZoom);
        
        mapController =mapView.getController();	//設定控制的map物件
		//get location
		// Get the location manager
		locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
		// Define the criteria how to select the locatioin provider -> use
		// default
	
		setupMap();
              
    }
	
	MyLocationOverlay mylayer;
	
	private void setupMap() {
		//加入定位層
		List<Overlay> overlays = mapView.getOverlays();	//在地圖上建立一個座標層
		mylayer = new MyLocationOverlay(this, mapView);	//建立定位層，並取得目前座標位置
		mylayer.runOnFirstFix(new Runnable() {		//每次更新時要執行的動作
			public void run() {
				mapView.setSatellite(true) ;//設定地圖檢示模式
				//.setTraffic(true)：一般地圖
				//.setSatellite(true)：衛星地圖
				//.setStreetView：街景圖
 
				mapController.setZoom(17);	//設定放大倍率1(地球)-21(街景)
				mapController.animateTo(mylayer.getMyLocation());	//指定地圖中央點為目前位置
			}
		});
		mapView.setBuiltInZoomControls(true);	//加入縮放控制
		
		//catch own position
		Criteria criteria = new Criteria();
		provider = locationManager.getBestProvider(criteria, false);
		Location location = locationManager.getLastKnownLocation(provider); 
		Double own_lat = location.getLatitude();
		Double own_lng = location.getLongitude();
		
		//找附近店家資訊(all) + transform
		ArrayList<Position_transform> pos_array = new ArrayList<Position_transform>();
		ArrayList<String> stores_in_db = new ArrayList<String>();
		DB db = new DB();
		try{
			//Double a =own_lat, b=own_lng;
			Double a =25.019943, b=121.542353;
			ArrayList<JSONObject> result = db.LocSearch(a,b);
			
			for(int i=0;i<result.size();i++){
				Position_transform pos = 
					new Position_transform(	result.get(i).getString("name"), 
											result.get(i).getJSONObject("geometry").getJSONObject("location").getDouble("lat"), 
											result.get(i).getJSONObject("geometry").getJSONObject("location").getDouble("lng")) ;
				pos_array.add(pos);
			}
			//Check for correctness
			/*
			for(int i=0;i<pos_array.size();i++){
				Log.e("pos_name = ",pos_array.get(i).location_name);
				Log.e("pos_lat = ",pos_array.get(i).location_lat.toString());
				Log.e("pos_lon = ",pos_array.get(i).location_lon.toString());
			}*/
			
			//Check 是否為我們的合作廠商
						
			ArrayList<NameValuePair> location_1 = new ArrayList<NameValuePair>();
			ArrayList<JSONObject> shop_loc = new ArrayList<JSONObject>();
			for(int i=0; i<pos_array.size();i++){
				location_1.add(new BasicNameValuePair("Lng",pos_array.get(i).location_lon.toString()));
				location_1.add(new BasicNameValuePair("Lat",pos_array.get(i).location_lat.toString()));
				shop_loc = db.DataSearch(location_1,"shop_loc_search");
				for(int j=0;j<shop_loc.size();j++){
					//Log.e("shop_id",shop_loc.get(j).getString("shopID"));
					stores_in_db.add(shop_loc.get(j).getString("shopID"));
				}
			}
			//check for Stores_in_db是否寫入
			/*
			for(int i=0; i<stores_in_db.size(); i++){
				Log.e("shop_id = ",stores_in_db.get(i));
			}*/
			
		}
		catch(Exception e){
			Log.e("log_tag", "Error get data "+e.toString());				
		}	
				
		//宣告地標圖示from內建圖庫
		Drawable point_star = getResources().getDrawable(android.R.drawable.star_on);
		//設定星星圖示大小
		point_star.setBounds(0, 0, point_star.getMinimumWidth(), point_star.getMinimumHeight());
		Landmark myLandmark = new Landmark(point_star, this);
		myLandmark.getpostions(stores_in_db);
		
		
		overlays.add(myLandmark);	//將地標層加入地圖座標層中
		overlays.add(mylayer);	//將定位層加入地圖座標層中
	}
 
	@Override
	protected void onResume() {
		super.onResume();
		mylayer.enableMyLocation();	//進入頁面時開始更新定位資訊
	}
 
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		mylayer.disableMyLocation();	//離開頁面時停止更新
	}

	@Override
	protected boolean isRouteDisplayed() {
		return false;
	}
		
	

	
}

