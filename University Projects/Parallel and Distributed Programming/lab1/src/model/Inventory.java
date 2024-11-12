package model;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Inventory {
    private static final List<String> PRODUCT_NAMES = Arrays.asList(
            "Apples", "Bananas", "Oranges", "Grapes", "Strawberries",
            "Blueberries", "Watermelon", "Pineapple", "Peach", "Kiwi",
            "Mango", "Papaya", "Lemon", "Lime", "Cherry",
            "Avocado", "Coconut", "Blackberry", "Raspberry", "Pear",
            "Plum", "Apricot", "Fig", "Date", "Tangerine",
            "Cantaloupe", "Honeydew", "Pomegranate", "Nectarine", "Passionfruit",
            "Jackfruit", "Persimmon", "Dragonfruit", "Starfruit", "Longan",
            "Lychee", "Tamarind", "Guava", "Quince", "Olive",
            "Date", "Bittermelon", "Jujube", "Sapodilla", "Rambutan",
            "Durian", "Soursop", "Custard Apple", "Langsat", "Tamarillo"
    );
    private final ConcurrentHashMap<String, Product> products = new ConcurrentHashMap<>();
    private final List<Bill> salesRecords = Collections.synchronizedList(new ArrayList<>());
    private double totalMoney = 0.0;
    private final Lock moneyAndSalesLock = new ReentrantLock();

    public void randomlyInitializeInventory() {
        Random rand = new Random();
        int N = rand.nextInt(48) + 3;
        Set<String> selectedProducts = new HashSet<>();
        while (selectedProducts.size() < N) {
            int index = rand.nextInt(PRODUCT_NAMES.size());
            selectedProducts.add(PRODUCT_NAMES.get(index));
        }
        for (String productName : selectedProducts) {
            double price = 0.5 + (4.5 * rand.nextDouble());
            int quantity = rand.nextInt(999999 - 99999 + 1) + 99999;
            addProduct(productName, price, quantity);
        }
    }

    public void initializeInventoryForFirstTest() {
        for (int i = 0; i < 5; i++) {
            String productName = PRODUCT_NAMES.get(i);
            double price = 1.0;
            int quantity = 500000;
            addProduct(productName, price, quantity);
        }
    }

    public void initializeInventoryForSecondTest() {
        for (int i = 0; i < 10; i++) {
            String productName = PRODUCT_NAMES.get(i);
            double price = 2.0;
            int quantity = 1000000;
            addProduct(productName, price, quantity);
        }
    }

    public int getQuantity(String productName) {
        Product product = products.get(productName);
        return product != null ? product.getQuantity() : 0;
    }

    public Set<String> getProductNames() {
        return products.keySet();
    }

    public void addProduct(String name, double price, int quantity) {
        products.put(name, new Product(name, price, quantity));
    }

    public void sell(String productName, int quantitySold) {
        Product product = products.get(productName);
        if (product != null) {
            moneyAndSalesLock.lock();
            if (product.sell(quantitySold)) {
                double saleAmount = product.getPrice() * quantitySold;
                List<String> itemsSold = Collections.singletonList(productName + " x " + quantitySold);
                Bill bill = new Bill(itemsSold, saleAmount);
                totalMoney += saleAmount;
                salesRecords.add(bill);
                moneyAndSalesLock.unlock();
            } else {
                System.out.println("Product " + productName + " is out of stock or not enough quantity.");
                moneyAndSalesLock.unlock();
            }
        }
    }

    public boolean inventoryCheck() {
        try {
            double calculatedTotalMoney = 0.0;
            Map<String, Integer> soldQuantities = new HashMap<>();
            for (String productName : products.keySet()) {
                soldQuantities.put(productName, 0);
            }
            moneyAndSalesLock.lock();
            for (Bill bill : salesRecords) {
                for (String item : bill.getItems()) {
                    String[] parts = item.split(" x ");
                    String productName = parts[0];
                    int quantitySold = Integer.parseInt(parts[1]);
                    soldQuantities.put(productName, soldQuantities.get(productName) + quantitySold);
                }
                calculatedTotalMoney += bill.getTotal();
            }
            if (calculatedTotalMoney != totalMoney) {
                System.out.println("Error: Total money does not match the recorded bills.");
                return false;
            }
            for (Map.Entry<String, Product> entry : products.entrySet()) {
                String productName = entry.getKey();
                Product product = entry.getValue();
                int expectedRemainingQuantity = product.getInitialQuantity() - soldQuantities.get(productName);
                if (product.getQuantity() != expectedRemainingQuantity) {
                    System.out.println("Error: Inventory mismatch for " + productName);
                    return false;
                }
            }
            System.out.println("Inventory check passed. All sales are justified.");
            printRemainingProducts();
            return true;
        } finally {
            moneyAndSalesLock.unlock();
        }
    }

    private void printRemainingProducts() {
        System.out.println("Remaining Products in Inventory:");
        for (Map.Entry<String, Product> entry : products.entrySet()) {
            String productName = entry.getKey();
            int quantity = entry.getValue().getQuantity();
            System.out.println(productName + ": " + quantity);
        }
    }
}
