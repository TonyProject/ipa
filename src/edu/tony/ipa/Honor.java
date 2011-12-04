package edu.tony.ipa;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

public class Honor extends Activity {
	/** Called when the activity is first created. */
	private LinearLayout linearLayout;
	private DB db;
	private List<String> honorList, nameList;
	private ArrayList<JSONObject> result_h, result_id, result_ipa;
	@Override
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.honor);
        linearLayout = (LinearLayout) findViewById(R.id.honorlayout);
        honorList = new ArrayList<String>();
        nameList = new ArrayList<String>();
        db = new DB();
       /* 
        //輸入要公布的ID
        String[] honor = {"100000001","100000000"};
        //
        
        for(int i=0;i<honor.length;i++)
        {
            search(honor[i]);
        }
        */
        
        ////search king
        try{
        	ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
			//nameValuePairs.add(new BasicNameValuePair("HonorID",honor));
			
			result_h = db.DataSearch(nameValuePairs,"king_search");

			Log.e("log_act","size="+result_h.size());
			
			
			
			
			for(int i=0;i<result_h.size();i++){
				Log.e("r_act",result_h.get(i).getString("honorName"));
				honorList.add(result_h.get(i).getString("honorName"));
				
				ArrayList<NameValuePair> nameValuePairs_id = new ArrayList<NameValuePair>();
				Log.e("r_act",result_h.get(i).getString("honorID"));
				nameValuePairs_id.add(new BasicNameValuePair("HonorID","100000001"));
				result_id = db.DataSearch(nameValuePairs_id,"honor_search");
			    nameList.add(result_id.get(0).getString("ipaID"));
			}
			
			
			
			//honorList.add(result_h.get(0).getString("honorName"));
			
			//search myhonor
			/*
			ArrayList<NameValuePair> nameValuePairs_id = new ArrayList<NameValuePair>();
			nameValuePairs_id.add(new BasicNameValuePair("HonorID",honor));
			result_id = db.DataSearch(nameValuePairs,"honor_search");
			
			*/
			
			/*
			Log.e("log_act","size="+result_id.size());
			Log.e("r_act",result_id.get(0).getString("ipaID"));
			String name = (result_id.get(0).getString("ipaID"));
			nameList.add(result_id.get(0).getString("ipaID"));
			*/
			
			//search ipaid
			/*
			ArrayList<NameValuePair> nameValuePairs_ipaid = new ArrayList<NameValuePair>();
			nameValuePairs_ipaid.add(new BasicNameValuePair("ipaID",name));
			result_id = db.DataSearch(nameValuePairs,"myhonor_search");
			
			nameList.add(result_ipa.get(0).getString("name"));
			*/
			
			//search ipa
			/*
			for(int i=0;i<result_h.size();i++){
				Log.e("r_act",result_h.get(i).getString("name"));
				honorList.add(result_h.get(i).getString("name"));
				activityDetailList.add(result_a.get(i).getString("description"));
			}*/
			
			
        	
        }catch(Exception e){
    		Log.e("log_tag", "Error get data "+e.toString());				
    		}
        //
        
        addBtn(honorList.size(),honorList,nameList);

	}
	
	
    public void search(String honor){
    	try{
        	ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
			//nameValuePairs.add(new BasicNameValuePair("HonorID",honor));
			
            
			result_h = db.DataSearch(nameValuePairs,"king_search");

			Log.e("log_act","size="+result_h.size());
			Log.e("r_act",result_h.get(0).getString("honorName"));
			honorList.add(result_h.get(0).getString("honorName"));
			
			//search myhonor
			ArrayList<NameValuePair> nameValuePairs_id = new ArrayList<NameValuePair>();
			nameValuePairs_id.add(new BasicNameValuePair("HonorID",honor));
			result_id = db.DataSearch(nameValuePairs,"honor_search");
			
			
			Log.e("log_act","size="+result_id.size());
			Log.e("r_act",result_id.get(0).getString("ipaID"));
			String name = (result_id.get(0).getString("ipaID"));
			nameList.add(result_id.get(0).getString("ipaID"));
			//search ipaid
			/*
			ArrayList<NameValuePair> nameValuePairs_ipaid = new ArrayList<NameValuePair>();
			nameValuePairs_ipaid.add(new BasicNameValuePair("ipaID",name));
			result_id = db.DataSearch(nameValuePairs,"myhonor_search");
			
			nameList.add(result_ipa.get(0).getString("name"));
			*/
			
			//search ipa
			/*
			for(int i=0;i<result_h.size();i++){
				Log.e("r_act",result_h.get(i).getString("name"));
				honorList.add(result_h.get(i).getString("name"));
				activityDetailList.add(result_a.get(i).getString("description"));
			}*/
			
			
        	
        }catch(Exception e){
    		Log.e("log_tag", "Error get data "+e.toString());				
    		}	
        
    }
	
    public void addBtn(int count,List<String> honorName, List<String> Name){
    	
    	if(count > 0){
    		int k;
    		for(int i = 0; i < count; i++){
    			Button tempBtn = new Button(this);
    			tempBtn.setText(honorName.get(i)+ " " +Name.get(i));
    			//+ " " +Name.get(i)
    			tempBtn.setId(i);
    			//還要寫按Button的動作
    			tempBtn.setOnClickListener(new View.OnClickListener() {
    	             public void onClick(View v) {
    	            	 int id = v.getId();
    	            	// setActivityDetail(id);
    	             }
    	         });
    			
    			linearLayout.addView(tempBtn, 300, 100);
    		}
    	}
    	
    }

}
