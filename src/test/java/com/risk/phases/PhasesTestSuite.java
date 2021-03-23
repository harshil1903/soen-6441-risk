package com.risk.phases;

import com.risk.orders.*;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({PreMapLoadTest.class, GameStartupTest.class})
public class PhasesTestSuite {
}