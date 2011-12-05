package edu.tony.ipa;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import greendroid.app.GDActivity;

public class people_info_image extends GDActivity  {
	
	private ImageView info_ipachan;
	private ImageView image_like;
	private TextView like_amount;
	private ImageButton like_imagebutton;
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        setActionBarContentView(R.layout.people_info_image);
        
        info_ipachan = (ImageView)findViewById(R.id.info_ipachan);
        image_like = (ImageView)findViewById(R.id.image_like);
        like_amount = (TextView)findViewById(R.id.like_amount);
        like_imagebutton = (ImageButton)findViewById(R.id.like_imageButton);
        
        Intent intent = getIntent();
        Integer ipaid = intent.getIntExtra("ipaID", -1);
        String img = intent.getStringExtra("img") ;
        Integer likenum = intent.getIntExtra("likenum", 0) ;
        
        Bitmap bitmap = getBitmapFromUrl(img);
        info_ipachan.setImageBitmap(bitmap);
        like_amount.setText(likenum.toString());
		
	}
	
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
