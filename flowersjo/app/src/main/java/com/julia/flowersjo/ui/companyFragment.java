package com.julia.flowersjo.ui;

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
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.julia.flowersjo.Bouquet;
import com.julia.flowersjo.R;

import java.util.List;

import static com.julia.flowersjo.MainActivity.listOfGradBalloons;
import static com.julia.flowersjo.splashScreen.listOfBalloons;

public class companyFragment extends Fragment {
    RecyclerView recycler;
    Toolbar toolbar;
    TextView ToolbarTitle;


    public static companyFragment newInstance(){
        companyFragment fragment = new companyFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saved) {
        View view = inflater.inflate(R.layout.company_profile, container, false);

        toolbar = (Toolbar) getActivity().findViewById(R.id.toolbar);
        ToolbarTitle = (TextView) toolbar.findViewById(R.id.toolbar_title);
        ToolbarTitle.setText("Company Name");
        recycler = view.findViewById(R.id.recycler);
        GridLayoutManager mGridLayoutManager = new GridLayoutManager(getContext(), 3);
        recycler.setHasFixedSize(true);
        recycler.setItemViewCacheSize(20);
        recycler.setDrawingCacheEnabled(true);
        recycler.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);
        recycler.setLayoutManager(mGridLayoutManager);


        listOfGradBalloons.clear();
        for (int i = 0; i < listOfBalloons.size(); i++) {
            Log.d("tag", "balloons: type : " + listOfBalloons.get(i).getType());

            if (listOfBalloons.get(i).getType().equals("graduation")) {
                listOfGradBalloons.add(listOfBalloons.get(i));

            }

        }

        companyFragment.myAdapter myadapter = new companyFragment.myAdapter(getContext(), listOfGradBalloons);
        myadapter.setHasStableIds(true);
        recycler.setAdapter(myadapter);

        return view;

    }

    public class myAdapter extends RecyclerView.Adapter<myAdapter.companyViewHolder>{
        private Context context;
        private List<Bouquet> listOfThing;


        myAdapter(Context context, List<Bouquet> listOfThing){
            this.context = context;
            this.listOfThing = listOfThing;
        }

        @Override
        public long getItemId(int p){
            return p;
        }
        @Override
        public int getItemViewType(int p){
            return p;
        }

        @NonNull
        @Override
        public companyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.company_image, parent, false);

            return new companyViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull companyViewHolder holder, int p){
            Bouquet flower = listOfThing.get(p);
            String im = flower.getImage();
            byte[] decodedString = Base64.decode(im, Base64.DEFAULT);
            Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
            Bitmap resized = Bitmap.createScaledBitmap(decodedByte, 100, 100, true);
            holder.mImage.setImageBitmap(resized);
        }

        @Override
        public int getItemCount(){return listOfThing.size();}

        class companyViewHolder extends RecyclerView.ViewHolder{
            ImageView mImage;


            public companyViewHolder(@NonNull View itemView) {
                super(itemView);
                mImage = itemView.findViewById(R.id.image53);
            }
        }
    }

}
