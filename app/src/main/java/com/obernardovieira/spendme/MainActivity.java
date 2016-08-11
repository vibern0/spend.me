package com.obernardovieira.spendme;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*Timer t = new Timer();
        TimerTask tt = new TimerTask() {
            @Override
            public void run() {
                setContentView(R.layout.activity_main);
            }
        };
        t.schedule(tt, 5000);*/
    }

    public void goSpends(View view) {
        Intent intent = new Intent(MainActivity.this, SpendsActivity.class);
        MainActivity.this.startActivity(intent);
    }

    public void goCategories(View view) {
        Intent intent = new Intent(MainActivity.this, CategoriesActivity.class);
        MainActivity.this.startActivity(intent);
    }

    public void goOptions(View view) {
        // Kabloey
    }
}
