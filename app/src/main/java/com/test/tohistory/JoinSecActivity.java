package com.test.tohistory;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class JoinSecActivity extends AppCompatActivity {
    EditText nickname, id, pwd, pwd2;
    Spinner spinner;
    String c_age;
    ImageButton loginbtn;
    ImageButton backbtn;

    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference databaseReference = firebaseDatabase.getReference();

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join_sec);

        nickname = findViewById(R.id.nickname);
        id = findViewById(R.id.id);
        pwd = findViewById(R.id.pwd);
        pwd2 = findViewById(R.id.pwd2);
        loginbtn = findViewById(R.id.login_btn);
        backbtn = findViewById(R.id.backbtn);

        spinner = findViewById(R.id.age_spinner);


        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                c_age=parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // -비밀번호 확인란과 일치하면 가입완료-
                if(pwd.getText().toString().equals(pwd2.getText().toString())) {

                    // -firebase에 데이터 저장(회원가입)-
                    UserFirebase userData=new UserFirebase(nickname.getText().toString(),pwd.getText().toString(),c_age);
                    userData.insertData(id.getText().toString());

                    //회원가입 완료 토스트 메시지
                    Toast.makeText(getApplicationContext(),"회원가입 완료",Toast.LENGTH_LONG).show();

                    // -activity_d_login으로 이동
                    Intent myintent = new Intent(JoinSecActivity.this, MainActivity.class);
                    startActivity(myintent);
                } else {
                    Toast.makeText(getApplicationContext(),"비밀번호가 일치하지 않습니다",Toast.LENGTH_LONG).show();
                }
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
