package com.college;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;

public class AboutMe extends Activity{
	
	ImageButton imgbtn;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
	    setContentView(R.layout.about_me);
	    
	    imgbtn=(ImageButton)findViewById(R.id.close_about_me);
	    
	    imgbtn.setOnClickListener(new OnClickListener() {
			
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				finish();
			}
		});
	}

}
