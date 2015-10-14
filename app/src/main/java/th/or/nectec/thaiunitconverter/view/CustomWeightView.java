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

import th.or.nectec.thaiunitconverter.R;

public class CustomWeightView extends RelativeLayout {

    private TextView weightText;
    private ImageView weightIcon;

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

    public void setCustomWeightInfo(String weightTextStr, Drawable weightIconDrawable){
        weightText.setText(weightTextStr);
        weightIcon.setImageDrawable(weightIconDrawable);
    }

    public void setCustomWeightInfoByResource(@StringRes int weightTextId, @DrawableRes int weightIconId){
        String weightTextStr = getResources().getString(weightTextId);
        Drawable weightIconDrawable = getResources().getDrawable(weightIconId);

        setCustomWeightInfo(weightTextStr, weightIconDrawable);
    }
    public void setCustomWeightText(String weightTextStr){
        weightText.setText(weightTextStr);
    }

    public void setCustomWeightText(@StringRes int weightTextId){
        weightText.setText(weightTextId);
    }
    public void setCustomWeightDrawable(Drawable weightIconDrawable){
        weightIcon.setImageDrawable(weightIconDrawable);
    }
    public void setCustomWeightDrawable(@DrawableRes int weightIconId){
        weightIcon.setImageResource(weightIconId);
    }
}

