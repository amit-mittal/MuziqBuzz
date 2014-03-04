package com.college;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class Music extends Activity {
      ListView musiclist;
      Cursor musiccursor;
      int music_column_index;
      int count;
      MediaPlayer mMediaPlayer;

      public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.music);
            init_phone_music_grid();
      }
      

      public void init_phone_music_grid() {
          	mMediaPlayer = new MediaPlayer();
            String[] proj = { 	MediaStore.Audio.Media._ID,
            					MediaStore.Audio.Media.DATA,
            					MediaStore.Audio.Media.DISPLAY_NAME,
            					MediaStore.Audio.Media.ARTIST,
            					MediaStore.Audio.Media.ALBUM_ID};
            
            musiccursor = managedQuery(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,proj, null, null, MediaStore.Audio.Media.DISPLAY_NAME);
            
            count = musiccursor.getCount();
            
            musiclist = (ListView) findViewById(R.id.PhoneMusicList);
            
            musiclist.setAdapter(new MusicAdapter(Music.this));

            musiclist.setOnItemClickListener(new OnItemClickListener() {
                public void onItemClick(AdapterView parent, View v, int position,long id) {
                	  System.gc();
                      music_column_index = musiccursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DATA);
                      musiccursor.moveToPosition(position);
                      
                      String filename = musiccursor.getString(music_column_index);
                      
                      musiccursor.moveToPosition(position);
                      
                      music_column_index = musiccursor.getColumnIndexOrThrow(MediaStore.Audio.Media.ALBUM_ID);
                      musiccursor.moveToPosition(position);
                      
                      Long album = musiccursor.getLong(music_column_index);
                      
                      Intent intent = new Intent(Music.this, ViewMusic.class);
	                  intent.putExtra("musicfilename", filename);
	                  intent.putExtra("musicimage", album);
	                  startActivity(intent);

/*                      try {
                            if (mMediaPlayer.isPlaying()) {
                                  mMediaPlayer.reset();
                            }
                            mMediaPlayer.setDataSource(filename);
                            mMediaPlayer.prepare();
                            mMediaPlayer.start();
                      } catch (Exception e) {

                      }
*/                }
          });
            
      }

      public class MusicAdapter extends BaseAdapter {
            private Context mContext;

            public MusicAdapter(Context c) {
                  mContext = c;
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
                
            	musiccursor.moveToPosition(position);
                music_column_index = musiccursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DISPLAY_NAME);
                //this sorts capital different and small letters as different
                
                tt.setText(musiccursor.getString(music_column_index));
                
                musiccursor.moveToPosition(position);
                
                music_column_index = musiccursor.getColumnIndexOrThrow(MediaStore.Audio.Media.ARTIST);
                musiccursor.moveToPosition(position);
                
                bt.setText(musiccursor.getString(music_column_index));
                
                musiccursor.moveToPosition(position);                        

                return v;
            	
                 /*TextView tv = new TextView(mContext.getApplicationContext());
                 String id = null;
                 
                 tv.setHeight(60);
                 tv.setTextSize(15);
                 tv.setGravity(Gravity.CENTER_VERTICAL);
                 tv.setTextColor(Color.WHITE);
                 
                // if (convertView == null) {
                	   musiccursor.moveToPosition(position);
                       music_column_index = musiccursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DISPLAY_NAME);
                       musiccursor.moveToPosition(position);
                       id = musiccursor.getString(music_column_index);
                       music_column_index = musiccursor.getColumnIndexOrThrow(MediaStore.Audio.Media.ARTIST);
                       musiccursor.moveToPosition(position);
                       
                       if(musiccursor.getString(music_column_index)!=null)
                    	   id += "\n" + musiccursor.getString(music_column_index);
                       else
                    	   id += "\nUnknown Artist";
                       
                       musiccursor.moveToPosition(position);
                       tv.setText(id);
                 /*} else
                       tv = (TextView) convertView;
                 *///return tv;
          }

      }
//}



/********************************************************************/

/*public class Music extends Activity{
	
	ListView musiclist;
  Cursor musiccursor,cursor;
  int music_column_index;
  int count;
  private Intent aIntent;
  public static String filename;
  private RelativeLayout mRelativeLayout;
  


  @Override
  public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.music);
        
     /* //your database elect statement 
        String selection = MediaStore.Audio.Media.IS_MUSIC + " != 0";
        //your projection statement 
        String[] projection = {
                MediaStore.Audio.Media._ID,
                MediaStore.Audio.Media.ARTIST,
                MediaStore.Audio.Media.TITLE,
                MediaStore.Audio.Media.DATA,
                MediaStore.Audio.Media.DISPLAY_NAME,
                MediaStore.Audio.Media.DURATION,
                MediaStore.Audio.Media.ALBUM_ID
        };
        //query 
        cursor = this.managedQuery(
                MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
                projection,
                selection,
                null,
                null);


        while(cursor.moveToNext()){
                songs.add(cursor.getString(0));
                songs.add(cursor.getString(1));
                songs.add(cursor.getString(2));
                songs.add(cursor.getString(3));
                songs.add(cursor.getString(4));
                songs.add(cursor.getString(5));
                album_id.add((long) cursor.getFloat(6));
        } 
        int a[]= new int[]{R.id.textView1 ,R.id.textView3};//, R.id.textview2};
        ListAdapter adapter = new SimpleCursorAdapter(Music.this,
                android.R.layout.simple_list_item_1, cursor, new String[]{MediaStore.Audio.Media.TITLE/*,MediaStore.Audio.Media.ARTIST
        		, MediaStore.Audio.Media.DURATION*//*} ,a);
                setListAdapter(adapter); 
    }

        
  */
  
       /*
        
        init_phone_music_grid();
        mRelativeLayout = new RelativeLayout(this);
        setContentView(mRelativeLayout);
        mRelativeLayout.addView(musiclist);
        
  }

  public void init_phone_music_grid() {
        //System.gc();

        String[] projection = {
                MediaStore.Audio.Media._ID,
                MediaStore.Audio.Media.ARTIST,
                MediaStore.Audio.Media.TITLE,
                MediaStore.Audio.Media.DATA,
                MediaStore.Audio.Media.DISPLAY_NAME,
                MediaStore.Audio.Media.DURATION,
                MediaStore.Audio.Media.ALBUM
        };

        Uri allsongsuri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
        String selection = MediaStore.Audio.Media.IS_MUSIC + " != 0";

        musiccursor = managedQuery(allsongsuri, projection , selection, null, null);

        count = musiccursor.getCount();
        musiclist = (ListView) findViewById(R.id.PhoneMusicList);
        musiclist.setAdapter(new EfficientAdapter(getApplicationContext()));
        musiclist.setOnItemClickListener(musicgridlistener);

  }
  
  private OnItemClickListener musicgridlistener = new OnItemClickListener() {
        
	  public void onItemClick(AdapterView<?> parent, View v, int position,long id) {
              System.gc();
              music_column_index = musiccursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DATA);
              musiccursor.moveToPosition(position);
              filename = musiccursor.getString(music_column_index);
              aIntent = new Intent(v.getContext(), AudioFX.class);
              aIntent.getStringExtra(filename);
              startActivity(aIntent);
        }
      };

     class EfficientAdapter extends BaseAdapter {
      private Context mContext;

      public EfficientAdapter(Context c) {
          mContext = c;
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
              System.gc();
              String id = null;
              TextView tv;
              if (convertView == null) {
              tv = new TextView(mContext.getApplicationContext());
              } else{
              tv = (TextView) convertView;
              }
              musiccursor.moveToPosition(position);
              music_column_index = musiccursor.getColumnIndexOrThrow(MediaStore.Audio.Media.TITLE);
              id = musiccursor.getString(music_column_index);
              tv.setText(id);
              tv.setTextSize(20);
              return tv;
              }
          }
	 
	
	
	
	/**********************************************************/
     /******************************************************/
	
/**/
	//For menu implement  //
	@Override
	public boolean onCreateOptionsMenu(Menu menu) { 
		super.onCreateOptionsMenu(menu);
		MenuInflater mi=getMenuInflater();
		mi.inflate(R.menu.menu, menu);
		
		return true;
		}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		super.onOptionsItemSelected(item);
		switch (item.getItemId()) {
		case R.id.about:
			Intent in= new Intent(this,AboutMe.class);
			startActivity(in);
			break;

		case R.id.prefs:
			Intent intent= new Intent(this,Sound_setting.class);
			startActivity(intent);
			break;
		
		case R.id.exit:
			AlertDialog.Builder builder= new AlertDialog.Builder(Music.this);
			builder.setMessage("Are you sure you want to Exit");
			builder.setCancelable(false);
			builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
				
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub
					Music.this.finish();
					mMediaPlayer.release();
				}
			});
			builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
				
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub
					dialog.cancel();
				}
			});
			builder.show();
		}
		return false;
	}   // menu implement close here
	
	
}
