package com.julia.flowersjo;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.RelativeLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.FirebaseApp;
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

public class splashScreen extends AppCompatActivity {

  RelativeLayout loading;
  DatabaseReference databaseReference;
  FirebaseDatabase firebaseDatabase;
  static public List<clientorder> clientOrders = new ArrayList<>();

  static public List<flower> listOfFlowers = new ArrayList<>();

  static public List<Bouquet> listOfBouqs = new ArrayList<>();
  static public List<Bouquet> listOfBalloons = new ArrayList<>();
  static public List<orders> orderS = new ArrayList<>();

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.splash_screen);
//    FirebaseApp.initializeApp(getApplicationContext());
    firebaseDatabase = FirebaseDatabase.getInstance();

//    FirebaseDatabase.getInstance().setPersistenceEnabled(true);
      FirebaseDatabase.getInstance().setPersistenceEnabled(true);
      readFlowersDatabase();
      readBouquetsDatabase();
      readBalloonsDatabase();
//    readOrdersList();
//    readOrders();
//        readLoveDatabase();
//        readThankDatabase();
//        readWeddingDatabase();
//        readBabyDatabase();
//        readBirthdayDatabase();
//        readAllDatabase();



    //Splash Screen duration
    int secondsDelayed = 100;
    Handler handler = new Handler();

    handler.postDelayed(new Runnable() {
      public void run() {

        if(!listOfFlowers.isEmpty()){

          //testing


          startActivity(new Intent(splashScreen.this, MainActivity.class));


          finish();
        }else {
          handler.postDelayed(this, secondsDelayed);
        }

      }
    }, secondsDelayed);
  }

  private void readOrders() {
    firebaseDatabase = FirebaseDatabase.getInstance();
    databaseReference = firebaseDatabase.getReference("orders");
    databaseReference.addValueEventListener(new ValueEventListener() {
      @Override
      public void onDataChange(DataSnapshot dataSnapshot) {

        DatabaseHelper d = new DatabaseHelper();
        orderS.clear();
        orderS = d.DatabaseHelperReadOrdersS(dataSnapshot);



      }

      @Override
      public void onCancelled(DatabaseError error) {
        // Failed to read value
        Log.w(TAG, "Failed to read value.", error.toException());
      }
    });
  }

  private void readOrdersList() {
    firebaseDatabase = FirebaseDatabase.getInstance();
    databaseReference = firebaseDatabase.getReference("clientorder");
    databaseReference.addValueEventListener(new ValueEventListener() {
      @Override
      public void onDataChange(DataSnapshot dataSnapshot) {

        DatabaseHelper d = new DatabaseHelper();
        clientOrders.clear();
        clientOrders = d.DatabaseHelperReadOrders(dataSnapshot);



      }

      @Override
      public void onCancelled(DatabaseError error) {
        // Failed to read value
        Log.w(TAG, "Failed to read value.", error.toException());
      }
    });
  }

  public void readBalloonsDatabase() {
    firebaseDatabase = FirebaseDatabase.getInstance();
    databaseReference = firebaseDatabase.getReference("Balloons");
    databaseReference.limitToFirst(40).addListenerForSingleValueEvent(new ValueEventListener() {
      @Override
      public void onDataChange(DataSnapshot dataSnapshot) {

        DatabaseHelper d = new DatabaseHelper();
        listOfBalloons.clear();
        listOfBalloons = d.DatabaseHelperReadBouquets(dataSnapshot);



      }

      @Override
      public void onCancelled(DatabaseError error) {
        // Failed to read value
        Log.w(TAG, "Failed to read value.", error.toException());
      }
    });
  }

  private void readBouquetsDatabase() {
    firebaseDatabase = FirebaseDatabase.getInstance();
    databaseReference = firebaseDatabase.getReference("Bouquets");
    databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
      @Override
      public void onDataChange(DataSnapshot dataSnapshot) {

        DatabaseHelper d = new DatabaseHelper();
        listOfBouqs.clear();
        listOfBouqs = d.DatabaseHelperReadBouquets(dataSnapshot);



      }

      @Override
      public void onCancelled(DatabaseError error) {
        // Failed to read value
        Log.w(TAG, "Failed to read value.", error.toException());
      }
    });
  }
  private void readFlowersDatabase() {

    firebaseDatabase = FirebaseDatabase.getInstance();
    databaseReference = firebaseDatabase.getReference("flowers");
    databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
      @Override
      public void onDataChange(DataSnapshot dataSnapshot) {

        DatabaseHelper d = new DatabaseHelper();
        listOfFlowers.clear();
        listOfFlowers =  d.DatabaseHelperRead(dataSnapshot);


      }

      @Override
      public void onCancelled(DatabaseError error) {
        // Failed to read value
        Log.w(TAG, "Failed to read value.", error.toException());
      }
    });
  }

  @Override
  public void onStart(){
    super.onStart();

  }
}

//
//    private void readBirthdayDatabase() {
//        firebaseDatabase = FirebaseDatabase.getInstance();
//        databaseReference = firebaseDatabase.getReference("birthday");
//        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//
//                DatabaseHelper d = new DatabaseHelper();
//                listBirthday.clear();
//                listBirthday = d.DatabaseHelperReadBirthday(dataSnapshot);
//
//
//
//            }
//
//            @Override
//            public void onCancelled(DatabaseError error) {
//                // Failed to read value
//                Log.w(TAG, "Failed to read value.", error.toException());
//            }
//        });
//    }
//
//    private void readBabyDatabase() {
//        firebaseDatabase = FirebaseDatabase.getInstance();
//        databaseReference = firebaseDatabase.getReference("newbaby");
//        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//
//                DatabaseHelper d = new DatabaseHelper();
//                listBaby.clear();
//                listBaby = d.DatabaseHelperReadBaby(dataSnapshot);
//
//
//            }
//
//            @Override
//            public void onCancelled(DatabaseError error) {
//                // Failed to read value
//                Log.w(TAG, "Failed to read value.", error.toException());
//            }
//        });
//    }
//
//    private void readWeddingDatabase() {
//        firebaseDatabase = FirebaseDatabase.getInstance();
//        databaseReference = firebaseDatabase.getReference("wedding");
//        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//
//                DatabaseHelper d = new DatabaseHelper();
//                listWedding.clear();
//                listWedding = d.DatabaseHelperReadWedding(dataSnapshot);
//
//
//            }
//
//            @Override
//            public void onCancelled(DatabaseError error) {
//                // Failed to read value
//                Log.w(TAG, "Failed to read value.", error.toException());
//            }
//        });
//    }
//
//    private void readThankDatabase() {
//        firebaseDatabase = FirebaseDatabase.getInstance();
//        databaseReference = firebaseDatabase.getReference("thank");
//        listThank.clear();
//        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//
//                DatabaseHelper d = new DatabaseHelper();
//                listThank = d.DatabaseHelperReadThank(dataSnapshot);
//
//
//
//
//            }
//
//            @Override
//            public void onCancelled(DatabaseError error) {
//                // Failed to read value
//                Log.w(TAG, "Failed to read value.", error.toException());
//            }
//        });
//
//    }
//
//    private void readLoveDatabase() {
//        firebaseDatabase = FirebaseDatabase.getInstance();
//        databaseReference = firebaseDatabase.getReference("love");
//        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                DatabaseHelper d = new DatabaseHelper();
//                listLove = d.DatabaseHelperReadLove(dataSnapshot);
//
//
//            }
//
//            @Override
//            public void onCancelled(DatabaseError error) {
//                // Failed to read value
//                Log.w(TAG, "Failed to read value.", error.toException());
//            }
//        });
//    }

//
//        firebaseDatabase = FirebaseDatabase.getInstance();
//        databaseReference = firebaseDatabase.getReference("birthday");
//        databaseReference.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//
//                DatabaseHelper d = new DatabaseHelper();
//                listOfBouqs.clear();
//                listOfBouqs =   d.DatabaseHelperReadBirthday(dataSnapshot);
//                firebaseDatabase = FirebaseDatabase.getInstance();
//                databaseReference = firebaseDatabase.getReference("love");
//                databaseReference.addValueEventListener(new ValueEventListener() {
//                    @Override
//                    public void onDataChange(DataSnapshot dataSnapshot) {
//                        list1.clear();
//                        DatabaseHelper d = new DatabaseHelper();
//                        list1 =   d.DatabaseHelperReadLove(dataSnapshot);
//
//                        listOfBouqs.addAll(list1);
//
//                        firebaseDatabase = FirebaseDatabase.getInstance();
//                        databaseReference = firebaseDatabase.getReference("wedding");
//                        databaseReference.addValueEventListener(new ValueEventListener() {
//                            @Override
//                            public void onDataChange(DataSnapshot dataSnapshot) {
//
//                                DatabaseHelper d = new DatabaseHelper();
//                                list2.clear();
//                                list2 = d.DatabaseHelperReadWedding(dataSnapshot);
//
//                                listOfBouqs.addAll(list2);
//                                firebaseDatabase = FirebaseDatabase.getInstance();
//                                databaseReference = firebaseDatabase.getReference("newbaby");
//                                databaseReference.addValueEventListener(new ValueEventListener() {
//                                    @Override
//                                    public void onDataChange(DataSnapshot dataSnapshot) {
//
//                                        DatabaseHelper d = new DatabaseHelper();
//                                        list3.clear();
//                                        list3 =   d.DatabaseHelperReadBaby(dataSnapshot);
//                                        listOfBouqs.addAll(list3);
//
//                                        firebaseDatabase = FirebaseDatabase.getInstance();
//                                        databaseReference = firebaseDatabase.getReference("thank");
//
//                                        databaseReference.addValueEventListener(new ValueEventListener() {
//                                            @Override
//                                            public void onDataChange(DataSnapshot dataSnapshot) {
//
//                                                list4.clear();
//                                                DatabaseHelper d = new DatabaseHelper();
//                                                list4 =   d.DatabaseHelperReadThank(dataSnapshot);
//                                                listOfBouqs.addAll(list4);
//
//
//
//                                                for(int i = 0; i < listOfBouqs.size(); i++){
//                                                    String desc = listOfBouqs.get(i).getDescription();
//                                                    int rep = 0;
//                                                    for(int j = 0; j < listOfBouqs.size(); j++){
//                                                        String desc2 = listOfBouqs.get(j).getDescription();
//                                                        if(desc.equals(desc2)){
//                                                            rep++;
//                                                        }
//                                                    }
//                                                    if(rep > 1){
//                                                        for(int k = 0; k < listOfBouqs.size(); k++){
//                                                            if(desc.equals(listOfBouqs.get(k).getDescription())){
//                                                                listOfBouqs.remove(k);
//                                                                break;
//                                                            }
//                                                        }
//                                                    }
//                                                }
//
//
//
//
//
//
//                                            }
//
//                                            @Override
//                                            public void onCancelled(DatabaseError error) {
//                                                // Failed to read value
//                                                Log.w(TAG, "Failed to read value.", error.toException());
//                                            }
//                                        });
//
//
//
//
//                                    }
//
//                                    @Override
//                                    public void onCancelled(DatabaseError error) {
//                                        // Failed to read value
//                                        Log.w(TAG, "Failed to read value.", error.toException());
//                                    }
//                                });
//
//
//
//                            }
//
//                            @Override
//                            public void onCancelled(DatabaseError error) {
//                                // Failed to read value
//                                Log.w(TAG, "Failed to read value.", error.toException());
//                            }
//                        });
//
//
//
//
//                    }
//
//                    @Override
//                    public void onCancelled(DatabaseError error) {
//                        // Failed to read value
//                        Log.w(TAG, "Failed to read value.", error.toException());
//                    }
//                });
//
//
//
//
//
//            }
//
//            @Override
//            public void onCancelled(DatabaseError error) {
//                // Failed to read value
//                Log.w(TAG, "Failed to read value.", error.toException());
//            }
//        });
//
//
//