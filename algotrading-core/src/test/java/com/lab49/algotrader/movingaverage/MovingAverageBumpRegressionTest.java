package com.lab49.algotrader.movingaverage;

import com.lab49.algotrader.calcs.BumpCalculator;

// check the bump calculator works as expected
public class MovingAverageBumpRegressionTest extends AbstractMovingAverageTest {

    public MovingAverageBumpRegressionTest() {
        super(new BumpCalculator());
    }
}
