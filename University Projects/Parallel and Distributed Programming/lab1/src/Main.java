import model.Inventory;
import model.InventoryChecker;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Inventory randomInventory = new Inventory();
        randomInventory.randomlyInitializeInventory();
        
        Inventory testInventory1 = new Inventory();
        testInventory1.initializeInventoryForFirstTest();

        Inventory testInventory2 = new Inventory();
        testInventory2.initializeInventoryForSecondTest();

        int numberOfThreads = 6;

//        executeInventorySimulation(randomInventory, numberOfThreads);
        executeInventorySimulation(testInventory1, numberOfThreads);
//        executeInventorySimulation(testInventory2, numberOfThreads);
    }

    private static void executeInventorySimulation(Inventory inventory, int numberOfThreads) {
        InventoryChecker inventoryChecker = new InventoryChecker(inventory, 300);
        Thread checkerThread = new Thread(inventoryChecker);
        checkerThread.start();

        long startTime = System.currentTimeMillis();

        List<Thread> salesThreads = createSalesThreads(inventory, numberOfThreads);

        joinThreads(salesThreads);

        inventoryChecker.stop();
        try {
            checkerThread.join();
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }

        inventory.inventoryCheck();
        long endTime = System.currentTimeMillis();
        long duration = endTime - startTime;
        System.out.println("Total execution time: " + (duration / 1000.0) + " seconds");
    }

    private static List<Thread> createSalesThreads(Inventory inventory, int numberOfThreads) {
        List<Thread> threads = new ArrayList<>();
        for (int i = 0; i < numberOfThreads; i++) {
            Runnable salesTask = createSalesTask(inventory);
            Thread salesThread = new Thread(salesTask);
            threads.add(salesThread);
            salesThread.start();
        }
        return threads;
    }

    private static void joinThreads(List<Thread> threads) {
        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private static Runnable createSalesTask(Inventory inventory) {
        return () -> {
            boolean inventoryEmpty = false;
            while (!inventoryEmpty) {
                inventoryEmpty = true;
                for (String productName : inventory.getProductNames()) {
                    if (inventory.getQuantity(productName) > 0) {
                        int quantitySold = 1;
                        inventory.sell(productName, quantitySold);
                        inventoryEmpty = false;
                    }
                }
            }
        };
    }
}
