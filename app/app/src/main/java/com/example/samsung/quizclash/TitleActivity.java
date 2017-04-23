package com.example.samsung.quizclash;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.net.InetAddress;
import java.util.concurrent.Semaphore;


public class TitleActivity extends Activity {
    private Button btnBegin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_title);

        bindElements();
        addListenerOnButtonBegin();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_title, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case R.id.title_action_settings:
                //TODO
                break;
            case R.id.title_action_instruction:
                Intent intent = new Intent(this, InstructionActivity.class);
                this.startActivity(intent);
                break;
            default:
                return super.onOptionsItemSelected(item);
        }

        return true;
    }


    private void bindElements(){
        btnBegin = (Button) findViewById(R.id.title_btn_begin);
    }


    private void addListenerOnButtonBegin() {
        btnBegin.setOnClickListener(new View.OnClickListener() {
            Boolean isInternet = false;
            private final Semaphore semaphore = new Semaphore(0);
            @Override
            public void onClick(View v) {

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        isInternet = isInternetAvailable();
                        semaphore.release();
                    }
                }).start();

                try {
                    semaphore.acquire();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                if (isInternet) {
                    Intent intent;

                    intent = new Intent(v.getContext(), QuestionActivity.class);
                    v.getContext().startActivity(intent);
                }
                else {
                    Toast.makeText(TitleActivity.this,
                            "No internet connection detected!",
                            Toast.LENGTH_SHORT).show();
                }

            }

        });
    }


    public boolean isInternetAvailable() {
        try {
            InetAddress ipAddr = InetAddress.getByName("google.com");

            if (ipAddr.toString().equals("")) {
                return false;
            } else {
                return true;
            }

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

}
