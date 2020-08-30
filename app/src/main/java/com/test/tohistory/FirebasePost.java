package com.test.tohistory;

import com.google.firebase.database.Exclude;
import com.google.firebase.database.IgnoreExtraProperties;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by DowonYoon on 2017-07-11.
 */

@IgnoreExtraProperties
public class FirebasePost {
    public String title;
    public String category;
    public String age;
    public String content;
    public String user;

    public FirebasePost(){
        // Default constructor required for calls to DataSnapshot.getValue(FirebasePost.class)
    }

    public FirebasePost(String title, String category, String age, String content, String user) {
        this.title = title;
        this.category = category;
        this.age = age;
        this.content = content;
        this.user = user;
    }

    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("title", title);
        result.put("category", category);
        result.put("age", age);
        result.put("content", content);
        result.put("user", user);
        return result;
    }
}

