package com.test.tohistory;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.test.tohistory.FirebaseDatabaseHelper;
import com.test.tohistory.R;
import com.test.tohistory.RecyclerView_Config;
import com.test.tohistory.toy;

import java.util.List;

public class toyListActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;

    protected void onCreated(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_toyload);
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerview_toys);
        new FirebaseDatabaseHelper().readtoys(new FirebaseDatabaseHelper.DataStatus() {
            @Override
            public void DataIsLoaded(List<toy> toys, List<String> keys) {
                new RecyclerView_Config().setConfig(mRecyclerView, toyListActivity.this,toys,keys);
            }
        });
    }
}
