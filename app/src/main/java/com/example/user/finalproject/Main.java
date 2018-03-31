package com.example.user.finalproject;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class Main extends AppCompatActivity {

    Switch sw;
    Intent t;
    EditText et;
    String eq;
    FileOutputStream fos;
    OutputStreamWriter osw;
    BufferedWriter bw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sw= (Switch) findViewById(R.id.sw1);
        et= (EditText) findViewById(R.id.et1);
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
        eq=et.getText().toString();
        if(eq.equals("")){
            AlertDialog.Builder adb1;
            adb1=new AlertDialog.Builder(this);

            adb1.setTitle("Error");
            adb1.setMessage("You didn't enter an equation");
            adb1.setIcon(R.drawable.error_icon);
            adb1.setPositiveButton("ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.cancel();
                }
            });

            AlertDialog ad= adb1.create();
            ad.show();
        }
        else{
            try {
                fos=openFileOutput("equation.txt", Context.MODE_PRIVATE);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            osw=new OutputStreamWriter(fos);
            bw=new BufferedWriter(osw);
            try {
                bw.write(eq);
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                bw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

            if(!sw.isChecked()){
                t=new Intent(this,Derivative.class);
                startActivity(t);
            }
            else{
                t=new Intent(this,Integral.class);
                startActivity(t);
            }
        }
    }
}
