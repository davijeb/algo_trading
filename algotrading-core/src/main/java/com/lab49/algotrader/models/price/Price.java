package com.lab49.algotrader.models.price;

import com.lab49.algotrader.utils.annotations.Immutable;

import java.io.Serializable;

/**
 * Immutable price data used to store
 * product and price information.
 *
 * @author Jeremy Davies [jerdavies@gmail.com]
 */
@Immutable
public class Price implements Serializable, Cloneable, Resettable {

    private String productName;
    private final double price;

    public Price(final String productName, final double price) {
        this.productName = productName;
        this.price = price;
    }

    public String getProductName() {
        return productName;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Price price1 = (Price) o;

        if (Double.compare(price1.price, price) != 0) return false;
        if (productName != null ? !productName.equals(price1.productName) : price1.productName != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = productName != null ? productName.hashCode() : 0;
        temp = price != +0.0d ? Double.doubleToLongBits(price) : 0L;
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return productName + "," + price;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    /**
     * A standard price will not trigger a queue reset event
     * @return false
     */
    @Override
    public boolean reset() {
        return false;
    }

}
