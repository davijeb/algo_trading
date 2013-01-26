package com.lab49.algotrading.harness.io;

import com.lab49.algotrader.models.PriceReset;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.BlockingQueue;

/**
 * Multi-threaded applications are difficult to test so the SerializableBlockingQueueWrapper
 * is aimed at allowing the user to re-run each test. Once a simulation size and run has been created
 * this class then serializes a copy of the queue, allowing the test to be re-run.
 *
 * @param <Price>
 */
public class SerializableBlockingQueueWrapper<Price> implements SerializableQueueWrapper<Price> {

    // The serialized filename
    private static final String SERIALIZED_NAME = "pricedata.ser";

    // The queue used as the price sink
    private final BlockingQueue<Price> sharedQueue;

    // The copy of the shared queue
    private final List<Price> queue;

    /**
     * Constructor given the shared blocking queue
     * @param sharedQueue the the main sink for Price data
     */
    public SerializableBlockingQueueWrapper(BlockingQueue<Price> sharedQueue) {
        this.sharedQueue = sharedQueue;
        this.queue = new LinkedList<Price>();
    }

    /**
     * Serialize and write the queue copy to the file system
     * @throws IOException
     */
    @Override
    public synchronized void write() throws IOException {
        FileOutputStream fos = new FileOutputStream(SERIALIZED_NAME);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(queue);
        oos.flush();
        oos.close();
    }

    /**
     * Read the serialized queue from the file system
     * @return a list of price points
     * @throws IOException
     * @throws ClassNotFoundException
     */
    @Override
    public synchronized List<Price> read() throws IOException, ClassNotFoundException {
        FileInputStream fis = new FileInputStream(SERIALIZED_NAME);
        ObjectInputStream ois = new ObjectInputStream(fis);
        List<Price> ted = (List<Price>) ois.readObject();
        ois.close();
        return ted;
    }

    /**
     * Add a price to the queue copy
     * @param p the price
     */
    @Override
    public void add(Price p) {
        queue.add(p);
    }

    /**
     * Re-run the current serialized price points
     * @throws IOException
     * @throws ClassNotFoundException
     */
    @Override
    public void rerun() throws IOException, ClassNotFoundException {

        sharedQueue.clear();
        List<Price> q = read();
        sharedQueue.add((Price) new PriceReset());
        sharedQueue.addAll(q);
    }

    @Override
    public void clear() {
        queue.clear();
    }
}
