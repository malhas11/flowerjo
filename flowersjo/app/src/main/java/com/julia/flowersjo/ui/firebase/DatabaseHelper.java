package com.julia.flowersjo.ui.firebase;

import com.julia.flowersjo.Bouquet;
import com.julia.flowersjo.Task;
import com.julia.flowersjo.flower;
import com.julia.flowersjo.orders;
import com.julia.flowersjo.ui.clientorder;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper {

    DatabaseReference databaseReference;
    FirebaseDatabase firebaseDatabase;

    List<Bouquet> bouqs = new ArrayList<>();
    List<Task> tasks = new ArrayList<>();
    List<clientorder> clientorders = new ArrayList<>();
    List<flower> flowersList = new ArrayList<>();
    List<orders> orderList = new ArrayList<>();

    public List<flower> DatabaseHelperRead(DataSnapshot dataSnapshot) {

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("flowers");

        List<String> keys = new ArrayList<>();
        for (DataSnapshot keyNode : dataSnapshot.getChildren()) {
            keys.add(keyNode.getKey());
            Task task = keyNode.getValue(Task.class);
            tasks.add(task);
            String im = task.getImage();
            String title = task.getTitle();
            String description = task.getDescription();
            flowersList.add(
                    new flower(
                            title,
                            description,
                            im
                    )
            );
        }
        return flowersList;
    }

    public List<clientorder> DatabaseHelperReadOrders(DataSnapshot dataSnapshot) {

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("clientorder");

        List<String> keys = new ArrayList<>();
        for (DataSnapshot keyNode : dataSnapshot.getChildren()) {
            keys.add(keyNode.getKey());
            clientorder task = keyNode.getValue(clientorder.class);
            clientorders.add(task);
            String address = task.getAddress();
            String name = task.getName();
            String email = task.getEmail();
            String city = task.getCity();
            String orderid = task.getOrderid();
            String number = task.getNumber();
            String payment = task.getPayment();
            String type = task.getType();
            String price = task.getPrice();
            clientorders.add(
                    new clientorder(
                            address,
                            email,
                            name,
                            city,
                            number,
                            orderid,
                            payment,
                            price,
                            type
                    )
            );
        }
        return clientorders;
    }


    public List<Bouquet> DatabaseHelperReadBouquets(DataSnapshot dataSnapshot) {

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("Bouquets");

        for (DataSnapshot keyNode : dataSnapshot.getChildren()) {
            Bouquet bouq = keyNode.getValue(Bouquet.class);
            bouqs.add(bouq);
            String im = bouq.getImage();

            String title = bouq.getPrice();
            String description = bouq.getDescription();
            String type = bouq.getType();
            String key = keyNode.getKey();
            int x = 0;
            for (int i = 0; i < bouqs.size(); i++) {
                if (description.equals(bouqs.get(i).getDescription())) {
                    x++;
                }
            }
            if (x == 0) {

                bouqs.add(
                        new Bouquet(
                                title,
                                description,
                                im,
                                type,
                                key
                        )
                );
            }
        }
        return bouqs;
    }

    public List<orders> DatabaseHelperReadOrdersS(DataSnapshot dataSnapshot) {
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("orders");

        List<String> keys = new ArrayList<>();
        for (DataSnapshot keyNode : dataSnapshot.getChildren()) {
            keys.add(keyNode.getKey());
            orders task = keyNode.getValue(orders.class);
            orderList.add(task);
            String date = task.getDate();
            String id = task.getOrderID();
            String time = task.getTime();
            String image = task.getImage();
            String from = task.getFrom();
            String to = task.getTo();
            String quantity = task.getQuantity();
            String message = task.getMessage();

            orderList.add(
                    new orders(
                            id,
                            image,
                            quantity,
                            time,
                            date,
                            from,
                            to,
                            message
                    )
            );
        }
        return orderList;
    }
}