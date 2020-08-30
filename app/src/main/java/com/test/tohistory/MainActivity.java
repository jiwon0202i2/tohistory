package com.test.tohistory;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Iterator;

public class MainActivity extends AppCompatActivity {

    EditText id, pwd;
    ImageButton loginbtn;
    ImageButton jointext;

    public DatabaseReference reference;

    public static Context context_main; // context 변수 선언
    public String loginID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        id = findViewById(R.id.id);
        pwd = findViewById(R.id.pwd);
        loginbtn = findViewById(R.id.login_btn);
        jointext = findViewById(R.id.jointext);

        reference = FirebaseDatabase.getInstance().getReference("users");

        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reference.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        // flag는 해당 번호를 찾았을때 회원가입절차를 무시하기 위해 사용
                        boolean flag = true;
                        Iterator<DataSnapshot> child = dataSnapshot.getChildren().iterator();

                        while (child.hasNext()) {
                            DataSnapshot data = child.next();
                            String ID = data.getKey();

                            // -DB의 phonenumber와 password를 EditText로 받은 phonenumber와 password를 비교
                            if (ID.equals(id.getText().toString()) && data.child("pwd").getValue().toString().equals(pwd.getText().toString())) {
                                Toast.makeText(getApplicationContext(), "로그인이 되었습니다", Toast.LENGTH_LONG).show();
                                loginID = ID;
//                                로그인이 되면 activity_d_sildmenu로 이동
                                Intent intent = new Intent(MainActivity.this, WriteBoardActivity.class);
                                startActivity(intent);
                                flag = false;
                                break;
                            }
                        }
                        // 번호를 찾지 못한 경우 Toast메시지를 띄우고 회원가입 페이지로 이동
                        if (flag) {
                            Toast.makeText(getApplicationContext(), "존재하지 않는 번호입니다. 회원가입이 필요합니다", Toast.LENGTH_LONG).show();
                            Intent intent_i = new Intent(MainActivity.this, JoinFirstActivity.class);
                            startActivity(intent_i);
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });

        jointext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, JoinFirstActivity.class);
                startActivity(intent);
            }
        });


        context_main = this;
    }
}