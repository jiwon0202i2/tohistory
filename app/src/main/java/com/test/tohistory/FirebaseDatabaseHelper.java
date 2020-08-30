package com.test.tohistory;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class FirebaseDatabaseHelper {
    private FirebaseDatabase mdatabase;
    private DatabaseReference mReferencetoy;
    private List<toy> toys = new ArrayList<>();

    public interface  DataStatus{
        void DataIsLoaded(List<toy> toys, List<String> keys);

    }

    public FirebaseDatabaseHelper(){
        mdatabase = FirebaseDatabase.getInstance();
        mReferencetoy = mdatabase.getReference("toy");
    }

    public void readtoys(final DataStatus dataStatus){
        mReferencetoy.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                toys.clear();
                List<String> keys = new ArrayList<>();
                for(DataSnapshot keyNode : dataSnapshot.getChildren()){
                    keys.add(keyNode.getKey());
                    toy toy = keyNode.getValue(toy.class);
                }
                dataStatus.DataIsLoaded(toys, keys);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}
