package com.kgh.coffelist.activity;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.ExpandableListView.OnGroupClickListener;
import android.widget.ExpandableListView.OnGroupCollapseListener;
import android.widget.ExpandableListView.OnGroupExpandListener;
import android.widget.Toast;

import com.kgh.coffelist.R;
import com.google.analytics.tracking.android.EasyTracker;
import com.kgh.coffelist.adapter.BaseExpandableAdapter;


public class ExpandableListViewStarBucks extends Activity implements LocationListener{
	
	//location
	private LocationManager locManager;
	Geocoder geoCoder;
	private Location myLocation = null;
	double latPoint = 0;
	double lngPoint = 0;
	float  speed = 0;
	int clickcheck = 1;
	private int selected = 0;
	
	
	//font setup	
	private Typeface tfsmall;
	private Typeface tfbold;

	private TextView tall;
	private TextView title;
	private TextView inwon;
	private TextView number;
	private TextView check;
	private TextView maptext;
	private TextView other;
	
	
	private ArrayList<String> mGroupList = null;
	private ArrayList<ArrayList<String>> mChildList = null;
	private ArrayList<String> mChildListContent = null;
	private ArrayList<String> mChildListContent2 = null;
	private ArrayList<String> mChildListContent3 = null;
	private ArrayList<String> mChildListContent4 = null;
	private ArrayList<String> mChildListContent5 = null;
	
	//계산???�어�?TextView
	private TextView coffename;
	private TextView coffeprice;
	private TextView coffepriceavr;

	private ImageView iv_image;///////여기까지함
	private EditText dutchcal;
	private String total;
	private int totalint;
	//public int check;
	private View scateList;
	private View fragment;
	private View overlaycalbutton;
	private double totalavr;
	private String totalstring = "";
	private String listname[] = {};
	private String name [][] = {{"오늘의 커피","아이스 커피"},{"에스프레소","에스프레소 마키아또","에스프레소 콘 파나","아이스 카페 아메리카노",
		"카페 아메리카노","두유 카페 라떼","아이스 두유 카페 라떼","아이스 카페 라떼","아이스 카푸치노","카페 라떼","카푸치노","스타벅스 더블 샷","바닐라 라떼",
		"아이스 바닐라 라떼","아이스 카페 모카","아이스 코코아 카푸치노","카페 모카","코코아 카푸치노","리스트레토 비안코","스타벅스 돌체 라떼","아이스 스타벅스 돌체 라떼",
		"아이스 카라멜 마키아또","카라멜 마키아또","아이스 화이트 초콜릿 모카","화이트 초콜릿 모카","카라멜 리스트레토 비안코"},{"바닐라 크림 프라푸치노","커피 프라푸치노",
			"라스베리 블랙커런트 블렌디드 주스","망고 패션 후르츠 블렌디드 주스","에스프레소 프라푸치노","초콜릿 크림 프라푸치노","카라멜 크림 프라푸치노","차이 티 크림 프라푸치노",
			"딸기 레몬 블렌디드 주스","딸기 크림 프라푸치노","모카 프라푸치노","카라멜 프라푸치노","에스프레소 칩 프라푸치노","초콜릿 크림 칩 프라푸치노","화이트 초콜릿 모카 프라푸치노",
			"자바 칩 프라푸치노","그린 티 크림 프라푸치노","라스베리 바나나 프라푸치노","망고 바나나 프라푸치노","초콜릿 바나나 프라푸치노"},{"라벤더 얼그레이 티","민트 블렌드 티","바닐라 루이보스 티","아이스 바렌더 얼 그레이 티",
				"아이스 민트 블렌드 티","아이스 바닐라 루이보스 티","아이스 잉글리쉬 브렉퍼스트 티","아이스 차이 티","아이스 캐모마일 블렌드 티","아이스 히비스커스 블렌드 티","잉글리쉬 브렉퍼스트 티",
				"차이 티","캐모마일 블렌드 티","히비스커스 블렌드 티","아이스 쉐이큰 레몬 그라스 젠 티","아이스 쉐이큰 스위트 오렌지 블랙 티","아이스 쉐이큰 패션 후르츠 티",
				"레몬그라스 젠 티 레모네이드","스위트 오렌지 블랙 티 레모네이드","패션 후르츠 티 레모네이드","라벤더 얼그레이 티 라떼","바닐라 루이보스 티 라떼","아이스 라벤더 얼그레이 티 라떼",
				"아이스 바닐라 루이보스 티 라떼","아이스 잉글리쉬 브렉퍼스트 티 라떼","잉글리쉬 브렉퍼스트 티 라떼","제주 녹차","아이스 차이 티 라떼","차이 티 라떼","그린 티 라떼",
				"아이스 그린 티 라떼","아이스 에스프레소 샷 그린 티 라떼 라이트","에스프레소 샷 그린 티 라데 라이트"},{"스팀 밀크","우유","핫 초콜릿","화이트 핫 초콜릿",
					"시그니처 핫 초콜릿","아이스 시그니처 초콜릿","카라멜 시그니처 핫 초콜릿","헤이즐넛 시그니처 핫 초콜릿"}};
	private int price [][] = {{3600,3900},
			{3400,3600,3600,3900,3900,4400,4400,4400,4400,4400,4400,
			4600,4900,4900,4900,4900,4900,4900,5000,5400,5400,5400,5400,
			5500,5500,5600},{4600,4600,4800,4800,5100,5100,5100,5200,5600,5600,5600,5600,5700,
				5700,5700,5900,6100,6300,6300,6300},{3900,3900,3900,3900,3900,3900,3900,3900,3900,3900,
					3900,3900,3900,3900,4200,4200,4200,4800,4800,4800,4900,4900,4900,4900,4900,4900,4900,
					5100,5100,5900,5900,5900,5900},{3900,3900,4400,5000,5100,5100,5100,5100}};
			
	//계산?�서 초기??버튼
	private Button b1;
	private Button calbutton;
	private Button map;
	private Button movebutton;
	private Button young;
	private Button movebutton1;
	
		SharedPreferences setting;
	SharedPreferences.Editor editor;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		//?�이?�웃 겹치�?		
		Window win = getWindow();
		win.setContentView(R.layout.list_main); //�?번째 메인 ?�이?�웃
		
		LayoutInflater inflater = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		LinearLayout linear = (LinearLayout)inflater.inflate(R.layout.overlaybutton, null);
		
		LinearLayout.LayoutParams paramlinear = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT);
		win.addContentView(linear, paramlinear);
		
		//setContentView(R.layout.list_main);
	
		
		float screenWidth = getContext().getResources().getDisplayMetrics().widthPixels;
		float screenHeight = getContext().getResources().getDisplayMetrics().heightPixels;
		Log.i("screenWidth : ", "" + screenWidth);
		Log.i("screenHeight : ", "" + screenHeight);
		
		overlaycalbutton = (View)findViewById(R.id.overcalbutton);
		LinearLayout.LayoutParams calbutton1 = (android.widget.LinearLayout.LayoutParams) overlaycalbutton.getLayoutParams();
		
		/*calbutton1.leftMargin = ((int)screenWidth/5)*4;
		Log.i("leftmargin : ", "" + calbutton1.leftMargin);
		calbutton1.topMargin =  ((int)screenHeight/100)*80;
		Log.i("topMargin : ", "" + calbutton1.topMargin);
		*/
		setLayout();

		
		
		//location ?�정
        locManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
       // locManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 5, this);
        locManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 1000, 5, this);
 
        geoCoder = new Geocoder(this, Locale.KOREAN); 
		
		
		setting = getSharedPreferences("setting", 0);
		editor = setting.edit();
		//fragment??값넣�?TextView?�이??�?�?��?�기
		coffename = (TextView) findViewById(R.id.coffename);
		coffeprice = (TextView) findViewById(R.id.coffeprice);
		coffepriceavr = (TextView) findViewById(R.id.coffepriceavr);
		b1 = (Button) findViewById(R.id.inital);
		calbutton = (Button) findViewById(R.id.calbutton);
		dutchcal = (EditText)findViewById(R.id.dutchcal);
		map = (Button) findViewById(R.id.map);
		movebutton = (Button) findViewById(R.id.overcalbutton1);
		movebutton1 = (Button) findViewById(R.id.overcalbutton);
		young = (Button) findViewById(R.id.youngyaung);
		
		movebutton.setVisibility(View.INVISIBLE);
		
		
		
		//font setup
		tall = (TextView) findViewById(R.id.tall);
		title = (TextView) findViewById(R.id.title);
		inwon = (TextView) findViewById(R.id.inwon);
		number = (TextView) findViewById(R.id.number);
		check = (TextView) findViewById(R.id.check);
		
		
		tfsmall = Typeface.createFromAsset(getAssets(),"fontbold.ttf");
		tfbold = Typeface.createFromAsset(getAssets(), "fontbold.ttf");
		
		maptext = (TextView) findViewById(R.id.maptext);
		other = (TextView) findViewById(R.id.other);
		
		
		maptext.setTypeface(tfsmall);
		other.setTypeface(tfsmall);
		coffepriceavr.setTypeface(tfsmall);
		coffeprice.setTypeface(tfsmall);
		title.setTypeface(tfbold);
		tall.setTypeface(tfbold);
		inwon.setTypeface(tfsmall);
		number.setTypeface(tfsmall);
		check.setTypeface(tfbold);
		coffename.setTypeface(tfsmall);
		
		check.setMovementMethod(ScrollingMovementMethod.getInstance());
		coffename.setMovementMethod(ScrollingMovementMethod.getInstance());
		
		other.setText("영양 정보 :");
		
		// ExpandableListView ?�정
		mGroupList = new ArrayList<String>();
		mChildList = new ArrayList<ArrayList<String>>();
		mChildListContent = new ArrayList<String>(); 
		mChildListContent2 = new ArrayList<String>();
		mChildListContent3 = new ArrayList<String>();
		mChildListContent4 = new ArrayList<String>();
		mChildListContent5 = new ArrayList<String>();
		
		mGroupList.add("브루드 커피");
		mGroupList.add("에스프레소");
		mGroupList.add("프라푸치노");
		mGroupList.add("티");
		mGroupList.add("기타음료");
		//�?번째 그룹??차일??		
		for(int i=0; i<name[0].length; i++){
			mChildListContent.add(name[0][i] + "\n" + price[0][i] + "원");
		}
		//??번째 그룹??차일??		
		for(int i=0; i<name[1].length; i++){
			mChildListContent2.add(name[1][i] + "\n" + price[1][i] + "원");
		}
		
		//?�번�?그룹??차일??		
		for(int i=0; i<name[2].length; i++){
			mChildListContent3.add(name[2][i] + "\n" + price[2][i] + "원");
		}
		//??번째 차일??		
		for(int i=0; i<name[3].length; i++){
			mChildListContent4.add(name[3][i] + "\n" + price[3][i] + "원");
		}
		for(int i=0; i<name[4].length; i++){
			mChildListContent5.add(name[4][i] + "\n" + price[4][i] + "원");
		}
		
		mChildList.add(mChildListContent);
		mChildList.add(mChildListContent2);
		mChildList.add(mChildListContent3);
		mChildList.add(mChildListContent4);
		mChildList.add(mChildListContent5);
		
		mListView.setAdapter(new BaseExpandableAdapter(this, mGroupList, mChildList));
		
		// dutch Text Setting
		//dutch.setText("?�치 계산");
		
		
	      
		
		// 계산 버튼 ?�릭 ??		
		calbutton.setOnClickListener(new Button.OnClickListener(){
			public void onClick(View v){
				//coffepriceavr.setText(inwonstr);
				int inwon = Integer.parseInt(dutchcal.getText().toString());
				if(inwon == 0){
					coffepriceavr.setText("인원을 입력 하세요.");
				}
				else{
				totalavr = totalint / inwon;
				String avrstr = Integer.toString((int)totalavr);
				coffepriceavr.setText("---------------\n" +"총 인원 : " + inwon + "명\n" + "1  인당: " +avrstr +"원");
				}
			}
		});
	
		
		movebutton1.setOnClickListener(new OnClickListener(){
			public void onClick(View v){
				//Toast.makeText(ExpandableListViewStarBucks.this, "등록되었습니다", Toast.LENGTH_SHORT).show(); 
				chgLayoutDisplay();
			}
		});
		
			
		// 계산�?fragment move
		movebutton.setOnClickListener(new Button.OnClickListener(){
			public void onClick(View v){
				chgLayoutDisplay();
			}
		});
		
		// ?�양 ?�보 버튼
		young.setOnClickListener(new Button.OnClickListener(){
			public void onClick(View v){
				Intent intent = new Intent(Intent.ACTION_VIEW);
				Uri u;
				u = Uri.parse("http://www.istarbucks.co.kr/Menu/product_list.asp?Prod=P020200");
				intent.setData(u);
				startActivity(intent);
				
			}
		});
		
		
		map.setOnClickListener(new Button.OnClickListener(){
			public void onClick(View v){
				Log.d("button click", "button");
				//GetLocations();
				
				StringBuffer juso = new StringBuffer();

				Log.d("getlocation in", "in");
				
				if (myLocation != null) {
					latPoint = myLocation.getLatitude();
					lngPoint = myLocation.getLongitude();
					Log.d("latPoint", String.valueOf(latPoint));
					//speed = (float)(myLocation.getSpeed() * 3.6);
				try {
					Log.d("juso in", String.valueOf(latPoint));
					Log.d("juso in", String.valueOf(lngPoint));
						// �꾨�?寃쎈룄瑜��?�슜�섏�?�꾩??�꾩?���二?�냼?��媛�졇�?�떎. 
						List<Address> addresses;
						addresses = geoCoder.getFromLocation(latPoint, lngPoint, 1);
						Log.d("addresses after", String.valueOf(lngPoint));
						for(Address addr: addresses){
							Log.d("for in", String.valueOf(lngPoint));
							int index = addr.getMaxAddressLineIndex();
							for(int i=0;i<=index;i++){
								Log.d("2:for in", String.valueOf(lngPoint));
								juso.append(addr.getAddressLine(i));
								juso.append(" ");
							}
							Log.d("1:for end", String.valueOf(lngPoint));
							juso.append("\n");
						}
						Log.d("2:for end", String.valueOf(lngPoint));
					} catch (IOException e) {
						e.printStackTrace();
					}
					String juso1 = String.valueOf(juso);
					
					Uri u;
					u = Uri.parse("https://maps.google.co.kr/?q=스타벅스&near=" + juso1 +"&radius=1");
					Intent intent = new Intent(Intent.ACTION_VIEW,u);
					//intent.setData(u);
					startActivity(intent);
						
				}		
				
				
			}
		});
		

		
		
		//초기??버튼 ?�릭 ??		
		b1.setOnClickListener(new Button.OnClickListener(){
			public void onClick(View v){
				dutchcal.setText("0");
				totalint = 0;
				totalstring = "";
				total = "";				
				coffename.setText("");
				coffepriceavr.setText("");
				coffeprice.setText("");
				selected = 0;
			}
		});
		
		// 그륩???�릭?�을 ??
		mListView.setOnGroupClickListener(new OnGroupClickListener() {
			//@Override
			public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
			
				return false;
			}
		});
		
		// 차일???�릭 ?�을 ??
		mListView.setOnChildClickListener(new OnChildClickListener() {
			
			@Override
			public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
				
		
				selected++;
				
				//if(selected > 15){
				//	Toast.makeText(ExpandableListViewStarBucks.this, "더 이상 선택하실 수 없습니다.", Toast.LENGTH_SHORT).show(); 
				//}else{
					
				
				//coffepriceavr.setText(Integer.toString(cnt) + "??);
				totalstring += ">" + name[groupPosition][childPosition] + "\n";
				//listname[childPosition] = totalstring;
				totalint += price[groupPosition][childPosition];
				total = Integer.toString(totalint);
				coffename.setText(totalstring);
				coffeprice.setText("총 금액 : " + total +"원");
				//Log.d("count", coffename.get);
				//mListView = (ExpandableListView) findViewById(R.id.elv_list);
				
				//}
				return false;
			}
		});
		
		// 그룹???�힐 경우	
		mListView.setOnGroupCollapseListener(new OnGroupCollapseListener() {
			@Override
			public void onGroupCollapse(int groupPosition) {
				
			}
		});
		
		// 그룹???�릴 경우		
		mListView.setOnGroupExpandListener(new OnGroupExpandListener() {
			@Override
			public void onGroupExpand(int groupPosition) {
			
			}
		});
	}
	
	public void chgLayoutDisplay(){

		scateList = (View)findViewById(R.id.elv_list);
		fragment = (View)findViewById(R.id.fragment2);
		LinearLayout.LayoutParams f = (android.widget.LinearLayout.LayoutParams) fragment.getLayoutParams();
		
		LinearLayout.LayoutParams p = (android.widget.LinearLayout.LayoutParams) scateList.getLayoutParams();
		float weight = p.weight;
		
		int h = p.width;
		int i = 0;
		if(f.weight < 9){
			p.weight = 1;
			f.weight = 9;
			Log.d("p.weight < 0.9", String.valueOf(p.weight));
			scateList.setLayoutParams(p);
		}else{
			
			//p.width -= 0.000005;
			p.weight = (float) 4.5;
			f.weight = (float) 6.5;
			Log.d("p.weight > 1", String.valueOf(p.weight));
			scateList.setLayoutParams(p);
		}
	}
	
	

	public void GetLocations() {
		
		/*StringBuffer juso = new StringBuffer();

		Log.d("getlocation in", "in");
		
		if (myLocation != null) {
			latPoint = myLocation.getLatitude();
			lngPoint = myLocation.getLongitude();
			Log.d("latPoint", String.valueOf(latPoint));
			//speed = (float)(myLocation.getSpeed() * 3.6);
		try {
			Log.d("juso in", String.valueOf(latPoint));
			Log.d("juso in", String.valueOf(lngPoint));
				// �꾨�?寃쎈룄瑜��?�슜�섏�?�꾩??�꾩?���二?�냼?��媛�졇�?�떎. 
				List<Address> addresses;
				addresses = geoCoder.getFromLocation(latPoint, lngPoint, 1);
				Log.d("addresses after", String.valueOf(lngPoint));
				for(Address addr: addresses){
					Log.d("for in", String.valueOf(lngPoint));
					int index = addr.getMaxAddressLineIndex();
					for(int i=0;i<=index;i++){
						Log.d("2:for in", String.valueOf(lngPoint));
						juso.append(addr.getAddressLine(i));
						juso.append(" ");
					}
					Log.d("1:for end", String.valueOf(lngPoint));
					juso.append("\n");
				}
				Log.d("2:for end", String.valueOf(lngPoint));
			} catch (IOException e) {
				e.printStackTrace();
			}
			String juso1 = String.valueOf(juso);
			
			Uri u;
			u = Uri.parse("https://maps.google.co.kr/?q=스타벅스&near=" + juso1 +"&radius=1");
			Intent intent = new Intent(Intent.ACTION_VIEW,u);
			//intent.setData(u);
			startActivity(intent);
				
		}		
			*/
	}

	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {

	if (keyCode == KeyEvent.KEYCODE_MENU) {
		//Toast.makeText(ExpandableListViewStarBucks.this, "메뉴", Toast.LENGTH_SHORT).show();
	return false;
	}
	
	if (keyCode == KeyEvent.KEYCODE_SETTINGS) {
		Toast.makeText(ExpandableListViewStarBucks.this, "셋팅", Toast.LENGTH_SHORT).show();
	return false;
	}

	return super.onKeyDown(keyCode, event);
	}


	public void onLocationChanged(Location location) {
		Log.d("location", "location changed");
		myLocation = location;
	}

	public void onProviderDisabled(String s) {

	}

	public void onProviderEnabled(String s) {

	}

	public void onStatusChanged(String s, int i, Bundle bundle) {

	}

	@Override
	  public void onStart() {
	    super.onStart();
	  
	    EasyTracker.getInstance(this).activityStart(this);  // Add this method.
	  }

	  @Override
	  public void onStop() {
	    super.onStop();
	    
	    EasyTracker.getInstance(this).activityStop(this);  // Add this method.
	  }
	/*
	 * Layout
	 */
	  private Context getContext() 
	  {
		  return this;
	  }

	private ExpandableListView mListView;

	private void setLayout(){
		mListView = (ExpandableListView) findViewById(R.id.elv_list);
	}
}