package com.julia.flowersjo.ui.flowerData;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toolbar;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.julia.flowersjo.Bouquet;
import com.julia.flowersjo.MainActivity;
import com.julia.flowersjo.R;
import com.julia.flowersjo.splashScreen;
import com.julia.flowersjo.ui.flowers.flowerProfile;

import java.util.ArrayList;
import java.util.List;

import static com.julia.flowersjo.MainActivity.listOfBabyBalloons;
import static com.julia.flowersjo.MainActivity.listOfBirthdayBalloons;
import static com.julia.flowersjo.MainActivity.listOfGradBalloons;
import static com.julia.flowersjo.MainActivity.listOfLoveBalloons;
import static com.julia.flowersjo.MainActivity.listOfThankBalloons;
import static com.julia.flowersjo.MainActivity.profileOne;
import static com.julia.flowersjo.MainActivity.rolled;
import static com.julia.flowersjo.MainActivity.whatsNew;
import static com.julia.flowersjo.splashScreen.listOfBalloons;
import static com.julia.flowersjo.splashScreen.listOfBouqs;

public class FlowerFragment extends Fragment {

    RecyclerView mRecyclerView;
    TextView toolbarTitle;
    private Toolbar toolbar;
    ImageView goback;
    public String cat;

    public static FlowerFragment newInstance(){
        FlowerFragment fragment = new FlowerFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.gridview_ocassions, container, false);

        mRecyclerView = view.findViewById(R.id.grid_recycler);
        GridLayoutManager mGridLayoutManager = new GridLayoutManager(getContext(), 2);
        mRecyclerView.setHasFixedSize(true);

        mRecyclerView.setItemViewCacheSize(20);
        mRecyclerView.setDrawingCacheEnabled(true);
        mRecyclerView.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);
        mRecyclerView.setLayoutManager(mGridLayoutManager);
        toolbar = (Toolbar) getActivity().findViewById(R.id.toolbar);
        toolbarTitle = (TextView) toolbar.findViewById(R.id.toolbar_title);
        goback = (ImageView) toolbar.findViewById(R.id.goback);
        cat = toolbarTitle.getText().toString();

        splashScreen s = new splashScreen();
        s.readAllBalloonsDatabase();
        ProgressDialog progressDialog = new ProgressDialog(getActivity(), R.style.MyDialogTheme);
        progressDialog.setMessage("Loading...");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setIndeterminate(true);
        progressDialog.show();
        progressDialog.setCancelable(false);
        Handler handler = new Handler();
        int delay = 1000;

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if(listOfBalloons.size()> 11){
                    progressDialog.dismiss();

                    if (cat.equals("Love bouquets")) {

                        List<Bouquet> listOfLove = new ArrayList<>();
                        for(int i = 0; i < listOfBouqs.size(); i++) {
                            if(listOfBouqs.get(i).getType().equals("love")){
                                listOfLove.add(listOfBouqs.get(i));
                                Log.d("description", listOfLove.get(i).getPrice());
                            }
                        }

                        FlowerFragment.MyAdapter myAdapter = new FlowerFragment.MyAdapter(getContext(), listOfLove);
                        myAdapter.setHasStableIds(true);
                        mRecyclerView.setAdapter(myAdapter);

                    } else if (cat.equals("Thank you bouquets")) {
                        List<Bouquet> listOfThank = new ArrayList<>();
                        for(int i = 0; i < listOfBouqs.size(); i++) {
                            if(listOfBouqs.get(i).getType().equals("thankyou")){
                                listOfThank.add(listOfBouqs.get(i));
                            }
                        }
                        FlowerFragment.MyAdapter myAdapter = new FlowerFragment.MyAdapter(getContext(), listOfThank);
                        myAdapter.setHasStableIds(true);

                        mRecyclerView.setAdapter(myAdapter);

                    } else if (cat.equals("New Baby bouquets")) {
                        List<Bouquet> listOfBaby = new ArrayList<>();
                        for(int i = 0; i < listOfBouqs.size(); i++) {
                            if(listOfBouqs.get(i).getType().equals("newbaby")){
                                listOfBaby.add(listOfBouqs.get(i));
                            }
                        }
                        FlowerFragment.MyAdapter myAdapter = new FlowerFragment.MyAdapter(getContext(), listOfBaby);
                        myAdapter.setHasStableIds(true);
                        mRecyclerView.setAdapter(myAdapter);



                    } else if (cat.equals("Get well bouquets")) {
                        List<Bouquet> listOfWedding = new ArrayList<>();
                        for(int i = 0; i < listOfBouqs.size(); i++) {
                            if(listOfBouqs.get(i).getType().equals("wedding")){
                                listOfWedding.add(listOfBouqs.get(i));
                            }
                        }
                        FlowerFragment.MyAdapter myAdapter = new FlowerFragment.MyAdapter(getContext(), listOfWedding);
                        myAdapter.setHasStableIds(true);
                        mRecyclerView.setAdapter(myAdapter);

                    } else if (cat.equals("Birthday bouquets")) {
                        List<Bouquet> listOfBirthday = new ArrayList<>();
                        for(int i = 0; i < listOfBouqs.size(); i++) {
                            if(listOfBouqs.get(i).getType().equals("birthday")){
                                listOfBirthday.add(listOfBouqs.get(i));
                            }
                        }
                        FlowerFragment.MyAdapter myAdapter = new FlowerFragment.MyAdapter(getContext(), listOfBirthday);
                        myAdapter.setHasStableIds(true);
                        mRecyclerView.setAdapter(myAdapter);




                    } else if (cat.equals("Graduation bouquets")) {
                        List<Bouquet> listOfGraduation = new ArrayList<>();
                        for(int i = 0; i < listOfBouqs.size(); i++) {
                            if(listOfBouqs.get(i).getType().equals("graduation")){
                                listOfGraduation.add(listOfBouqs.get(i));
                            }
                        }
                        FlowerFragment.MyAdapter myAdapter = new FlowerFragment.MyAdapter(getContext(), listOfGraduation);
                        myAdapter.setHasStableIds(true);
                        mRecyclerView.setAdapter(myAdapter);




                    }else if(cat.equals("All Bouquets")){
                        List<Bouquet> listOfAll = new ArrayList<>();
                        for(int i = 0; i < listOfBouqs.size();i++){
                            listOfAll.add(listOfBouqs.get(i));
                        }
                        for(int i = 0; i < listOfAll.size(); i++){
                            String desc = listOfAll.get(i).getDescription();
                            String price = listOfAll.get(i).getPrice();
                            int rep = 0;
                            for(int j = 0; j < listOfAll.size(); j++){
                                String desc2 = listOfAll.get(j).getDescription();
                                String price2 = listOfAll.get(j).getPrice();
                                if(desc.equals(desc2) && price.equals(price2)){
                                    rep++;
                                }
                            }
                            if(rep > 1){
                                for(int k = 0; k < listOfAll.size(); k++){
                                    if(desc.equals(listOfAll.get(k).getDescription()) && price.equals(listOfAll.get(k).getPrice())){
                                        listOfAll.remove(k);
                                        rep--;
                                        if(rep == 1){
                                            break;
                                        }

                                    }
                                }
                            }
                        }
                        MyAdapter myAdapter = new MyAdapter(getContext(), listOfAll);

                        myAdapter.setHasStableIds(true);
                        mRecyclerView.setAdapter(myAdapter);

                    } else if (cat.equals("Birthday Balloons")){
                        splashScreen s = new splashScreen();
                        s.readAllBalloonsDatabase();
                        int secondsDelayed = 100;
                        Handler handler = new Handler();

                        handler.postDelayed(new Runnable() {
                            public void run() {

                                if(!listOfBalloons.isEmpty()){

                                    //testing
                                    listOfBirthdayBalloons.clear();
                                    for(int i = 0; i < listOfBalloons.size(); i++) {
                                        if(listOfBalloons.get(i).getType().equals("birthday")){
                                            listOfBirthdayBalloons.add(listOfBalloons.get(i));
                                        }
                                    }
                                    FlowerFragment.MyAdapter myAdapter = new FlowerFragment.MyAdapter(getContext(), listOfBirthdayBalloons);
                                    myAdapter.setHasStableIds(true);

                                    mRecyclerView.setAdapter(myAdapter);
                                }else {
                                    handler.postDelayed(this, secondsDelayed);
                                }

                            }
                        }, secondsDelayed);


                    } else if (cat.equals("New Baby Balloons")){
                        listOfBabyBalloons.clear();

                        for(int i = 0; i < listOfBalloons.size(); i++) {
                            if(listOfBalloons.get(i).getType().equals("newbaby")){
                                listOfBabyBalloons.add(listOfBalloons.get(i));
                            }
                        }
                        FlowerFragment.MyAdapter myAdapter = new FlowerFragment.MyAdapter(getContext(), listOfBabyBalloons);
                        myAdapter.setHasStableIds(true);

                        mRecyclerView.setAdapter(myAdapter);

                    } else if(cat.equals("Thank you Balloons")) {

                        listOfThankBalloons.clear();
                        for(int i = 0; i < listOfBalloons.size(); i++) {
                            if(listOfBalloons.get(i).getType().equals("thankyou")){
                                listOfThankBalloons.add(listOfBalloons.get(i));
                            }
                        }
                        FlowerFragment.MyAdapter myAdapter = new FlowerFragment.MyAdapter(getContext(), listOfThankBalloons);
                        myAdapter.setHasStableIds(true);

                        mRecyclerView.setAdapter(myAdapter);

                    }else if(cat.equals("Love Balloons")) {

                        listOfLoveBalloons.clear();
                        for(int i = 0; i < listOfBalloons.size(); i++) {
                            Log.d("tag", "balloons: type : " + listOfBalloons.get(i).getType());

                            if(listOfBalloons.get(i).getType().equals("love")){
                                listOfLoveBalloons.add(listOfBalloons.get(i));

                            }

                        }
                        FlowerFragment.MyAdapter myAdapter = new FlowerFragment.MyAdapter(getContext(), listOfLoveBalloons);
                        myAdapter.setHasStableIds(true);

                        mRecyclerView.setAdapter(myAdapter);

                    }else if(cat.equals("Graduation balloons")) {

                        listOfGradBalloons.clear();
                        for(int i = 0; i < listOfBalloons.size(); i++) {
                            Log.d("tag", "balloons: type : " + listOfBalloons.get(i).getType());

                            if(listOfBalloons.get(i).getType().equals("graduation")){
                                listOfGradBalloons.add(listOfBalloons.get(i));

                            }

                        }
                        FlowerFragment.MyAdapter myAdapter = new FlowerFragment.MyAdapter(getContext(), listOfGradBalloons);
                        myAdapter.setHasStableIds(true);

                        mRecyclerView.setAdapter(myAdapter);

                    } else if(cat.equals("All Balloons")){
                        List<Bouquet> listOfAll = new ArrayList<>();
                        for(int i = 0; i <listOfBalloons.size();i++){
                            if(listOfBalloons.get(i).getType().equals("String")){
                                listOfBalloons.remove(i);
                            }
                        }
                        for(int i = 0; i < listOfBalloons.size();i++){
                            listOfAll.add(listOfBalloons.get(i));
                            Log.d("list of balloons", "type: " + listOfBalloons.get(i).getType());
                        }
                        for(int i = 0; i < listOfAll.size(); i++){
                            String desc = listOfAll.get(i).getDescription();
                            String price2 = listOfAll.get(i).getPrice();
                            int rep = 0;
                            for(int j = 0; j < listOfAll.size(); j++){
                                String desc2 = listOfAll.get(j).getDescription();
                                String price = listOfAll.get(j).getPrice();

                                if(desc.equals(desc2) && price.equals(price2) ){
                                    rep++;
                                }
                            }
                            if(rep > 1){
                                for(int k = 0; k < listOfAll.size(); k++){
                                    if(desc.equals(listOfAll.get(k).getDescription()) && price2.equals(listOfAll.get(k).getPrice())){
                                        listOfAll.remove(k);
                                        rep--;
                                        if(rep == 1){
                                            break;
                                        }

                                    }
                                }
                            }
                        }

                        MyAdapter myAdapter = new MyAdapter(getContext(), listOfAll);
                        myAdapter.setHasStableIds(true);
                        mRecyclerView.setAdapter(myAdapter);


                    }
                    mRecyclerView.scrollToPosition(rolled);
                }else
                    handler.postDelayed(this, delay);

            }
        }, delay);



        return view;
    }

    public class MyAdapter extends RecyclerView.Adapter<MyAdapter.FlowerViewHolder> {

        private Context mContext;
        private List< Bouquet > listOfThings;
        String cate;
        TextView title;
        Toolbar toolbar;

        MyAdapter(Context mContext, List<Bouquet> listOfThings) {
            this.mContext = mContext;
            this.listOfThings = listOfThings;
            for(int i = 0;  i < listOfThings.size(); i++){
                Log.d("price", listOfThings.get(i).getPrice());


            }


        }
        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public int getItemViewType(int position) {
            return position;
        }
        @NonNull
        @Override
        public FlowerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View mView = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_item_row, parent, false);

            toolbar = mView.findViewById(R.id.toolbar);
            title = mView.findViewById(R.id.toolbar_title);

            return new FlowerViewHolder(mView);
        }


        @Override
        public void onBindViewHolder(@NonNull FlowerViewHolder holder, int position) {

            Bouquet flower = listOfThings.get(position);
            String t = flower.getPrice().replaceAll("\\D+","");
            holder.mTitle.setText(t + " JOD");
            String im = flower.getImage();
            byte[] decodedString = Base64.decode(im, Base64.DEFAULT);
            Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
//            Bitmap resized = Bitmap.createScaledBitmap(decodedByte, 100, 100, true);
            holder.mImage.setImageBitmap(decodedByte);
        }


        @Override
        public int getItemCount() {
            return listOfThings.size();
        }


        class FlowerViewHolder extends RecyclerView.ViewHolder {
            ImageView mImage;
            TextView mTitle;

            public FlowerViewHolder(View itemView) {
                super(itemView);
                mTitle = itemView.findViewById(R.id.ivPrice);
                mImage = itemView.findViewById(R.id.image53);
                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Log.d("Tag", "onClick: " + getAdapterPosition());
                        int x = getAdapterPosition();
                        //
                        if(cat.equals("Love bouquets")){
                            List<Bouquet> listOfLove = new ArrayList<>();
                            for(int i = 0; i < listOfBouqs.size(); i++) {
                                if(listOfBouqs.get(i).getType().equals("love")){
                                    listOfLove.add(listOfBouqs.get(i));
                                }
                            }

                            profileOne = listOfLove.get(x);

                        } else if(cat.equals("Thank you bouquets")){
                            List<Bouquet> listOfThank = new ArrayList<>();

                            for(int i = 0; i < listOfBouqs.size(); i++) {
                                if(listOfBouqs.get(i).getType().equals("thankyou")){
                                    listOfThank.add(listOfBouqs.get(i));
                                }
                            }

                            profileOne = listOfThank.get(x);
                        } else if(cat.equals("New Baby bouquets")){
                            List<Bouquet> listOfBaby = new ArrayList<>();

                            for(int i = 0; i < listOfBouqs.size(); i++) {
                                if(listOfBouqs.get(i).getType().equals("newbaby")){
                                    listOfBaby.add(listOfBouqs.get(i));
                                }
                            }

                            profileOne = listOfBaby.get(x);
                        } else if(cat.equals("Get well bouquets")){
                            List<Bouquet> listOfWedding= new ArrayList<>();

                            for(int i = 0; i < listOfBouqs.size(); i++) {
                                if(listOfBouqs.get(i).getType().equals("wedding")){
                                    listOfWedding.add(listOfBouqs.get(i));
                                }
                            }

                            profileOne = listOfWedding.get(x);
                        } else if(cat.equals("Birthday bouquets")){
                            List<Bouquet> listOfBirthday= new ArrayList<>();

                            for(int i = 0; i < listOfBouqs.size(); i++) {
                                if(listOfBouqs.get(i).getType().equals("birthday")){
                                    listOfBirthday.add(listOfBouqs.get(i));
                                }
                            }

                            profileOne = listOfBirthday.get(x);
                        }else if(cat.equals("Graduation bouquets")){
                            List<Bouquet> listOfBirthday= new ArrayList<>();

                            for(int i = 0; i < listOfBouqs.size(); i++) {
                                if(listOfBouqs.get(i).getType().equals("graduation")){
                                    listOfBirthday.add(listOfBouqs.get(i));
                                }
                            }

                            profileOne = listOfBirthday.get(x);
                        }else if (cat.equals("Love Balloons")){

                            profileOne = listOfLoveBalloons.get(x);

                        }else if (cat.equals("Birthday Balloons")){

                            profileOne = listOfBirthdayBalloons.get(x);

                        }else if (cat.equals("New Baby Balloons")){

                            profileOne = listOfBabyBalloons.get(x);

                        }else if (cat.equals("Thank you Balloons")){

                            profileOne = listOfThankBalloons.get(x);

                        } else if (cat.equals("Graduation balloons")){

                            profileOne = listOfGradBalloons.get(x);

                        }  else if(cat.equals("All Bouquets")){
                            List<Bouquet> listOfAllBouqs = new ArrayList<>();

                            for(int i = 0; i < listOfBouqs.size();i++){
                                listOfAllBouqs.add(listOfBouqs.get(i));
                            }

                            for(int i = 0; i < listOfAllBouqs.size(); i++){
                                String desc = listOfAllBouqs.get(i).getDescription();
                                String price = listOfAllBouqs.get(i).getPrice();
                                int rep = 0;
                                for(int j = 0; j < listOfAllBouqs.size(); j++){
                                    String desc2 = listOfAllBouqs.get(j).getDescription();
                                    String price2 = listOfAllBouqs.get(j).getPrice();
                                    if(desc.equals(desc2) && price.equals(price2)){
                                        rep++;
                                    }
                                }
                                if(rep > 1){
                                    for(int k = 0; k < listOfAllBouqs.size(); k++){
                                        if(desc.equals(listOfAllBouqs.get(k).getDescription()) && price.equals(listOfAllBouqs.get(k).getPrice())){
                                            listOfAllBouqs.remove(k);
                                            rep--;
                                            if(rep == 1){
                                                break;
                                            }

                                        }
                                    }
                                }
                            }
                            profileOne = listOfAllBouqs.get(x);
                        }
                        else if(cat.equals("All Balloons")){
                            List<Bouquet> listOfAllBalloons = new ArrayList<>();

                            for(int i = 0; i < listOfBalloons.size();i++){
                                listOfAllBalloons.add(listOfBalloons.get(i));
                            }

                            for(int i = 0; i < listOfAllBalloons.size(); i++){
                                String desc = listOfAllBalloons.get(i).getDescription();
                                String price = listOfAllBalloons.get(i).getPrice();
                                int rep = 0;
                                for(int j = 0; j < listOfAllBalloons.size(); j++){
                                    String desc2 = listOfAllBalloons.get(j).getDescription();
                                    String price2 = listOfAllBalloons.get(j).getPrice();
                                    if(desc.equals(desc2) && price.equals(price2)){
                                        rep++;
                                    }
                                }
                                if(rep > 1){
                                    for(int k = 0; k < listOfAllBalloons.size(); k++){
                                        if(desc.equals(listOfAllBalloons.get(k).getDescription()) && price.equals(listOfAllBalloons.get(k).getPrice())){
                                            listOfAllBalloons.remove(k);
                                            rep--;
                                            if(rep == 1){
                                                break;
                                            }

                                        }
                                    }
                                }
                            }

                            profileOne = listOfAllBalloons.get(x);
                        }
                        goback.setVisibility(View.VISIBLE);

                        rolled = x;
                        FragmentActivity activity = (FragmentActivity) itemView.getContext();
                        FragmentManager manager = activity.getSupportFragmentManager();
                        FragmentTransaction transaction = manager.beginTransaction();
                        transaction.replace(R.id.nav_host_fragment, flowerProfile.newInstance());
                        transaction.commit();
                    }
                });
            }
        }
    }
}
