package th.or.nectec.thaiunitconverter.fragment;

import android.graphics.Color;
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

public class Tung extends Fragment implements View.OnClickListener{
    public static final int STANDARD_PERCENT = 15;
    private Button wetRiceButton;
    private Button dryRiceButton;
    private EditText riceQuantity;
    private EditText humidQuantity;
    private TextView sumaryView;
    private LinearLayout tung10, tung15;
    private double wetRiceValue;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    public static Tung newInstance() {
        Tung fragment = new Tung();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_tung, container, false);
        initInstances(rootView);
        return rootView;

    }

    private void initInstances(View rootView) {
        riceQuantity = (EditText) rootView.findViewById(R.id.rice_quantity);
        sumaryView = (TextView) rootView.findViewById(R.id.weight_wet_sumary);
        wetRiceButton = (Button) rootView.findViewById(R.id.calculate_wet_button);
        tung10 = (LinearLayout) rootView.findViewById(R.id.tung_10kg);
        tung15 = (LinearLayout) rootView.findViewById(R.id.tung_15kg);

        tung10.setOnClickListener(this);
        tung15.setOnClickListener(this);
        wetRiceButton.setOnClickListener(this);
    }

    int unitFactor = 0;

    @Override
    public void onClick(View view) {
        double wetRiceValue = 0;
        // Check which radio button was clicked
        switch (view.getId()) {
            case R.id.tung_10kg:
                unitFactor = 10;
                tung10.setBackgroundColor(getResources().getColor(R.color.light_gray));
                tung15.setBackgroundColor(Color.TRANSPARENT);
                //wetRiceValue = ThaiUnitCalculator.calculateKrasobpuiToKg(value);
                break;
            case R.id.tung_15kg:
                unitFactor = 15;
                tung15.setBackgroundColor(getResources().getColor(R.color.light_gray));
                tung10.setBackgroundColor(Color.TRANSPARENT);
                //wetRiceValue = ThaiUnitCalculator.calculateKrasobtonnToKg(value);
                break;
            case R.id.calculate_wet_button:
                wetRiceValue = calculateWetRice(unitFactor, riceQuantity);
                sumaryView.setText(unitFactor + " กิโลกรัม * " + riceQuantity.getText().toString() + " ถัง = " + wetRiceValue + " กิโลกรัม");
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
            case 10:
                //unitFactor = 30;
                wetRiceValue = ThaiUnitCalculator.calculatetung10ToKg(riceQuantityValue);
                break;
            case 15:
                //unitFactor = 50;
                wetRiceValue = ThaiUnitCalculator.calculatetung15ToKg(riceQuantityValue);
                break;
        }
        return wetRiceValue;
    }
}

