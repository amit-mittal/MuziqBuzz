package com.college;

import android.app.TabActivity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.widget.TabHost;

public class Tab extends TabActivity{
	
	TabHost tabhost;
	Intent in;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tab);
		Resources res=getResources();
		tabhost=getTabHost();
		TabHost.TabSpec spec;
		
		in=new Intent(Tab.this,Music.class);
		spec=tabhost.newTabSpec("Music").setIndicator("Music", res.getDrawable(R.drawable.music_tab)).setContent(in);
		tabhost.addTab(spec);
		
		in=new Intent(Tab.this,Videos.class);
		spec=tabhost.newTabSpec("Videos").setIndicator("Videos",res.getDrawable(R.drawable.video_tab)).setContent(in);
		tabhost.addTab(spec);
		
		in=new Intent(Tab.this,MyPlaylist.class);
		spec=tabhost.newTabSpec("MyPlaylist").setIndicator("MyPlaylist",res.getDrawable(R.drawable.my_playlist)).setContent(in);
		tabhost.addTab(spec);		
	}
	 

}
