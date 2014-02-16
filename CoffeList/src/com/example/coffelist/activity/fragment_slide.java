package com.example.coffelist.activity;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.coffelist.R;

public class fragment_slide extends Fragment {
	private Button fragbutton;
	public int check;
	private TextView tall;
	
	SharedPreferences setting;
	SharedPreferences.Editor editor;
	 
	@Override
	    public View onCreateView(LayoutInflater inflater, ViewGroup container,
	            Bundle savedInstanceState) {
		// View v = inflater.inflate(R.layout.fragment_slide, container, false);
		 LinearLayout sampleLayout = (LinearLayout) inflater.inflate(R.layout.fragment_slide ,null);
		 
		 FragmentTransaction ft = getFragmentManager().beginTransaction();
		// ft.add(this, "frag1");
		// ft.commit();
		 /*   
       	fragbutton = (Button)v.findViewById(R.id.fragbutton);
       	fragbutton.setOnClickListener(new Button.OnClickListener(){
			public void onClick(View v){
				//coffepriceavr.setText(inwonstr);
				check +=1;
			}
		});
		  */      
		
       if(check%2 == 0){
    	   
	        ft.show(this);
	        ft.commit();
       }else{
    	   ft.hide(this);
	        ft.commit();
       }
	        
	      
	        return sampleLayout;
	    }
}

//sharedpreference
//setting = this.getActivity().getSharedPreferences("setting", 0);
		//setting.getInt("check", check);
		//Toast.makeText(getActivity(),"fragment check = "+ check,Toast.LENGTH_SHORT).show();
		