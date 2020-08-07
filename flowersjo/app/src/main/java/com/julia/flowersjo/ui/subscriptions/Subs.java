package com.julia.flowersjo.ui.subscriptions;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.julia.flowersjo.R;
import com.google.android.material.tabs.TabLayout;

public class Subs extends Fragment {

  ViewPager viewPager, viewpager2;
  SlideAdapter slideAdapter, slideAdapter2;
  CheckBox month1;

  public static Subs newInstance(){
    Subs subs = new Subs();
    return subs;
  }

  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.subscriptions, container, false);

    viewPager = (ViewPager) view.findViewById(R.id.viewpager);
    month1 = (CheckBox) view.findViewById(R.id.month1sub);


    slideAdapter = new SlideAdapter(getContext());
    viewPager.setAdapter(slideAdapter);


    TabLayout tabLayout = (TabLayout) view.findViewById(R.id.sliding_tabs);
    tabLayout.setupWithViewPager(viewPager, true);





    return view;
  }


}
