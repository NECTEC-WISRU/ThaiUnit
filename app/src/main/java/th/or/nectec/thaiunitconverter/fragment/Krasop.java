package th.or.nectec.thaiunitconverter.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import th.or.nectec.thaiunitconverter.R;

import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import th.or.nectec.thaiunitconverter.R;
import th.or.nectec.thaiunitconverter.activity.ThaiUnitCalculator;

import java.util.ArrayList;

/**
 * Created by User on 7/10/2558.
 */

public class Krasop extends Fragment {

    public static final int STANDARD_PERCENT = 15;
    private Button wetRiceButton;
    private Button dryRiceButton;
    private EditText riceQuantity;
    private EditText humidQuantity;
    private double wetRiceValue;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.krasop);

        numberView = (EditText) getView().findViewById(R.id.rice_quantity);
        buttonView = (Button) getView().findViewById(R.id.calculate_wet_button);
        krasop = (LinearLayout) getView().findViewById(R.id.krasop_30kg);
        krasop = (LinearLayout) getView().findViewById(R.id.krasop_50kg);
        krasop = (LinearLayout) getView().findViewById(R.id.krasop_100kg);
    }

    public static Krasop newInstance() {
        Krasop fragment = new Krasop();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.krasop, container, false);
    }

    private double calculateWetResult(int selectedid, double value) {
        double wetRiceValue = 0;
        // Check which radio button was clicked
        switch (selectedid) {
            case R.id.kasop_30kg:
                wetRiceValue = ThaiUnitCalculator.calculateKrasobpuiToKg(value);
                break;
            case R.id.kasop_50kg:
                wetRiceValue = ThaiUnitCalculator.calculateKrasobtonnToKg(value);
                break;
            case R.id.kasop_100kg:
                wetRiceValue = ThaiUnitCalculator.calculateKrasobToKg(value);
                break;
        }
        return wetRiceValue;
    }
}
