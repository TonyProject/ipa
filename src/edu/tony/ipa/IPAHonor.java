package edu.tony.ipa;

import greendroid.app.GDActivity;

import java.util.ArrayList;
import java.util.HashMap;

import android.os.Bundle;
import android.widget.GridView;
import android.widget.SimpleAdapter;

public class IPAHonor extends GDActivity{
	private GridView honor;
	private ArrayList<HashMap<String, Object>> grid_honor
			= new ArrayList<HashMap<String, Object>>(); 
	
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setActionBarContentView(R.layout.ipa_honor);
        
        honor = (GridView) findViewById(R.id.honor); 
        
        for(int i=0;i<10;i++)  
        {  
        	HashMap<String, Object> map = new HashMap<String, Object>();  
        	map.put("honor", R.drawable.icon);
        	grid_honor.add(map);  
        }
        SimpleAdapter saImageItems = new SimpleAdapter(
        	this,  
            grid_honor,
            R.layout.list_items,      
            new String[] {"honor"},
            new int[] {R.id.item});  
        honor.setAdapter(saImageItems);  
    }
}