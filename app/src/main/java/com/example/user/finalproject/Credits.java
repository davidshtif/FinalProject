package com.example.user.finalproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Credits extends AppCompatActivity {

    Intent t;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_credits);

        t=getIntent();
    }

    public void back(View view) {
        finish();
    }

    public void reset3(View view) {
        Intent res=new Intent(this,Main.class);
        startActivity(res);
    }
}
