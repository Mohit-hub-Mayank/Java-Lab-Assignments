import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

// 1. Product Interface
interface Product {
    void displayDetails();
}

// 2. LegacyItem Class (The Adaptee)
class LegacyItem {
    private int itemId;
    private String description;

    public LegacyItem(int itemId, String description) {
        this.itemId = itemId;
        this.description = description;
    }

    public void print() {
        System.out.println("Legacy Item [" + itemId + "]: " + description);
    }
}

// 3. ProductAdapter Class (The Adapter)
class ProductAdapter implements Product {
    private LegacyItem legacyItem;

    public ProductAdapter(LegacyItem legacyItem) {
        this.legacyItem = legacyItem;
    }

    @Override
    public void displayDetails() {
        // Adapting the legacy print() method to the Product interface
        legacyItem.print();
    }
}

// 4. NewProduct Class
class NewProduct implements Product {
    private String name;

    public NewProduct(String name) {
        this.name = name;
    }

    @Override
    public void displayDetails() {
        System.out.println("New Product: " + name);
    }
}

// 5. InventoryManager Class (The Singleton)
class InventoryManager {
    private static InventoryManager instance;
    private List<Product> products;

    // Private constructor for Singleton
    private InventoryManager() {
        products = new ArrayList<>();
    }

    // Static method to get the single instance
    public static InventoryManager getInstance() {
        if (instance == null) {
            instance = new InventoryManager();
        }
        return instance;
    }

    public void addProduct(Product product) {
        products.add(product);
    }

    // Returns an Iterator (Iterator Pattern)
    public Iterator<Product> returnInventory() {
        return products.iterator();
    }
}

// 6. Main Class
public class InventorySystem {
    public static void main(String[] args) {
        // Create the single instance of InventoryManager
        InventoryManager manager = InventoryManager.getInstance();

        // Adding a NewProduct
        manager.addProduct(new NewProduct("MacBook Pro M3"));
        manager.addProduct(new NewProduct("Sony WH-1000XM5"));

        // Adding a LegacyItem via the ProductAdapter
        LegacyItem oldItem1 = new LegacyItem(101, "Vintage Typewriter");
        LegacyItem oldItem2 = new LegacyItem(102, "Analog Film Camera");
        
        manager.addProduct(new ProductAdapter(oldItem1));
        manager.addProduct(new ProductAdapter(oldItem2));

        // Iterate through the inventory
        System.out.println("--- Current Inventory Status ---");
        Iterator<Product> inventoryIterator = manager.returnInventory();

        while (inventoryIterator.hasNext()) {
            Product product = inventoryIterator.next();
            product.displayDetails();
        }
    }
}
