package th.or.nectec.thaiunitconverter.view;

/**
 * Created by User on 14/10/2558.
 */

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.annotation.DrawableRes;
import android.support.annotation.StringRes;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

import th.or.nectec.thaiunitconverter.R;

public class CustomWeightView extends RelativeLayout {

    private TextView weightText;
    private ImageView weightIcon;
    boolean checked = false;
    double weightFactor = 0;

    public CustomWeightView(Context context) {
        super(context);
        initInflate();
        initInstances();
    }

    public CustomWeightView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initInflate();
        initInstances();
        initWithAttrs(attrs);
    }

    public CustomWeightView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initInflate();
        initInstances();
        initWithAttrs(attrs);
    }

    private void initInflate() {
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.custom_weight_view, this);
    }

    private void initInstances() {
        weightIcon = (ImageView) findViewById(R.id.weight_icon);
        weightText = (TextView) findViewById(R.id.weight_text);
    }

    private void initWithAttrs(AttributeSet attrs) {

    }

    public void setCustomWeightInfo(String weightTextStr, Drawable weightIconDrawable, double weightFactor) {
        this.weightFactor = weightFactor;

        String pattern = "###,###.##";
        DecimalFormat df = new DecimalFormat(pattern);
        String output = df.format(weightFactor);

        weightText.setText(weightTextStr + " (" + df.format(weightFactor) + " กก.)");
        weightIcon.setImageDrawable(weightIconDrawable);
    }

    public void setCustomWeightInfoByResource(@StringRes int weightTextId, @DrawableRes int weightIconId, double weightFactor) {
        String weightTextStr = getResources().getString(weightTextId);
        Drawable weightIconDrawable = getResources().getDrawable(weightIconId);

        setCustomWeightInfo(weightTextStr, weightIconDrawable, weightFactor);
    }

    public void setCustomWeightText(String weightTextStr) {
        weightText.setText(weightTextStr);
    }

    public void setCustomWeightText(@StringRes int weightTextId) {
        weightText.setText(weightTextId);
    }

    public void setCustomWeightDrawable(Drawable weightIconDrawable) {
        weightIcon.setImageDrawable(weightIconDrawable);
    }

    public void setCustomWeightDrawable(@DrawableRes int weightIconId) {
        weightIcon.setImageResource(weightIconId);
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
        if (checked) {
            this.setBackgroundColor(getResources().getColor(R.color.light_gray));
        } else {
            this.setBackgroundColor(Color.TRANSPARENT);
        }
    }

    public boolean isChecked() {
        return checked;
    }

    public double getWeightFactor() {
        return weightFactor;
    }

    public void setWeightFactor(double weightFactor) {
        this.weightFactor = weightFactor;
    }

}

