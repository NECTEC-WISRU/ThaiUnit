package th.or.nectec.thaiunitconverter;

import android.content.Intent;
import android.support.test.rule.ActivityTestRule;

import org.junit.Before;
import org.junit.Test;

import th.or.nectec.thaiunitconverter.activity.CalculateActivity;
import th.or.nectec.thaiunitconverter.activity.Main;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withChild;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

/**
 * Created by N. Choatravee on 14/10/2558.
 */

public class CustomUnitFactorTest {
    public ActivityTestRule<CalculateActivity> mActivityTestRule = new ActivityTestRule<>(CalculateActivity.class);
    CalculateActivity mActivity;

    public static String CUSTOM_UNIT_FACTOR = "250";

    @Before
    public void setUp(){
        Intent intent = new Intent();
        intent.addCategory("unitconverter.intent.category.KRASOP");
        mActivity = mActivityTestRule.launchActivity(intent);
    }

    @Test
    public void ทำการเพิ่มปริมาณที่กำหนดเองแล้วต้องแสดงปริมาณที่เพิ่มเข้ามา() {
        onView(withId(R.id.more_option))
                .perform(click());
        onView(withId(R.id.custom_unit_factor))
                .perform(replaceText(CUSTOM_UNIT_FACTOR));
        onView(withId(android.R.id.button1))
                .perform(click());
        onView(withText(CUSTOM_UNIT_FACTOR + " กก."))
                .check(matches(isDisplayed()));
    }

    @Test
    public void ทำการเพิ่มปริมาณที่กำหนดเองและทำการคำนวณด้วยหน่วยที่เพิ่มเข้ามา() {
        onView(withId(R.id.more_option))
                .perform(click());
        onView(withId(R.id.custom_unit_factor))
                .perform(replaceText(CUSTOM_UNIT_FACTOR));
        onView(withId(android.R.id.button1))
                .perform(click());
        onView(withText(CUSTOM_UNIT_FACTOR + " กก."))
                .perform(click());
        onView(withId(R.id.rice_quantity))
                .perform(replaceText("4"));
        onView(withId(R.id.calculate_wet_button))
                .perform(click());
        onView(withId(R.id.answer_weight_wet_sumary))
                .check(matches(withText("1,000 กิโลกรัม")));
    }


}
