package com.college;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.preference.PreferenceManager;

public class Welcome extends Activity {
	
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome);
        
        
/*      SharedPreferences soundPreference =PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        boolean sound=soundPreference.getBoolean("checkbox", true);
      	mp= MediaPlayer.create(Welcome.this, R.raw.mymusic);
        if(sound)
        mp.start();
*/
        
        Thread timer=new Thread(){
        	public void run(){
        		try {
					sleep(2000);
				} catch (Exception e){}
        		finally{
        			Intent in=new Intent(Welcome.this,Tab.class);
        			startActivity(in);}
        	}};
        	
        timer.start();
    }
	
	public void onPause(){
		super.onPause();
		finish();
		}
}