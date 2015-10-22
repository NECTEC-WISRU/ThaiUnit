package th.or.nectec.thaiunitconverter.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import th.or.nectec.thaiunitconverter.R;

public class Main extends AppCompatActivity {

    private LinearLayout krasop, kwian, aum, tung, peep;

    int intentResult = 1;

    Intent calculateIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mainpage);

        getSupportActionBar().hide();

        krasop = (LinearLayout) findViewById(R.id.krasopButton);
        kwian = (LinearLayout) findViewById(R.id.kwianButton);
        aum = (LinearLayout) findViewById(R.id.aumButton);
        tung = (LinearLayout) findViewById(R.id.tungButton);
        peep = (LinearLayout) findViewById(R.id.peepButton);


        krasop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent calculateIntent = new Intent("unitconverter.intent.action.CALCULATE");
                if (getIntent().getAction().equals("unitconverter.intent.action.CALCULATE_DRY_RICE")){
                    calculateIntent.putExtra("is_launch_from_other", true);
                }
                calculateIntent.addCategory("unitconverter.intent.category.KRASOP");
                startActivityForResult(calculateIntent, intentResult);
            }
        });

        tung.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent calculateIntent = new Intent("unitconverter.intent.action.CALCULATE");
                if (getIntent().getAction().equals("unitconverter.intent.action.CALCULATE_DRY_RICE")){
                    calculateIntent.putExtra("is_launch_from_other", true);
                }
                calculateIntent.addCategory("unitconverter.intent.category.TUNG");
                startActivityForResult(calculateIntent, intentResult);
            }
        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (getIntent().getAction().equals("unitconverter.intent.action.CALCULATE_DRY_RICE") && resultCode==RESULT_OK) {
            setResult(RESULT_OK, data);
            finish();
        }
    }
}


