package edu.tony.ipa;

import java.util.ArrayList;
import java.util.HashMap;
import greendroid.app.GDActivity;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.AnimationUtils;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ViewSwitcher.ViewFactory;


public class IPACloset extends GDActivity implements ViewFactory{
	private SimpleAdapter listItemAdapter = null;
	private ImageSwitcher myImageSwitcher;
	private ListView list;
	private Gallery dressGallery;
	private ArrayList< HashMap<String, Object> > listItem;
	private String ipaChan_url= 
    	"http://androidheadlines.com/wp-content/uploads/2011/07/contest-androidspin-wants-to-help-you-win-a-asus-transformer_tt-fr_0.png";

	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setActionBarContentView(R.layout.ipa_closet);
        
        list = (ListView) findViewById(R.id.dress_list);
        /*listItemAdapter = 
        	new SimpleAdapter(
        		this,
        		listItem,
        		R.layout.list_items,  
        		new String[] {"dress"},   
        		new int[] {R.id.image}  
        	);    
        list.setAdapter(listItemAdapter);
        
		HashMap<String, Object> item = new HashMap<String, Object>();
		for(int i=0; i<3; i++) {
			ImageView dress_image = (ImageView) findViewById(R.id.image);
	        //Bitmap bitmap = IPAChan.getBitmapFromUrl(ipaChan_url);
	        //dress_image.setImageBitmap(bitmap);
			dress_image.setImageResource(R.drawable.like);
			item.put("dress", dress_image);
			listItem.add(item);
		}*/
		
		//listItemAdapter.notifyDataSetChanged();
        
        myImageSwitcher = (ImageSwitcher)findViewById(R.id.imageswitcher);
        myImageSwitcher.setFactory(this);
        myImageSwitcher.setInAnimation(AnimationUtils.loadAnimation(this, android.R.anim.fade_in));
        myImageSwitcher.setOutAnimation(AnimationUtils.loadAnimation(this, android.R.anim.fade_out));
        
        dressGallery = (Gallery)findViewById(R.id.dress);
        dressGallery.setAdapter(new ImageAdapter(this));
        dressGallery.setOnItemSelectedListener(myGalleryOnItemSelectedListener);
    }
    
    private OnItemSelectedListener myGalleryOnItemSelectedListener
     	= new OnItemSelectedListener(){
    	@Override
    	public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
    		// TODO Auto-generated method stub
    		myImageSwitcher.setImageResource(mThumbIds[arg2]);
    	}
    	@Override
    	public void onNothingSelected(AdapterView<?> arg0) {
    		// TODO Auto-generated method stub
    	}
    };
    
    private Integer[] mThumbIds = {
            R.drawable.like,
            R.drawable.share,
            R.drawable.jdownloader,
            R.drawable.icon,
            R.drawable.textedit
          };
    
    public class ImageAdapter extends BaseAdapter{
    	private Context context;
    	public ImageAdapter(Context c){
    		context = c;
    	}
     
    	@Override
    	public int getCount() {
    		// TODO Auto-generated method stub
    		return mThumbIds.length;
    	}

    	@Override
    	public Object getItem(int position) {
    		// TODO Auto-generated method stub
    		return mThumbIds[position];
    	}

    	@Override
    	public long getItemId(int position) {
    		// TODO Auto-generated method stub
    		return position;
    	}

    	@Override
    	public View getView(int position, View convertView, ViewGroup parent) {
    		// TODO Auto-generated method stub
    		ImageView imageView;
    		if (convertView == null) {
    			// if it's not recycled, initialize some attributes
    			imageView = new ImageView(context);
    			imageView.setLayoutParams(new Gallery.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
    			imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
    			imageView.setPadding(8, 8, 8, 8);
    		} else {
    			imageView = (ImageView) convertView;
    		}

    		imageView.setImageResource(mThumbIds[position]);
    		return imageView;
    	}
    }

    @Override
    public View makeView() {
    	// TODO Auto-generated method stub
    	ImageView i = new ImageView(this);
        i.setScaleType(ImageView.ScaleType.FIT_CENTER);
        i.setLayoutParams(new ImageSwitcher.LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT));
        return i;
    }
}

