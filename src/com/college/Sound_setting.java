package com.college;

import android.os.Bundle;
import android.preference.PreferenceActivity;

public class Sound_setting extends PreferenceActivity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		addPreferencesFromResource(R.xml.sound_setting_pref);
		
	}

	
}
