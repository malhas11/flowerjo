package com.julia.flowersjo;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toolbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.ui.AppBarConfiguration;

import com.julia.flowersjo.ui.Menu.MenuFragment;
import com.julia.flowersjo.ui.cart.cartFragment;
import com.julia.flowersjo.ui.cart.emptyCart;
import com.julia.flowersjo.ui.cartlist;
import com.julia.flowersjo.ui.clientorder;
import com.julia.flowersjo.ui.flowerData.FlowerFragment;
import com.julia.flowersjo.ui.flowers.FlowersFragment;
import com.julia.flowersjo.ui.homeTest.homeIconClick;
import com.julia.flowersjo.ui.pastorders.pastorderinfomain;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ImageView searchIcon, filterIcon;
    private Toolbar toolbar;
    public static TextView toolbarTitle;
    static public boolean cash = false;
    static public boolean card = false;
    static public boolean delivery = false;
    static public boolean collection = false;
    static public String priceAll;
    static public String ID;
    EditText searchbar;
    Fragment selectedFragment = null;
    boolean searchIconClicked = false;
    private static Context mContext;
    static public String priceMain, imageMain, descriptionMain;
    static public boolean typeF = false;
    static public Bouquet profileOne;
    static public flower profileTwo;
    static public boolean runOnce = false;
    static public List<Bouquet> whatsNew = new ArrayList<>();
    static public clientorder client = new clientorder();
    static public List<pastorderinfomain> pastorderinfomainlist = new ArrayList<>();

    //cart list
    static public List<cartlist> listofcart = new ArrayList<>();
    static public List<orders> orderList = new ArrayList<>();

    static public BottomNavigationView navView;

    static public boolean badgel = false;
    static public boolean flower2 = false;

    //recycler
    static public int rolled = 0;
    static public List<String> descriptionM = new ArrayList<>();
    static public List<String> priceM = new ArrayList<>();
    static public List<String> imageM = new ArrayList<>();

    //    static public FromToMessage fromToMessages;
    static public List<Bouquet> listOfLoveBalloons  = new ArrayList<>();
    static public List<Bouquet> listOfBirthdayBalloons = new ArrayList<>();
    static public List<Bouquet> listOfGradBalloons = new ArrayList<>();

    static public List<Bouquet> listOfBabyBalloons = new ArrayList<>();
    static public List<Bouquet> listOfThankBalloons = new ArrayList<>();
    static public List<Bouquet> allProducts = new ArrayList<>();
    static public  boolean clicked = false;

    static public String totalA, subtotal, from, To, message;
    static public List<String> descriptionBouqL = new ArrayList<>();
    static public List<String> priceBouqL = new ArrayList<>();
    static public List<String> imageBouqL = new ArrayList<>();
    static BottomNavigationView navigation;

    public static void openEditProfile() {
        Intent myIntent = new Intent(mContext, editProfile.class);
        mContext.startActivity(myIntent);

    }





    @Override
    public void onBackPressed(){
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.nav_view);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);




        String title = toolbarTitle.getText().toString();

        if(title.equals("Love")){
            toolbarTitle.setText("Love bouquets");
            selectedFragment = FlowerFragment.newInstance();
        }else if(title.equals("My Orders")){
            toolbarTitle.setText("Options");
            selectedFragment = MenuFragment.newInstance();

        }
        else if(title.equals("Thank you")){
            toolbarTitle.setText("Thank you bouquets");
            selectedFragment = FlowerFragment.newInstance();
        }else if(title.equals("New Baby")){
            toolbarTitle.setText("New Baby bouquets");
            selectedFragment = FlowerFragment.newInstance();
        }else if(title.equals("Wedding")){
            toolbarTitle.setText("Wedding bouquets");
            selectedFragment = FlowerFragment.newInstance();
        }else if(title.equals("Birthday")){
            toolbarTitle.setText("Birthday bouquets");
            selectedFragment = FlowerFragment.newInstance();
        }else {
            toolbarTitle.setText("Home");

            searchbar.setVisibility(View.GONE);
//            searchIcon.setVisibility(View.GONE);
            filterIcon.setVisibility(View.GONE);
            toolbarTitle.setVisibility(View.VISIBLE);
            selectedFragment = homeIconClick.newInstance();
            navigation.setSelectedItemId(R.id.navigation_home);
        }


        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.nav_host_fragment, selectedFragment);
        transaction.commit();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext = this;
        navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications)
                .build();
//        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        //NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        // NavigationUI.setupWithNavController(navView, navController);






        navigation = (BottomNavigationView) findViewById(R.id.nav_view);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        selectedFragment = homeIconClick.newInstance();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.nav_host_fragment, selectedFragment);
        transaction.commit();
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbarTitle = (TextView) toolbar.findViewById(R.id.toolbar_title);
        toolbarTitle.setText("Home");
        searchbar = (EditText) findViewById(R.id.searchbar);
        filterIcon = (ImageView) findViewById(R.id.toolbar_filter_icon);



//        searchIcon.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//
//                searchbar.setVisibility(View.VISIBLE);
//                toolbarTitle.setVisibility(View.GONE);
//                searchIcon.setVisibility(View.GONE);
//                filterIcon.setVisibility(View.VISIBLE);
//                searchIconClicked = true;
//
//            }
//        });
//        backIcon.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                searchbar.setVisibility(View.GONE);
//                searchIcon.setVisibility(View.VISIBLE);
//                filterIcon.setVisibility(View.GONE);
//                backIcon.setVisibility(View.GONE);
//                toolbarTitle.setVisibility(View.VISIBLE);
//                basketIcon.setVisibility(View.VISIBLE);
//            }
//        });

        toolbarTitle.setTextSize(23);
    }



    //    private void addAllLove(String description, String price, String image){
//        bouq.add(
//                new Bouquet (
//                        price,
//                        description,
//                        image
//                )
//        );
//    }
    private void addDescriptionLove(String description) {
        descriptionBouqL.add(description);
        Log.d("Tag", "description: "  + description );

    }
    private void addImageLove(String image){
        imageBouqL.add(image);

    }
    private void addPriceLove(String price){
        priceBouqL.add(price);
    }


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {

            switch (item.getItemId()){
                case R.id.navigation_home:
                    rolled = 0;
                    toolbarTitle.setText("Home");

                    searchbar.setVisibility(View.GONE);
//                    searchIcon.setVisibility(View.GONE);
                    filterIcon.setVisibility(View.GONE);
                    toolbarTitle.setVisibility(View.VISIBLE);
                    selectedFragment = homeIconClick.newInstance();
                    break;
                case R.id.navigation_dashboard:
                    rolled = 0;

                    toolbarTitle.setText("Shopping Cart");
                    searchbar.setVisibility(View.GONE);
//                    searchIcon.setVisibility(View.GONE);
                    filterIcon.setVisibility(View.GONE);
                    toolbarTitle.setVisibility(View.VISIBLE);

                    if(listofcart.isEmpty()){
                        selectedFragment = emptyCart.newInstance();
                    }else{
                        selectedFragment = cartFragment.newInstance();
                    }

                    break;
                case R.id.navigation_flowers:
                    rolled = 0;

                    toolbarTitle.setText("Flowers & Plants");
                    searchbar.setVisibility(View.GONE);
//                    searchIcon.setVisibility(View.GONE);
                    filterIcon.setVisibility(View.GONE);
                    toolbarTitle.setVisibility(View.VISIBLE);

                    selectedFragment = FlowersFragment.newInstance();
                    break;
                case R.id.navigation_menu:
                    rolled = 0;

                    toolbarTitle.setText("Options");
                    searchbar.setVisibility(View.GONE);
//                    searchIcon.setVisibility(View.GONE);
                    filterIcon.setVisibility(View.GONE);
                    toolbarTitle.setVisibility(View.VISIBLE);

                    selectedFragment = MenuFragment.newInstance();
                    break;
//
//                case R.id.navigation_subscribe:
//                    toolbarTitle.setText("Subscribe");
//                    searchbar.setVisibility(View.GONE);
////                    searchIcon.setVisibility(View.GONE);
//                    filterIcon.setVisibility(View.GONE);
//                    toolbarTitle.setVisibility(View.VISIBLE);
//
//                    selectedFragment = Subs.newInstance();
//                    break;
            }
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.nav_host_fragment, selectedFragment);
            transaction.commit();

            return true;
        }
    };

}
