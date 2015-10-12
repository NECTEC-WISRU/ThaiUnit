package com.example.user.unitconverternew;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

public class Main extends AppCompatActivity {

    private LinearLayout krasop, kwian, aum, tung, peep;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mainpage);


        krasop = (LinearLayout) findViewById(R.id.krasopButton);
        kwian = (LinearLayout) findViewById(R.id.kwianButton);
        aum = (LinearLayout) findViewById(R.id.aumButton);
        tung = (LinearLayout) findViewById(R.id.tungButton);
        peep = (LinearLayout) findViewById(R.id.peepButton);

        krasop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), Krasop.class);
                startActivity(i);
            }
        });

        kwian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), Kwian.class);
                startActivity(i);
            }
        });

        aum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), Aum.class);
                startActivity(i);
            }
        });

        tung.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), Tung.class);
                startActivity(i);
            }
        });

        peep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), Peep.class);
                startActivity(i);
            }
        });
    }
}

