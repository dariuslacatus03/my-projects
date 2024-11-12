package model;

import scalarproductcalculator.ScalarProductCalculator;

public class Producer implements Runnable {
    @Override
    public void run() {
        try {
            for (int index = 0; index < ScalarProductCalculator.vectorA.length; index++) {
                long product = ScalarProductCalculator.vectorA[index] * ScalarProductCalculator.vectorB[index];
                System.out.println("Producer computed product[" + index + "] = " + product);
                ScalarProductCalculator.productQueue.put(product);
            }
            ScalarProductCalculator.productQueue.put(-1L);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println("Producer interrupted: " + e.getMessage());
        }
    }
}
