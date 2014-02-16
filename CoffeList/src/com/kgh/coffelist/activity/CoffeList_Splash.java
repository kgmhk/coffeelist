package com.kgh.coffelist.activity;


import com.kgh.coffelist.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class CoffeList_Splash extends Activity{

	 @Override
	    protected void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.coffelistsplash);
	         
	        Handler hd = new Handler();
	        hd.postDelayed(new Runnable() {
	 
	            @Override
	            public void run() {
	                //finish();       // 3 초후 이미지를 닫아버림
	            	//Intent intent = new Intent(CoffeList_Splash.this, Tab.class);
	            	//startActivity(intent);
	            	finish();
	            }
	        }, 3000);
	 
//	      Handler handler = new Handler() {
//	          public void handleMessage(android.os.Message msg) {
//	              finish();
//	          }
//	      };
//	      handler.sendEmptyMessageDelayed(0, 3000);       
	    }
}
