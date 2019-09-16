package com.itonemm.minishop;

import android.content.ClipData;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import androidx.annotation.NonNull;

public class FireBaseDataBaseHelper {

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;


    public FireBaseDataBaseHelper() {
        firebaseDatabase=FirebaseDatabase.getInstance();
        databaseReference=firebaseDatabase.getReference("Items");
    }

    public void addItem(ItemModel model)
    {

        String key=databaseReference.push().getKey();
        databaseReference.child(key).setValue(model);


    }

    public void updateItem(ItemModel itemModel,String key)
    {
        databaseReference.child(key).setValue(itemModel);


    }

    public void getAllItesm()
    {


        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                ShowItem.keys.clear();
                ShowItem.itemModels.clear();

                for(DataSnapshot snapshot:dataSnapshot.getChildren())
                {

                    ShowItem.keys.add(snapshot.getKey());
                    ItemModel itemModel=snapshot.getValue(ItemModel.class);
                    ShowItem.itemModels.add(itemModel);
                }
                ShowItem.displaydata();


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }

    public void delete(String key)
    {
        databaseReference.child(key).setValue(null);
    }





}
