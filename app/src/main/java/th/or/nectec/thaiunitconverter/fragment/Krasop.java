package th.or.nectec.thaiunitconverter.fragment;

import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.text.InputType;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import th.or.nectec.thaiunitconverter.R;

import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import th.or.nectec.thaiunitconverter.activity.ThaiUnitCalculator;
import th.or.nectec.thaiunitconverter.view.CustomWeightSingleChoiceViewGroup;
import th.or.nectec.thaiunitconverter.view.CustomWeightView;

/**
 * Created by User on 7/10/2558.
 */

public class Krasop extends Fragment implements View.OnClickListener {

    public static final int STANDARD_PERCENT = 15;
    private Button wetRiceButton;
    private Button dryRiceButton;
    private Button plusButton;
    private Button minusButton;

    private EditText riceQuantity;
    private EditText humidQuantity;
    private TextView sumaryView;
    private LinearLayout krasop30, krasop50, krasop100, moreOption;
    private CustomWeightSingleChoiceViewGroup customWeightLayout;
    private double wetRiceValue;

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
        plusButton = (Button) rootView.findViewById(R.id.plus);
        minusButton = (Button) rootView.findViewById(R.id.minus);

        moreOption = (LinearLayout) rootView.findViewById(R.id.more_option);
        krasop30 = (LinearLayout) rootView.findViewById(R.id.krasop_30kg);
        krasop50 = (LinearLayout) rootView.findViewById(R.id.krasop_50kg);
        krasop100 = (LinearLayout) rootView.findViewById(R.id.krasop_100kg);
        customWeightLayout = (CustomWeightSingleChoiceViewGroup) rootView.findViewById(R.id.custom_weight_layout);

        krasop30.setOnClickListener(this);
        krasop50.setOnClickListener(this);
        krasop100.setOnClickListener(this);
        wetRiceButton.setOnClickListener(this);
        plusButton.setOnClickListener(this);
        minusButton.setOnClickListener(this);
        moreOption.setOnClickListener(this);

        riceQuantity.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    //do something
                    calculateAndShowWetRice();
                }
                return false;
            }
        });
    }

    double unitFactor = 0;

    @Override
    public void onClick(View view) {
        double wetRiceValue = 0;

        switch (view.getId()) {
            case R.id.krasop_30kg:
                unitFactor = 30;
                krasop30.setBackgroundColor(getResources().getColor(R.color.light_gray));
                krasop50.setBackgroundColor(Color.TRANSPARENT);
                krasop100.setBackgroundColor(Color.TRANSPARENT);
                //wetRiceValue = ThaiUnitCalculator.calculateKrasobpuiToKg(value);
                break;
            case R.id.krasop_50kg:
                unitFactor = 50;
                krasop50.setBackgroundColor(getResources().getColor(R.color.light_gray));
                krasop100.setBackgroundColor(Color.TRANSPARENT);
                krasop30.setBackgroundColor(Color.TRANSPARENT);
                //wetRiceValue = ThaiUnitCalculator.calculateKrasobtonnToKg(value);
                break;
            case R.id.krasop_100kg:
                unitFactor = 100;
                krasop100.setBackgroundColor(getResources().getColor(R.color.light_gray));
                krasop30.setBackgroundColor(Color.TRANSPARENT);
                krasop50.setBackgroundColor(Color.TRANSPARENT);
                //wetRiceValue = ThaiUnitCalculator.calculateKrasobToKg(value);
                break;
            case R.id.calculate_wet_button:
                calculateAndShowWetRice();
                break;
            case R.id.plus:
                int a = Integer.parseInt(riceQuantity.getText().toString());
                int b = a + 1;
                riceQuantity.setText(new Integer(b).toString());
                break;
            case R.id.minus:
                int c = Integer.parseInt(riceQuantity.getText().toString());
                int d = c - 1;
                riceQuantity.setText(new Integer(d).toString());
                if (d < 0) {
                    riceQuantity.setText("0");
                }
                break;
            case R.id.more_option:
                //Toast.makeText(getActivity() , "hello", Toast.LENGTH_SHORT).show();

                showCustomWeightDialog();
                krasop100.setBackgroundColor(Color.TRANSPARENT);
                krasop30.setBackgroundColor(Color.TRANSPARENT);
                krasop50.setBackgroundColor(Color.TRANSPARENT);

                break;
        }
    }

    private void calculateAndShowWetRice() {
        double wetRiceValue;
        wetRiceValue = calculateWetRice(unitFactor, riceQuantity);
        sumaryView.setText(unitFactor + " กิโลกรัม * " + riceQuantity.getText().toString() + " กระสอบ = " + wetRiceValue + " กิโลกรัม");
    }

    private void showCustomWeightDialog() {
        final AlertDialog.Builder alert = new AlertDialog.Builder(getActivity());
        final EditText edittext = new EditText(getActivity());
        edittext.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);

        //alert.setMessage("ใสขนาดที่ต้องการ่");
        alert.setTitle("ใส่ขนาดที่ต้องการ");
        alert.setView(edittext);
        alert.setPositiveButton("ตกลง", null);
        alert.setNegativeButton("ยกเลิก", null);

        final AlertDialog customWeightDialog = alert.create();

        customWeightDialog.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface dialogInterface) {
                Button b = customWeightDialog.getButton(AlertDialog.BUTTON_POSITIVE);
                b.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        String customSizeStr = edittext.getText().toString();
                        if (TextUtils.isEmpty(customSizeStr)) {
                            Toast.makeText(getActivity(), "กรุณาระบุขนาดของกระสอบ", Toast.LENGTH_SHORT).show();
                        } else if (customSizeStr.equals("0")){
                            Toast.makeText(getActivity(), "กรุณาระบุขนาดของกระสอบ", Toast.LENGTH_SHORT).show();
                        }else {
                            unitFactor = Double.valueOf(customSizeStr);

                            CustomWeightView customWeightView = new CustomWeightView(getActivity());
                            customWeightView.setCustomWeightInfoByResource(R.string.krasop, R.drawable.krasop);
                            customWeightLayout.addCustomWeightView(customWeightView);
                            //unitFactor = Integer.valueOf(customSizeStr);

                            customWeightDialog.dismiss();
                        }
                    }
                });
            }
        });

        customWeightDialog.show();
    }


    private double calculateWetRice(double unitFactor, EditText riceQuantityEditText) {
        double riceQuantityValue = 0;
        String riceQuantityValueStr = riceQuantityEditText.getText().toString();


        if (TextUtils.isEmpty(riceQuantityValueStr)) {
            riceQuantityValue = 0;
        } else {
            riceQuantityValue = Double.valueOf(riceQuantityValueStr);
        }
        double wetRiceValue = 0;
        switch ((int) unitFactor) {
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
            default:
                wetRiceValue = unitFactor * riceQuantityValue;
                break;
        }
        return wetRiceValue;
    }
}

