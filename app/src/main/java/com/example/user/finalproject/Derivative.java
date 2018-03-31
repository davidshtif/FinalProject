package com.example.user.finalproject;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;


public class Derivative extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    WebView webView;
    String stringURL,eq,x,str;
    RadioButton rb1,rb2;
    EditText et;
    InputStream is;
    InputStreamReader tmp;
    BufferedReader reader;
    StringBuffer buffer;
    Spinner spinner;
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_derivative);

        webView=(WebView)findViewById(R.id.wv1);
        rb1=(RadioButton)findViewById(R.id.rb1);
        rb2=(RadioButton)findViewById(R.id.rb2);
        et=(EditText)findViewById(R.id.et2);
        spinner=(Spinner)findViewById(R.id.spinner1);
        tv=(TextView)findViewById(R.id.yoeq1);

        ArrayAdapter<CharSequence>adapter=ArrayAdapter.createFromResource(this,R.array.results,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);


        try {
            is=openFileInput("equation.txt");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        tmp=new InputStreamReader(is);
        reader=new BufferedReader(tmp);
        buffer=new StringBuffer();
        try{
            while((str = reader.readLine()) != null){
                buffer.append(str+"\n");
            }
        }
        catch(IOException e){
            e.printStackTrace();
        }
        try {
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        eq=""+buffer;
        tv.setText("Your equaion: "+eq);
        eq = eq.replaceAll("\\+","%2B");
        webView.getSettings().setJavaScriptEnabled(true);

        stringURL="http://www.wolframalpha.com/input/?i=derivative+of+"+eq;

        webView.setWebViewClient(new MyWebViewClient());
        webView.loadUrl(stringURL);
    }

    public void calculate(View view) {
        x=et.getText().toString();
        if(x.equals("")){
            AlertDialog.Builder adb1;
            adb1=new AlertDialog.Builder(this);

            adb1.setTitle("Error");
            adb1.setMessage("You didn't enter x");
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
            if(rb1.isChecked()){ //חישוב ערכים לפנוקציה
                stringURL="http://www.wolframalpha.com/input/?i=f(x)="+eq+" where x="+x;
                webView.loadUrl(stringURL);
                webView.setWebViewClient(new MyWebViewClient());
            }
            else{
                if(rb2.isChecked()){//חישוב ערכים לנגזרת
                    stringURL="http://www.wolframalpha.com/input/?i=derivative for f(x)="+eq+" where x="+x;
                    webView.loadUrl(stringURL);
                    webView.setWebViewClient(new MyWebViewClient());
                }
                else{ //אף אחד מהם
                    AlertDialog.Builder adb1;
                    adb1=new AlertDialog.Builder(this);

                    adb1.setTitle("Error");
                    adb1.setMessage("You didn't pick an option");
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
            }
        }

    }

    public void reset1(View view) {
        Intent res=new Intent(this,Main.class);
        startActivity(res);
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        if(i==0){
            et.setText("");
        }
        else{
            x=""+(i-6);
            et.setText(""+x);
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
        return;
    }

    private class MyWebViewClient extends WebViewClient{
        public boolean shouldOverrideUrlLoading(WebView view,String stringURL){
            view.loadUrl(stringURL);
            return true;
        }

        @Override
        public void onPageFinished(WebView view, String url)
        {
            String script = "(function(){\n" +
                    "    document.getElementById(\"input-container\").style.display = \"none\"\n" +
                    "document.getElementsByTagName(\"nav\")[0].style.display = \"none\"\n" +
                    "document.getElementsByClassName(\"page bg-default results-view\")[0].style.display = \"none\"\n" +
                    "document.getElementById(\"headerad-pro\").style.display = \"none\"\n" +
                    "document.getElementById(\"relatedQueries\").style.display = \"none\"\n" +
                    "document.getElementById(\"premium-support-wrapper\").style.display = \"none\"\n" +
                    "document.getElementById(\"footer-wrap\").style.display = \"none\"\n" +
                    "document.getElementsByTagName(\"footer\")[3].style.display = \"none\"\n" +
                    "document.getElementById(\"Plot\").style.display = \"none\"\n" +
                    "document.getElementById(\"Input\").style.display = \"none\"\n" +
                    "})();";
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                webView.evaluateJavascript(script, null);
            }
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
