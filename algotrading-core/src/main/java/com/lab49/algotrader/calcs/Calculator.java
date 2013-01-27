package com.lab49.algotrader.calcs;

import com.lab49.algotrader.models.price.Price;

import java.util.List;

/**
 * TODO: finish
 * @author Jeremy Davies [jerdavies@gmail.com]
 * @version 1.0
 * @updated 27-Jan-2013 12:03:20
 */
public interface Calculator {
    Price calc(List<Price> input);
}
