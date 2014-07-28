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
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
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


public class ExpandableListViewTwosomePlace extends Activity implements LocationListener{
	
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
	private ArrayList<String> mChildListContent6 = null;
	private ArrayList<String> mChildListContent7 = null;
	private ArrayList<String> mChildListContent8 = null;
	
	//계산???�어�?TextView
	private TextView coffename;
	private TextView coffeprice;
	private TextView coffepriceavr;
	private ImageView iv_image;///////여기까지함
	private EditText dutchcal;
	private String total;
	private int totalint;
	private View scateList;
	private View fragment;
	private double totalavr;
	private String totalstring = "";
	private String listname[] = {};
	private String name [][] = {{"아이스 진셍 라떼","블랙빈 라떼","블루베리 요거트 라떼","오렌지 자몽 주스","키위 바나나 주스","토마토 레몬 주스","소이 블랙빈 라떼",
		"진셍라뗴"},{"화이트클라우드 라떼","롱블랙","아이스 롱블랙","숏라떼","아이스 숏라떼","소이 바닐라 라떼","아이스 화이트 모카","카라멜 마키아또","블랙쿠키 카페라떼",
			"아이스 커피","아이스 카라멜 모카","아이스 카라멜 마키아도","아이스 바닐라 라떼","바닐라 라떼","오늘의 커피","아이스 카페 모카","아이스 카페 라떼","아이스 카페 아메리카노",
			"화이트 모카","카푸치노","카라멜 카페 모카","카페 모카","카페 라떼","카페 아메리카노","에스프레소 마키아또 더블","에스프레소 콘파나 더블","에스프레소 더블","에스프레소 마키아또 싱글",
			"에스프레소 콘파나 싱글","에스프레소 싱글"},{"에스프레소 프라페","유자 프라페","초코크림 프라페","요거 프라페","바닐라 크림 프라페","진셍 프라페","모카칩 프라페",
				"카라멜 프라페","커피 프라페","그린티 프라페","망고 프라페","스트로베리 프라페"},{"스트로베리 자몽 주스","스트로베리 바나나 주스","스트로베리 피치 프라페",
					"스트로베리라떼","유자레몬티","로얄밀크티","버블 카페라떼 프라페","아이스 버블 밀크티","아이스 버블 진셍 라떼","아이스 버블 그린티 라떼","아이스 초콜릿",
					"프리미엄티","아이스 바닐라 블랙티 라떼","바닐라 블랙티 라떼","아이스 그린티 라떼","마샬라 차이 라떼","핫 초콜릿","그린티 라떼","아이스 티"},{"바베큐치킨파니니",
						"쿠바노파니니","BELT 샌드위치","햄치즈 올리브 파니니","패스트라미햄 치즈 샌드위치","크랜베리 치킨 샌드위치","토마토모짜렐라 샌드위치","레몬 치킨 클럽 샌드위치"},{
							"그릴드치킨시저 샐러드","리코타치즈올리브 샐러드","시트러스 샐러드","허브치킨 가든 샐러드"},{"바베큐치킨 파니니 All Day 브런치","쿠바노파니니 All Day 브런치",
								"더블치즈 파니니 세트","살라미 루꼴라 머핀 세트","스크램블에그 샌드위치 세트"},{"로얄밀크티쉬폰","블랙포레스트","투썸에클레어[초콜릿]","쇼콜라파베",
									"투썸에클레어[커피]","마카롱(산딸기)","마카롱(패션)","마카롱(녹차)","마카롱(초코)","마카롱(커피)","마카롱(블루베리)","마카롱 세트( 12개입)",
									"마카롱 세트(6개입)","화이트초코바나나무스","자몽치즈무스","화이트스트로베리무스피스","레드벨벳","캐롯피스","카라멜애플파운드","마블초코파운드",
									"레몬파운드","초콜릿후르츠파운드","리얼체리치즈케익","딸기요거피스","클래식가토피스","디저트마카롱/패션망고","디저트마카롱/그린티","디저트마카롱/스트로베리",
									"마스카포네 티라미수","얼그레이 쇼콜라","후람보아지에","플레인 요거 아이스크림"}};
	private int price [][] = {{5300,5300,6000,6000,5500,5500,5800,5300},{4900,4100,4100,4400,4400,4900,5400,5400,5800,4100,5400,
		5400,4900,4900,4100,4900,4400,4100,5400,4400,5400,4900,4400,4100,4000,4000,3800,3500,3500,3300},{5300,5700,5200,5800,
			5200,5500,5300,5300,4300,5200,5200,5500},{5500,5500,5800,5300,5500,5500,5200,6000,5800,5500,5000,5300,5300,5300,4800,5300,
				4500,4800,4000},{6000,6000,5500,5500,6000,6000,6000,6500},{5500,5800,5800,5500},{9000,9000,5000,5000,5000},{4800,5100,3000,5300,
					3000,2000,2000,2000,2000,2000,2000,24000,12000,5300,5300,5500,5500,5500,4800,4800,4800,4800,6000,5000,
					5300,5500,5500,5500,5300,5000,5500,4800}};
			
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
		movebutton1 = (Button) findViewById(R.id.overcalbutton1);
		
		movebutton1.setVisibility(View.INVISIBLE);

		
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
		
		
		check.setMovementMethod(ScrollingMovementMethod.getInstance());
		coffename.setMovementMethod(ScrollingMovementMethod.getInstance());
		
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
		
		other.setText("영양정보 :");
		
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
		
		mGroupList.add("힐링음료");
		mGroupList.add("에스프레소");
		mGroupList.add("블랜디드 프라페");
		mGroupList.add("티 베리에이션");
		mGroupList.add("샌드위치");
		mGroupList.add("샐러드");
		mGroupList.add("세트메뉴");
		mGroupList.add("디저트");
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
		for(int i=0; i<name[5].length; i++){
			mChildListContent6.add(name[5][i] + "\n" + price[5][i] + "원");
		}
		
		for(int i=0; i<name[6].length; i++){
			mChildListContent7.add(name[6][i] + "\n" + price[6][i] + "원");
		}
		
		for(int i=0; i<name[7].length; i++){
			mChildListContent8.add(name[7][i] + "\n" + price[7][i] + "원");
		}
		
		
		mChildList.add(mChildListContent);
		mChildList.add(mChildListContent2);
		mChildList.add(mChildListContent3);
		mChildList.add(mChildListContent4);
		mChildList.add(mChildListContent5);
		mChildList.add(mChildListContent6);
		mChildList.add(mChildListContent7);
		mChildList.add(mChildListContent8);
		
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
				chgLayoutDisplay();
				//chgLayoutDisplay();
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
				u = Uri.parse("http://www.twosome.co.kr/menu/nutrition.asp");
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
				
				return false;
			}
		});
		
		// 차일???�릭 ?�을 ??
		mListView.setOnChildClickListener(new OnChildClickListener() {
			
			@Override
			public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
			
		
				
				//coffepriceavr.setText(Integer.toString(cnt) + "??);
				totalstring += ">" + name[groupPosition][childPosition] + "\n";
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
			u = Uri.parse("https://maps.google.co.kr/?q=투썸플레이스&near=" + juso1 +"&radius=1");
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