package model;

import scalarproductcalculator.ScalarProductCalculator;

public class Consumer implements Runnable {
    @Override
    public void run() {
        try {
            while (true) {
                long product = ScalarProductCalculator.productQueue.take();
                if (product == -1) break;
                ScalarProductCalculator.totalSum += product;
                System.out.println("Consumer added product to totalSum = " + ScalarProductCalculator.totalSum);
            }
            System.out.println("Final scalar product: " + ScalarProductCalculator.totalSum);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println("Consumer interrupted: " + e.getMessage());
        }
    }
}
