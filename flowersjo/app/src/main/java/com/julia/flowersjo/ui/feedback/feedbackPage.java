package com.julia.flowersjo.ui.feedback;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.fragment.app.Fragment;

import com.julia.flowersjo.R;
import com.julia.flowersjo.ui.Feedback;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class feedbackPage extends Fragment {

  EditText first_name, email, phone_number, fbmessage;
  Button sendfeedback;
  FirebaseDatabase firebaseDatabase;
  DatabaseReference databaseReference;



  public static feedbackPage newInstance() {
    feedbackPage fragment = new feedbackPage();
    return fragment;
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.feedback_page, container, false);

    first_name = (EditText) view.findViewById(R.id.first_name);
    email = (EditText) view.findViewById(R.id.email);
    phone_number = (EditText) view.findViewById(R.id.phone_number);
    fbmessage = (EditText) view.findViewById(R.id.fbmessage);
    sendfeedback = (Button) view.findViewById(R.id.sendfeedback);

    sendfeedback.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        Feedback f = new Feedback(first_name.getText().toString(), email.getText().toString(), phone_number.getText().toString(), fbmessage.getText().toString());

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("feedback");

        String key = databaseReference.push().getKey();

        databaseReference.child(key).setValue(f).addOnSuccessListener(new OnSuccessListener<Void>() {
          @Override
          public void onSuccess(Void aVoid) {
            Log.d("success", "adding successful");
          }
        });

      }
    });







    return view;
  }
}