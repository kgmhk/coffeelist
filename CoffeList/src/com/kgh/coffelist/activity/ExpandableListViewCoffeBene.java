package com.kgh.coffelist.activity;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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


public class ExpandableListViewCoffeBene extends Activity implements LocationListener{
	
	//location
	private LocationManager locManager;
	Geocoder geoCoder;
	private Location myLocation = null;
	double latPoint = 0;
	double lngPoint = 0;
	float  speed = 0;
	
	
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
	private ArrayList<String> mChildListContent6 = null;
	private ArrayList<String> mChildListContent7 = null;
	private ArrayList<String> mChildListContent8 = null;
	private ArrayList<String> mChildListContent9 = null;
	private ArrayList<String> mChildListContent10 = null;
	
	
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
	private String name [][] = {{"아메리카노","에스프레소","에스프레소(콘파냐/마끼야또)","아포가또","카라멜 마끼아또","카페모카/화이트모카",
		"바닐라라떼/크림카라멜라떼","크림망뜨라떼(민트라떼)","카페라떼","카푸치노"},{"브라질/콜롬비아","케냐/에디오피아"},{"에스프레소 초코칩 프라페노","카라멜 프라페노",
			"모카 프라페노","화이트 모카 프라페노","미숫가루 프라페노","그린티 프라페노","초코칩 프라페노","망뜨초코(민트초코) 프라페노","플레인 스무디",
			"딸기 스무디","블루베리 스무디"},{"미숫가루 라떼","밀크티 라떼","리얼초콜릿 라떼","고구마 라떼","블루베리 라떼","그린티 라떼","우유 라떼"},{"녹차",
				"홍차(잉글리쉬블랙퍼스트/얼그레이)","허브차(케몸일/민트)","과일차(유자차/허니레몬차)","아이스티(복숭아/레몬)"},{"와플(플레인/호두)",
					"생크림 와플(딸기/카라멜 바나나)","요거트 와플(딸기/블루베리)","크림치즈 와플(딸기/블루베리)","Favorite와플(코코넛/젤라또/메이플넛",
					"Plate 와플(베리/후루츠)","카라멜 시나몬 브레드","갈릭&치즈 브레드","초코바나나 브레드","블루베리요거트 브레드"},{"팥빙수","커피빙수",
						"딸기빙수","녹차타워빙수"},{"골든메달리스트","피나콜라다","초코바나나카푸치노","코코피니 오렌지&망고","모히또(오리지널/복분자/알로에)",
							"블루베리블러썸","유자블러썸","상그리아","뺑쇼","에이드"},{"오렌지","자몽","키위","홍시","시즌메뉴(딸기/토마토)"}};
	private int price [][] = {{3800,3500,3800,6000,4800,4800,4800,4800,4300,4300},{6000,7500},{5500,5500,5500,5500
		,5500,5500,5500,5500,5500,5500,5500},{5000,5000,5000,5000,5200,5200,3000},{4000,4000,4000,4000,4500},{2500,4000,
			4000,4500,4500,4500,6000,6000,6000,6700},{8900,9800,9800,9800},{5800,5800,5800,5800,6300,5500,5500,5500,5500,5500},
			{5800,5800,5800,5800,5800}};
			
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
		
		other.setText("영양 정보 :");
		// ExpandableListView ?�정
		mGroupList = new ArrayList<String>();
		mChildList = new ArrayList<ArrayList<String>>();
		mChildListContent = new ArrayList<String>(); 
		mChildListContent2 = new ArrayList<String>();
		mChildListContent3 = new ArrayList<String>();
		mChildListContent4 = new ArrayList<String>();
		mChildListContent5 = new ArrayList<String>();
		mChildListContent6 = new ArrayList<String>();
		mChildListContent7 = new ArrayList<String>();
		mChildListContent8 = new ArrayList<String>();
		mChildListContent9 = new ArrayList<String>();
		mChildListContent10 = new ArrayList<String>();

		mGroupList.add("커피");
		mGroupList.add("싱글 오리진");
		mGroupList.add("프라페노");
		mGroupList.add("라떼");
		mGroupList.add("차");
		mGroupList.add("베이커리");
		mGroupList.add("빙수");
		mGroupList.add("논알콜칵테일");
		mGroupList.add("과일주스");
		//�?번째 그룹??차일??		
		for(int i=0; i<name[0].length; i++){
			mChildListContent.add(name[0][i] + "\n" + price[0][i] + "원");
		}
		Log.d("name1", String.valueOf(name[0].length));
		Log.d("name1", String.valueOf(price[0].length));
		//??번째 그룹??차일??		
		for(int i=0; i<name[1].length; i++){
			mChildListContent2.add(name[1][i] + "\n" + price[1][i] + "원");
		}
		
		Log.d("name2", String.valueOf(name[1].length));
		Log.d("name2", String.valueOf(price[1].length));
		
		//?�번�?그룹??차일??		
		for(int i=0; i<name[2].length; i++){
			mChildListContent3.add(name[2][i] + "\n" + price[2][i] + "원");
		}
		Log.d("name3", String.valueOf(name[2].length));
		Log.d("name3", String.valueOf(price[2].length));
		//??번째 차일??		
		for(int i=0; i<name[3].length; i++){
			mChildListContent4.add(name[3][i] + "\n" + price[3][i] + "원");
		}
		Log.d("name4", String.valueOf(name[3].length));
		Log.d("name4", String.valueOf(price[3].length));
		for(int i=0; i<name[4].length; i++){
			mChildListContent5.add(name[4][i] + "\n" + price[4][i] + "원");
		}
		Log.d("name5", String.valueOf(name[4].length));
		Log.d("name5", String.valueOf(price[4].length));
		for(int i=0; i<name[5].length; i++){
			mChildListContent6.add(name[5][i] + "\n" + price[5][i] + "원");
		}
		Log.d("name6", String.valueOf(name[5].length));
		Log.d("name6", String.valueOf(price[5].length));
		for(int i=0; i<name[6].length; i++){
			mChildListContent7.add(name[6][i] + "\n" + price[6][i] + "원");
		}
		Log.d("name7", String.valueOf(name[6].length));
		Log.d("name7", String.valueOf(price[6].length));
		for(int i=0; i<name[7].length; i++){
			mChildListContent8.add(name[7][i] + "\n" + price[7][i] + "원");
		}
		Log.d("name8", String.valueOf(name[7].length));
		Log.d("name8", String.valueOf(price[7].length));
		for(int i=0; i<name[8].length; i++){
			mChildListContent9.add(name[8][i] + "\n" + price[8][i] + "원");
		}
		Log.d("name9", String.valueOf(name[8].length));
		Log.d("name9", String.valueOf(price[8].length));

		
		
		mChildList.add(mChildListContent);
		mChildList.add(mChildListContent2);
		mChildList.add(mChildListContent3);
		mChildList.add(mChildListContent4);
		mChildList.add(mChildListContent5);
		mChildList.add(mChildListContent6);
		mChildList.add(mChildListContent7);
		mChildList.add(mChildListContent8);
		mChildList.add(mChildListContent9);
		
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
		
		//young.setVisibility(View.VISIBLE);
		// ?�양 ?�보 버튼
		young.setOnClickListener(new Button.OnClickListener(){
			public void onClick(View v){
				Intent intent = new Intent(Intent.ACTION_VIEW);
				Uri u;
				u = Uri.parse("http://www.caffebene.co.kr/sub04/nutritionFacts/facts01");
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
				totalint += price[groupPosition][childPosition];
				total = Integer.toString(totalint);
				coffename.setText(totalstring);
				coffeprice.setText("총 금액 : " + total +"원");
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
			u = Uri.parse("https://maps.google.co.kr/?q=카페베네&near=" + juso1);
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