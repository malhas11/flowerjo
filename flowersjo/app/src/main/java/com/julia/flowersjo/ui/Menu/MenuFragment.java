package com.julia.flowersjo.ui.Menu;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toolbar;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.julia.flowersjo.MainActivity;
import com.julia.flowersjo.R;
import com.julia.flowersjo.editProfile;
import com.julia.flowersjo.ui.feedback.feedbackPage;
import com.julia.flowersjo.ui.pastorders.pastOrders;

import java.util.ArrayList;
import java.util.List;

public class MenuFragment extends Fragment {
  RecyclerView recyclerView;
  menuAdapter adapter;
  List<menu> menuList;
  ImageView arrow;
  MainActivity openit;
  Toolbar toolbar;
  TextView toolbarTitle;
  ImageView back;


  public static MenuFragment newInstance(){
    MenuFragment fragment = new MenuFragment();
    return fragment;
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
    View view = inflater.inflate(R.layout.main_menu_recycler_view, container, false);
    toolbar = (Toolbar) getActivity().findViewById(R.id.toolbar);
    toolbarTitle = (TextView) toolbar.findViewById(R.id.toolbar_title);
    menuList = new ArrayList<>();
    recyclerView = (RecyclerView) view.findViewById(R.id.menu_recycler_view);
    recyclerView.setHasFixedSize(true);
    recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    arrow = (ImageView) view.findViewById(R.id.arrow_product_imageview);

    back = (ImageView) toolbar.findViewById(R.id.goback);

    back.setVisibility(View.GONE);
    openit = new MainActivity();
//    menuList.add(
//            new menu (
//                    "Edit profile"
//            )
//    );
    menuList.add(
            new menu (
                    "My Orders"
            )
    );

//    menuList.add(
//            new menu (
//                    "Terms and Conditions"
//            )
//    );
    menuList.add(
            new menu (
                    "Send Feedback"
            )
    );


    adapter = new menuAdapter(getContext(), menuList);
    recyclerView.setAdapter(adapter);
    return view;
  }



  public class menuAdapter extends RecyclerView.Adapter<menuAdapter.menuViewHolder>{

    private Context context;
    private List<menu> menuList;

    public menuAdapter(Context context, List<menu> menuList){
      this.context = context;
      this.menuList = menuList;
    }

    @NonNull
    @Override
    public menuViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i){
      LayoutInflater inflater = LayoutInflater.from(context);
      final View view = inflater.inflate(R.layout.layout_menu_options, null);
      menuViewHolder menuViewHolder = new menuViewHolder(view);
      return menuViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull menuViewHolder menuViewHolder, int i){
      menu menu = menuList.get(i);
      menuViewHolder.menuTitle.setText(menu.getTitle());
    }

    @Override
    public int getItemCount(){return menuList.size();}

    class menuViewHolder extends RecyclerView.ViewHolder {
      TextView menuTitle;
      public menuViewHolder(@NonNull View itemView){
        super(itemView);

        menuTitle = (TextView) itemView.findViewById(R.id.menu_title);
        itemView.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
            Log.d("Tag", "onClick: " + getAdapterPosition());
            int x = getAdapterPosition();

            if(x == 3){
              Intent i = new Intent(getContext(), editProfile.class);
              startActivity(i);
            } else if(x == 1){
              Fragment selectedFragment = feedbackPage.newInstance();
              FragmentTransaction transaction = getFragmentManager().beginTransaction();
              transaction.replace(R.id.nav_host_fragment, selectedFragment);
              transaction.commit();
            }else if(x == 0){
              toolbarTitle.setText("My Orders");
              Fragment selectedFragment = pastOrders.newInstance();
              FragmentTransaction transaction = getFragmentManager().beginTransaction();
              transaction.replace(R.id.nav_host_fragment, selectedFragment);
              transaction.commit();
            }

          }
        });
      }
    }
  }


}
