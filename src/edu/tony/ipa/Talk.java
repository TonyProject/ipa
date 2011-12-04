package edu.tony.ipa;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Talk extends Activity {
	/** Called when the activity is first created. */
	private TextView showtext;
	private Button say;
	private EditText text;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        setContentView(R.layout.talk);
        
        showtext = (TextView)findViewById(R.id.show);
        say = (Button)findViewById(R.id.say);
        text = (EditText)findViewById(R.id.text);
        
	}

}
