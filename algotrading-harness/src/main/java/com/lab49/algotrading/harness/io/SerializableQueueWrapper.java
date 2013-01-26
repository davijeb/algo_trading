package com.lab49.algotrading.harness.io;

import java.io.IOException;
import java.util.List;

public interface SerializableQueueWrapper<Price> {

    <Price> List<Price> read() throws IOException, ClassNotFoundException;

    void write() throws IOException;
    void add(Price p);
    void clear();
    void rerun() throws IOException, ClassNotFoundException;
}
