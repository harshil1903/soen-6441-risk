package com.risk.strategy;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

/**
 * Test Suite for Strategy
 *
 * @author Harshil
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({AggressiveStrategyTest.class, BenevolentStrategyTest.class, CheaterStrategyTest.class, RandomStrategyTest.class})
public class StrategyTestSuite {
}