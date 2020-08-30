package com.test.tohistory;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class JoinFirstActivity extends AppCompatActivity {

    ImageButton nextbtn;
    ImageButton backbtn;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join_first);

        nextbtn = findViewById(R.id.next_btn);
        backbtn = findViewById(R.id.backbtn);

        nextbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myintent = new Intent(JoinFirstActivity.this, JoinSecActivity.class);
                startActivity(myintent);
            }
        });

        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
