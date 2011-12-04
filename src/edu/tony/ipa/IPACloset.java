package edu.tony.ipa;

import greendroid.app.GDActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.SimpleAdapter.ViewBinder;
import android.widget.ViewSwitcher.ViewFactory;


public class IPACloset extends GDActivity implements ViewFactory{
	private SimpleAdapter listItemAdapter = null;
	private ImageSwitcher myImageSwitcher;
	private ListView list;
	private Gallery dressGallery;
	private ArrayList< HashMap<String, Object> > listItem = new ArrayList<HashMap<String, Object>>();
	private String ipaChan_url= 
    	"http://www.jamesinsummer.com/taiwanTide/images/android.png";

	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setActionBarContentView(R.layout.ipa_closet);
        
        //The list of items
		HashMap<String, Object> item = new HashMap<String, Object>();
        Bitmap bitmap = IPAChan.getBitmapFromUrl(ipaChan_url);
        //dress_image.setImageBitmap(bitmap);
		//dress_image.setImageResource(R.drawable.like);
        
        item.put("item", bitmap);
		Log.i("Item_test", item.get("item").toString());
		for (int i=0; i<20; i++)
		listItem.add(item);
        
		list = (ListView) findViewById(R.id.item_list);
        listItemAdapter = 
        	new SimpleAdapter(
        		this,
        		listItem,
        		R.layout.list_items,  
        		new String[] {"item"},   
        		new int[] {R.id.item}  
        	);    
        listItemAdapter.setViewBinder(new MyViewBinder());
        list.setAdapter(listItemAdapter);
		//listItemAdapter.notifyDataSetChanged();
        
        myImageSwitcher = (ImageSwitcher)findViewById(R.id.imageswitcher);
        myImageSwitcher.setFactory(this);
        myImageSwitcher.setInAnimation(AnimationUtils.loadAnimation(this, android.R.anim.fade_in));
        myImageSwitcher.setOutAnimation(AnimationUtils.loadAnimation(this, android.R.anim.fade_out));
        
        dressGallery = (Gallery)findViewById(R.id.dress_type);
        dressGallery.setAdapter(new ImageAdapter(this));
        dressGallery.setOnItemSelectedListener(myGalleryOnItemSelectedListener);
        
    }
    public class MyViewBinder implements ViewBinder {
        @Override
        public boolean setViewValue(View view, Object data,
                        String textRepresentation) {
        		if( (view instanceof ImageView) & (data instanceof Bitmap) ) {
                        ImageView iv = (ImageView) view;
                        Bitmap bm = (Bitmap) data;     
                        iv.setImageBitmap(bm); 
                        return true;
                }
                return false;
        }
    }
    
    private OnItemSelectedListener myGalleryOnItemSelectedListener
     	= new OnItemSelectedListener(){
    	public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
    		// TODO Auto-generated method stub
    		myImageSwitcher.setImageResource(mThumbIds[arg2]);
    	}
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
     
    	public int getCount() {
    		// TODO Auto-generated method stub
    		return mThumbIds.length;
    	}

    	public Object getItem(int position) {
    		// TODO Auto-generated method stub
    		return mThumbIds[position];
    	}

    	public long getItemId(int position) {
    		// TODO Auto-generated method stub
    		return position;
    	}

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

    public View makeView() {
    	// TODO Auto-generated method stub
    	ImageView i = new ImageView(this);
        i.setScaleType(ImageView.ScaleType.FIT_CENTER);
        i.setLayoutParams(new ImageSwitcher.LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT));
        return i;
    }
    
    
}

