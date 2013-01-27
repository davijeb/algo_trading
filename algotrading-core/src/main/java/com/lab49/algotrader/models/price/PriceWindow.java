package com.lab49.algotrader.models.price;

import java.util.List;

/**
 * Interface for the fixed size pricing window
 * @author Jeremy Davies [jerdavies@gmail.com]
 * @version 1.0
 * @updated 27-Jan-2013 12:03:22
 */
public interface PriceWindow {

    List<Price> getWindow();
}
