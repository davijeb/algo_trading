package com.lab49.algotrader.models.trade;


import com.lab49.algotrader.TradingProperties;
import com.lab49.algotrader.models.price.Price;
import com.lab49.algotrader.utils.annotations.Immutable;
import com.lab49.algotrader.utils.enums.DirectionEnum;

/**
 * Immutable Trade object used to define trading behaviour
 * for a given Price.
 *
 * @author Jeremy Davies [jerdavies@gmail.com]
 */
@Immutable
public class Trade {

    // Trading price
    private final Price price;

    // Trading direction (BUY/SELL)
    private final DirectionEnum direction;

    // Trading quantity (default is 1000)
    private final int quantity = TradingProperties.INSTANCE.getInteger("trading.quantity");

    /**
     * Private constructor. Creation managed by static helper method.
     * @param price the trade price
     * @param direction the trade direction (BUY/SELL)
     */
    private Trade(final Price price,
                  final DirectionEnum direction) {
        this.price = price;
        this.direction = direction;
	}

    /**
     * Static Trade creation method
     * @param price the trade price information
     * @param direction the trade direction (BUY/SELL)
     * @return a new trade
     */
    public static Trade create(final Price price,
                               final DirectionEnum direction) {
        return new Trade(price,direction);
    }


    @Override
    public String toString() {
        return price.getProductName() + "," + direction + "," + price.getPrice() + "," + quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Trade trade = (Trade) o;

        if (quantity != trade.quantity) return false;
        if (direction != trade.direction) return false;
        if (price != null ? !price.equals(trade.price) : trade.price != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = price != null ? price.hashCode() : 0;
        result = 31 * result + (direction != null ? direction.hashCode() : 0);
        result = 31 * result + quantity;
        return result;
    }
}
