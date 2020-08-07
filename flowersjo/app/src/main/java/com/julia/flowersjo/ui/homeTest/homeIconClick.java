package com.julia.flowersjo.ui.homeTest;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toolbar;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.julia.flowersjo.Bouquet;
import com.julia.flowersjo.MainActivity;
import com.julia.flowersjo.R;
import com.julia.flowersjo.Task;
import com.julia.flowersjo.flower;
import com.julia.flowersjo.splashScreen;
import com.julia.flowersjo.ui.flowerData.FlowerFragment;
import com.julia.flowersjo.ui.flowers.flowerProfile;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.julia.flowersjo.MainActivity.clicked;
import static com.julia.flowersjo.MainActivity.profileOne;
import static com.julia.flowersjo.MainActivity.profileTwo;
import static com.julia.flowersjo.MainActivity.runOnce;
import static com.julia.flowersjo.MainActivity.typeF;
import static com.julia.flowersjo.MainActivity.whatsNew;
import static com.julia.flowersjo.splashScreen.listOfBalloons;
import static com.julia.flowersjo.splashScreen.listOfBouqs;
import static com.julia.flowersjo.splashScreen.listOfFlowers;

public class homeIconClick extends Fragment {


//    List<Bouquet> listOfBouqs = new ArrayList<>();

    List<Bouquet> list1 = new ArrayList<>();
    List<Bouquet> list2 = new ArrayList<>();
    List<Bouquet> list3 = new ArrayList<>();
    List<Bouquet> list4 = new ArrayList<>();
    List<flower> scrambled = new ArrayList<>();

    DatabaseReference databaseReference;
    SharedPreferences sharedPreferences;
    FirebaseDatabase firebaseDatabase;

    Button viewNew;
    private Toolbar toolbar;
    TextView toolbarTitle, flowerTypes, viewOcassions, viewFlowers, viewPopular;
    ImageView firstProduct;
    CardView loveCat, thankCat, weddingCat, birthdayCat, babyCat, loveBal, thankBal, birthdayBal, babyBal, graduation_flowers, graduation_balloons;

    CardView card1, card2, card3, card4, card5;

    ImageView floweri1, floweri2, floweri3, floweri4, floweri5, floweri6, new1, new2, new3, new4, new5, goback;
    TextView flowerp1,flowerp2, flowerp3, flowerp4, flowerp5, flowerp6, newp1, newp2, newp3, newp4, newp5;
    RelativeLayout loading;

    List<Task> tasks = new ArrayList<>();
    List<flower> flowersList = new ArrayList<>();
    //    DatabaseReference databaseReference;
//    FirebaseDatabase firebaseDatabase;
    List<flower> flowers = new ArrayList<>();

    List<String> fImages = new ArrayList<>();
    List<String> fPrices = new ArrayList<>();



    public static homeIconClick newInstance(){
        homeIconClick fragment = new homeIconClick();

        return fragment;
    }


    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View root = inflater.inflate(R.layout.home_fragment_test, container, false);
//        love_category = (Button) root.findViewById(R.id.love_category);
//        thankyou_category = (Button) root.findViewById(R.id.thankyou_category);
        loveBal = (CardView) root.findViewById(R.id.love_balloons);
        birthdayBal = (CardView) root.findViewById(R.id.birthday_balloons);
//        thankBal = (CardView) root.findViewById(R.id.thank_balloons);
        babyBal = (CardView) root.findViewById(R.id.baby_balloons);
        toolbar = (Toolbar) getActivity().findViewById(R.id.toolbar);
        toolbarTitle = (TextView) toolbar.findViewById(R.id.toolbar_title);
        flowerTypes = (TextView) root.findViewById(R.id.flower_types);
        viewFlowers = (TextView) root.findViewById(R.id.view_flowers);
        viewPopular = (TextView) root.findViewById(R.id.view_balloons);
        loveCat = (CardView) root.findViewById(R.id.Love_Cat);
        thankCat = (CardView) root.findViewById(R.id.thankyou_cat);
        weddingCat = (CardView) root.findViewById(R.id.wedding_cat);
        birthdayCat = (CardView) root.findViewById(R.id.birthday_cat);
        babyCat = (CardView) root.findViewById(R.id.newbaby_cat);
        graduation_balloons = (CardView) root.findViewById(R.id.graduation_balloons);
        graduation_flowers = (CardView) root.findViewById(R.id.graduation_flowers);
//        loading = (RelativeLayout)root.findViewById(R.id.loadingPanel);
        viewOcassions = (TextView) root.findViewById(R.id.view_occasions);
//        loading.setVisibility(View.VISIBLE);
        //flowers pictures and prices
        floweri1 = (ImageView) root.findViewById(R.id.fflower_image);
        flowerp1 = (TextView) root.findViewById(R.id.fflower_price);

        floweri2 = (ImageView) root.findViewById(R.id.floweri2);
        flowerp2 = (TextView) root.findViewById(R.id.flowerp2);

        floweri3 = (ImageView) root.findViewById(R.id.floweri3);
        flowerp3 = (TextView) root.findViewById(R.id.flowerp3);

        floweri4 = (ImageView) root.findViewById(R.id.floweri4);
        flowerp4 = (TextView) root.findViewById(R.id.flowerp4);
//
//        floweri5 = (ImageView) root.findViewById(R.id.floweri5);
//        flowerp5 = (TextView) root.findViewById(R.id.flowerp5);
//
//        floweri6 = (ImageView) root.findViewById(R.id.floweri6);
//        flowerp6 = (TextView) root.findViewById(R.id.flowerp6);

        //whats new
        new1 = (ImageView) root.findViewById(R.id.new1);
        new2 = (ImageView) root.findViewById(R.id.new2);
        new3 = (ImageView) root.findViewById(R.id.new3);
        new4 = (ImageView) root.findViewById(R.id.new4);
        new5 = (ImageView) root.findViewById(R.id.new5);

        newp1 = (TextView) root.findViewById(R.id.newp1);
        newp2 = (TextView) root.findViewById(R.id.newp2);
        newp3 = (TextView) root.findViewById(R.id.newp3);
        newp4 = (TextView) root.findViewById(R.id.newp4);
        newp5 = (TextView) root.findViewById(R.id.newp5);


        card1 = (CardView) root.findViewById(R.id.card1);
        card2 = (CardView) root.findViewById(R.id.card2);
        card3 = (CardView) root.findViewById(R.id.card3);
        card4 = (CardView) root.findViewById(R.id.card4);
        card5 = (CardView) root.findViewById(R.id.card5);

        goback = (ImageView) toolbar.findViewById(R.id.goback);
        goback.setVisibility(View.GONE);

        card1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                profileOne = whatsNew.get(0);
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.nav_host_fragment, flowerProfile.newInstance());
                transaction.commit();
            }
        });
        card2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                profileOne = whatsNew.get(1);
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.nav_host_fragment, flowerProfile.newInstance());
                transaction.commit();
            }
        });

        card3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                profileOne = whatsNew.get(2);
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.nav_host_fragment, flowerProfile.newInstance());
                transaction.commit();
            }
        });
        card4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                profileOne = whatsNew.get(3);
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.nav_host_fragment, flowerProfile.newInstance());
                transaction.commit();
            }
        });
        card5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                profileOne = whatsNew.get(4);
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.nav_host_fragment, flowerProfile.newInstance());
                transaction.commit();
            }
        });




        String JSON = "bouq";
//
//        ProgressDialog progress = new ProgressDialog(getContext());
////
//        progress.setMessage("getting flowers :) ");
//        progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
//        progress.setIndeterminate(true);
//        loading = (RelativeLayout) root.findViewById(R.id.loadingPanel);




//        ProgressDialog dialog = ProgressDialog.show(getContext(), "",
//                "Loading. Please wait...", true);
        if(clicked == false){
            clicked = true;
//            ProgressDialog progressDialog = new ProgressDialog(getActivity(), R.style.MyDialogTheme);
//            progressDialog.setMessage("Loading...");
//            progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
//            progressDialog.setIndeterminate(true);
//            progressDialog.show();
//            progressDialog.setCancelable(false);
//            Handler handler = new Handler();
            int delay = 1000;
//
//            handler.postDelayed(new Runnable() {
//                @Override
//                public void run() {
//                    if(!flowerp1.getText().toString().equals(" ")){
//                        progressDialog.dismiss();
//
//                    }else
//                        handler.postDelayed(this, delay);
//
//                }
//            }, delay);


        }


        if(runOnce == false) {

            List<Bouquet> listOfAllBouqs = new ArrayList<>();
            for (int i = 0; i < listOfBouqs.size(); i++) {
                listOfAllBouqs.add(listOfBouqs.get(i));
            }
            for (int i = 0; i < listOfAllBouqs.size(); i++) {
                String desc = listOfAllBouqs.get(i).getDescription();
                String price = listOfAllBouqs.get(i).getPrice();
                int rep = 0;
                for (int j = 0; j < listOfAllBouqs.size(); j++) {
                    String desc2 = listOfAllBouqs.get(j).getDescription();
                    String price2 = listOfAllBouqs.get(j).getPrice();
                    if (desc.equals(desc2) && price.equals(price2)) {
                        rep++;
                    }
                }
                if (rep > 1) {
                    for (int k = 0; k < listOfAllBouqs.size(); k++) {
                        if (desc.equals(listOfAllBouqs.get(k).getDescription()) && price.equals(listOfAllBouqs.get(k).getPrice())) {
                            listOfAllBouqs.remove(k);
                            rep--;
                            if (rep == 1) {
                                break;
                            }

                        }
                    }
                }
            }
//            for(int i = 0; i < listOfBalloons.size(); i++){
//                Log.d("Balloons", "type: " + listOfBalloons.get(i).getType());
//            }

            List<Bouquet> listOfAllBalloons = new ArrayList<>();
            for (int i = 0; i < listOfBalloons.size(); i++) {
                listOfAllBalloons.add(listOfBalloons.get(i));
            }

            for (int i = 0; i < listOfAllBalloons.size(); i++) {
                String desc = listOfAllBalloons.get(i).getDescription();
                String price = listOfAllBalloons.get(i).getPrice();
                int rep = 0;
                for (int j = 0; j < listOfAllBalloons.size(); j++) {
                    String desc2 = listOfAllBalloons.get(j).getDescription();
                    String price2 = listOfAllBalloons.get(j).getPrice();
                    if (desc.equals(desc2) && price.equals(price2)) {
                        rep++;
                    }
                }
                if (rep > 1) {
                    for (int k = 0; k < listOfAllBalloons.size(); k++) {
                        if (desc.equals(listOfAllBalloons.get(k).getDescription()) && price.equals(listOfAllBalloons.get(k).getPrice())) {
                            listOfAllBalloons.remove(k);
                            rep--;
                            if (rep == 1) {
                                break;
                            }

                        }
                    }
                }
            }

//            whatsNew.clear();

            runOnce = true;

        }
        getFlowers();

        getNewProducts();




//        sharedPreferences = getContext().getSharedPreferences(JSON, MODE_PRIVATE);
//        String json = sharedPreferences.getString("bouq", null);
//        Type type = new TypeToken<List<Bouquet>>() {}.getType();
//        List<Bouquet> listBouqs = gson.fromJson(json, type);

//        Log.d("worked " , "worked: " + json);


//        String picture = listBouqs.get(0).getImage();
//        getAllProducts();

//        byte[] decodedString = Base64.decode(picture, Base64.DEFAULT);
//        Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
//        new1.setImageBitmap(decodedByte);
//
//        String picture1 = listBouqs.get(1).getImage();
//        byte[] decodedString1 = Base64.decode(picture1, Base64.DEFAULT);
//        Bitmap decodedByte1 = BitmapFactory.decodeByteArray(decodedString1, 0, decodedString1.length);
//        new2.setImageBitmap(decodedByte1);
//
//        String picture2 = listBouqs.get(2).getImage();
//        byte[] decodedString2 = Base64.decode(picture2, Base64.DEFAULT);
//        Bitmap decodedByte2 = BitmapFactory.decodeByteArray(decodedString2, 0, decodedString2.length);
//        new3.setImageBitmap(decodedByte2);
//
//        String picture3 = listBouqs.get(3).getImage();
//        byte[] decodedString3 = Base64.decode(picture3, Base64.DEFAULT);
//        Bitmap decodedByte3 = BitmapFactory.decodeByteArray(decodedString3, 0, decodedString3.length);
//        new4.setImageBitmap(decodedByte3);
//
//        String picture4 = listBouqs.get(4).getImage();
//        byte[] decodedString4 = Base64.decode(picture4, Base64.DEFAULT);
//        Bitmap decodedByte4 = BitmapFactory.decodeByteArray(decodedString4, 0, decodedString4.length);
//        new5.setImageBitmap(decodedByte4);
//
//        newp1.setText(listBouqs.get(0).getPrice());
//        newp2.setText(listBouqs.get(1).getPrice());
//        newp3.setText(listBouqs.get(2).getPrice());
//        newp4.setText(listBouqs.get(3).getPrice());
//        newp5.setText(listBouqs.get(4).getPrice());


//        for(int i = 0; i < listBouqs.size();i++){
//            Log.d("TAG" , "worked : " + listBouqs.get(i).getPrice());
//
//        }



        floweri1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                priceMain = flowerp1.getText().toString();
//                ByteArrayOutputStream stream = new ByteArrayOutputStream();
//                BitmapDrawable drawable = (BitmapDrawable) floweri1.getDrawable();
//                Bitmap bitmap = drawable.getBitmap();
//                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
//                byte[] byteFormat = stream.toByteArray();
//                imageMain = Base64.encodeToString(byteFormat, Base64.NO_WRAP);
//
//                descriptionMain = listOfFlowers.get(0).getDescription();
                typeF = true;
                profileTwo = listOfFlowers.get(0);

                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.nav_host_fragment, flowerProfile.newInstance());
                transaction.commit();
            }
        });
        floweri2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                priceMain = flowerp2.getText().toString();
//                ByteArrayOutputStream stream = new ByteArrayOutputStream();
//                BitmapDrawable drawable = (BitmapDrawable) floweri2.getDrawable();
//                Bitmap bitmap = drawable.getBitmap();
//                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
//                byte[] byteFormat = stream.toByteArray();
//                imageMain = Base64.encodeToString(byteFormat, Base64.NO_WRAP);
//
//                descriptionMain = listOfFlowers.get(1).getDescription();
                typeF = true;
                profileTwo = listOfFlowers.get(1);
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.nav_host_fragment, flowerProfile.newInstance());
                transaction.commit();
            }
        });

        floweri3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                priceMain = flowerp3.getText().toString();
//                ByteArrayOutputStream stream = new ByteArrayOutputStream();
//                BitmapDrawable drawable = (BitmapDrawable) floweri3.getDrawable();
//                Bitmap bitmap = drawable.getBitmap();
//                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
//                byte[] byteFormat = stream.toByteArray();
//                imageMain = Base64.encodeToString(byteFormat, Base64.NO_WRAP);
//
//                descriptionMain = listOfFlowers.get(2).getDescription();
                typeF = true;
                profileTwo = listOfFlowers.get(2);
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.nav_host_fragment, flowerProfile.newInstance());
                transaction.commit();
            }
        });
        floweri4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                priceMain = flowerp4.getText().toString();
//                ByteArrayOutputStream stream = new ByteArrayOutputStream();
//                BitmapDrawable drawable = (BitmapDrawable) floweri4.getDrawable();
//                Bitmap bitmap = drawable.getBitmap();
//                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
//                byte[] byteFormat = stream.toByteArray();
//                imageMain = Base64.encodeToString(byteFormat, Base64.NO_WRAP);
//
//                descriptionMain = listOfFlowers.get(3).getDescription();
                typeF = true;
                profileTwo = listOfFlowers.get(3);
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.nav_host_fragment, flowerProfile.newInstance());
                transaction.commit();
            }
        });


        viewPopular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toolbarTitle.setText("All Balloons");
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.nav_host_fragment, FlowerFragment.newInstance());
                transaction.commit();
            }
        });

        viewOcassions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toolbarTitle.setText("All Bouquets");
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.nav_host_fragment, FlowerFragment.newInstance());
                transaction.commit();
            }
        });

//        viewNew.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                toolbarTitle.setText("Whats new");
//                FragmentTransaction transaction = getFragmentManager().beginTransaction();
//                transaction.replace(R.id.nav_host_fragment, FlowerFragment.newInstance());
//                transaction.commit();
//            }
//        });

        viewFlowers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toolbarTitle.setText("Flowers & Plants");
                BottomNavigationView navigation = (BottomNavigationView) getActivity().findViewById(R.id.nav_view);

                View zview = navigation.findViewById(R.id.navigation_flowers);
                zview.performClick();
            }
        });

//        firstProduct.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
////                FragmentTransaction transaction = getFragmentManager().beginTransaction();
////                transaction.replace(R.id.nav_host_fragment, flowerDescFragment.newInstance());
////                transaction.commit();
//            }
//        });


        loveBal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toolbarTitle.setText("Love Balloons");
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.nav_host_fragment, FlowerFragment.newInstance());
                transaction.commit();
            }
        });
//        thankBal.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                toolbarTitle.setText("Thank you Balloons");
//                FragmentTransaction transaction = getFragmentManager().beginTransaction();
//                transaction.replace(R.id.nav_host_fragment, FlowerFragment.newInstance());
//                transaction.commit();
//            }
//        });
        birthdayBal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toolbarTitle.setText("Birthday Balloons");
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.nav_host_fragment, FlowerFragment.newInstance());
                transaction.commit();
            }
        });
        babyBal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toolbarTitle.setText("New Baby Balloons");
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.nav_host_fragment, FlowerFragment.newInstance());
                transaction.commit();
            }
        });
        loveCat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                toolbarTitle.setText("Love bouquets");
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.nav_host_fragment, FlowerFragment.newInstance());
                transaction.commit();
            }
        });
        graduation_flowers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                toolbarTitle.setText("Graduation bouquets");
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.nav_host_fragment, FlowerFragment.newInstance());
                transaction.commit();
            }
        });
        thankCat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toolbarTitle.setText("Thank you bouquets");
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.nav_host_fragment, FlowerFragment.newInstance());
                transaction.commit();
            }
        });
        babyCat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toolbarTitle.setText("New Baby bouquets");
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.nav_host_fragment, FlowerFragment.newInstance());
                transaction.commit();
            }
        });

        weddingCat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toolbarTitle.setText("Get well bouquets");
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.nav_host_fragment, FlowerFragment.newInstance());
                transaction.commit();
            }
        });

        birthdayCat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toolbarTitle.setText("Birthday bouquets");
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.nav_host_fragment, FlowerFragment.newInstance());
                transaction.commit();
            }
        });
        graduation_balloons.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toolbarTitle.setText("Graduation balloons");
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.nav_host_fragment, FlowerFragment.newInstance());
                transaction.commit();
            }
        });

        return root;
    }

    private void getNewProducts() {



        //adding info to whatsnew design
        int secondsDelayed = 100;
        Handler handler = new Handler();

        handler.postDelayed(new Runnable() {
            public void run() {

                if(!listOfBouqs.isEmpty()){

                    //testing
                    int sizeBouq = listOfBouqs.size() - 1;


                    for (int i = 0; i <= 8; i++) {
                        if(sizeBouq >= 0) {
                            whatsNew.add(listOfBouqs.get(sizeBouq));

                            sizeBouq--;

                        }
                    }

//            for(int i = 0; i <=4; i++){
//                if(sizeBalloon >= 0){
//                    whatsNew.add(listOfBalloons.get(sizeBalloon));
//                    sizeBalloon--;
//                }
//
//            }


                    Collections.shuffle(whatsNew);

                    for(int i = 0; i < listOfFlowers.size(); i++){
                        scrambled.add(listOfFlowers.get(i));

                    }
                    Collections.shuffle(listOfFlowers);

                    byte[] decodedString = Base64.decode(whatsNew.get(0).getImage(), Base64.DEFAULT);
                    Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
                    Bitmap resized = Bitmap.createScaledBitmap(decodedByte, 180, 180, true);

                    new1.setImageBitmap(resized);
                    String t = whatsNew.get(0).getPrice().replaceAll("\\D+","");

                    newp1.setText(t + " JOD");

                    decodedString = Base64.decode(whatsNew.get(1).getImage(), Base64.DEFAULT);
                    decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
                    resized = Bitmap.createScaledBitmap(decodedByte, 180, 180, true);

                    new2.setImageBitmap(resized);
                    t = whatsNew.get(1).getPrice().replaceAll("\\D+","");

                    newp2.setText(t + " JOD");

                    decodedString = Base64.decode(whatsNew.get(2).getImage(), Base64.DEFAULT);
                    decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
                    resized = Bitmap.createScaledBitmap(decodedByte, 180, 180, true);

                    new3.setImageBitmap(resized);
                    t = whatsNew.get(2).getPrice().replaceAll("\\D+","");

                    newp3.setText(t + " JOD");

                    decodedString = Base64.decode(whatsNew.get(3).getImage(), Base64.DEFAULT);
                    decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
                    resized = Bitmap.createScaledBitmap(decodedByte, 180, 180, true);

                    new4.setImageBitmap(resized);
                    t = whatsNew.get(3).getPrice().replaceAll("\\D+","");

                    newp4.setText(t + " JOD");

                    decodedString = Base64.decode(whatsNew.get(4).getImage(), Base64.DEFAULT);
                    decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
                    resized = Bitmap.createScaledBitmap(decodedByte, 180, 180, true);

                    new5.setImageBitmap(resized);
                    t = whatsNew.get(4).getPrice().replaceAll("\\D+","");

                    newp5.setText(t + " JOD");


                }else {
                    handler.postDelayed(this, secondsDelayed);
                }

            }
        }, secondsDelayed);





    }

//    private void getAllProducts() {
//        firebaseDatabase = FirebaseDatabase.getInstance();
//        databaseReference = firebaseDatabase.getReference("love");
//        databaseReference.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//
//                DatabaseHelper d = new DatabaseHelper();
//                listOfBouqs.clear();
//                listOfBouqs =   d.DatabaseHelperReadLove(dataSnapshot);
//
//
//
//
//
//            }
//
//            @Override
//            public void onCancelled(DatabaseError error) {
//                // Failed to read value
//                Log.w(TAG, "Failed to read value.", error.toException());
//            }
//        });
//        firebaseDatabase = FirebaseDatabase.getInstance();
//        databaseReference = firebaseDatabase.getReference("birthday");
//        databaseReference.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//
//                list1.clear();
//                DatabaseHelper d = new DatabaseHelper();
//                list1 =   d.DatabaseHelperReadBirthday(dataSnapshot);
//
//
//
//
//
//            }
//
//            @Override
//            public void onCancelled(DatabaseError error) {
//                // Failed to read value
//                Log.w(TAG, "Failed to read value.", error.toException());
//            }
//        });
//
//
//        Handler handler = new Handler();
//        int delay = 1000;
//
//        handler.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                if(!listOfBouqs.isEmpty() && !list1.isEmpty()){
//                    listOfBouqs.addAll(list1);
//                    for(int i = 0; i < listOfBouqs.size(); i++){
//                        Log.d("tag", "worked bitches : " + listOfBouqs.get(i).getPrice());
//
//                    }
//
//                }else
//                    handler.postDelayed(this, delay);
//
//            }
//        }, delay);
//
//
//
//
//
//    }

    public void getFlowers() {




        if ((flowerp1.getText().toString()).equals(" ")) {
            byte[] decodedString = Base64.decode(listOfFlowers.get(0).getImage(), Base64.DEFAULT);
            Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);

            floweri1.setImageBitmap(decodedByte);
            flowerp1.setText(listOfFlowers.get(0).getTitle() + " JOD");


        }
        if ((flowerp2.getText().toString()).equals(" ")) {
            byte[] decodedString = Base64.decode(listOfFlowers.get(1).getImage(), Base64.DEFAULT);
            Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);

            floweri2.setImageBitmap(decodedByte);
            flowerp2.setText(listOfFlowers.get(1).getTitle() + " JOD");
        }
        if ((flowerp3.getText().toString()).equals(" ")) {
            byte[] decodedString = Base64.decode(listOfFlowers.get(2).getImage(), Base64.DEFAULT);
            Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);

            floweri3.setImageBitmap(decodedByte);
            flowerp3.setText(listOfFlowers.get(2).getTitle() + " JOD");
        }
        if ((flowerp4.getText().toString()).equals(" ")) {
            byte[] decodedString = Base64.decode(listOfFlowers.get(3).getImage(), Base64.DEFAULT);
            Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);

            floweri4.setImageBitmap(decodedByte);
            flowerp4.setText(listOfFlowers.get(3).getTitle() + " JOD");
        }
//        if ((flowerp5.getText().toString()).equals(" ")) {
//            if (listOfFlowers.get(4) != null) {
//
//                byte[] decodedString = Base64.decode(listOfFlowers.get(4).getImage(), Base64.DEFAULT);
//                Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
//                Drawable mDrawable = new BitmapDrawable(getResources(), decodedByte);
//                floweri5.setImageDrawable(mDrawable);
//                flowerp5.setText(listOfFlowers.get(4).getTitle() + " JOD");
//            }
//        }
//        if ((flowerp6.getText().toString()).equals(" ")) {
//            if (listOfFlowers.get(5) != null) {
//
//                byte[] decodedString = Base64.decode(listOfFlowers.get(5).getImage(), Base64.DEFAULT);
//                Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
//                Drawable mDrawable = new BitmapDrawable(getResources(), decodedByte);
//                floweri6.setImageDrawable(mDrawable);
//                flowerp6.setText(listOfFlowers.get(5).getTitle() + " JOD");
//            }
//        }
    }

}
