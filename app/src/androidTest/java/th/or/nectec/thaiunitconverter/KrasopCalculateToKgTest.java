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
import static android.support.test.espresso.matcher.ViewMatchers.withHint;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

/**
 * Created by N. Choatravee on 14/10/2558.
 */

public class KrasopCalculateToKgTest {
    public ActivityTestRule<CalculateActivity> mActivityTestRule = new ActivityTestRule<>(CalculateActivity.class);
    CalculateActivity mActivity;

    @Before
    public void setUp(){
        Intent intent = new Intent();
        intent.addCategory("unitconverter.intent.category.KRASOP");
        mActivity = mActivityTestRule.launchActivity(intent);
    }

    @Test
    public void กดปุ่มกระสอบ30โลแล้วป้อน4กระสอบต้องได้120กิโลกรัม(){
        onView(withText("30 กก."))
                .perform(click());
        onView(withId(R.id.rice_quantity))
                .perform(replaceText("4"));
        onView(withId(R.id.calculate_wet_button))
                .perform(click());
        onView(withId(R.id.answer_weight_wet_sumary))
                .check(matches(withText("120 กิโลกรัม")));
    }

    @Test
    public void กดปุ่มกระสอบ50โลแล้วป้อน4กระสอบต้องได้200กิโลกรัม(){
        onView(withText("50 กก."))
                .perform(click());
        onView(withId(R.id.rice_quantity))
                .perform(replaceText("4"));
        onView(withId(R.id.calculate_wet_button))
                .perform(click());
        onView(withId(R.id.answer_weight_wet_sumary))
                .check(matches(withText("200 กิโลกรัม")));
    }

    @Test
    public void กดปุ่มกระสอบ100โลแล้วป้อน4กระสอบต้องได้400กิโลกรัม(){
        onView(withText("100 กก."))
                .perform(click());
        onView(withId(R.id.rice_quantity))
                .perform(replaceText("4"));
        onView(withId(R.id.calculate_wet_button))
                .perform(click());
        onView(withId(R.id.answer_weight_wet_sumary))
                .check(matches(withText("400 กิโลกรัม")));
    }


}
