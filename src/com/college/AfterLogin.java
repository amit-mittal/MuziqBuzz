package com.college;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class AfterLogin extends Activity{
	
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.afterlogin);
        
        String str=getIntent().getExtras().getString("username");
        TextView tv=(TextView)findViewById(R.id.afterLoginText);
        tv.setText("Welcome Mr./Mrs. " + str);
        
	}

}
