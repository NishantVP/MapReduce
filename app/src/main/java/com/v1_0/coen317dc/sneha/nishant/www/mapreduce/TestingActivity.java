package com.v1_0.coen317dc.sneha.nishant.www.mapreduce;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class TestingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_testing);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }


    public void clickedFirstConnection (View view) {
        System.out.println("First Connection Clicked");
        //Start a Service which will initiate the communication wiht the server in background
        // use this to start and trigger a service
        Intent i = new Intent(TestingActivity.this, FirstConnectionService.class);
        // potentially add data to the intent
        i.putExtra("KEY1", "Value to be used by the service");
        TestingActivity.this.startService(i);
    }


}
