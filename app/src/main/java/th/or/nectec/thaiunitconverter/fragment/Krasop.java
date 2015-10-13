package th.or.nectec.thaiunitconverter.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import th.or.nectec.thaiunitconverter.R;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import th.or.nectec.thaiunitconverter.activity.ThaiUnitCalculator;

/**
 * Created by User on 7/10/2558.
 */

public class Krasop extends Fragment implements View.OnClickListener {

    public static final int STANDARD_PERCENT = 15;
    private Button wetRiceButton;
    private Button dryRiceButton;
    private EditText riceQuantity;
    private EditText humidQuantity;
    private TextView sumaryView;
    private LinearLayout krasop30, krasop50, krasop100;
    private double wetRiceValue;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


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
        View rootView = inflater.inflate(R.layout.krasop, container, false);
        initInstances(rootView);
        return rootView;

    }

    private void initInstances(View rootView) {
        riceQuantity = (EditText) rootView.findViewById(R.id.rice_quantity);
        sumaryView = (TextView) rootView.findViewById(R.id.weight_wet_sumary);
        wetRiceButton = (Button) rootView.findViewById(R.id.calculate_wet_button);
        krasop30 = (LinearLayout) rootView.findViewById(R.id.krasop_30kg);
        krasop50 = (LinearLayout) rootView.findViewById(R.id.krasop_50kg);
        krasop100 = (LinearLayout) rootView.findViewById(R.id.krasop_100kg);

        krasop30.setOnClickListener(this);
        krasop50.setOnClickListener(this);
        krasop100.setOnClickListener(this);
        wetRiceButton.setOnClickListener(this);
    }

    int unitFactor = 0;

    @Override
    public void onClick(View view) {
        double wetRiceValue = 0;
        // Check which radio button was clicked
        switch (view.getId()) {
            case R.id.krasop_30kg:
                unitFactor = 30;
                //wetRiceValue = ThaiUnitCalculator.calculateKrasobpuiToKg(value);
                break;
            case R.id.krasop_50kg:
                unitFactor = 50;
                //wetRiceValue = ThaiUnitCalculator.calculateKrasobtonnToKg(value);
                break;
            case R.id.krasop_100kg:
                unitFactor = 100;
                //wetRiceValue = ThaiUnitCalculator.calculateKrasobToKg(value);
                break;
            case R.id.calculate_wet_button:
                wetRiceValue = calculateWetRice(unitFactor, riceQuantity);
                sumaryView.setText(unitFactor + " กิโลกรัม * " + riceQuantity.getText().toString() + " กระสอบ = " + wetRiceValue + " กิโลกรัม");
            }
        }

    private double calculateWetRice(int unitFactor, EditText riceQuantityEditText) {
        double riceQuantityValue = 0;
        String riceQuantityValueStr = riceQuantityEditText.getText().toString();

        if (TextUtils.isEmpty(riceQuantityValueStr)) {
            riceQuantityValue =0;
        } else {
            riceQuantityValue = Double.valueOf(riceQuantityValueStr);
        }
        double wetRiceValue = 0;
        switch (unitFactor) {
            case 30:
                //unitFactor = 30;
                wetRiceValue = ThaiUnitCalculator.calculateKrasobpuiToKg(riceQuantityValue);
                break;
            case 50:
                //unitFactor = 50;
                wetRiceValue = ThaiUnitCalculator.calculateKrasobtonnToKg(riceQuantityValue);
                break;
            case 100:
                //unitFactor = 100;
                wetRiceValue = ThaiUnitCalculator.calculateKrasobToKg(riceQuantityValue);
                break;
        }
        return wetRiceValue;
    }
}

