package com.julia.flowersjo;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.android.billingclient.api.BillingClient;
import com.julia.flowersjo.config.Config;
import com.paypal.android.sdk.payments.PayPalConfiguration;
import com.paypal.android.sdk.payments.PayPalPayment;
import com.paypal.android.sdk.payments.PayPalService;
import com.paypal.android.sdk.payments.PaymentActivity;
import com.paypal.android.sdk.payments.PaymentConfirmation;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.math.BigDecimal;

import static android.app.Activity.RESULT_OK;
import static com.julia.flowersjo.MainActivity.priceAll;

public class paymentFragment extends Fragment {

    Button makepayment;
    private BillingClient billingClient;
    public static final int PAYPAL_REQUEST_CODE = 7171;
    private static PayPalConfiguration config = new PayPalConfiguration().environment(PayPalConfiguration.ENVIRONMENT_PRODUCTION)
            .clientId(Config.PAYPAL_CLIENT_ID);

    public static paymentFragment newInstance(){
        paymentFragment fragment = new paymentFragment();
        return fragment;
    }

    @Override
    public void onDestroy() {

        getContext().stopService(new Intent(getContext(), PayPalService.class));
        super.onDestroy();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.payment_information, container, false);

        makepayment = (Button) view.findViewById(R.id.makepayment);

        String amount = priceAll;

        Intent intent = new Intent(getContext(), PayPalService.class);
        intent.putExtra(PayPalService.EXTRA_PAYPAL_CONFIGURATION, config);
        getContext().startService(intent);

        makepayment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                processpayment();
            }
        });


        return view;
    }

    private void processpayment() {
        String price = priceAll.replaceAll("\\D+","") ;
        int p = Integer.parseInt(price) / 10;
        double ans = p * 1.41;
//        int valueX = (int) Math.rint(x);

        p = (int) Math.rint(ans);
        p = p + 2;
        PayPalPayment payPalPayment = new PayPalPayment(new BigDecimal(String.valueOf(p)), "USD", "Pay for your order", PayPalPayment.PAYMENT_INTENT_SALE);
        Intent intent = new Intent(getContext(), PaymentActivity.class);
        intent.putExtra(PayPalService.EXTRA_PAYPAL_CONFIGURATION, config);
        intent.putExtra(PaymentActivity.EXTRA_PAYMENT, payPalPayment);
        startActivityForResult(intent, PAYPAL_REQUEST_CODE);

    }


    private static JSONObject getBaseRequest() throws JSONException {
        return new JSONObject().put("apiVersion", 2).put("apiVersionMinor", 0);
    }
    private static JSONObject getGatewayTokenizationSpecification() throws JSONException {
        return new JSONObject(){{      put("type", "PAYMENT_GATEWAY");
            put("parameters", new JSONObject(){{        put("gateway", "example");
                put("gatewayMerchantId", "exampleGatewayMerchantId");
            }
            });
        }};
    }
    private static JSONArray getAllowedCardNetworks() {
        return new JSONArray()
                .put("MASTERCARD")
                .put("VISA");
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode == PAYPAL_REQUEST_CODE){
            if(resultCode == RESULT_OK){
                PaymentConfirmation confirmation = data.getParcelableExtra(PaymentActivity.EXTRA_RESULT_CONFIRMATION);
                if(confirmation != null){
                    try {
                        String paymentDetails = confirmation.toJSONObject().toString(4);
                        startActivity(new Intent(getContext(), successActivity.class).putExtra("PaymentDetails", paymentDetails));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
            else{
                Toast.makeText(getContext(), "Cancel", Toast.LENGTH_SHORT).show();
            }
        } else if(resultCode == PaymentActivity.RESULT_EXTRAS_INVALID){
            Toast.makeText(getContext(), "invalid", Toast.LENGTH_SHORT).show();
        }
    }
}



