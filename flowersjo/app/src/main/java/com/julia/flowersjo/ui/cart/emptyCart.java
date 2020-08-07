package com.julia.flowersjo.ui.cart;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.julia.flowersjo.R;

public class emptyCart extends Fragment {


  public static emptyCart newInstance(){
    emptyCart fragment = new emptyCart();
    return fragment;
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.empty_cart, container, false);

    return view;
  }

}
