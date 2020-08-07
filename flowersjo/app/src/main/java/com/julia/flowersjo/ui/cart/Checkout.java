package com.julia.flowersjo.ui.cart;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Looper;
import android.provider.Settings;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import com.julia.flowersjo.JavaMailAPI;
import com.julia.flowersjo.MainActivity;
import com.julia.flowersjo.R;
import com.julia.flowersjo.orders;
import com.julia.flowersjo.successActivity;
import com.julia.flowersjo.ui.clientorder;
import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.IdpResponse;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Random;

import static android.app.Activity.RESULT_OK;
import static com.julia.flowersjo.MainActivity.ID;
import static com.julia.flowersjo.MainActivity.client;
import static com.julia.flowersjo.MainActivity.listofcart;
import static com.julia.flowersjo.MainActivity.orderList;
import static com.julia.flowersjo.MainActivity.subtotal;
import static com.julia.flowersjo.MainActivity.totalA;
import static com.julia.flowersjo.splashScreen.clientOrders;

public class Checkout extends Fragment {

    Button delivery, collection, login;
    EditText fname, email, pnumber, address1, city;
    TextView location, warningselection, add1textview, citytextview, emailwarning, subtotal_price, total_price, chargeS;
    Geocoder geocoder;
    DatabaseReference databaseReference;
    FirebaseDatabase firebaseDatabase;
    private static final int RC_SIGN_IN = 123;
    FusedLocationProviderClient mFusedLocationClient;
    Button makeOrder;
    int PERMISSION_ID = 123;
    boolean N, E, Num, Ad, C, makeorder, all;
    List<Address> add ;
    public static Checkout newInstance(){
        Checkout fragment = new Checkout();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.checkout, container, false);

        for(int i = 0; i < orderList.size();i++){
            Log.d("orders date", orderList.get(i).getDate());

        }
        N = false;
        E = false;
        Num = false;
        Ad = false;
        C = false;
        makeorder = false;
        all = false;

        subtotal_price = (TextView) view.findViewById(R.id.subtotal_price);
        address1 = (EditText) view.findViewById(R.id.add1);
        city = (EditText) view.findViewById(R.id.city);
        fname = (EditText) view.findViewById(R.id.first_name);
        email = (EditText) view.findViewById(R.id.email);
        pnumber = (EditText) view.findViewById(R.id.phone_number);
        location = (TextView) view.findViewById(R.id.location);
        delivery = (Button) view.findViewById(R.id.delivery);
        collection = (Button) view.findViewById(R.id.collection);
        login = (Button) view.findViewById(R.id.login);
        citytextview = (TextView) view.findViewById(R.id.citytextview);
        add1textview = (TextView) view.findViewById(R.id.add1textview);
        warningselection = (TextView) view.findViewById(R.id.warningselection);
        emailwarning = (TextView) view.findViewById(R.id.emailwarning);
        makeOrder = (Button) view.findViewById(R.id.makeorder);
        String EMAIL = email.getText().toString();
        login.setVisibility(View.VISIBLE);
        total_price = (TextView) view.findViewById(R.id.total_price);
        chargeS = (TextView) view.findViewById(R.id.chargeS);




        //cart info prices

        String databaseNameWithPath = "/data/data/" + "com.julia.flowersjo" + "/databases/" + "infoName";
        SQLiteDatabase dc;
        dc = SQLiteDatabase.openOrCreateDatabase(databaseNameWithPath, null);

        try{

            dc.execSQL("CREATE TABLE IF NOT EXISTS infoName(name String, email String, pnum String);");
            Cursor resultSet = dc.rawQuery("Select * from infoName", null);
            resultSet.moveToNext();
            int icount = resultSet.getInt(0);
            int x = 0;
            Cursor resultSet2 = dc.rawQuery("Select * from infoName", null);

            while(resultSet2.moveToNext() && x < 1) {


                String name = resultSet2.getString(0);
                fname.setText(name);
                String e = resultSet2.getString(1);
                email.setText(e);
                String n = resultSet2.getString(2);
                pnumber.setText(n);
                String emailPattern2 = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

                if(!email.getText().toString().matches(emailPattern2)){
                    login.setVisibility(View.VISIBLE);

                }else {
                    login.setVisibility(View.GONE);
                }
                if (icount == 0) {

                    ContentValues iv = new ContentValues();
                    iv.put("name", " ");
                    iv.put("email", " ");
                    iv.put("pnum", " ");
                    dc.insert("infoName", null, iv);
                }


                x++;
            }

        }catch (Exception e){
            ContentValues iv = new ContentValues();
            dc.delete("infoName", "1", null);

            iv.put("name", " ");
            iv.put("email", " ");
            iv.put("pnum", " ");
            dc.insert("infoName", null, iv);
        }

        int subtotal = 0;
        int total = 0;
        for(int i = 0; i < listofcart.size(); i++){
            String x = listofcart.get(i).getPrice().replaceAll("\\D+","");
            subtotal = Integer.parseInt(x) + subtotal;
            total = subtotal;

        }
        total = total + 3;
        total_price.setText(Integer.toString(total) + " JOD");
        subtotal_price.setText(Integer.toString(subtotal) + " JOD");



        if(EMAIL.equals("") || EMAIL.equals(" ")){
            login.setVisibility(View.VISIBLE);
        }else {
            login.setVisibility(View.GONE);
        }
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<AuthUI.IdpConfig> providers = Arrays.asList(
                        new AuthUI.IdpConfig.EmailBuilder().build(),
                        new AuthUI.IdpConfig.GoogleBuilder().build());

// Create and launch sign-in intent
                startActivityForResult(
                        AuthUI.getInstance()
                                .createSignInIntentBuilder()
                                .setAvailableProviders(providers)
                                .build(),
                        RC_SIGN_IN);

            }
        });


        Random rand = new Random();
        int orderID = 0;
        int rep = 0;

        boolean used = true;
        String id = "";
        while(used){
            orderID = rand.nextInt(999999999);
            id = Integer.toString(orderID);
            for(int i = 0; i < clientOrders.size(); i++){
                if(id.equals(clientOrders.get(i).getOrderid())){
                    rep++;
                }
            }
            if(rep == 0){
                used = false;
            }
        }


        ID = id;

        for(int i = 0; i < listofcart.size(); i++){
            Log.d("orderdate: ", listofcart.get(i).getDate());
        }

        orderList.clear();
        for(int i = 0; i < listofcart.size(); i++){
            orders orders = new orders();

            orders.setDate(listofcart.get(i).getDate());
            orders.setTime(listofcart.get(i).getTime());
            orders.setImage(listofcart.get(i).getImage());
            orders.setQuantity(listofcart.get(i).getQuantity());
            orders.setOrderID(id);
            orders.setTo(listofcart.get(i).getTo());
            orders.setFrom(listofcart.get(i).getFrom());
            orders.setMessage(listofcart.get(i).getMessage());

            orderList.add(orders);
        }
        for(int i = 0; i < orderList.size(); i++){
            Log.d("orderlist ",  orderList.get(i).getDate());
        }



        delivery.setBackgroundResource(R.drawable.mybutton);
        MainActivity.delivery = true;


        String emailPattern2 = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

        if(email.getText().toString().matches(emailPattern2)){
            login.setVisibility(View.GONE);

        }
        makeOrder.setBackgroundResource(R.drawable.mybutton);

        makeOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String address = address1.getText().toString();
                String cit = city.getText().toString();
                String EMAIL = email.getText().toString();
                String name = fname.getText().toString();
                String num = pnumber.getText().toString();
                if((MainActivity.delivery == false && MainActivity.collection == false)){
                    warningselection.setVisibility(View.VISIBLE);
                }
                if((address.equals("")  || cit.equals("")  || EMAIL.equals("") || name.equals("") || num.equals("")) && MainActivity.delivery){

                    AlertDialog.Builder builder1 = new AlertDialog.Builder(getContext(), R.style.MyDialogTheme);
                    builder1.setMessage("Make sure you have filled all information.");

                    builder1.setCancelable(true);

                    builder1.setPositiveButton(
                            "OK",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    dialog.cancel();
                                }
                            });



                    AlertDialog alert11 = builder1.create();
                    alert11.show();
                } else if((MainActivity.delivery || MainActivity.collection)) {
//


                    if(MainActivity.collection){


                            client.setAddress("");
                            client.setCity("");
                            client.setEmail(email.getText().toString());
                            client.setName(fname.getText().toString());
                            client.setNumber(pnumber.getText().toString());
                            client.setPayment("cash");
                            client.setType("collectionAccepted");
                            client.setPrice(totalA);
                            client.setOrderid(ID);
                            String databaseNameWithPath = "/data/data/" + "com.julia.flowersjo" + "/databases/" + "orderID";
                            addInformation(client, orderList);

                            SQLiteDatabase d ;
                            d = SQLiteDatabase.openOrCreateDatabase(databaseNameWithPath, null);
                            d.execSQL("CREATE TABLE IF NOT EXISTS orderID(orderid integer primary key);");
                            ContentValues insertValues = new ContentValues();
                            insertValues.put("orderid", ID);
                            d.insert("orderID", null, insertValues);
                            sendEmail();
                            listofcart.clear();

                            String dbwp = "/data/data/" + "com.julia.flowersjo" + "/databases/" + "infoName";

                            SQLiteDatabase dc = SQLiteDatabase.openOrCreateDatabase(dbwp, null);
                            dc.execSQL("CREATE TABLE IF NOT EXISTS infoName(name String, email String, pnum String);");

                            Cursor resultSet = dc.rawQuery("Select * from infoName", null);
                            resultSet.moveToNext();
                            int icount = resultSet.getInt(0);
                            Cursor resultSet2 = dc.rawQuery("Select * from infoName", null);

                            ContentValues cv = new ContentValues();
                            int x =0;
                            while(resultSet2.moveToNext() && x < 1) {

                                dc.delete("infoName", "1", null);
                                cv.put("name", fname.getText().toString());
                                cv.put("email", email.getText().toString());
                                cv.put("pnum", pnumber.getText().toString());
                                dc.insert("infoName", null, cv);


                                x++;

                            }


                            Intent i = new Intent(getContext(), successActivity.class);
                            startActivity(i);

                    } else if (MainActivity.delivery){
                        if(!address.equals("")  && !cit.equals("")  && !EMAIL.equals("") && !name.equals("") && !num.equals("")){

                            if(!cit.equals("Amman")){
                                AlertDialog.Builder builder1 = new AlertDialog.Builder(getContext(), R.style.MyDialogTheme);
                                builder1.setMessage("Delivery is only available in Amman at the moment");

                                builder1.setCancelable(true);

                                builder1.setPositiveButton(
                                        "OK",
                                        new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog, int id) {
                                                dialog.cancel();
                                            }
                                        });



                                AlertDialog alert11 = builder1.create();
                                alert11.show();
                            }else {

                                client.setAddress(address1.getText().toString());
                                client.setCity(city.getText().toString());
                                client.setEmail(email.getText().toString());
                                client.setName(fname.getText().toString());
                                client.setNumber(pnumber.getText().toString());
                                client.setPayment("cash");

                                client.setType("delivery");

                                client.setPrice(totalA);
                                client.setOrderid(ID);

                                addInformation(client, orderList);
                                sendEmail();
                                String databaseNameWithPath = "/data/data/" + "com.example.flowers" + "/databases/" + "orderID";

                                SQLiteDatabase d ;
                                d = SQLiteDatabase.openOrCreateDatabase(databaseNameWithPath, null);
                                d.execSQL("CREATE TABLE IF NOT EXISTS orderID(orderid integer primary key);");
                                ContentValues insertValues = new ContentValues();
                                insertValues.put("orderid", ID);
                                d.insert("orderID", null, insertValues);
                                Intent i = new Intent(getContext(), successActivity.class);
                                startActivity(i);
                            }

                        }
                    }







                }




            }
        });
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
        email.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                login.setVisibility(View.VISIBLE);

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String emailV = email.getText().toString().trim();

                if(!emailV.matches(emailPattern) || s.length() == 0){
                    emailwarning.setVisibility(View.VISIBLE);
                    login.setVisibility(View.VISIBLE);
                }else {
                    emailwarning.setVisibility(View.GONE);
                    login.setVisibility(View.GONE);
                }
            }
        });


        location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mFusedLocationClient = LocationServices.getFusedLocationProviderClient(getActivity());
                getLastLocation();

            }
        });
        collection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.collection = true;
                MainActivity.delivery = false;
                delivery.setBackgroundResource(R.drawable.another_button_background);
                collection.setBackgroundResource(R.drawable.mybutton);
                citytextview.setVisibility(View.GONE);
                add1textview.setVisibility(View.GONE);
                address1.setVisibility(View.GONE);
                city.setVisibility(View.GONE);
                location.setVisibility(View.GONE);
            }
        });

//        cashe.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                cash = true;
//                card = false;
//                cashe.setBackgroundResource(R.drawable.mybutton);
//                carde.setBackgroundResource(R.drawable.another_button_background);
//            }
//        });
        delivery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                if(collection.isChecked()){
//                    collection.setChecked(false);
//
//                }
                MainActivity.collection = false;
                MainActivity.delivery = true;
                delivery.setBackgroundResource(R.drawable.mybutton);
                collection.setBackgroundResource(R.drawable.another_button_background);
                citytextview.setVisibility(View.VISIBLE);
                add1textview.setVisibility(View.VISIBLE);
                address1.setVisibility(View.VISIBLE);
                city.setVisibility(View.VISIBLE);
                location.setVisibility(View.VISIBLE);

            }
        });




        return view;
    }
    public void addInformation(clientorder client, List<orders> ordersList){
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("clientorder");

        String key = databaseReference.push().getKey();
        databaseReference.child(key).setValue(client).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {

                Log.d("Success", "adding successful");
            }
        });

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("orders");
        for(int i = 0; i < ordersList.size(); i++){
            String keys = databaseReference.push().getKey();

            databaseReference.child(keys).setValue(ordersList.get(i)).addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void aVoid) {
                    Log.d("Success", "adding successful");

                }
            });
        }
    }
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RC_SIGN_IN) {
            IdpResponse response = IdpResponse.fromResultIntent(data);

            if (resultCode == RESULT_OK) {
                // Successfully signed in
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                fname.setText(user.getDisplayName());
                email.setText(user.getEmail());
                if(user.getPhoneNumber()!= null){
                    pnumber.setText(user.getPhoneNumber());
                }
                login.setVisibility(View.GONE);
                // ...
            } else {
                // Sign in failed. If response is null the user canceled the
                // sign-in flow using the back button. Otherwise check
                // response.getError().getErrorCode() and handle the error.
                // ...
            }
        }
    }
    private void sendEmail() {

        String mail = email.getText().toString().trim();
        String subject = "Order Successful";

        StringBuilder str = new StringBuilder();
        str.append("Dear " + fname.getText().toString());
        str.append('\n');
        str.append("Your order is being prepared.");
        str.append('\n');
        str.append("Order Details: ");
        str.append(listofcart.get(0).getPrice());
        str.append('\n');
        str.append("total: ");

        String message = "<br/><br/>" + "Your order is being prepared. <br/>" + "Order Details:<br/>" + listofcart.get(0).getPrice() + "<br/>" + "total: " + totalA + "<br/>" + "Subtotal: " + subtotal;
        JavaMailAPI javaMailAPI = new JavaMailAPI(getContext(),mail,subject,str.toString());

        javaMailAPI.execute();
    }

    @SuppressLint("MissingPermission")
    private void requestNewLocationData(){

        LocationRequest mLocationRequest = new LocationRequest();
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        mLocationRequest.setInterval(0);
        mLocationRequest.setFastestInterval(0);
        mLocationRequest.setNumUpdates(1);

        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(getActivity());
        mFusedLocationClient.requestLocationUpdates(
                mLocationRequest, mLocationCallback,
                Looper.myLooper()
        );

    }
    private LocationCallback mLocationCallback = new LocationCallback() {
        @Override
        public void onLocationResult(LocationResult locationResult) {
            Location mLastLocation = locationResult.getLastLocation();
        }
    };
    public boolean isOnline() {
        ConnectivityManager cm =
                (ConnectivityManager) getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        if (netInfo != null && netInfo.isConnectedOrConnecting()) {
            return true;
        }
        return false;
    }
    private boolean isLocationEnabled() {
        LocationManager locationManager = (LocationManager) getContext().getSystemService(Context.LOCATION_SERVICE);
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) || locationManager.isProviderEnabled(
                LocationManager.NETWORK_PROVIDER
        );
    }
    @SuppressLint("MissingPermission")
    private void getLastLocation(){
        if (checkPermissions()) {
            if (isLocationEnabled()) {
                mFusedLocationClient.getLastLocation().addOnCompleteListener(
                        new OnCompleteListener<Location>() {
                            @Override
                            public void onComplete(@NonNull Task<Location> task) {
                                Location location = task.getResult();
                                if (location == null) {
                                    requestNewLocationData();
                                } else {
                                    geocoder = new Geocoder(getActivity(), Locale.getDefault());
                                    Double latitude = location.getLatitude();
                                    String lat = Double.toString(latitude);
                                    Double longi = location.getLongitude();


                                    try {

                                        add = geocoder.getFromLocation(latitude, longi, 1);
                                        String address = add.get(0).getAddressLine(0);
                                        String City = add.get(0).getLocality();
                                        if(!City.equals("Amman")){
                                            AlertDialog.Builder builder1 = new AlertDialog.Builder(getContext(), R.style.MyDialogTheme);
                                            builder1.setMessage("Delivery is only available in Amman at the moment");

                                            builder1.setCancelable(true);

                                            builder1.setPositiveButton(
                                                    "OK",
                                                    new DialogInterface.OnClickListener() {
                                                        public void onClick(DialogInterface dialog, int id) {
                                                            dialog.cancel();
                                                        }
                                                    });



                                            AlertDialog alert11 = builder1.create();
                                            alert11.show();
                                        }else {
                                            city.setText(City);
                                            address1.setText(address);
                                        }

                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }


                                }
                            }
                        }
                );
            } else {
                Toast.makeText(getActivity(), "Turn on location", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                startActivity(intent);
            }
        } else {
            requestPermissions();
        }
    }

    private boolean checkPermissions(){
        if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED){
            return true;
        }
        return false;
    }
    private void requestPermissions(){
        ActivityCompat.requestPermissions(
                getActivity(),
                new String[]{Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION},
                PERMISSION_ID
        );
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == PERMISSION_ID) {
            if(grantResults.length>0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                // Granted. Start getting the location information
            }
        }
    }


}
