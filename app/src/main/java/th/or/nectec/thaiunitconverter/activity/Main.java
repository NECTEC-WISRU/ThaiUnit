package th.or.nectec.thaiunitconverter.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import th.or.nectec.thaiunitconverter.fragment.Aum;
import th.or.nectec.thaiunitconverter.fragment.Krasop;
import th.or.nectec.thaiunitconverter.fragment.Kwian;
import th.or.nectec.thaiunitconverter.fragment.Peep;
import th.or.nectec.thaiunitconverter.R;
import th.or.nectec.thaiunitconverter.fragment.Tung;

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


        kwian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), CalculateActivity.class);
                startActivity(i);
            }
        });

        /*krasop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), Krasop.class);
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
        });*/
    }
}

