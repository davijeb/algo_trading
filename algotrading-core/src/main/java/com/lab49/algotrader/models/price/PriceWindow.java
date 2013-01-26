package com.lab49.algotrader.models.price;

import java.util.List;

/**
 * Interface for the fixed size pricing window
 *
 * @author Jeremy Davies [jerdavies@gmail.com]
 */
public interface PriceWindow {

    List<Price> getWindow();
}
