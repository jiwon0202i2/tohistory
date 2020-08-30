package com.test.tohistory;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Date;

public class ToyFirebase {
    public DatabaseReference reference;

    public String title;
    public String category;
    public String content;
    public String age;
    public String user;


    public ToyFirebase(String title, String category,String content, String age, String user){
        this.title=title;
        this.category=category;
        this.content=content;
        this.age=age;
        this.user = user;
    }


    public void insertToyData(String title) {
        ToyFirebase toyData = new ToyFirebase(title, category, content, age, user);
        reference = FirebaseDatabase.getInstance().getReference();
        reference.child("toy").child(title).setValue(toyData);
    }
}
