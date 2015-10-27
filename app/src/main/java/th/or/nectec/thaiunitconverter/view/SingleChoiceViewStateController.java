package th.or.nectec.thaiunitconverter.view;

import android.view.View;

import java.util.ArrayList;

/**
 * Created by User on 15/10/2558.
 */

//สร้าง customviewgroup สำหรับเลือกปริมานที่แปลงหน่วยที่กำหนดเองให้อยู่ใน linearlayout

public class SingleChoiceViewStateController {

    ArrayList<CustomCheckedView> customWeightViewArrayList = new ArrayList<>();
    int currentCheckItemPosition = -1; //ไม่มีค่า ค่าตัวแรกคือ 0

    OnCheckedChangeListener checkChangedListener;

    public void addView(CustomCheckedView view){
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setCheckedItem(view);
            }
        });
        customWeightViewArrayList.add(view);
    }

    public void setCheckedItem(View v){
        int size = customWeightViewArrayList.size();
        for(int i=0; i<size; i++){
            CustomCheckedView eachView = customWeightViewArrayList.get(i);
            if(eachView.equals(v)){
                currentCheckItemPosition = i;
                eachView.setChecked(true);
                checkChangedListener.onCheckChanged(v);
            }else{
                eachView.setChecked(false);
            }
        }
    }
    public CustomCheckedView getSelectedCustomWeightView(){
        if (currentCheckItemPosition == -1){
            return null;
        }
        return customWeightViewArrayList.get(currentCheckItemPosition);
    }

    public interface CustomCheckedView{
        void setOnClickListener(View.OnClickListener onClickListener);
        void setChecked(boolean isChecked);
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

    public void setOnCheckedChangeListener(OnCheckedChangeListener onCheckedChangeListener){
        checkChangedListener = onCheckedChangeListener;
    }

    public interface OnCheckedChangeListener {
        void onCheckChanged(View v);
    }
}
