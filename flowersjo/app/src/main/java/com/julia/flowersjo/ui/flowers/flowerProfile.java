package com.julia.flowersjo.ui.flowers;

import android.app.DatePickerDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toolbar;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.github.chrisbanes.photoview.PhotoViewAttacher;
import com.julia.flowersjo.R;
import com.julia.flowersjo.ui.cartlist;
import com.julia.flowersjo.ui.flowerData.FlowerFragment;
import com.google.android.material.bottomnavigation.BottomNavigationItemView;
import com.google.android.material.bottomnavigation.BottomNavigationMenuView;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.io.ByteArrayOutputStream;
import java.util.Calendar;

import static com.julia.flowersjo.MainActivity.listofcart;
import static com.julia.flowersjo.MainActivity.navView;
import static com.julia.flowersjo.MainActivity.profileOne;
import static com.julia.flowersjo.MainActivity.profileTwo;
import static com.julia.flowersjo.MainActivity.typeF;

public class flowerProfile extends Fragment {
    Button plus, minus, addToCart,datePick, timePick;
    TextView quantity, title, description, toolbarTitle,warningtime,company;
    ImageView flowerImage, goback, comp;

    EditText message_from, message_to, message_written;

    boolean j = false;
    View badge;
    Toolbar toolbar;
    TextView ToolbarTitle;
    TextView noti;
    Calendar dateSelected = Calendar.getInstance();
    PhotoViewAttacher mAttacher;


    private DatePickerDialog datePickerDialog;

    public static flowerProfile newInstance(){
        flowerProfile fragment = new flowerProfile();
        return fragment;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.flower_profile, container, false);


//
        flowerImage = (ImageView) view.findViewById(R.id.imageFlower);
        toolbarTitle = (TextView) view.findViewById(R.id.toolbar_title);
        plus = (Button) view.findViewById(R.id.plus);
        minus = (Button) view.findViewById(R.id.minus);
        quantity = (TextView) view.findViewById(R.id.quantity);
        title = (TextView) view.findViewById(R.id.priceFlower);
        description = (TextView) view.findViewById(R.id.description_product);
        addToCart = (Button) view.findViewById(R.id.add_to_cart);

        message_from = (EditText) view.findViewById(R.id.message_from);
        message_to = (EditText) view.findViewById(R.id.message_to);
        message_written = (EditText) view.findViewById(R.id.message_written);
        toolbar = (Toolbar) getActivity().findViewById(R.id.toolbar);
        goback = (ImageView) toolbar.findViewById(R.id.goback);
        toolbarTitle = (TextView) toolbar.findViewById(R.id.toolbar_title);

    if(!listofcart.isEmpty()) {
        j = true;

        addBadgeIn();
    }

        goback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goback.setVisibility(View.GONE);
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.nav_host_fragment, FlowerFragment.newInstance());
                transaction.commit();
//                removeBadge(navView, 2);

            }
        });




        if(typeF == false){
            String t = profileOne.getPrice().replaceAll("\\D+","");

            title.setText(t + " JOD");
            description.setText(profileOne.getDescription());
            String im = profileOne.getImage();
            byte[] decodedString = Base64.decode(im, Base64.DEFAULT);
            Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
            flowerImage.setImageBitmap(decodedByte);
            mAttacher = new PhotoViewAttacher(flowerImage);
        } else {
            title.setText(profileTwo.getTitle() + " JOD");
            description.setText(profileTwo.getDescription());
            String im = profileTwo.getImage();
            byte[] decodedString = Base64.decode(im, Base64.DEFAULT);
            Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
            flowerImage.setImageBitmap(decodedByte);
            mAttacher = new PhotoViewAttacher(flowerImage);

            typeF = false;
        }
        mAttacher.update();





        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String number = quantity.getText().toString();
                int n = Integer.parseInt(number);
                n = n + 1;
                number = Integer.toString(n);
                quantity.setText(number);
            }
        });

        minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String number = quantity.getText().toString();
                int n = Integer.parseInt(number);
                if(n > 1){
                    n = n - 1;
                    number = Integer.toString(n);
                    quantity.setText(number);
                }
            }
        });

        addToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                    String flowerPrice = title.getText().toString();
                    String quantityCart = quantity.getText().toString();

                    cartlist listcarts = new cartlist();

                    ByteArrayOutputStream stream = new ByteArrayOutputStream();
                    BitmapDrawable drawable = (BitmapDrawable) flowerImage.getDrawable();
                    Bitmap bitmap = drawable.getBitmap();
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
                    byte[] byteFormat = stream.toByteArray();
                    String imageE = Base64.encodeToString(byteFormat, Base64.NO_WRAP);


                    listcarts.setImage(imageE);
                    listcarts.setPrice(flowerPrice);
                    listcarts.setQuantity(quantityCart);


                boolean d = false;
                if(!listofcart.isEmpty()){
                    d = true;
                }
                    listofcart.add(listcarts);

                if(d){
                    j = true;
                    addBadgeIn();
                }else {
                    j = false;
                    addBadgeIn();
                    j = true;
                }


                    Log.d("Tag", "quantity: " + listofcart.get(0).getQuantity() + "  PRICE: " + listofcart.get(0).getPrice());


                }





        });


        return view;
    }

    private void addBadgeIn() {

     if(!j){
         BottomNavigationMenuView bt = (BottomNavigationMenuView) navView.getChildAt(0);
         View k = bt.getChildAt(2);
         BottomNavigationItemView itemView = (BottomNavigationItemView) k;
                 badge = LayoutInflater.from(getActivity()).inflate(R.layout.notification_badge, itemView, true);
         noti = (TextView) badge.findViewById(R.id.nb);
         noti.setVisibility(View.VISIBLE);

         noti.setText(Integer.toString(listofcart.size()));

     } else {

         BottomNavigationMenuView bottomNavigationMenuView = (BottomNavigationMenuView) navView.getChildAt(0);

         View v = bottomNavigationMenuView.getChildAt(2);

         noti = (TextView) v.findViewById(R.id.nb);
         noti.setText(Integer.toString(listofcart.size()));
         navView.getMenu().findItem(R.id.navigation_dashboard).setTitle("Cart");

     }
    }

    public void removeBadge(BottomNavigationView navigationView, int index) {


        BottomNavigationMenuView bottomNavigationMenuView = (BottomNavigationMenuView) navigationView.getChildAt(0);
        View v = bottomNavigationMenuView.getChildAt(index);
        BottomNavigationItemView itemView = (BottomNavigationItemView) v;
        itemView.removeViewAt(itemView.getChildCount()-1);
    }


}
