package th.or.nectec.thaiunitconverter.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

import java.util.ArrayList;

/**
 * Created by User on 15/10/2558.
 */

//สร้าง customviewgroup สำหรับเลือกปริมานที่แปลงหน่วยที่กำหนดเองให้อยู่ใน linearlayout

public class CustomWeightSingleChoiceViewGroup extends LinearLayout {

    ArrayList<CustomWeightView> customWeightViewArrayList = new ArrayList<>();
    int currentCheckItemPosition = -1; //ไม่มีค่า ค่าตัวแรกคือ 0

    public CustomWeightSingleChoiceViewGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.setOrientation(HORIZONTAL);// เพิ่มในแนวนอน เรียงๆกันไป
    }

    public void addCustomWeightView(CustomWeightView view){
        view.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                setCheckedItem(view);
            }
        });
        addView(view);
        customWeightViewArrayList.add(view);
    }

    private  void setCheckedItem(View v){
        int size = customWeightViewArrayList.size();
        for(int i=0; i<size; i++){
            CustomWeightView eachView = customWeightViewArrayList.get(i);
            if(eachView.equals(v)){
                currentCheckItemPosition = i;
                eachView.setChecked(true);
            }else{
                eachView.setChecked(false);
            }
        }
    }
}
