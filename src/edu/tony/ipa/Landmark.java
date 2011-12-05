package edu.tony.ipa;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.util.Log;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.ItemizedOverlay;
import com.google.android.maps.OverlayItem;

/*------設定地圖地標層-----*/
class Landmark extends ItemizedOverlay <OverlayItem> {
	private List<OverlayItem> items = new ArrayList<OverlayItem>();
	private Context context;	//主程式
	public Landmark(Drawable defaultMarker) {
		super(defaultMarker);
	}
	public void getpostions(ArrayList<String> stores_in_db){
		
		DB db = new DB();
		try{
		//找優惠資訊(activity)
			ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
			//GeoPoint point[] = new GeoPoint[stores_in_db.size()];
			Log.e("stores_in_db","size"+stores_in_db.size());
			for(int i=0;i<stores_in_db.size();i++){
				//Log.e("stores_in_db","now"+i);
				
				
				nameValuePairs.add(new BasicNameValuePair("ShopID",stores_in_db.get(i)));
				ArrayList<JSONObject> result_a = db.DataSearch(nameValuePairs,"shop_search");
				
				GeoPoint point = new GeoPoint(
					(int)(result_a.get(0).getDouble("latitude") * 1000000),
					(int)(result_a.get(0).getDouble("longitude") * 1000000)
				);
				
				//Log.e("r_act",result_a.get(i).getString("shopName"));
				
				items.add(new OverlayItem(point, result_a.get(0).getString("shopName"), result_a.get(0).getString("description")));
			
			}
		}
		catch(Exception e){
			Log.e("log_tag", "Error get data "+e.toString());				
		}	
		//parse json data
		
		populate();	//自動完成所有處理程序，呼叫createItem取得所有地標
		
	}
	
	public Landmark(Drawable defaultMarker, Context context) {
		super(defaultMarker);
		this.context = context;
		
	}
 
	@Override
	protected OverlayItem createItem(int i) {
		// TODO Auto-generated method stub
		return items.get(i);	//取得第i個地標
	}
 
	@Override
	public int size() {
		return items.size();	//地標總數量，會被populate()呼叫
	}
 
	@Override
	protected boolean onTap(int index) {	//點擊地標時的反應
		//彈出視窗
		AlertDialog.Builder builder = new AlertDialog.Builder(context);	//顯示於主程式main
		builder.setTitle(items.get(index).getTitle());	//加入標題
		builder.setMessage(items.get(index).getSnippet());	//加入說明
		
		
		
		//builder.setView(arg0);   //要連謝采璇的!
		 
		builder.setPositiveButton("OK",
			new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int which) {}
			}
		);
		builder.show();
		return super.onTap(index);
	}

}