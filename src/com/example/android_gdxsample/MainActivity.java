package com.example.android_gdxsample;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;

import android.content.pm.ActivityInfo;
import android.os.Bundle;


public class MainActivity extends AndroidApplication {
	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setRequestedOrientation (ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		//setRequestedOrientation (ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
		
		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
		initialize(new MainGDX(), config);
	}
    
}
