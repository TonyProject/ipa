package edu.tony.ipa;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import greendroid.app.GDActivity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;

public class RadarTest extends GDActivity{
	private String radr_url;
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setActionBarContentView(R.layout.ipa_attr);

        RadarChart rd = new RadarChart();
        radr_url = rd.example2();
    	ImageView radr = (ImageView) findViewById(R.id.attr_radar); 
        Bitmap bitmap = getBitmapFromUrl(radr_url);
        radr.setImageBitmap(bitmap);
	}

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

}
