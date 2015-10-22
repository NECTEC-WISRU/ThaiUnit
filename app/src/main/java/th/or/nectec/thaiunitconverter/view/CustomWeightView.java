package th.or.nectec.thaiunitconverter.view;

/**
 * Created by User on 14/10/2558.
 */

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.annotation.DrawableRes;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.text.DecimalFormat;

import th.or.nectec.thaiunitconverter.R;

public class CustomWeightView extends RelativeLayout implements SingleChoiceViewStateController.CustomCheckedView{

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

    public void setCustomWeightInfo(Drawable weightIconDrawable, double weightFactor) {
        this.weightFactor = weightFactor;
        String pattern = "###,###.##";
        DecimalFormat df = new DecimalFormat(pattern);
        String output = df.format(weightFactor);

        weightText.setText(output + " กก.");
        weightIcon.setImageDrawable(weightIconDrawable);
    }

    public void setCustomWeightInfoByResource(@DrawableRes int weightIconId, double weightFactor) {
        Drawable weightIconDrawable = getResources().getDrawable(weightIconId);
        setCustomWeightInfo(weightIconDrawable, weightFactor);
    }

    @Override
    public void setChecked(boolean checked) {
        this.checked = checked;
        if (checked) {
            this.setBackground(getResources().getDrawable(R.drawable.circle_background));
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

