package th.or.nectec.thaiunitconverter.view;

import android.content.Context;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
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

    public   void setCheckedItem(View v){
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
    public CustomWeightView getSelectedCustomWeightView(){
        if (currentCheckItemPosition == -1){
            return null;
        }
        return customWeightViewArrayList.get(currentCheckItemPosition);
    }

    //TODO savestate ของ view
//    @Override
//    public Parcelable onSaveInstanceState() {
//        super.onSaveInstanceState();
//        Bundle bundle = new Bundle();
//        // Save current View's state here
//        bundle.putInt("checkedPosition", currentCheckItemPosition);
//        bundle.putParcelableArrayList("customWeightViewArray", customWeightViewArrayList);
//        return bundle;
//    }
//
//    @Override
//    public void onRestoreInstanceState(Parcelable state) {
//        super.onRestoreInstanceState(state);
//        // Restore View's state here
//    }

}
