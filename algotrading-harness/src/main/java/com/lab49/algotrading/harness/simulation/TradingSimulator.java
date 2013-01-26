package com.lab49.algotrading.harness.simulation;

import com.lab49.algotrader.algos.TradingAlgorithm;
import com.lab49.algotrader.algos.TradingAlgorithmImpl;
import com.lab49.algotrader.models.price.Price;
import com.lab49.algotrader.models.price.PriceReset;
import com.lab49.algotrading.harness.io.SerializableBlockingQueueWrapper;
import com.lab49.algotrading.harness.io.SerializableQueueWrapper;

import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.BlockingQueue;

/**
 * Author: Jez
 * Project: lab49
 */
public class TradingSimulator {

    private final BlockingQueue<Price> sharedQueue;
    private final Scanner sc;
    private final SerializableQueueWrapper<Price> wrapper;
    private final PriceGenerator generator;

    public TradingSimulator(BlockingQueue<Price> sharedQueue) throws InterruptedException, IOException, ClassNotFoundException {

        this.sharedQueue = sharedQueue;
        this.wrapper = new SerializableBlockingQueueWrapper<Price>(this.sharedQueue);
        this.sc = new Scanner(System.in);
        this.generator = new PriceGenerator(wrapper, sharedQueue);

        init();

        generateModelAnswer();
    }

    private void init() throws InterruptedException, IOException, ClassNotFoundException {
        System.out.println("Add single price point: <PRODUCT_NAME> <PRICE>");
        System.out.println("Generate random prices: g <NUMBER_OF_PRICES>");
        System.out.println("Replay previous run: r <NUMBER_OF_PRICES>");
        System.err.println("Press * to exit");

        while(sc.hasNextLine()) {
            String line = sc.nextLine();

            if(line.split(" ").length == 1 && line.split(" ")[0].equals("*")) System.exit(0); // kill the app

            else if(line.split(" ").length == 1 && line.split(" ")[0].equals("r")) {
                wrapper.rerun();
            }
            else if(line.split(" ").length != 2) {
                System.out.println("Incorrect syntax, please try again");
            }  else {
                if(line.split(" ")[0].equals("g")) {
                    sharedQueue.add(new PriceReset());
                    generator.generate(Integer.parseInt(line.split(" ")[1]));
                } else {
                    sharedQueue.add(new Price(line.split(" ")[0],Double.valueOf(line.split(" ")[1])));
                }
            }
        }
    }

    /**
     * Prove the trade generation creates the expected result as defined in the test
     */
    private void generateModelAnswer() {
        sharedQueue.add(new Price("BP",   7.61));
        sharedQueue.add(new Price("RDSA", 2201.00));
        sharedQueue.add(new Price("RDSA", 2209.00));
        sharedQueue.add(new Price("BP",   7.66));
        sharedQueue.add(new Price("BP",   7.64));
        sharedQueue.add(new Price("BP",   7.67));       // should see this
    }
}
