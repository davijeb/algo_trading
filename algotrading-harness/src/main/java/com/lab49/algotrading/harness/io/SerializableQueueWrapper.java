package com.lab49.algotrading.harness.io;

import com.lab49.algotrader.models.Price;

import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.BlockingQueue;

public interface SerializableQueueWrapper<Price> {

    <Price> List<Price> read() throws IOException, ClassNotFoundException;

    void write() throws IOException;
    void add(Price p);
    void clear();
    void rerun() throws IOException, ClassNotFoundException;
}
