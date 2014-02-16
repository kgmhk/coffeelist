package com.kgh.coffelist.adpost;

import com.nbpcorp.mobilead.sdk.MobileAdListener;
import com.nbpcorp.mobilead.sdk.MobileAdView;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.widget.LinearLayout;

public class AdPostActivity extends Activity implements MobileAdListener {
	
	private MobileAdView adView = null;
	
	@Override 
	public void onCreate(Bundle savedInstanceState) { 
		super.onCreate(savedInstanceState); 
		//requestWindowFeature(Window.FEATURE_NO_TITLE);
		LinearLayout l = new LinearLayout(this);
		adView = new MobileAdView(this); // 2 
		adView.setChannelID("mandroid_df99eaa751ae4a9db65b7c09a00641b4"); // 3 
		adView.setTest(false); // 4 
		adView.setListener(this); // 5 
		adView.start(); // 6 
		l.addView(adView, LinearLayout.LayoutParams.FILL_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT); 
		setContentView(l); 
	}
	
	@Override
	protected void onDestroy(){
		super.onDestroy();
		
		if(adView != null){
			adView.destroy();
			adView = null;
		}
	}
	
	@Override
	public void onReceive(int err){
		
		}
}
