package scalarproductcalculator;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class ScalarProductCalculator {
    public static long[] vectorA;
    public static long[] vectorB;
    public static final int QUEUE_SIZE = 100;
    public static final BlockingQueue<Long> productQueue = new ArrayBlockingQueue<>(QUEUE_SIZE);
    public static long totalSum = 0L;

    public static void initializeVectors(int size) {
        vectorA = new long[size];
        vectorB = new long[size];
        for (int i = 0; i < size; i++) {
            vectorA[i] = i + 1;
            vectorB[i] = i + 1;
        }
    }

    public static long computeSumSequentially() {
        long sum = 0L;
        for (int i = 0; i < vectorA.length; i++) {
            sum += vectorA[i] * vectorB[i];
        }
        return sum;
    }
}
