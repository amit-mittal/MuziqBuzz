package com.college;

import java.io.FileDescriptor;

import android.app.Activity;
import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.ParcelFileDescriptor;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SeekBar;

public class ViewMusic extends Activity {
      
	public String filename;
	Long album;
	MediaPlayer mMediaPlayer;
	ImageButton forw,rew,play,stop,pause;
	SeekBar seek;
    ImageView albumart;  
	
    public Bitmap getAlbumart(Long album_id) 
    {
         Bitmap bm = null;
         try 
         {
             final Uri sArtworkUri = Uri.parse("content://media/external/audio/albumart");

             Uri uri = ContentUris.withAppendedId(sArtworkUri, album_id);

             ParcelFileDescriptor pfd = getContentResolver().openFileDescriptor(uri, "r");

             if (pfd != null) 
             {
                 FileDescriptor fd = pfd.getFileDescriptor();
                 bm = BitmapFactory.decodeFileDescriptor(fd);
             }
     } catch (Exception e) {
     }
     return bm;
 }

    
    
      public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.viewmusic);
            
            albumart=(ImageView)findViewById(R.id.albumart);
            forw=(ImageButton)findViewById(R.id.forw);
            rew=(ImageButton)findViewById(R.id.rew);
            play=(ImageButton)findViewById(R.id.play);
            pause=(ImageButton)findViewById(R.id.pause);
            stop=(ImageButton)findViewById(R.id.stop);
            seek=(SeekBar)findViewById(R.id.seek);
            
            Intent i = getIntent();
            Bundle extras = i.getExtras();
            
            filename = getIntent().getExtras().getString("musicfilename");
            album=getIntent().getExtras().getLong("musicimage");
            
            Bitmap bmp=getAlbumart(album);
            
            albumart.setImageBitmap(bmp);
            
            mMediaPlayer = new MediaPlayer();
            
            play.setOnClickListener(new OnClickListener() {
				public void onClick(View arg0) {
					stopService(new Intent(ViewMusic.this, MyService.class));
	            	//first stop the previous playing player
	            	
	            	Intent intent = new Intent(ViewMusic.this, MyService.class);
	                intent.putExtra("musicfilename", filename);
	                startService(intent);
				}
			});
            
            stop.setOnClickListener(new OnClickListener() {
            	public void onClick(View arg0) {
            		stopService(new Intent(ViewMusic.this, MyService.class));
				}
			});
            	/*stopService(new Intent(this, MyService.class));
            	//first stop the previous playing player
            	
            	Intent intent = new Intent(ViewMusic.this, MyService.class);
                intent.putExtra("musicfilename", filename);
                startService(intent);*/
                //start the new player
                
            	/*
				mMediaPlayer.setDataSource(filename);
				mMediaPlayer.prepare();
	            mMediaPlayer.start();*/
	            
			
            
      }
      
      /*protected void onPause() {
    	  super.onPause();
    	  
    	  if(mMediaPlayer.isPlaying())
    	  {
    		  try
    		  {
    			  Thread.sleep(40000); 
    		  }
    		  catch(Exception e){
    		  
    		  }
    	  }
      }*/
}