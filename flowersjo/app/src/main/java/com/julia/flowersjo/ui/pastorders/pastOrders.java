package com.julia.flowersjo.ui.pastorders;

import android.app.ProgressDialog;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
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
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.julia.flowersjo.R;
import com.julia.flowersjo.orders;
import com.julia.flowersjo.ui.clientorder;
import com.julia.flowersjo.ui.firebase.DatabaseHelper;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static android.content.ContentValues.TAG;
import static com.julia.flowersjo.MainActivity.navView;
import static com.julia.flowersjo.MainActivity.pastorderinfomainlist;

public class pastOrders extends Fragment {
    RecyclerView recyclerView;
    orderAdapter adapter;
    static public List<orders> orderSe = new ArrayList<>();
    List<orders> newO = new ArrayList<>();

    DatabaseReference databaseReference;
    FirebaseDatabase firebaseDatabase;
    List<String> orderids = new ArrayList<>();
    List<clientorder> clientOrderss = new ArrayList<>();
    List<clientorder> newC = new ArrayList<>();
    ImageView back;
    Toolbar toolbar;

    public static pastOrders newInstance(){
        pastOrders fragment = new pastOrders();
        return fragment;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.recyclerview_pastorders, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.pastorders_recycler);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        ProgressDialog progressDialog = new ProgressDialog(getActivity(), R.style.MyDialogTheme);
        progressDialog.setMessage("Loading...");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setIndeterminate(true);
        progressDialog.show();
        progressDialog.setCancelable(false);

        toolbar = (Toolbar) getActivity().findViewById(R.id.toolbar);
        back = (ImageView) toolbar.findViewById(R.id.goback);

        back.setVisibility(View.VISIBLE);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                FragmentTransaction transaction = getFragmentManager().beginTransaction();
//                transaction.replace(R.id.nav_host_fragment, MenuFragment.newInstance());
//                transaction.commit();
//                navView.setSelectedItemId(R.id.navigation_menu);

                View view = navView.findViewById(R.id.navigation_menu);
                view.performClick();
            }
        });
        orderSe.clear();
        readOrders();
        readOrdersList();


        int secondsDelayed = 100;
        Handler handler = new Handler();

        handler.postDelayed(new Runnable() {
            public void run() {


                if(!orderSe.isEmpty() && !clientOrderss.isEmpty()){


                    try{
                        String databaseNameWithPath = "/data/data/" + "com.julia.flowersjo" + "/databases/" + "orderID";

                        SQLiteDatabase db = SQLiteDatabase.openOrCreateDatabase(databaseNameWithPath, null);
                        Cursor resultSet = db.rawQuery("Select * from orderid", null);



                        while(resultSet.moveToNext()){
                            orderids.add(resultSet.getString(0));

                        }
                        int re;

                        for(int i = 0; i < orderSe.size(); i++){
                            String id = orderSe.get(i).getOrderID();
                            re = 0;
                            for(int j = 0; j < orderids.size(); j++){
                                if(id.equals(orderids.get(j))){
                                    newO.add(orderSe.get(i));
                                }
                            }

                        }
                    } catch (Exception e){

                    }
                    //testing


//                    for(int i = 0; i < orderids.size(); i++){
//                        Log.d("orders ids", orderids.get(i));
//                    }


                    for(int i = 0; i < newO.size(); i++) {
                        String orderid = newO.get(i).getOrderID();
                        String time = newO.get(i).getTime();
                        String image = newO.get(i).getImage();
                        String date = newO.get(i).getDate();

                        int rep = 0;
                        for (int j = 0; j < newO.size(); j++) {
                            String id2 = newO.get(j).getOrderID();
                            String time2 = newO.get(j).getTime();
                            String image2 = newO.get(j).getImage();
                            String date2 = newO.get(j).getDate();


                            if (orderid.equals(id2) && time.equals(time2) && image.equals(image2) && date.equals(date2)) {
                                rep++;
                            }
                        }
                        while (rep > 1) {

                            for (int j = 0; j < newO.size(); j++) {
                                String id2 = newO.get(j).getOrderID();
                                String time2 = newO.get(j).getTime();
                                String image2 = newO.get(j).getImage();
                                String date2 = newO.get(j).getDate();


                                if (orderid.equals(id2) && time.equals(time2) && image.equals(image2) && date.equals(date2)) {
                                    newO.remove(i);
                                    rep--;
                                    if (rep == 1) {
                                        break;
                                    }

                                }
                            }



                        }
                    }
//                    for(int i = 0; i < orderSe.size(); i++){
//                        String id = orderSe.get(i).getOrderID();
//                        re = 0;
//                        for(int j = 0; j < orderids.size(); j++){
//                            if(id.equals(orderids.get(j))){
//                                newO.add(orderSe.get(i));
//                            }
//                        }
//
//                    }


                    for(int i = 0; i < clientOrderss.size(); i++) {
                        String orderid = clientOrderss.get(i).getOrderid();


                        int rep = 0;
                        for (int j = 0; j < clientOrderss.size(); j++) {
                            String id2 = clientOrderss.get(j).getOrderid();



                            if (orderid.equals(id2)) {
                                rep++;
                            }
                        }
                        while (rep > 1) {
                            clientOrderss.remove(i);
                            rep--;
                            if (rep == 1) {
                                break;
                            }



                        }
                    }
                    for(int i = 0; i < clientOrderss.size(); i++){
                        Log.d("orders ids", clientOrderss.get(i).getOrderid());
                    }
                    for(int i = 0; i < clientOrderss.size(); i++){
                        String id = clientOrderss.get(i).getOrderid();

                        for(int j = 0;  j < newO.size(); j++){
                            if(id.equals(newO.get(j).getOrderID())){
                                newC.add(clientOrderss.get(i));
                            }
                        }

                    }
//                    for(int i = 0; i < newC.size(); i++){
//                        Log.d("orders ids", newC.get(i).getOrderid());
//                    }
                    for(int i = 0; i < newC.size(); i++) {
                        String orderid = newC.get(i).getOrderid();


                        int rep = 0;
                        for (int j = 0; j < newC.size(); j++) {
                            String id2 = newC.get(j).getOrderid();



                            if (orderid.equals(id2)) {
                                rep++;
                            }
                        }
                        while (rep > 1) {
                            newC.remove(i);
                            rep--;
                            if (rep == 1) {
                                break;
                            }



                        }
                    }
//                    for(int i = 0; i < newC.size(); i++){
//                        Log.d("orders ids", newC.get(i).getOrderid());
//                    }
//
//                    for(int i = 0; i < orderids.size(); i++){
//                        String id = orderids.get(i);
//                        int r = 0;
//                        for(int j = 0; j < orderids.size(); j++){
//                            if(id.equals(orderids.get(j))){
//                                r++;
//                            }
//                        }
//                        while(r > 1){
//                            for(int k = 0; k < orderids.size(); k++){
//                                if(id.equals(orderids.get(k))){
//                                    orderids.remove(k);
//                                    r--;
//                                }
//                            }
//                        }
//                    }
////

                  putAllTogether();

                    Collections.reverse(pastorderinfomainlist);
                    adapter = new orderAdapter(getContext(), pastorderinfomainlist);
                    adapter.setHasStableIds(true);

                    recyclerView.setAdapter(adapter);
                    progressDialog.dismiss();

                }else {
                    handler.postDelayed(this, secondsDelayed);
                }

            }
        }, secondsDelayed);



//        this.orderid = orderid;
//        this.date = date;
//

        return view;
    }




    public class orderAdapter extends RecyclerView.Adapter<orderAdapter.orderViewHolder>{

        private Context context;
        List<pastorderinfomain> pastorderinfomainList;


        public orderAdapter(Context context, List<pastorderinfomain> pastorderinfomain){
            this.context = context;
            this.pastorderinfomainList = pastorderinfomain;
        }

        @NonNull
        @Override
        public orderViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i){
            LayoutInflater inflater = LayoutInflater.from(context);
            final View view = inflater.inflate(R.layout.pastorder_layout_item, null);
            orderViewHolder orderViewHolder = new orderViewHolder(view);
            return orderViewHolder;
        }

        @Override
        public void onBindViewHolder(@NonNull orderViewHolder orderViewHolder, int i){
            pastorderinfomain pastorderinfomain = pastorderinfomainList.get(i);
            orderViewHolder.orderid.setText(pastorderinfomain.getOrderid());
            String dateandtime = pastorderinfomain.getDate()+ " " +pastorderinfomain.getTime();

            orderViewHolder.timeanddate.setText(dateandtime);
//
//            if(pastorderinfomain.getType().contains("Accepted")){
//                orderViewHolder.status.setText("Accepted");
//                orderViewHolder.status.setTextColor(Color.GREEN);
//
//            } else if(pastorderinfomain.getType().contains("Cancelled")){
//                orderViewHolder.status.setText("Cancelled");
//                orderViewHolder.status.setTextColor(Color.RED);
//            }
            String im = pastorderinfomain.getImage();
            byte[] decodedString = Base64.decode(im, Base64.DEFAULT);
            Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
            Bitmap resized = Bitmap.createScaledBitmap(decodedByte, 180, 180, true);

            orderViewHolder.imageCart.setImageBitmap(resized);

        }

        @Override
        public int getItemCount(){
            return pastorderinfomainList.size();
        }


        class orderViewHolder extends RecyclerView.ViewHolder {

            TextView orderid, status, timeanddate;
            ImageView imageCart;


            public orderViewHolder(@NonNull View itemView) {
                super(itemView);

                orderid = (TextView) itemView.findViewById(R.id.orderid);
//                status = (TextView) itemView.findViewById(R.id.status);
                timeanddate = (TextView) itemView.findViewById(R.id.timeanddate);
                imageCart = (ImageView) itemView.findViewById(R.id.imageCart);


                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int x = getAdapterPosition();
                            Fragment selectedFragment = pastOrderDetail.newInstance();
                            Bundle bd1 = new Bundle();
                            bd1.putInt("a", x);
                            selectedFragment.setArguments(bd1);

                            FragmentTransaction transaction = getFragmentManager().beginTransaction();
                            transaction.replace(R.id.nav_host_fragment, selectedFragment);
                            transaction.commit();
                        for(int i = 0; i < pastorderinfomainlist.size(); i++){
//
                        }
                    }
                });


            }
        }
    }

    private void putAllTogether(){




        pastorderinfomainlist.clear();
        for(int i = 0; i < newO.size(); i++){
            String id = newO.get(i).getOrderID();
            for(int j = 0; j < newC.size(); j++){
                if(id.equals(newC.get(j).getOrderid())){
                    Log.d("orders new", newC.get(j).getOrderid());
                    pastorderinfomain p = new pastorderinfomain();

                    p.setOrderid(newC.get(j).getOrderid());
                    p.setImage(newO.get(i).getImage());
                    p.setDate(newO.get(i).getDate());
                    p.setTime(newO.get(j).getTime());
                    p.setName(newC.get(j).getName());
                    p.setEmail(newC.get(j).getEmail());
                    p.setNumber(newC.get(j).getNumber());
                    p.setAddress(newC.get(j).getAddress());
                    p.setCity(newC.get(j).getCity());
                    p.setType(newC.get(j).getType());

                    pastorderinfomainlist.add(p);
                }
            }

        }


//
//        for(int i = 0; i < pastorderinfomainlist.size(); i++){
//            String orderid = pastorderinfomainlist.get(i).getOrderid();
//            String time = pastorderinfomainlist.get(i).getTime();
//            String image = pastorderinfomainlist.get(i).getImage();
//            String date = pastorderinfomainlist.get(i).getDate();
//
//            int rep = 0;
//            for(int j = 0; j < pastorderinfomainlist.size(); j++){
//                String id2 = pastorderinfomainlist.get(j).getOrderid();
//                String time2 = pastorderinfomainlist.get(j).getTime();
//                String image2 = pastorderinfomainlist.get(j).getImage();
//                String date2 = pastorderinfomainlist.get(j).getDate();
//
//
//                if(orderid.equals(id2) && time.equals(time2) && image.equals(image2)&& date.equals(date2)){
//                    rep++;
//                }
//            }
//            if(rep > 1){
//                for(int k = 0; k < pastorderinfomainlist.size(); k++){
//                    if(orderid.equals(pastorderinfomainlist.get(k).getOrderid()) && time.equals(pastorderinfomainlist.get(k).getTime()) && image.equals(pastorderinfomainlist.get(k).getImage()) && date.equals(pastorderinfomainlist.get(k).getDate())){
//                        pastorderinfomainlist.remove(k);
//                        rep--;
//                        if(rep == 1){
//                            break;
//                        }
//
//                    }
//                }
//            }
//        }



    }
    private void readOrders() {
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("orders");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                DatabaseHelper d = new DatabaseHelper();
                orderSe.clear();
                orderSe = d.DatabaseHelperReadOrdersS(dataSnapshot);



            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });
    }

    public void readOrdersList() {
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("clientorder");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                DatabaseHelper d = new DatabaseHelper();
                clientOrderss.clear();
                clientOrderss = d.DatabaseHelperReadOrders(dataSnapshot);



            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });
    }

//    public void readOrderDetails() {
//        String id = pastorderinfomainlist.get(a).getOrderid();
//        for(int i = 0; i < clientOrderss.size(); i++){
//            if(id.equals(clientOrderss.get(i).getOrderid())){
//                c = clientOrderss.get(i);
//            }
//        }
//
//    }
}
