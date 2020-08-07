package com.julia.flowersjo.ui.pastorders;

import android.app.ProgressDialog;
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

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.julia.flowersjo.R;
import com.julia.flowersjo.ui.clientorder;
import com.julia.flowersjo.ui.firebase.DatabaseHelper;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;
import static com.julia.flowersjo.MainActivity.pastorderinfomainlist;

public class pastOrderDetail extends Fragment {

    TextView given_name, given_email, given_number, given_address, given_city, timeanddate, orderid, given_type, status;
    ImageView imageCart, goback;
    Toolbar toolbar;
    List<pastOrderDetail> pastOrderDetailList = new ArrayList<>();
    int a;
    DatabaseReference databaseReference;
    FirebaseDatabase firebaseDatabase;
     List<clientorder> clientOrderss = new ArrayList<>();
    clientorder c = new clientorder();

    public static pastOrderDetail newInstance(){
        pastOrderDetail fragment = new pastOrderDetail();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.pastorder_details, container, false);

        toolbar = (Toolbar) getActivity().findViewById(R.id.toolbar);
        goback = (ImageView) toolbar.findViewById(R.id.goback);
        goback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.nav_host_fragment, pastOrders.newInstance());
                transaction.commit();
            }
        });

        given_address = (TextView) view.findViewById(R.id.given_address);
        given_city = (TextView) view.findViewById(R.id.given_city);
        given_email = (TextView) view.findViewById(R.id.given_email);
//        status = (TextView) view.findViewById(R.id.status);
        given_name = (TextView) view.findViewById(R.id.given_name);
        given_number = (TextView) view.findViewById(R.id.given_number);
        timeanddate = (TextView) view.findViewById(R.id.timeanddate);
        orderid = (TextView) view.findViewById(R.id.orderid);
//        given_type = (TextView) view.findViewById(R.id.given_type);
        imageCart = (ImageView) view.findViewById(R.id.imageCart);
        readOrdersList();

         a = getArguments().getInt("a");

        int secondsDelayed = 100;
        Handler handler = new Handler();

        ProgressDialog progressDialog = new ProgressDialog(getActivity(), R.style.MyDialogTheme);
        progressDialog.setMessage("Loading...");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setIndeterminate(true);
        progressDialog.show();
        progressDialog.setCancelable(false);

        handler.postDelayed(new Runnable() {
            public void run() {

                if(!clientOrderss.isEmpty()){

                    //testing

                    readOrderDetails();
                    if(c.getAddress().equals("")){
                       given_address.setVisibility(View.GONE);
                       given_city.setVisibility(View.GONE);
                    }
                    given_address.setText(c.getAddress());
                    given_name.setText(c.getName());
                    given_city.setText(c.getCity());
                    given_email.setText(c.getEmail());
                    given_number.setText(c.getNumber());
                    orderid.setText(pastorderinfomainlist.get(a).getOrderid());
//                    if(c.getType().equals("collectionAccepted")){
////                        status.setText("Accepted");
////                        given_type.setText("Collection");
////                        status.setTextColor(Color.GREEN);
//
//                    }else{
////                        status.setText("Cancelled");
//                        given_type.setText(c.getType());
//                        status.setTextColor(Color.RED);
//                    }
                    timeanddate.setText(pastorderinfomainlist.get(a).getDate() + " " + pastorderinfomainlist.get(a).getTime());
                    String im = pastorderinfomainlist.get(a).getImage();
                    byte[] decodedString = Base64.decode(im, Base64.DEFAULT);
                    Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
                    Bitmap resized = Bitmap.createScaledBitmap(decodedByte, 180, 180, true);

                    imageCart.setImageBitmap(resized);

                    progressDialog.dismiss();


                }else {
                    handler.postDelayed(this, secondsDelayed);
                }

            }
        }, secondsDelayed);

        return view;
    }

    public void readOrdersList() {
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("clientorder");
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
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

    public void readOrderDetails() {
        String id = pastorderinfomainlist.get(a).getOrderid();
        for(int i = 0; i < clientOrderss.size(); i++){
            if(id.equals(clientOrderss.get(i).getOrderid())){
                c = clientOrderss.get(i);
            }
        }

    }

}
