package th.or.nectec.thaiunitconverter.fragment;

import android.content.Context;
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
import android.view.inputmethod.InputMethodManager;
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

public class Tung extends Fragment implements View.OnClickListener{
    public static final int STANDARD_PERCENT = 15;
    private Button wetRiceButton;
    private Button dryRiceButton;

    private Button plusButton, minusButton;

    private EditText riceQuantity;
    private EditText humidQuantity;
    private TextView sumaryView;
    private double wetRiceValue;
    private LinearLayout moreOption;
    private CustomWeightSingleChoiceViewGroup customWeightLayout;

    double unitFactor = 0;
    double[] defaultUnitFactor = new double[]{10, 15};

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
        plusButton = (Button) rootView.findViewById(R.id.plus);
        minusButton = (Button) rootView.findViewById(R.id.minus);

        moreOption = (LinearLayout) rootView.findViewById(R.id.more_option);
        customWeightLayout = (CustomWeightSingleChoiceViewGroup) rootView.findViewById(R.id.custom_weight_layout);

        wetRiceButton.setOnClickListener(this);
        plusButton.setOnClickListener(this);
        minusButton.setOnClickListener(this);
        moreOption.setOnClickListener(this);

        for (int index = 0; index < defaultUnitFactor.length; index++) {
            CustomWeightView customWeightView = new CustomWeightView(getActivity());
            customWeightView.setCustomWeightInfoByResource(R.string.tung, R.drawable.tung, defaultUnitFactor[index]);
            customWeightLayout.addCustomWeightView(customWeightView);
        }

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


    @Override
    public void onClick(View view) {
        double wetRiceValue = 0;
        // Check which radio button was clicked
        switch (view.getId()) {
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

                break;
        }
    }

    private double calculateWetRice(double unitFactor, EditText riceQuantityEditText) {
        double riceQuantityValue = 0;
        String riceQuantityValueStr = riceQuantityEditText.getText().toString();

        if (TextUtils.isEmpty(riceQuantityValueStr)) {
            riceQuantityValue =0;
        } else {
            riceQuantityValue = Double.valueOf(riceQuantityValueStr);
        }
        double wetRiceValue = 0;
        switch ((int)unitFactor) {
            case 10:
                //unitFactor = 30;
                wetRiceValue = ThaiUnitCalculator.calculatetung10ToKg(riceQuantityValue);
                break;
            case 15:
                //unitFactor = 50;
                wetRiceValue = ThaiUnitCalculator.calculatetung15ToKg(riceQuantityValue);
                break;
            default:
                wetRiceValue = unitFactor * riceQuantityValue;
                break;
        }
        return wetRiceValue;
    }
    private void calculateAndShowWetRice() {
        double wetRiceValue;
        CustomWeightView selectedView = customWeightLayout.getSelectedCustomWeightView();

        hideSoftKeyboard();

        if (selectedView == null) {
            Toast.makeText(getActivity(), "กรุณาเลือกขนาดของถัง", Toast.LENGTH_SHORT).show();
        } else {
            unitFactor = customWeightLayout.getSelectedCustomWeightView().getWeightFactor();
            wetRiceValue = calculateWetRice(unitFactor, riceQuantity);
            sumaryView.setText(unitFactor + " กิโลกรัม * " + riceQuantity.getText().toString() + " ถัง = " + wetRiceValue + " กิโลกรัม");
        }

    }

    private void hideSoftKeyboard(){
        // Check if no view has focus:
        View view = getActivity().getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
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
                            Toast.makeText(getActivity(), "กรุณาระบุขนาดของถัง", Toast.LENGTH_SHORT).show();
                        } else if (customSizeStr.equals("0")) {
                            Toast.makeText(getActivity(), "กรุณาระบุขนาดของถัง", Toast.LENGTH_SHORT).show();
                        } else {
                            unitFactor = Double.valueOf(customSizeStr);
                            CustomWeightView customWeightView = new CustomWeightView(getActivity());
                            customWeightView.setCustomWeightInfoByResource(R.string.tung, R.drawable.tung, unitFactor);
                            customWeightLayout.addCustomWeightView(customWeightView);
                            //unitFactor = Integer.valueOf(customSizeStr);
                            customWeightLayout.setCheckedItem(customWeightView);

                            customWeightDialog.dismiss();
                        }
                    }
                });
            }
        });

        customWeightDialog.show();
    }
}

