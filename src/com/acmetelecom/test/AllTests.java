package com.acmetelecom.test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
	BillingSystemTest.class,
	CallTest.class
})
public class AllTests {
}
