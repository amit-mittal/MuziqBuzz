package com.college;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.VideoView;

public class ViewVideo extends Activity {
      private String filename;
      @Override
      public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            System.gc();
            Intent i = getIntent();
            Bundle extras = i.getExtras();
            filename = extras.getString("videofilename");
            VideoView vv = new VideoView(getApplicationContext());
            setContentView(vv);
            vv.setVideoPath(filename);
            vv.setMediaController(new MediaController(this));
            vv.requestFocus();
            vv.start();
      }
} 