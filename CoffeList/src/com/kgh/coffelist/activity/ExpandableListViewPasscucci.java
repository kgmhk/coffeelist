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
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ExpandableListView;
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


public class ExpandableListViewPasscucci extends Activity implements LocationListener{
	
	//location
	private LocationManager locManager;
	Geocoder geoCoder;
	private Location myLocation = null;
	double latPoint = 0;
	double lngPoint = 0;
	float  speed = 0;
	int clickcheck = 1;
	
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
	private EditText dutchcal;
	private String total;
	private int totalint;
	private View scateList;
	private View fragment;
	private double totalavr;
	private String totalstring = "";
	private String listname[] = {};
	private String name [][] = {{"모히또 카라멜 라떼","오리지널 드립커피","아메리카노","카페라떼","카라멜 라떼 마끼아또","바닐라 라떼 마끼아또",
		"화이트 초콜릿 라떼 마끼아또","카페모카","카라멜 모카","티라미스 모카","카푸치노","헤이즐넛 카푸치노","에스프레소 마끼아또","에스프레소 도피오",
		"에스프레소","쏠티 아포가또","블랙 슈가 아포가또","아이스 오리지널 드립커피","아이스 아메리카노","아이스 카페라떼","아이스 카라멜라떼 마끼아또",
		"아이스 바날라라떼 마끼아또","아이스 화이트초콜릿라떼 마끼아또","아이스 카페모카","아이스 카라멜모카","아이스 티라미스모카"},{"핫 자몽 주스","핫 오렌지 주스","자색 고구마 라떼","초코차이 라떼","핫초콜릿","그린티 라떼",
			"잉글리쉬 블랙퍼스트 티라떼","녹차","홍차","허브차","자바칩 모히또 그라니따","레몬 모히또 그라니따","그린티 그라니따","그라니따(모카)",
			"그라니따(카라멜)","그라니따(딸기)","그라니따(홍시)","요거트 젤라또(플레인)","요거트 젤라또(딸기)","요거트 젤라또(블루베리)",
			"후레쉬 주스(키위)","후레쉬 주스(토마토)","에이드(레몬)","에이드(오렌지)","에이드(자몽)","아이스티(스위트/라즈베리)",
			"아이스초콜릿","아이스그린티라떼","아이스 잉글리쉬 블랙퍼스트 티라떼","레드빈 그라니따","그린 레드빈 그라니따","흑임자 레드빈 그라니따"},
			{"달콤한 하트 케익","사랑의 마법 케익","데블스초코 케익","NEW 치즈티라미수","블루베리 치즈케익","라이트 초코 무스케익","수플레 치즈케익",
				"진한 초코 퍼지 케익","티라미수 컵 케익","스파이시 튜나 랩","치킨 비트 랩","파니니 에그감자","파니니 크랜베리 클럽","파니니 불고기",
				"파니니 치킨로제","파니니 클래식","파니니 파스트라미","파니니 토스트","파니니 크림치즈(베이글)","파니니 깨베이글","더블 치즈 스콘",
				"피칸 초코 스콘","오리지널 스콘","블루베리 베이글 & 크림치즈","어니언 베이글 & 크림치즈","플레인 베이글 & 크림치즈","허니 브래드+생크림",
				"허니 브래드+젤라또","블루베리 & 체리머핀","블루베리 타르트","촉촉치즈","초코 퐁당","잇츠 마이 브라우니","시나몬 초코칩 쿠키",
				"뛰일 아몬드","리얼 초코 쿠키","화이트마카다미아쿠키","파스쿠찌 로스팅 빈 초콜릿","파스쿠찌 아몬드 볼 초콜릿","호두레몬쿠키","홍차쿠키",
				"크런치 아몬드 초콜릿 밀크","크런치 아몬드 초콜릿 다크"},{"딸기","레몬","바닐라","아마레나 체리","요거트","초콜릿","티라미수"}};
	private int price [][] = {{5500,3900,4000,4500,5000,5000,5000,5000,5500,5500,4500,5000,3800,3800,3300,
		4300,4300,3900,4000,4500,5000,5000,5000,5000,5500,5500},{5500,5500,5300,5300,4500,5000,5000,4500,4500,4500,
			6000,6000,6000,6000,6000,6000,6000,6000,6000,6000,6000,6000,5000,5000,5000,4000,4500,5000,
			5000,6500,6500,6500},{14000,14000,5000,5000,5000,5200,4500,4500,4800,5500,5500,4800,4500,5500,5200,5000,
				5500,5000,5000,5500,3000,3000,3000,3300,3300,3300,5700,6200,2800,2500,3500,3500,3000,4500,4500,
				1800,1800,7500,7500,3000,3000,2300,2300},{3000,3000,3000,3000,3000,3000,3000}};
			
	//계산?�서 초기??버튼
	private Button b1;
	private Button calbutton;
	private Button map;
	private Button movebutton;
	private Button young;
	
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
		movebutton = (Button) findViewById(R.id.overcalbutton);
		young = (Button) findViewById(R.id.youngyaung);
		
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
		
		other.setText("제휴정보 :");
		// ExpandableListView ?�정
		mGroupList = new ArrayList<String>();
		mChildList = new ArrayList<ArrayList<String>>();
		mChildListContent = new ArrayList<String>(); 
		mChildListContent2 = new ArrayList<String>();
		mChildListContent3 = new ArrayList<String>();
		mChildListContent4 = new ArrayList<String>();
		mChildListContent5 = new ArrayList<String>();

		mGroupList.add("커피");
		mGroupList.add("음료");
		mGroupList.add("베이커리");
		mGroupList.add("젤라또");
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
		
		mChildList.add(mChildListContent);
		mChildList.add(mChildListContent2);
		mChildList.add(mChildListContent3);
		mChildList.add(mChildListContent4);

		
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
	
		// 계산�?fragment move
		movebutton.setOnClickListener(new Button.OnClickListener(){
			public void onClick(View v){
				chgLayoutDisplay();
			}
		});
		//young.setText("제휴정보");
		// ?�양 ?�보 버튼
		young.setOnClickListener(new Button.OnClickListener(){
			public void onClick(View v){
				Intent intent = new Intent(Intent.ACTION_VIEW);
				Uri u;
				u = Uri.parse("http://www.caffe-pascucci.co.kr/pages/customer/cooperativeBenefit.asp");
				intent.setData(u);
				startActivity(intent);
				
			}
		});
		
		
		map.setOnClickListener(new Button.OnClickListener(){
			public void onClick(View v){
				Log.d("button click", "button");
				GetLocations();
				
				
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
			}
		});
		
		// 그륩???�릭?�을 ??
		mListView.setOnGroupClickListener(new OnGroupClickListener() {
			//@Override
			public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
				Toast.makeText(getApplicationContext(), "g click = " + groupPosition, 
						Toast.LENGTH_SHORT).show();
				return false;
			}
		});
		
		// 차일???�릭 ?�을 ??
		mListView.setOnChildClickListener(new OnChildClickListener() {
			
			@Override
			public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
				Toast.makeText(getApplicationContext(), "c click = " + childPosition, 
						Toast.LENGTH_SHORT).show();
		
				
				//coffepriceavr.setText(Integer.toString(cnt) + "??);
				totalstring += "◎" + name[groupPosition][childPosition] + "\n";
				//listname[childPosition] = totalstring;
				totalint += price[groupPosition][childPosition];
				total = Integer.toString(totalint);
				coffename.setText(totalstring);
				coffeprice.setText("총 금액 : " + total +"원");
				//Log.d("count", coffename.get);
				//mListView = (ExpandableListView) findViewById(R.id.elv_list);
				
				
				return false;
			}
		});
		
		// 그룹???�힐 경우	
		mListView.setOnGroupCollapseListener(new OnGroupCollapseListener() {
			@Override
			public void onGroupCollapse(int groupPosition) {
				Toast.makeText(getApplicationContext(), "g Collapse = " + groupPosition, 
						Toast.LENGTH_SHORT).show();
			}
		});
		
		// 그룹???�릴 경우		
		mListView.setOnGroupExpandListener(new OnGroupExpandListener() {
			@Override
			public void onGroupExpand(int groupPosition) {
				Toast.makeText(getApplicationContext(), "g Expand = " + groupPosition, 
						Toast.LENGTH_SHORT).show();
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
			p.weight = 0;
			f.weight = 10;
			Log.d("p.weight < 0.9", String.valueOf(p.weight));
			scateList.setLayoutParams(p);
		}else{
			
			//p.width -= 0.000005;
			p.weight = (float) 3.5;
			f.weight = (float) 6.5;
			Log.d("p.weight > 1", String.valueOf(p.weight));
			scateList.setLayoutParams(p);
		}
	}

	public void GetLocations() {
		
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
			Intent intent = new Intent(Intent.ACTION_VIEW);
			Uri u;
			u = Uri.parse("https://maps.google.co.kr/?q=파스쿠찌&near=" + juso1);
			intent.setData(u);
			startActivity(intent);
				
		}		
			
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
	private ExpandableListView mListView;

	private void setLayout(){
		mListView = (ExpandableListView) findViewById(R.id.elv_list);
	}
}