import model.Consumer;
import model.Producer;
import scalarproductcalculator.ScalarProductCalculator;

public class Main {

    public static void main(String[] args) {
        int size = 1000000;
        ScalarProductCalculator.initializeVectors(size);

        long threadedTime = computeScalarProductMultithreaded();
        System.out.println("Time taken (multithreaded): " + threadedTime + " milliseconds");
        System.out.println("Total sum (multithreaded): " + ScalarProductCalculator.totalSum);

        ScalarProductCalculator.totalSum = 0L;

        long sequentialTime = computeScalarProductSequentially();
        System.out.println("Time taken (sequential): " + sequentialTime + " milliseconds");
        System.out.println("Total sum (sequential): " + ScalarProductCalculator.totalSum);
    }

    private static long computeScalarProductMultithreaded() {
        long startTime = System.currentTimeMillis();

        Thread producerThread = new Thread(new Producer());
        Thread consumerThread = new Thread(new Consumer());

        producerThread.start();
        consumerThread.start();

        try {
            producerThread.join();
            consumerThread.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println("Main thread interrupted: " + e.getMessage());
        }

        long endTime = System.currentTimeMillis();
        return endTime - startTime;
    }

    private static long computeScalarProductSequentially() {
        long startTime = System.currentTimeMillis();
        ScalarProductCalculator.totalSum = ScalarProductCalculator.computeSumSequentially();
        long endTime = System.currentTimeMillis();
        return endTime - startTime;
    }
}
