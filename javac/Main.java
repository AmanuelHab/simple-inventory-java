package javac;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Inventory inventory = new Inventory();
        Scanner console = new Scanner(System.in);
        
        while(true){
        System.out.println("=== Inventory Managment System ===");
        System.out.println("1. Add item\n2. Search item\n3. Edit item\n4. Display all\n5. Exit");
        int choice = console.nextInt();
        console.nextLine();
            switch (choice) {
                case 1:
                    addItem(inventory, console);
                    break;
                case 2:
                    searchItem(inventory, console);
                    break;
                case 3:
                    editItem(inventory, console);
                    break;
                case 4:
                    System.out.println("== Display All ==");
                    inventory.displayAllItems();
                    break;
                case 5:
                    console.close();
                    System.out.println("Software Exits!");
                    return;
                default:
                    System.out.println("Invalid choice!");
                    break;
            }
        }
    }
    public static void addItem(Inventory inventory, Scanner console){
        System.out.println("== Add Item ==");
        System.out.print("Enter name: ");
        String name = console.nextLine();

        int index = inventory.findItemByName(name);
        // Update the item if it already exists
        if(!inventory.isEmpty() && index != -1){
            System.out.print("Enter quantity to be added: ");
            int quantity = console.nextInt();
            System.out.print("Enter cost: ");
            float cost = console.nextFloat();
            inventory.updateItem(index, quantity, cost);
            return;
        }

        System.out.print("Enter quantity: ");
        int quantity = console.nextInt();
        System.out.print("Enter cost: ");
        float cost = console.nextFloat();
        console.nextLine();

        Item.PricingStrategy strategy = getPricingStrategy(console); 
        float pricingValue = getPricingValue(console, strategy, cost);

        System.out.print("Enter details: ");
        String details = console.nextLine();
        Item item = new Item(name, quantity, cost, pricingValue, strategy, details);
        inventory.addItem(item);
        System.out.println("Item added successfully!");
    }
    public static Item.PricingStrategy getPricingStrategy(Scanner console){
        System.out.println("\nChoose pricing strategy:");
        System.out.println("1. Profit Coefficient (price = cost × coefficient)");
        System.out.println("2. Profit Margin (price = cost + fixed profit)");
        System.out.println("3. Direct Price (set price directly)");
        System.out.print("Choose option (1-3): ");
        int choice = console.nextInt();
        switch(choice){
            case 1: return Item.PricingStrategy.PROFIT_COEFFICIENT;
            case 2: return Item.PricingStrategy.PROFIT_MARGIN;
            case 3: return Item.PricingStrategy.DIRECT_PRICE;
            default:
                System.out.println("Invalid choice, using profit coefficient");
                return Item.PricingStrategy.PROFIT_COEFFICIENT;
        }
    }
    public static float getPricingValue(Scanner console, Item.PricingStrategy strategy, float cost){
        switch (strategy) {
            case PROFIT_COEFFICIENT: 
                System.out.print("Enter profit coefficient (E.g 1.5 for 50% profit): ");
                return console.nextFloat();
            case PROFIT_MARGIN:
                System.out.print("Enter profit margin per item: ");
                return console.nextFloat();
            case DIRECT_PRICE:
                System.out.print("Enter the price: ");
                float price = console.nextFloat();
                if(price < cost){
                    System.out.println("Caution: price is below cost!");
                    System.out.println("Do you want to continue with this price(Y/N)? ");
                    char choice = console.next().charAt(0);
                    if(choice == 'Y' || choice == 'y'){
                        return price;
                    }else if(choice == 'N' || choice == 'n'){
                        float newPrice = getPricingValue(console, strategy, cost);
                        return newPrice;
                    }else{
                        System.out.println("The price is set to " + price);
                    }
                }
                return price;
            default:
                return 1.0f;
        }
    }
    public static void searchItem(Inventory inventory, Scanner console){
        System.out.println("== Search Item ==");
        System.out.print("Enter name: ");
        String search = console.nextLine();
        int i = inventory.findItemByName(search);
        if(i == -1){
            System.out.println("Item not Found!");
            return;
        } 
        inventory.displayItem(i);
    }
    public static void editItem(Inventory inventory, Scanner console){
        System.out.println("== Edit Item ==");
        System.out.print("Enter name: ");
        String edit = console.nextLine();
        int j = inventory.findItemByName(edit);
        if(j == -1){
            System.out.println("Item not Found!");
            return;
        }
        inventory.displayItem(j);
        System.out.println("== Edit Menu ==");
        System.out.println("1.Edit name\n2. Edit details\n3. Edit pricing\n4. Return");
        int editChoice = console.nextInt();
        switch(editChoice){
            case 1:
                System.out.println("Enter name: ");
                String editedName = console.next();
                inventory.updateItemName(j, editedName);
                System.out.println("Name changed.");
                break;
            case 2:
                System.out.println("Enter details: ");
                String newDetails = console.next();
                inventory.updateItemDetails(j, newDetails);
                System.out.println("Updated details.");
                break;
            case 3: 
                editItemPricing(inventory, console, j);
                break;
            case 4:
                break;
            default:
                System.out.println("Invalid choice!");
                break;
        }
    }
    public static void editItemPricing(Inventory inventory, Scanner console, int index){
        System.out.println("== Edit Pricing ==");
        Item.PricingStrategy newStrategy = getPricingStrategy(console);
        float newPricingValue = getPricingValue(console, newStrategy, index);

        inventory.updateItemPricing(index, newStrategy, newPricingValue);
        System.out.println("Pricing updated!");
    }
}
