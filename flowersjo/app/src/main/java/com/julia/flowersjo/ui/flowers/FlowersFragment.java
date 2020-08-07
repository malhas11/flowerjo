package com.julia.flowersjo.ui.flowers;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
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
import com.julia.flowersjo.Task;
import com.julia.flowersjo.flower;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

import static com.julia.flowersjo.MainActivity.profileTwo;
import static com.julia.flowersjo.MainActivity.typeF;
import static com.julia.flowersjo.splashScreen.listOfFlowers;

public class FlowersFragment extends Fragment {
  RecyclerView recyclerView;
  flowerAdapter adapter;
  ImageView arrow;
  DatabaseReference databaseReference;
  FirebaseDatabase firebaseDatabase;
  Toolbar toolbar;
  List<Task> tasks = new ArrayList<>();
  TextView toolbarTitle;

  public static FlowersFragment newInstance(){
    FlowersFragment fragment = new FlowersFragment();
    return fragment;
  }
  public List<flower> getflowers(){


    return listOfFlowers;


  }




  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
    View view = inflater.inflate(R.layout.flowers_types_recycler_view, container, false);

    toolbar = (Toolbar) getActivity().findViewById(R.id.toolbar);
    toolbarTitle = (TextView) toolbar.findViewById(R.id.toolbar_title);
    toolbarTitle.setText("Flowers & Plants");
    recyclerView = (RecyclerView) view.findViewById(R.id.flowers_recycler_view);
    recyclerView.setHasFixedSize(true);
    recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    arrow = (ImageView) view.findViewById(R.id.arrow_product_imageview);



    readFromDatabase();

    return view;
  }

  public void openFlowerPage(String title, String description){

  }
  public class flowerAdapter extends RecyclerView.Adapter<flowerAdapter.flowerViewHolder> {
    private Context context;
    private List<flower> flowerList;
    String title, descr;

    public flowerAdapter(Context context, List<flower> flowerList){
      this.context = context;
      this.flowerList = flowerList;
    }


    @NonNull
    @Override
    public flowerViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i){
      LayoutInflater inflater = LayoutInflater.from(context);
      View view = inflater.inflate(R.layout.layout_flower_types, null);


      flowerViewHolder flowerViewHolder = new flowerViewHolder(view);


      return flowerViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull flowerViewHolder flowerViewHolder, int i){
      flower flower = listOfFlowers.get(i);
      flowerViewHolder.flowerTitle.setText(flower.getTitle() + " JOD");
      flowerViewHolder.flowerDescription.setText(flower.getDescription());
      String im = flower.getImage();
      byte[] decodedString = Base64.decode(im, Base64.DEFAULT);
      Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
      Bitmap resized = Bitmap.createScaledBitmap(decodedByte, 180, 180, true);

      flowerViewHolder.flowerImage.setImageBitmap(resized);

    }
    //
    @Override
    public int getItemCount() {return flowerList.size();}

    class flowerViewHolder extends RecyclerView.ViewHolder {
      TextView flowerTitle, flowerDescription;
      ImageView flowerImage;
      public flowerViewHolder(@NonNull View itemView){
        super(itemView);
        flowerDescription = (TextView) itemView.findViewById(R.id.descriptionVM);
        flowerImage = (ImageView) itemView.findViewById(R.id.imageVM);
        flowerTitle = (TextView) itemView.findViewById(R.id.flower_title);


        itemView.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
            typeF = true;
            Log.d("Tag", "onClick: " + getAdapterPosition());
            int x = getAdapterPosition();
            for(int i = 0; i < listOfFlowers.size()+ 1; i++){
              if(x == i){
                profileTwo = listOfFlowers.get(i);
              }
            }
            FragmentTransaction transaction = getFragmentManager().beginTransaction();
            transaction.replace(R.id.nav_host_fragment, flowerProfile.newInstance());
            transaction.commit();
          }
        });
      }
    }
  }




  public void readFromDatabase() {





    adapter = new FlowersFragment.flowerAdapter(getContext(), listOfFlowers);
    recyclerView.setAdapter(adapter);





  }


}

