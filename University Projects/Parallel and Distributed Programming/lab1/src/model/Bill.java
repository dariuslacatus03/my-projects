package model;

import java.util.List;

public class Bill {
    private final List<String> items;
    private final double total;

    public Bill(List<String> items, double total) {
        this.items = items;
        this.total = total;
    }

    public List<String> getItems() {
        return items;
    }

    public double getTotal() {
        return total;
    }

    @Override
    public String toString() {
        return "Bill{" +
                "items=" + items +
                ", total=" + total +
                '}';
    }
}
