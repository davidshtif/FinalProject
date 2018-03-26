package com.example.user.finalproject;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.RadioButton;
import android.widget.Switch;

public class Derivative extends AppCompatActivity {

    WebView webView;
    String stringURL;
    RadioButton rb1,rb2;
    Switch sw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_derivative);

        webView=(WebView)findViewById(R.id.wv);
        rb1=(RadioButton)findViewById(R.id.radioButton);
        rb2=(RadioButton)findViewById(R.id.radioButton2);
        sw=(Switch)findViewById(R.id.switch1);

        webView.getSettings().setJavaScriptEnabled(true);
        stringURL="https://www.symbolab.com/solver/derivative-calculator/%5Cfrac%7Bd%7D%7Bdx%7D%5Cleft(%5E%7B%20%7D%5Clog_%7B%20%7D%5Cleft(%5Cright)%5Cright)";
        webView.loadUrl(stringURL);
        webView.setWebViewClient(new MyWebViewClient());
    }

    public void calculate(View view) {
        if(rb1.isChecked()){ //חישוב ערכים לפנוקציה
            if(sw.isChecked()){//חישוב ערך ספציפי

            }
            else{//חישוב ערכים בין מינוס 10 ל10

            }
        }
        else{
           if(rb2.isChecked()){//חישוב ערכים לנגזרת
               if(sw.isChecked()){//חישוב ערך ספציפי

               }
               else{//חישוב ערכים בין מינוס 10 ל10

               }
           }
           else{ //אף אחד מהם
               AlertDialog.Builder adb1;
               adb1=new AlertDialog.Builder(this);

               adb1.setTitle("Error");
               adb1.setMessage("You didn't pick an option");
               adb1.setIcon(R.drawable.sign_error_icon);
               adb1.setPositiveButton("ok", new DialogInterface.OnClickListener() {
                   @Override
                   public void onClick(DialogInterface dialogInterface, int i) {
                       dialogInterface.cancel();
                   }
               });

               AlertDialog ad= adb1.create();
               ad.show();
           }
        }
    }

    private class MyWebViewClient extends WebViewClient{
        public boolean shouldOverrideUrlLoading(WebView view,String stringURL){
            view.loadUrl(stringURL);
            return true;
        }
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
}
