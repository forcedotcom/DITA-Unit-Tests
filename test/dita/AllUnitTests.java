package dita;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * Individual tests must be added in order to run all tests on a per-file basis
 * @author sortiz
 *
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
	SimpleXPathTest.class
})
public class AllUnitTests {
}