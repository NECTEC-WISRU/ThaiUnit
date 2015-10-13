package th.or.nectec.thaiunitconverter.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Set;

import th.or.nectec.thaiunitconverter.R;
import th.or.nectec.thaiunitconverter.fragment.Krasop;
import th.or.nectec.thaiunitconverter.fragment.Kwian;
import th.or.nectec.thaiunitconverter.fragment.Tung;

/**
 * Created by User on 7/10/2558.
 */
public class CalculateActivity extends AppCompatActivity {

    private Spinner spinner;

    private ArrayList<String> ThaiUnit = new ArrayList<String>();
    private String[] unitArray;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calculate_activity);


        unitArray = getResources().getStringArray(R.array.thai_unit);
        spinner = (Spinner) findViewById(R.id.thai_spinner);

        // Adapter ตัวแรก
        ArrayAdapter<String> adapterThai = new ArrayAdapter<String>
                (this, android.R.layout.simple_spinner_dropdown_item, unitArray);
        /*spinner.setAdapter(adapterThai);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                Toast.makeText(CalculateActivity.this, unitArray[position], Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });*/

        Set<String> intentCategories = getIntent().getCategories();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();

        if (intentCategories == null){
            
        } else if (intentCategories.contains("unitconverter.intent.category.KRASOP")) {
            fragmentTransaction.replace(R.id.container, Krasop.newInstance()).commit();
        } else if (intentCategories.contains("unitconverter.intent.category.TUNG")) {
            fragmentTransaction.replace(R.id.container, Tung.newInstance()).commit();
        }

    }
}


