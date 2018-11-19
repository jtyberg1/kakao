package com.example.user.kakao;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

public class Login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        final Context ctx = Login.this;
        final EditText etlD = findViewById(R.id.etID);
        final EditText etPass = findViewById(R.id.etPass);
        findViewById(R.id.btLogin).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //String id = etlD.getText().toString();
                //String pass = etPass.getText().toString();
                startActivity(new Intent(ctx,MemberList.class));
            }
        });
        findViewById(R.id.btCancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ctx,Login.class));
            }
        });
    }
}
