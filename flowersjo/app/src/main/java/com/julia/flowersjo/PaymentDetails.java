package com.julia.flowersjo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import static com.julia.flowersjo.MainActivity.priceAll;

public class PaymentDetails extends Activity {

    TextView txtid, txtAmount, txtStatus;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.paymentinfom);

        txtAmount = (TextView) findViewById(R.id.txtAmount);
        txtid = (TextView) findViewById(R.id.txtid);
        txtStatus = (TextView) findViewById(R.id.txtStatus);


        Intent intent = getIntent();

        try {
            JSONObject jsonObject = new JSONObject(intent.getStringExtra("PaymentDetails"));
            showDetails(jsonObject.getJSONObject("response"), priceAll);
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    private void showDetails(JSONObject response, String priceAll) {
        try {
            txtid.setText(response.getString("id"));
            txtStatus.setText(response.getString("state"));
            txtAmount.setText(priceAll);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
