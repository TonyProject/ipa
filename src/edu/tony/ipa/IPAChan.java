package edu.tony.ipa;

import greendroid.app.GDActivity;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class IPAChan extends GDActivity { 
    private String ipaChan_url= 
    	"http://androidheadlines.com/wp-content/uploads/2011/07/contest-androidspin-wants-to-help-you-win-a-asus-transformer_tt-fr_0.png";
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setActionBarContentView(R.layout.ipa_chan);
    	
        /*DB db = new DB();
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
    	}*/
    	
        Button closet = (Button) findViewById(R.id.closet);
        Button ipa_attr = (Button) findViewById(R.id.attr);
        Button honor = (Button) findViewById(R.id.honor);
    	ImageView ipaChan = (ImageView) findViewById(R.id.ipachan);
    	
        Bitmap bitmap = getBitmapFromUrl(ipaChan_url);
        ipaChan.setImageBitmap(bitmap);
    	
        ipa_attr.setOnClickListener(new Button.OnClickListener(){
			@Override
            public void onClick(View arg0) {
				//attrOnClick();
        		Intent i = new Intent();
      			i.setClass(IPAChan.this, RadarTest.class);
      			startActivity(i);
            }
        });
        
    }

    /**
     * 到Url去下載圖片回傳BITMAP回來
     * @param imgUrl
     * @return
     */
    private Bitmap getBitmapFromUrl(String imgUrl) {
                URL url;
                Bitmap bitmap = null;
                try {
                        url = new URL(imgUrl);
                        InputStream is = url.openConnection().getInputStream();
                        BufferedInputStream bis = new BufferedInputStream(is);
                        bitmap = BitmapFactory.decodeStream(bis);
                        bis.close();
                } catch (MalformedURLException e) {
                        e.printStackTrace();
                } catch (IOException e) {
                        e.printStackTrace();
                }
                return bitmap;
    }
    
    public void attrOnClick() {
        //This class is used to instantiate layout XML file into its corresponding View objects.
        LayoutInflater inflater = LayoutInflater.from(this);  
        final View addView = inflater.inflate(R.layout.ipa_attr, null);  
        /*TextView fun = (TextView)addView.findViewById(R.id.fun);
        TextView art = (TextView)addView.findViewById(R.id.art);
        TextView fashion = (TextView)addView.findViewById(R.id.fashion);
        TextView out = (TextView)addView.findViewById(R.id.out);
        TextView fat = (TextView)addView.findViewById(R.id.fat);
        TextView sunny = (TextView)addView.findViewById(R.id.sunny);*/

        final AlertDialog.Builder builder = new AlertDialog.Builder(IPAChan.this);  
        builder.setCancelable(false);
        builder.setIcon(R.drawable.icon);  
        builder.setTitle("IPA人格");
        builder.setView(addView);
        builder.setPositiveButton("確定",  
        		new DialogInterface.OnClickListener() {  
                	public void onClick(DialogInterface dialog, int whichButton) {
                		RadarChart rd = new RadarChart();
                        String radr_url = rd.example2();
                    	ImageView radr = (ImageView) findViewById(R.id.attr_radar); 
                        Bitmap bitmap2 = getBitmapFromUrl(radr_url);
                        radr.setImageBitmap(bitmap2);       		
                    }
          		}
        );  
        builder.setNegativeButton("取消",  
                new DialogInterface.OnClickListener() {  
                    public void onClick(DialogInterface dialog, int whichButton) {  
                           setTitle("");
                    }  
                }
        );  
        builder.show();
    }
}
