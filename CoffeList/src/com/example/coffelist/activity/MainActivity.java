package com.example.coffelist.activity;

import com.example.coffelist.R;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Window;
import android.widget.TabHost;
import android.app.Activity;
import android.app.FragmentTransaction;
import android.app.TabActivity;
import android.content.Intent;
import android.graphics.Color;

public class MainActivity extends TabActivity{

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
		spec = tabHost.newTabSpec("tab1").setIndicator("\nSTARBUCKS COFFE",getResources().getDrawable(R.drawable.starbucks_tab_image)).setContent(intent);
		tabHost.addTab(spec);
		
		//µÎ ¹øÂ° ÅÇ
		intent = new Intent(this, ExpandableListViewHollyCoffe.class);
		spec = tabHost.newTabSpec("tab2").setIndicator("\nHOLLYS COFFE",getResources().getDrawable(R.drawable.hollys_tab_image)).setContent(intent);
		tabHost.addTab(spec);
		
		intent = new Intent(this, ExpandableListViewCoffeBene.class);
		spec = tabHost.newTabSpec("tab3").setIndicator("\nCoffe Bene",getResources().getDrawable(R.drawable.coffebene_tab_image)).setContent(intent);
		tabHost.addTab(spec);
		
		//¼¼ ¹øÂ° ÅÇ
		intent = new Intent(this, ExpandableListViewTomnToms.class);
		spec = tabHost.newTabSpec("tab4").setIndicator("\nTomnToms",getResources().getDrawable(R.drawable.tomntoms_tab_image)).setContent(intent);
		tabHost.addTab(spec);
		
		
		intent = new Intent(this, ExpandableListViewAngel.class);
		spec = tabHost.newTabSpec("tab4").setIndicator("\nAngel in-us",getResources().getDrawable(R.drawable.angel_tab_image)).setContent(intent);
		tabHost.addTab(spec);
		

		
	 	//tabHost.addTab(tabHost.newTabSpec("tab1").setIndicator("starbucks",getResources().getDrawable(R.drawable.ic_launcher)).setContent(new Intent(this, ExpandableListViewStarBucks.class)));
        //tabHost.addTab(tabHost.newTabSpec("tab2").setIndicator("Hollys Coffe",getResources().getDrawable(R.drawable.hollys)).setContent(new Intent(this, ExpandableListViewHollyCoffe.class)));
        //tabHost.addTab(tabHost.newTabSpec("tab3").setIndicator("Tom n Toms").setContent(new Intent(this, ExpandableListViewTomnToms.class)));

        //tabHost.getTabWidget().getChildAt(0).setBackgroundColor(R.drawable.starbucks_tab_image);
		//tabHost.getTabWidget().getChildAt(0).getLayoutParams().height = 110;
		for(int tab=0; tab< tabHost.getTabWidget().getChildCount(); tab++){
			tabHost.getTabWidget().getChildAt(tab).getLayoutParams().width = 250;
		}
        //for(int tab=0; tab< tabHost.getTabWidget().getChildCount(); tab++){
       // 	tabHost.getTabWidget().getChildAt(tab).getLayoutParams().height = 130;
        //}
	}
   
}