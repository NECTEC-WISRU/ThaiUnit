package th.or.nectec.thaiunitconverter.suite;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import th.or.nectec.thaiunitconverter.KrasopCalculateToKgTest;
import th.or.nectec.thaiunitconverter.MainMenuTest;

/**
 * Created by N.Choatarvee on 3/9/2015.
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
        MainMenuTest.class,
        KrasopCalculateToKgTest.class,
})
public class KrasobSuite {
}
