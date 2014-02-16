package com.kgh.coffelist.activity;

import com.kgh.coffelist.R;
import com.kgh.coffelist.adpost.AdPostActivity;
import com.nbpcorp.mobilead.sdk.MobileAdListener;
import com.nbpcorp.mobilead.sdk.MobileAdView;

import android.os.Bundle;
import android.util.Log;
import android.widget.TabHost;
import android.widget.TabHost.OnTabChangeListener;
import android.widget.TextView;
import android.app.TabActivity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;

public class MainActivity extends TabActivity{

	MobileAdView adview = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		startActivity(new Intent(this, CoffeList_Splash.class));
		setContentView(R.layout.main);
		//requestWindowFeature(Window.FEATURE_NO_TITLE);
		
		TabHost tabHost = getTabHost();
		TabHost.TabSpec spec;
		Intent intent;
	 	
		//Ã¹ ¹øÂ° ÅÇ
		intent = new Intent(this, ExpandableListViewStarBucks.class);
		spec = tabHost.newTabSpec("tab1").setIndicator("\nSTARBUCKS",getResources().getDrawable(R.drawable.starbucks_tab_image)).setContent(intent);
		tabHost.addTab(spec);
		
		//µÎ ¹øÂ° ÅÇ
		intent = new Intent(this, ExpandableListViewHollyCoffe.class);
		spec = tabHost.newTabSpec("tab2").setIndicator("\nHOLLYS",getResources().getDrawable(R.drawable.hollys_tab_image)).setContent(intent);
		tabHost.addTab(spec);
		
		intent = new Intent(this, ExpandableListViewCoffeBene.class);
		spec = tabHost.newTabSpec("tab3").setIndicator("\nCoffe Bene",getResources().getDrawable(R.drawable.coffebene_tab_image)).setContent(intent);
		tabHost.addTab(spec);
		
		//¼¼ ¹øÂ° ÅÇ
		intent = new Intent(this, ExpandableListViewTomnToms.class);
		spec = tabHost.newTabSpec("tab4").setIndicator("\nTomnToms",getResources().getDrawable(R.drawable.tomntoms_tab_image)).setContent(intent);
		tabHost.addTab(spec);
		
		
		intent = new Intent(this, ExpandableListViewAngel.class);
		spec = tabHost.newTabSpec("tab5").setIndicator("\nAngel in-us",getResources().getDrawable(R.drawable.angel_tab_image)).setContent(intent);
		tabHost.addTab(spec);
		
		intent = new Intent(this, ExpandableListViewPasscucci.class);
		spec = tabHost.newTabSpec("tab6").setIndicator("\nPASSCUCCI",getResources().getDrawable(R.drawable.pascucci_tab_image)).setContent(intent);
		tabHost.addTab(spec);
		
		intent = new Intent(this, ExpandableListViewCoffeBean.class);
		spec = tabHost.newTabSpec("tab7").setIndicator("\nCOFFE BEAN",getResources().getDrawable(R.drawable.coffebean_tab_image)).setContent(intent);
		tabHost.addTab(spec);
		
		intent = new Intent(this, ExpandableListViewCoffinegurunaru.class);
		spec = tabHost.newTabSpec("tab8").setIndicator("\nCOFFINE GURUNARU",getResources().getDrawable(R.drawable.coffine_tab_image)).setContent(intent);
		tabHost.addTab(spec);
		
		intent = new Intent(this, ExpandableListViewTwosomePlace.class);
		spec = tabHost.newTabSpec("tab8").setIndicator("\nTWOSOME PLACE",getResources().getDrawable(R.drawable.twosome_tab_image)).setContent(intent);
		tabHost.addTab(spec);

		intent = new Intent(this, ExpandableListViewEdiya.class);
		spec = tabHost.newTabSpec("tab8").setIndicator("\nEDIYA",getResources().getDrawable(R.drawable.ediya_tab_image)).setContent(intent);
		tabHost.addTab(spec);
		
		intent = new Intent(this, ExpandableListViewDropTop.class);
		spec = tabHost.newTabSpec("tab8").setIndicator("\nCAFE DROPTOP",getResources().getDrawable(R.drawable.droptop_tab_image)).setContent(intent);
		tabHost.addTab(spec);
		
	 	//tabHost.addTab(tabHost.newTabSpec("tab1").setIndicator("starbucks",getResources().getDrawable(R.drawable.ic_launcher)).setContent(new Intent(this, ExpandableListViewStarBucks.class)));
        //tabHost.addTab(tabHost.newTabSpec("tab2").setIndicator("Hollys Coffe",getResources().getDrawable(R.drawable.hollys)).setContent(new Intent(this, ExpandableListViewHollyCoffe.class)));
        //tabHost.addTab(tabHost.newTabSpec("tab3").setIndicator("Tom n Toms").setContent(new Intent(this, ExpandableListViewTomnToms.class)));

		
		tabHost.setOnTabChangedListener(new OnTabChangeListener() {
			
			@Override
			public void onTabChanged(String tabId) {
				
			for(int i=0;i<getTabHost().getTabWidget().getChildCount();i++) {
				getTabHost().getTabWidget().getChildAt(i).setBackgroundColor(Color.parseColor("#F5F4EC")); //unselected
				TextView tv = (TextView) getTabHost().getTabWidget().getChildAt(i).findViewById(android.R.id.title);
				tv.setTextColor(Color.parseColor("#563A3F"));
			}
			getTabHost().getTabWidget().getChildAt(getTabHost().getCurrentTab()).setBackgroundColor(Color.parseColor("#563A3F"));
			TextView tv = (TextView) getTabHost().getTabWidget().getChildAt(getTabHost().getCurrentTab()).findViewById(android.R.id.title);
			tv.setTextColor(Color.parseColor("#F5F4EC"));
		
			
			
			int i = getTabHost().getCurrentTab();
			 Log.i("@@@@@@@@ ANN CLICK TAB NUMBER", "------" + i);

			    if (i == 0) {
			            Log.i("@@@@@@@@@@ Inside onClick tab 0", "onClick tab");

			    }
			    else if (i ==1) {
			            Log.i("@@@@@@@@@@ Inside onClick tab 1", "onClick tab");
			    }

			  }
			});
		
		
        //tabHost.getTabWidget().getChildAt(0).setBackgroundColor(R.drawable.starbucks_tab_image);
        //tabHost.getTabWidget().getChildAt(1).setBackgroundResource(R.drawable.starbucks_background_image);
		//tabHost.getTabWidget().getChildAt(0).getLayoutParams().height = 110;
		for(int tab=0; tab< tabHost.getTabWidget().getChildCount(); tab++){
			getTabHost().getTabWidget().getChildAt(tab).setBackgroundColor(Color.parseColor("#F5F4EC"));
			tabHost.getTabWidget().getChildAt(tab).getLayoutParams().width = 250;
			TextView tv = (TextView) getTabHost().getTabWidget().getChildAt(tab).findViewById(android.R.id.title);
			tv.setTextColor(Color.parseColor("#563A3F"));
		}
		
		//Ã¹ ¹øÂ° ÅÇ Selected
		getTabHost().getTabWidget().getChildAt(0).setBackgroundColor(Color.parseColor("#563A3F"));
		TextView tv = (TextView) getTabHost().getTabWidget().getChildAt(0).findViewById(android.R.id.title);
		tv.setTextColor(Color.parseColor("#F5F4EC"));
		
        //for(int tab=0; tab< tabHost.getTabWidget().getChildCount(); tab++){
       // 	tabHost.getTabWidget().getChildAt(tab).getLayoutParams().height = 130;
        //}
		adview = (MobileAdView) findViewById(R.id.adview1);
		adview.setListener(new MobileAdListener() {
			@Override
			public void onReceive(int arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		adview.setTest(true);
		adview.setChannelID("mandroid_df99eaa751ae4a9db65b7c09a00641b4");
		adview.start();
		
	}
	
	
	
	
	
	public static void setTabColor(TabHost tabhost) {
	    for(int i=0;i<tabhost.getTabWidget().getChildCount();i++) {
	        tabhost.getTabWidget().getChildAt(i).setBackgroundColor(Color.parseColor("#563A3F")); //unselected
	    }
	    tabhost.getTabWidget().getChildAt(tabhost.getCurrentTab()).setBackgroundColor(Color.parseColor("#F5F4EC")); // selected
	}
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
		
		if (adview != null) {
			adview.destroy();
			adview = null;
		}
		
	}
	
	public void onDraw(Canvas canvas){
		Bitmap bit = BitmapFactory.decodeResource(getResources(), R.drawable.tutorial);
		canvas.drawBitmap(bit,  0, 0, null);


	}
	
	
	

   
}