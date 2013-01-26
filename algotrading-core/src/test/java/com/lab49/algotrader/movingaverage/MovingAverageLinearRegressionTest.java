package com.lab49.algotrader.movingaverage;

import com.lab49.algotrader.calcs.LinearRegressionCalculator;

// check the linear regression calculator works as expected
public class MovingAverageLinearRegressionTest extends AbstractMovingAverageTest {

    public MovingAverageLinearRegressionTest() {
        super(new LinearRegressionCalculator());
    }
}
