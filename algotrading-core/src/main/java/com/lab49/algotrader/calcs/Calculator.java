package com.lab49.algotrader.calcs;

import com.lab49.algotrader.models.price.Price;

import java.util.List;

public interface Calculator {
    Price calc(List<Price> input);
}
