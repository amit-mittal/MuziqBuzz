package com.college;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

public class MyService extends Service {
	
	private static final String TAG = "MyService";
	MediaPlayer player;
	String filename;
	
	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}
	
	@Override
	public void onCreate() {
		Log.d(TAG, "onCreate");
	}

	@Override
	public void onDestroy() {
		Log.d(TAG, "onDestroy");
		player.stop();
	}
	
	@Override
	public void onStart(Intent intent, int startid) {
		
		/*if(player.isPlaying())
			player.pause();
		else
		{*/
			player = new MediaPlayer();
		
			Bundle b=intent.getExtras();
			filename=b.getString("musicfilename");
		
			try{
				player.setDataSource(filename);
				player.prepare();
				player.start();
			}
			catch(Exception e){
			
			}
//		}
		Log.d(TAG, "onStart");
	}
}