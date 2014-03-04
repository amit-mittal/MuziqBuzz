package com.college;

import java.io.File;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.View;
import android.view.ViewGroup;
import android.view.SurfaceHolder.Callback;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

public class Videos extends Activity {
	      private Cursor videocursor;
	      private int video_column_index;
	      ListView videolist;
	      int count;

	      @Override
	      public void onCreate(Bundle savedInstanceState) {
	            super.onCreate(savedInstanceState);
	            setContentView(R.layout.videos);
	            init_phone_video_grid();
	      }

	      private void init_phone_video_grid() {
	            System.gc();
	            String[] proj = {	MediaStore.Video.Media._ID,
	            					MediaStore.Video.Media.DATA,
	            					MediaStore.Video.Media.DISPLAY_NAME,
	            					MediaStore.Video.Media.ARTIST };
	            
	            videocursor = managedQuery(MediaStore.Video.Media.EXTERNAL_CONTENT_URI,proj, null, null, null);
	            count = videocursor.getCount();
	            
	            videolist = (ListView) findViewById(R.id.PhoneVideoList);
	            videolist.setAdapter(new VideoAdapter(getApplicationContext()));
	            videolist.setOnItemClickListener(videogridlistener);
	      }

	      private OnItemClickListener videogridlistener = new OnItemClickListener() {
	            public void onItemClick(AdapterView parent, View v, int position,long id) {
	                  System.gc();
	                  video_column_index = videocursor.getColumnIndexOrThrow(MediaStore.Video.Media.DATA);
	                  videocursor.moveToPosition(position);
	                  
	                  String filename = videocursor.getString(video_column_index);
	                  Intent intent = new Intent(Videos.this, ViewVideo.class);
	                  intent.putExtra("videofilename", filename);
	                  startActivity(intent);
	            }
	      };

	      public class VideoAdapter extends BaseAdapter {
	            private Context vContext;

	            public VideoAdapter(Context c) {
	                  vContext = c;
	            }

	            public int getCount() {
	                  return count;
	            }

	            public Object getItem(int position) {
	                  return position;
	            }

	            public long getItemId(int position) {
	                  return position;
	            }

	            public View getView(int position, View convertView, ViewGroup parent) {
	            	View v = convertView;
	            	
	            	LayoutInflater vi = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	                v = vi.inflate(R.layout.customlist, null);
	                
	                TextView tt = (TextView) v.findViewById(R.id.toptext);
	                TextView bt = (TextView) v.findViewById(R.id.bottomtext);
	                
	            	videocursor.moveToPosition(position);
	                video_column_index = videocursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DISPLAY_NAME);
	                //this sorts capital different and small letters as different
	                
	                tt.setText(videocursor.getString(video_column_index));
	                
	                videocursor.moveToPosition(position);
	                
	                video_column_index = videocursor.getColumnIndexOrThrow(MediaStore.Video.Media.ARTIST);
	                videocursor.moveToPosition(position);
	                
	                bt.setText(videocursor.getString(video_column_index));
	                
	                videocursor.moveToPosition(position);                        

	                return v;
	            	
	            	/*     System.gc();
	                  TextView tv = new TextView(vContext.getApplicationContext());
	                  String id = null;
	                  
	                        video_column_index = videocursor.getColumnIndexOrThrow(MediaStore.Video.Media.DISPLAY_NAME);
	                        videocursor.moveToPosition(position);
	                        id = videocursor.getString(video_column_index);
	                        video_column_index = videocursor.getColumnIndexOrThrow(MediaStore.Video.Media.SIZE);
	                        videocursor.moveToPosition(position);
	                        id += " Size(KB):" + videocursor.getString(video_column_index);
	                        tv.setText(id);
	                  
	                        return tv;*/
	            }
	      }
	}