package model;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Product {
    private final String name;
    private final double price;
    private final int initialQuantity;
    private int quantity;

    public Product(String name, double price, int quantity) {
        this.name = name;
        this.price = price;
        this.initialQuantity = quantity;
        this.quantity = quantity;
    }


    public double getPrice() {
        return price;
    }

    public synchronized int getQuantity() {
        return quantity;
    }

    public int getInitialQuantity() {
        return initialQuantity;
    }

    public synchronized boolean sell(int quantitySold) {
        if (quantity >= quantitySold) {
            quantity -= quantitySold;
            return true;
        }
        return false;
    }
}
