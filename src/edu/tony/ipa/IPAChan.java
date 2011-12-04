package edu.tony.ipa;

import greendroid.app.GDActivity;
import greendroid.widget.ActionBarItem.Type;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class IPAChan extends GDActivity { 
    private String ipaChan_url=
    	"http://piedralibre.files.wordpress.com/2010/11/emulador-para-android.png";
    	//"http://androidheadlines.com/wp-content/uploads/2011/07/contest-androidspin-wants-to-help-you-win-a-asus-transformer_tt-fr_0.png";
    private String user = null;
    private Integer fashion, sunny, fun, out, fat, art;
    private static final int ACTION_BAR_INFO = 0;
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setActionBarContentView(R.layout.ipa_chan);
        addActionBarItem(Type.Info, ACTION_BAR_INFO);
        
        
        SharedPreferences settings = getSharedPreferences("Account", 0);
        user = settings.getString("username", null);	
        Log.i("TESTUSER", "user name: "+ user);
        
        
    	
        Button closet = (Button) findViewById(R.id.closet);
        Button ipa_attr = (Button) findViewById(R.id.attr);
        Button honor = (Button) findViewById(R.id.honor);
    	ImageView ipaChan = (ImageView) findViewById(R.id.ipachan);
    	
        Bitmap bitmap = getBitmapFromUrl(ipaChan_url);
        ipaChan.setImageBitmap(bitmap);
        
        closet.setOnClickListener(new Button.OnClickListener(){
			public void onClick(View arg0) {
        		Intent i = new Intent();
      			i.setClass(IPAChan.this, IPACloset.class);
      			startActivity(i);
            }
        });

        honor.setOnClickListener(new Button.OnClickListener(){
			@Override
            public void onClick(View arg0) {
        		Intent i = new Intent();
      			i.setClass(IPAChan.this, IPAHonor.class);
      			startActivity(i);
            }
        });
        
        ipa_attr.setOnClickListener(new Button.OnClickListener(){
			public void onClick(View arg0) {
				attrOnClick();
        		/*Intent i = new Intent();
      			i.setClass(IPAChan.this, RadarTest.class);
      			startActivity(i);*/
            }
        });
        
    }
    public void attrOnClick() {
    	DB db = new DB();
    	ArrayList<NameValuePair> ipa = new ArrayList<NameValuePair>();
    	ipa.add(new BasicNameValuePair("AccID", user));
    	ArrayList<JSONObject> result_a = db.DataSearch(ipa,"ipa_search");
    	Log.i("TEST", "go go go: "+result_a.toString());
    	for(int i=0;i<result_a.size();i++){
    		try {
	            Log.i("r_act",result_a.get(i).getString("ipaID"));
	            fashion = Integer.parseInt(result_a.get(i).getString("fashion"));
	            sunny = Integer.parseInt(result_a.get(i).getString("sunny"));
	            fun = Integer.parseInt(result_a.get(i).getString("fun"));
	            out = Integer.parseInt(result_a.get(i).getString("out"));
	            fat = Integer.parseInt(result_a.get(i).getString("fat"));
	            art = Integer.parseInt(result_a.get(i).getString("art"));
            } catch (JSONException e) {
	          // TODO Auto-generated catch block
	          e.printStackTrace();
            }
    	}
    	
    	ImageView radr = new ImageView(this); 
        RadarChart rd = new RadarChart();
        String radr_url = rd.getIpaRadr(fashion, sunny, fun, out, fat, art);
        Bitmap bitmap2 = getBitmapFromUrl(radr_url);
        radr.setImageBitmap(bitmap2);

        final AlertDialog.Builder builder = new AlertDialog.Builder(IPAChan.this);  
        builder.setCancelable(false);
        builder.setTitle("IPA人格");
        builder.setView(radr);
        builder.setPositiveButton("確定", null); 
        builder.show();
    }

    /**
     * 到Url去下載圖片回傳BITMAP回來
     * @param imgUrl
     * @return
     */
    public static Bitmap getBitmapFromUrl(String imgUrl) {
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
    

}
