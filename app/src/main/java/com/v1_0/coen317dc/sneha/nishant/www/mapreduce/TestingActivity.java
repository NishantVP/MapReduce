package com.v1_0.coen317dc.sneha.nishant.www.mapreduce;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class TestingActivity extends AppCompatActivity {


    private SharedPreferences sharedpreferences;
    private String work;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_testing);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        sharedpreferences = getSharedPreferences("MyPREFERENCES", MODE_PRIVATE);
        work = sharedpreferences.getString("WORKER_FUNCTION", "");

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
            Intent i = new Intent(TestingActivity.this, FirstConnectionService.class);
            i.putExtra("KEY1", "Value to be used by the service");
            TestingActivity.this.startService(i);



    }

    public void clickedMapService (View view) {
        work = sharedpreferences.getString("WORKER_FUNCTION", "");
        System.out.println(work);
        if(work.equals("map")) {
            System.out.println("Map Clicked");
            Intent i = new Intent(TestingActivity.this, MapService.class);
            i.putExtra("KEY1", "Value to be used by the service");
            TestingActivity.this.startService(i);
        }
        else {
            System.out.println("Map Clicked");
            System.out.println("NOT map");
        }

    }

    public void clickedReduceService (View view) {
        work = sharedpreferences.getString("WORKER_FUNCTION", "");
        System.out.println(work);
        if(work.equals("reduce")) {
            System.out.println("Reduce Clicked");
            Intent i = new Intent(TestingActivity.this, ReduceService.class);
            i.putExtra("KEY1", "Value to be used by the service");
            TestingActivity.this.startService(i);
        }
        else {
            System.out.println("Reduce Clicked");
            System.out.println("NOT reduce");
        }

    }
    public void clickedRepeatedService (View view) {

        System.out.println("Repeate Service Clicked");
        Intent i = new Intent(TestingActivity.this, AutoRepeateMapReduceService.class);
        i.putExtra("KEY1", "Value to be used by the service");
        TestingActivity.this.startService(i);

    }


}
