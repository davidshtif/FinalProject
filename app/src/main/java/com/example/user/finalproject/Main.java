package com.example.user.finalproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Switch;
import android.widget.TextView;

public class Main extends AppCompatActivity {

    Switch sw;
    boolean type;
    Intent t;
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sw= (Switch) findViewById(R.id.sw1);
    }

    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.main,menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item){
        Intent cred=new Intent(this,Credits.class);
        startActivity(cred);
        return true;
    }

    public void go(View view) {
        if(sw.isChecked()){
            type=false; //Integral
            t=new Intent(this,Derivative.class);
            startActivity(t);
        }
        else{
            type=true;
            t=new Intent(this,Integral.class);
            startActivity(t);
        }
    }
}
