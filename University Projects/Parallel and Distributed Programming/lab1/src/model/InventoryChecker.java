package model;

public class InventoryChecker implements Runnable {
    private final Inventory inventory;
    private final long intervalMillis;
    private volatile boolean running = true;

    public InventoryChecker(Inventory inventory, long intervalMillis) {
        this.inventory = inventory;
        this.intervalMillis = intervalMillis;
    }

    public void stop() {
        running = false;
    }

    @Override
    public void run() {
        while (running) {
            try {
                Thread.sleep(intervalMillis);
                System.out.println("Performing periodic inventory check...");
                if (!inventory.inventoryCheck()) {
                    System.out.println("Warning: Inventory check failed during execution.");
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println("Inventory checker thread interrupted.");
            }
        }
    }
}
