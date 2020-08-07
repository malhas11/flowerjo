package com.julia.flowersjo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RatingBar;
import android.widget.TextView;

public class successActivity extends Activity {

    TextView homepage;
    RatingBar ratingBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.successful_page);


        homepage = (TextView) findViewById(R.id.home_page);
        ratingBar = (RatingBar) findViewById(R.id.rating);



        homepage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                finish();
                startActivity(i);
            }
        });


    }
}
