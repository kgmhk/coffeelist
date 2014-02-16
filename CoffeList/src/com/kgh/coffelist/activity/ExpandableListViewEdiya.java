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


public class ExpandableListViewEdiya extends Activity implements LocationListener{
	
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
	private String name [][] = {{"에스프레소","에스프레소 마끼아또","에스프레소 콘파냐","아메리카노","카페라떼","카푸치노","카라멜 마끼아또","스위트 바닐라라떼",
		"스위트 카라멜 넛 라떼","카페 모카","카라멜 카페 모카","화이트 초콜렛 모카","화이트 카라멜 모카","민트 모카","시나몬 모카"},{"초콜렛","화이트 초콜렛","오가닉 초콜렛",
			"민트 초콜렛","시나몬 초콜렛","녹차라떼","차이티라떼","토피 넛 라떼","카라멜크림 넛 라떼","아이스티","우유"},{"블루베리 요거트 플랫치노","딸기 요거트 플랫치노",
				"체리 요거트 플랫치노","녹차 요거트 플랫치노","플레인 요거트 플랫치노","그린애플 요거트 플랫치노","커피 플랫치노","모카 플랫치노","카라멜 플랫치노","초코렛칩 플랫치노",
				"민트 초코렛칩 플랫치노","유자 플랫치노","자몽 플랫치노","망고 플랫치노","그린애플 플랫치노"},{"홍차(얼그레이)","로즈쟈스민티","작설차","페퍼민트",
					"캐모마일 레드티","탈카페인 보이차","핑크 로즈 티","루이보스티 라떼","밀크 티","이곡 라떼","고구마 라떼"},{"플레인 와플","생크림 와플",
						"메이플 와플","크림치즈 와플","애플 시나몬 브레드","허니 카라멜 브레드","메이플 넛 브레드"},{"플레인 베이글","어니언 베이글","시나몬&레이즌 베이글",
							"블루베리 베이글","프레즐"},{"고구마 케익","티라미스 케익","초코 티라미스 케익","수플레 케익","초코무스 케익","플레인 스틱 케익","고구마 스틱 케익",
								"초코렛 스틱 케익","블루베리 스틱 케익","아메리칸 치즈 케익","그림 카카오 케익","블루베리 요거트 케익","크렘오 까망베르 케익","뉴욕 치즈 케익",
								"블랙 포레스트 케익"},{"오리지널 핫 번","핫 치즈 번","치즈 머핀","블루베리 머핀","초코칩 머핀","초코렛칩 청크 쿠키","오트밀 레이즌 쿠키",
									"로키로드 쿠키","마카다미아넛 쿠키"}};
	private int price [][] = {{2300,2400,2400,2500,2800,2800,3200,3300,3500,3000,3200,3200,3500,3500,3500},{2800,3000,
		3300,3300,3300,2900,2700,3300,3300,2300,2300},{3900,3900,4200,3900,3900,4200,3500,3700,3800,3900,4200,3400,
			3400,3100,3800},{2500,2500,2500,2700,2700,2500,2700,3500,3000,3000,3500},{2300,2500,2800,3000,4500,4600,
				4800},{1900,1900,1900,1900,2300},{3300,3300,3300,3300,3300,1500,1500,1500,1500,3500,3500,3500,
					3500,3500,3500},{1900,2000,1900,1900,1900,1200,1200,1200,1200}};
			
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
		
		mGroupList.add("커피");
		mGroupList.add("기타 음료");
		mGroupList.add("플랫치노");
		mGroupList.add("티");
		mGroupList.add("와플/허니 브레드");
		mGroupList.add("베이글/프레즐");
		mGroupList.add("케이크");
		mGroupList.add("번/머핀/쿠키");
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
				u = Uri.parse("http://www.ediya.com/product/info_calorie_beverages.asp?F_depth=3&S_depth=6&T_depth=1");
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
			u = Uri.parse("https://maps.google.co.kr/?q=이디야&near=" + juso1 +"&radius=1");
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