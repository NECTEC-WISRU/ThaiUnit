package th.or.nectec.thaiunitconverter;

import android.content.Intent;
import android.support.test.rule.ActivityTestRule;

import org.junit.Before;
import org.junit.Test;

import th.or.nectec.thaiunitconverter.activity.Main;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

/**
 * Created by N. Choatravee on 14/10/2558.
 */

public class MainMenuTest {
    public ActivityTestRule<Main> mActivityTestRule = new ActivityTestRule<>(Main.class);
    Main mActivity;

    @Before
    public void setUp(){
        Intent intent = new Intent();
        mActivity = mActivityTestRule.launchActivity(intent);
    }

    @Test
    public void เปิดหน้าเมนูหลักแล้วกดปุ่มกระสอบต้องเปิดหน้าคำนวณกระสอบ(){
        onView(withId(R.id.krasopButton))
                .perform(click());
        onView(withText(R.string.krasop_to_kg))
                .check(matches(isDisplayed()));
    }


}
