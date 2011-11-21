package edu.tony.ipa;

import greendroid.app.GDActivity;

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

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;

public class IPAChan extends GDActivity { 
    private static final String IMG_URL= "http://androidheadlines.com/wp-content/uploads/2011/07/contest-androidspin-wants-to-help-you-win-a-asus-transformer_tt-fr_0.png";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setActionBarContentView(R.layout.ipa_chan);
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
        Button closet = (Button) findViewById(R.id.closet);
        Button ipa_char = (Button) findViewById(R.id.six);
        Button honor = (Button) findViewById(R.id.honor);
    	ImageView imageView = (ImageView) findViewById(R.id.ipachan); 
        Bitmap bitmap = getBitmapFromUrl(IMG_URL);
        imageView.setImageBitmap(bitmap);
    	
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
                        url = new URL(IMG_URL);
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
