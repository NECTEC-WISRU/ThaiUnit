package th.or.nectec.thaiunitconverter.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.DrawableRes;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.text.Editable;
import android.text.InputType;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

import th.or.nectec.thaiunitconverter.R;
import th.or.nectec.thaiunitconverter.activity.ThaiUnitCalculator;
import th.or.nectec.thaiunitconverter.view.CustomWeightView;
import th.or.nectec.thaiunitconverter.view.SingleChoiceViewStateController;

/**
 * Created by User on 7/10/2558.
 */

public class CalculateFragment extends Fragment implements View.OnClickListener {

    public static final int STANDARD_PERCENT = 15;

    String unitStr;
    int unitIcon;
    double[] defaultUnitFactor;

    private Button wetRiceButton;
    private Button dryRiceButton;
    private Button plusButton;
    private Button minusButton;

    private Button plusPercentButton, minusPercentButton;

    private EditText riceQuantity;
    private EditText humidPercentView;
    private TextView sumaryView, answerSumaryView;

    private TextView sumaryDryView, answerSumaryDryView;

    private LinearLayout moreOption;
    private LinearLayout customWeightLayout;

    private LinearLayout dryResultLayout;
    private LinearLayout wetResultLayout;

    private double wetRiceValue = 0;
    private double dryRiceValue;

    String pattern = "###,###.##";
    DecimalFormat df = new DecimalFormat(pattern);

    SingleChoiceViewStateController singleChoiceViewStateController = new SingleChoiceViewStateController();

    double unitFactor = 0;

    public static CalculateFragment newInstance(String unit, @DrawableRes int unitIcon, double[] defaultUnitFactor) {
        CalculateFragment fragment = new CalculateFragment();
        Bundle args = new Bundle();
        args.putString("UNIT", unit);
        args.putInt("UNIT_ICON", unitIcon);
        args.putDoubleArray("DEFAULT_UNIT_FACTOR", defaultUnitFactor);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.krasop, container, false);

        unitStr = getArguments().getString("UNIT");
        unitIcon = getArguments().getInt("UNIT_ICON");
        defaultUnitFactor = getArguments().getDoubleArray("DEFAULT_UNIT_FACTOR");

        initInstances(rootView);
        return rootView;
    }

    private void initInstances(final View rootView) {
        riceQuantity = (EditText) rootView.findViewById(R.id.rice_quantity);

        humidPercentView = (EditText) rootView.findViewById(R.id.rice_humid_percent);

        sumaryView = (TextView) rootView.findViewById(R.id.weight_wet_sumary);
        answerSumaryView = (TextView) rootView.findViewById(R.id.answer_weight_wet_sumary);

        sumaryDryView = (TextView) rootView.findViewById(R.id.weight_dry_sumary);
        answerSumaryDryView = (TextView) rootView.findViewById(R.id.answer_weight_dry_sumary);

        wetRiceButton = (Button) rootView.findViewById(R.id.calculate_wet_button);
        plusButton = (Button) rootView.findViewById(R.id.plus);
        minusButton = (Button) rootView.findViewById(R.id.minus);

        dryRiceButton = (Button) rootView.findViewById(R.id.calculate_dry_button);
        plusPercentButton = (Button) rootView.findViewById(R.id.plus_percent);
        minusPercentButton = (Button) rootView.findViewById(R.id.minus_percent);

        moreOption = (LinearLayout) rootView.findViewById(R.id.more_option);
        customWeightLayout = (LinearLayout) rootView.findViewById(R.id.custom_weight_layout);

        wetResultLayout = (LinearLayout) rootView.findViewById(R.id.wet_result_layout);
        dryResultLayout = (LinearLayout) rootView.findViewById(R.id.dry_result_layout);

        wetRiceButton.setOnClickListener(this);
        plusButton.setOnClickListener(this);
        minusButton.setOnClickListener(this);
        moreOption.setOnClickListener(this);

        dryRiceButton.setOnClickListener(this);
        plusPercentButton.setOnClickListener(this);
        minusPercentButton.setOnClickListener(this);

        riceQuantity.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                wetResultLayout.setVisibility(View.GONE);
                dryResultLayout.setVisibility(View.GONE);
            }
        });


        humidPercentView.setText(String.valueOf(STANDARD_PERCENT));
        humidPercentView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {


                calculateAndShowDryResult();
            }
        });

        for (int index = 0; index < defaultUnitFactor.length; index++) {
            CustomWeightView customWeightView = new CustomWeightView(getActivity());
            customWeightView.setCustomWeightInfoByResource(unitIcon, defaultUnitFactor[index]);
            customWeightLayout.addView(customWeightView);
            singleChoiceViewStateController.addView(customWeightView);
        }

        riceQuantity.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    //do something
                    calculateAndShowWetRice();
                    calculateAndShowDryResult();
                    showResultLayout();
                }
                return false;
            }
        });
    }

    private void calculateAndShowDryResult() {
        double humidPercent = 0;

        String humidPercentStr = humidPercentView.getText().toString();

        if (TextUtils.isEmpty(humidPercentStr)) {
            //humidPercentView.setText(String.valueOf(STANDARD_PERCENT));
            Toast.makeText(getActivity(), String.format(getString(R.string.minimum_percent), STANDARD_PERCENT), Toast.LENGTH_SHORT).show();
        } else {
            humidPercent = Double.valueOf(humidPercentStr);
            if (humidPercent >= STANDARD_PERCENT) {
                dryRiceValue = calculateDryResult(humidPercent, wetRiceValue);
                answerSumaryDryView.setText(String.format(getString(R.string.answer_calculate_dry_result), df.format(dryRiceValue)));
                answerSumaryDryView.setVisibility(View.VISIBLE);
            } else {
                Toast.makeText(getActivity(), String.format(getString(R.string.minimum_percent), STANDARD_PERCENT), Toast.LENGTH_SHORT).show();
                //answerSumaryDryView.setVisibility(View.GONE);
            }
        }
    }

    private double calculateDryResult(double percent, double dryRiceValue) {
        return dryRiceValue*((100-percent)/(100- STANDARD_PERCENT));
    }

    @Override
    public void onClick(View view) {
        double wetRiceValue = 0;

        switch (view.getId()) {
            case R.id.calculate_wet_button:
                calculateAndShowWetRice();
                showResultLayout();
                break;
            case R.id.plus:
                increaseRiceQuantity();
                break;
            case R.id.minus:
                decreaseRiceQuantity();
                break;
            case R.id.more_option:
                //Toast.makeText(getActivity() , "hello", Toast.LENGTH_SHORT).show();
                showCustomWeightDialog();
                break;

            case R.id.calculate_dry_button:
                calculateAndShowDryResult();
                dryResultLayout.setVisibility(View.VISIBLE);
                //answerSumaryDryView.setVisibility(View.GONE);

                break;
            case R.id.plus_percent:
                increaseHumidPercent();
                break;
            case R.id.minus_percent:
                decreaseHumidPercent();
                break;
        }
    }

    private void showResultLayout() {
        wetResultLayout.setVisibility(View.VISIBLE);
        wetRiceButton.setVisibility(View.VISIBLE);
    }

    private void decreaseHumidPercent() {
        String humidPercentStr = humidPercentView.getText().toString();
        double g = TextUtils.isEmpty(humidPercentStr)
                ? 0 : Double.parseDouble(humidPercentStr);
        double h = g - 1;
        String outoutMinusPercent = df.format(h);
        humidPercentView.setText(outoutMinusPercent);
        if (h < STANDARD_PERCENT) {
            humidPercentView.setText(String.valueOf(STANDARD_PERCENT));
            //Toast.makeText(getActivity(), String.format(getString(R.string.minimum_percent), STANDARD_PERCENT), Toast.LENGTH_SHORT).show();
        }
    }

    private void increaseHumidPercent() {
        String humidPercentStr = humidPercentView.getText().toString();
        double e = TextUtils.isEmpty(humidPercentStr)
                ? 0 : Double.parseDouble(humidPercentStr);
        double f = e + 1;
        String outputPlusPercent = df.format(f);
        humidPercentView.setText(outputPlusPercent);
    }

    private void decreaseRiceQuantity() {
        String riceQuantityStr = riceQuantity.getText().toString();
        double c = TextUtils.isEmpty(riceQuantityStr)
                ? 0 : Double.parseDouble(riceQuantityStr);
        double d = c - 1;
        String outoutMinus = df.format(d);
        riceQuantity.setText(outoutMinus);
        if (d < 0) {
            riceQuantity.setText("0");
        }
    }

    private void increaseRiceQuantity() {
        String riceQuantityStr = riceQuantity.getText().toString();
        double a = TextUtils.isEmpty(riceQuantityStr)
                ? 0 : Double.parseDouble(riceQuantityStr);
        double b = a + 1;
        String outputPlus = df.format(b);
        riceQuantity.setText(outputPlus);
    }

    private void calculateAndShowWetRice() {

        CustomWeightView selectedView = (CustomWeightView) singleChoiceViewStateController.getSelectedCustomWeightView();

        hideSoftKeyboard();

        String riceQuantityStr = riceQuantity.getText().toString();

        if (selectedView == null) {
            Toast.makeText(getActivity(), String.format(getString(R.string.please_select_unit_factor), unitStr), Toast.LENGTH_SHORT).show();
        }else if(TextUtils.isEmpty(riceQuantityStr)){
            Toast.makeText(getActivity(), String.format(getString(R.string.please_define_rice_quantity), unitStr), Toast.LENGTH_SHORT).show();
        } else {
            unitFactor = ((CustomWeightView) singleChoiceViewStateController.getSelectedCustomWeightView()).getWeightFactor();
            wetRiceValue = calculateWetRice(unitFactor, riceQuantity);
            sumaryView.setText(String.format(getString(R.string.calculate_wet_result), df.format(unitFactor), df.format(Double.valueOf(riceQuantityStr)), unitStr));
            answerSumaryView.setText(String.format(getString(R.string.answer_calculate_wet_result), df.format(wetRiceValue)));

            //resultLayout.setVisibility(View.VISIBLE);
        }

        boolean isLaunchFromOther = getActivity().getIntent().getBooleanExtra("is_launch_from_other", false);

        if (isLaunchFromOther){
            Intent intent = new Intent();
            intent.putExtra("wetRiceResult", wetRiceValue);//การส่งค่าตัวแปรให้กับactivityปลายทาง
            intent.putExtra("dryRiceResult", dryRiceValue);
            getActivity().setResult(Activity.RESULT_OK, intent);//เมื่อ activity success ก้จะส่ง intent ไป
            getActivity().finish();
        }

    }

    private void showCustomWeightDialog() {
        final AlertDialog.Builder alert = new AlertDialog.Builder(getActivity());
        final EditText edittext = new EditText(getActivity());
        edittext.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
        edittext.setId(R.id.custom_unit_factor);
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
                            Toast.makeText(getActivity(), String.format(getString(R.string.please_define_unit_factor), unitStr), Toast.LENGTH_SHORT).show();
                        } else if (customSizeStr.equals("0")) {
                            Toast.makeText(getActivity(), String.format(getString(R.string.please_define_unit_factor), unitStr), Toast.LENGTH_SHORT).show();
                        } else {
                            unitFactor = Double.valueOf(customSizeStr);
                            CustomWeightView customWeightView = new CustomWeightView(getActivity());
                            customWeightView.setCustomWeightInfoByResource(unitIcon, unitFactor);
                            customWeightLayout.addView(customWeightView);
                            singleChoiceViewStateController.addView(customWeightView);
                            singleChoiceViewStateController.setCheckedItem(customWeightView);

                            customWeightDialog.dismiss();
                        }
                    }
                });
            }
        });

        customWeightDialog.show();
    }

    private void hideSoftKeyboard() {
        // Check if no view has focus:
        View view = getActivity().getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    private double calculateWetRice(double unitFactor, EditText riceQuantityEditText) {
        double riceQuantityValue = 0;
        String riceQuantityValueStr = riceQuantityEditText.getText().toString();


        if (TextUtils.isEmpty(riceQuantityValueStr)) {
            riceQuantityValue = 0;
        } else {
            riceQuantityValue = Double.valueOf(riceQuantityValueStr);
        }
        wetRiceValue = 0;

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

    ;
}

