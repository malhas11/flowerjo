package com.julia.flowersjo.ui.cart;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.Layout;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toolbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.julia.flowersjo.R;
import com.julia.flowersjo.ui.cartlist;
import com.google.android.material.bottomnavigation.BottomNavigationItemView;
import com.google.android.material.bottomnavigation.BottomNavigationMenuView;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;
import java.util.List;

import static com.julia.flowersjo.MainActivity.listofcart;
import static com.julia.flowersjo.MainActivity.navView;

public class cartFragment extends Fragment {

    Button checkout, datePick, timePick;
    RecyclerView mRecyclerView;
    ImageView goback;
    cartAdapter adapter;

    TextView noti, warningtime;
    boolean dateP = false;
    boolean dateT = false;
    boolean addt = false;


    Layout badge;
    TextView subtotal_price, total_price, tax_price, message_from, message_to, message_written;
    Toolbar toolbar;
    Spinner dropdown;
    DatabaseReference databaseReference;
    FirebaseDatabase firebaseDatabase;
    private int mYear, mMonth, mDay, mHour, mMinute;

    public static cartFragment newInstance(){
        cartFragment fragment = new cartFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.cart, container, false);

        mRecyclerView = view.findViewById(R.id.cart_recycler);
        timePick = (Button) view.findViewById(R.id.timePick);
        datePick = (Button) view.findViewById(R.id.datePick);
        warningtime = (TextView) view.findViewById(R.id.warningtime);
        checkout = view.findViewById(R.id.checkout);
        message_from = (TextView) view.findViewById(R.id.message_from);
        message_to = (TextView) view.findViewById(R.id.message_to);
        message_written = (TextView) view.findViewById(R.id.message_written);
        toolbar = (Toolbar) getActivity().findViewById(R.id.toolbar);
        goback = (ImageView) toolbar.findViewById(R.id.goback);
        goback.setVisibility(View.GONE);


        checkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for(int i = 0; i < listofcart.size(); i++){
                    if(addt) {
                        listofcart.get(i).setDate(datePick.getText().toString());
                        listofcart.get(i).setTime(timePick.getText().toString());
                        String from = message_from.getText().toString();
                        String to = message_to.getText().toString();
                        String message = message_written.getText().toString();
                        listofcart.get(i).setMessage(message);
                        listofcart.get(i).setTo(to);
                        listofcart.get(i).setFrom(from);
                        FragmentTransaction transaction = getFragmentManager().beginTransaction();
                        transaction.replace(R.id.nav_host_fragment, Checkout.newInstance());
                        transaction.commit();
                    }else {
                        AlertDialog.Builder builder1 = new AlertDialog.Builder(getContext(), R.style.MyDialogTheme);
                        builder1.setMessage("Please select the date and time");

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
                    }
                }
            }
        });

        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));


        adapter = new cartFragment.cartAdapter(getContext(), listofcart);
        mRecyclerView.setAdapter(adapter);


        datePick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar c = Calendar.getInstance();
                mYear = c.get(Calendar.YEAR);
                mMonth = c.get(Calendar.MONTH);
                mDay = c.get(Calendar.DAY_OF_MONTH);


                DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(), R.style.DialogTheme,

                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {
                                datePick.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);
                                datePick.setBackgroundResource(R.drawable.mybutton);
                                dateP = true;

                                if (dateP && dateT) {
                                    checkout.setBackgroundResource(R.drawable.mybutton);
                                    addt = true;
                                }
                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis());
                datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis()+ (1000*60*60*24*14));
                datePickerDialog.show();

            }
        });

        timePick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar c = Calendar.getInstance();
                mHour = c.get(Calendar.HOUR_OF_DAY);
                mMinute = c.get(Calendar.MINUTE);

                // Launch Time Picker Dialog
                TimePickerDialog timePickerDialog = new TimePickerDialog(getContext(), R.style.DialogTheme ,
                        new TimePickerDialog.OnTimeSetListener() {

                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay,
                                                  int minute) {

                                if(hourOfDay<9){
                                    hourOfDay = 9;
                                    warningtime.setVisibility(View.VISIBLE);
                                }else if(hourOfDay > 22){
                                    hourOfDay = 22;
                                    warningtime.setVisibility(View.VISIBLE);
                                }
                                timePick.setText(hourOfDay + ":" + minute);
                                timePick.setBackgroundResource(R.drawable.mybutton);

                                dateT = true;
                                if(dateP  && dateT){
                                    checkout.setBackgroundResource(R.drawable.mybutton);
                                    addt = true;
                                }
                            }
                        }, mHour, mMinute, false);

                timePickerDialog.show();
            }
        });



        return view;
    }

    private void addBageIn() {

        BottomNavigationMenuView bottomNavigationMenuView = (BottomNavigationMenuView) navView.getChildAt(0);

        View v = bottomNavigationMenuView.getChildAt(2);

        noti = (TextView) v.findViewById(R.id.nb);
        noti.setText(Integer.toString(listofcart.size()));

    }



    public class cartAdapter extends RecyclerView.Adapter<cartAdapter.cartViewHolder> {
        Context context;
        List<cartlist> cartlist;

        cartAdapter(Context context, List<cartlist> cartlist){
            this.context = context;
            this.cartlist = cartlist;

        }



        @NonNull
        @Override
        public cartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View mView = LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_order_layout, null);

            cartViewHolder cartViewHolder = new cartViewHolder(mView);


            return cartViewHolder;
        }

        @Override
        public void onBindViewHolder(@NonNull cartViewHolder holder, int position) {

            cartlist cart = listofcart.get(position);
            holder.mQuantity.setText(cart.getQuantity());

            String im = cart.getImage();
            byte[] decodedString = android.util.Base64.decode(im, Base64.DEFAULT);
            Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
            Bitmap resized = Bitmap.createScaledBitmap(decodedByte, 180, 180, true);

            holder.mImage.setImageBitmap(resized);

            holder.mPrice.setText(cart.getPrice());

        }

        @Override
        public int getItemCount() {
            return listofcart.size();
        }

        class cartViewHolder extends RecyclerView.ViewHolder {
            ImageView mImage;
            TextView mQuantity;
            TextView mPrice, dateandtime;
            ImageView delete;

            public cartViewHolder(View itemView) {
                super(itemView);


                mImage = itemView.findViewById(R.id.imageCart);
                mQuantity = itemView.findViewById(R.id.quantity);
                mPrice = itemView.findViewById(R.id.price_cart);
                delete = itemView.findViewById(R.id.delete_cart);
                dateandtime = itemView.findViewById(R.id.timeanddate);

                delete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int pos = getAdapterPosition();
                        listofcart.remove(pos);



                        View view = navView.findViewById(R.id.navigation_dashboard);


                        if(listofcart.isEmpty()){
                            removeBadge(navView, 2);

                        } else {
                            addBageIn();

                        }

                        view.performClick();

                    }
                });

            }








        }
    }

    public void removeBadge(BottomNavigationView navigationView, int index) {
        BottomNavigationMenuView bottomNavigationMenuView = (BottomNavigationMenuView) navigationView.getChildAt(0);
        View v = bottomNavigationMenuView.getChildAt(index);
        BottomNavigationItemView itemView = (BottomNavigationItemView) v;
        itemView.removeViewAt(itemView.getChildCount()-1);
    }
}
