package com.test.tohistory;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class UserFirebase {
    public DatabaseReference reference;

    public String nickname;
    public String id;
    public String pwd;
    public String c_age;

    public UserFirebase(String nickname,String pwd, String c_age){
        this.nickname=nickname;
        this.pwd=pwd;
        this.c_age=c_age;
    }

    public void insertData(String id) {
        UserFirebase userData = new UserFirebase(nickname, pwd, c_age);
        reference = FirebaseDatabase.getInstance().getReference();
        reference.child("users").child(id).setValue(userData);
    }
}

