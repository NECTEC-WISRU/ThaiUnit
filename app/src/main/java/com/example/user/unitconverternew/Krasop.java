package com.example.user.unitconverternew;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 7/10/2558.
 */
public class Krasop extends Activity {

    private Spinner spinner;

    private ArrayList<String> ThaiUnit = new ArrayList<String>();

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.krasop);

        spinner = (Spinner) findViewById(R.id.thai_spinner);

        //createThaiUnitData();

        // Adapter ตัวแรก
        ArrayAdapter<String> adapterThai = new ArrayAdapter<String>
                (this, android.R.layout.simple_spinner_dropdown_item, getResources().getStringArray(R.array.thai_unit));
        spinner.setAdapter(adapterThai);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(Krasop.this, getResources().getStringArray(R.array.thai_unit)[i], Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }
}


