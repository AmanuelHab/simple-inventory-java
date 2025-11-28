package com.Amanuel.inventory;
import com.Amanuel.inventory.models.Item;
import com.Amanuel.inventory.models.Package;
import com.Amanuel.inventory.services.Inventory;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Inventory inventory = new Inventory();
        Scanner console = new Scanner(System.in);
        
        while(true){
        System.out.println("==== Inventory Managment System ====");
        System.out.println("1. Add item\n2. Add package\n3. Display all items\n4. Display all packages\n5. Exit");
        int choice = console.nextInt();
        console.nextLine();
            switch (choice) {
                case 1:
                    addItem(inventory, console);
                    break;
                case 2:
                    addPackage(inventory, console);
                    break;
                case 3:
                    inventory.displayAllItems();
                    break;
                case 4:
                    inventory.displayAllPackages();
                    break;
                case 5:
                    System.out.println("Software Exits!");
                    console.nextLine();
                    console.close();
                    return;
                default:
                    System.out.println("Invalid choice!");
                    break;
            }
        }
    }
    public static void addItem(Inventory inventory, Scanner console){
        System.out.println("==== Add Item ====");
        System.out.print("Enter name: ");
        String name = console.nextLine();
        if(inventory.getItemId(name) != -1){
            System.out.println("Item already exists.");
            return;
        }
        inventory.addItem(name);
        System.out.println("Item added successfully!");
    }
    public static void addPackage(Inventory inventory, Scanner console){
        System.out.println("Enter item name: ");
        String name = console.nextLine();
        int itemId = inventory.getItemId(name);
        if(itemId == -1){
            System.out.println("Item doesn't exist!");
            return;
    }
        System.out.print("Enter quantity: ");
        int quantity = console.nextInt();
        System.out.print("Enter cost: ");
        float cost = console.nextFloat();
        /* 
        // Offer choice to accept/decline default pricing strategy
        System.out.println("Shall the package have the same pricing strategy as the first package? (Y/n): ");
        char choice = console.next().charAt(0);

            inventory.addPackage(itemId, null);
            return;
        */
        Package.PricingStrategy strategy = getPricingStrategy(console); 
        float pricingValue = getPricingValue(console, strategy, cost);
        console.nextLine();

        Package pac = new Package(itemId, quantity, cost, pricingValue, strategy);
        inventory.addPackage(itemId, pac);
        System.out.println("Package added successfully!");
    }
    public static Package.PricingStrategy getPricingStrategy(Scanner console){
        System.out.println("\nChoose pricing strategy:");
        System.out.println("1. Profit Coefficient (price = cost x coefficient)");
        System.out.println("2. Profit Margin (price = cost + fixed profit)");
        System.out.println("3. Direct Price (set price directly)");
        System.out.print("Choose option (1-3): ");
        int choice = console.nextInt();
        switch(choice){
            case 1: return Package.PricingStrategy.PROFIT_COEFFICIENT;
            case 2: return Package.PricingStrategy.PROFIT_MARGIN;
            case 3: return Package.PricingStrategy.DIRECT_PRICE;
            default:
                System.out.println("Invalid choice, using profit coefficient");
                return Package.PricingStrategy.PROFIT_COEFFICIENT;
        }
    }
    public static float getPricingValue(Scanner console, Package.PricingStrategy strategy, float cost){
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
    }/* 
    public static void searchItem(Inventory inventory, Scanner console){
        System.out.println("=== Search Item ===");
        System.out.print("Enter name: ");
        String search = console.nextLine();
        Item item = inventory.searchItemByName(search);
        if(item == null){
            System.out.println("Item not Found!");
            return;
        } 
        inventory.displayItem(item);
    }
    public static void editItem(Inventory inventory, Scanner console){
        System.out.println("=== Edit Item ===");
        System.out.print("Enter name: ");
        String edit = console.nextLine();
        int j = inventory.findItemByName(edit);
        if(j == -1){
            System.out.println("Item not Found!");
            return;
        }
        inventory.displayItem(j);
        System.out.println("=== Edit Menu ===");
        System.out.println("1. Edit name\n2. Edit pricing\n3. Return");
        int editChoice = console.nextInt();
        switch(editChoice){
            case 1:
                System.out.println("Enter name: ");
                console.nextLine();
                String editedName = console.next();
                inventory.updateItemName(j, editedName);
                System.out.println("Name changed.");
                break;
            case 2: 
                editItemPricing(inventory, console, j);
                break;
            case 3:
                break;
            default:
                System.out.println("Invalid choice!");
                break;
        }
    }
    public static void editItemPricing(Inventory inventory, Scanner console, int index){
        System.out.println("=== Edit Pricing ===");
        Package.PricingStrategy newStrategy = getPricingStrategy(console);
        float newPricingValue = getPricingValue(console, newStrategy, index);

        inventory.updateItemPricing(index, newStrategy, newPricingValue);
        System.out.println("Pricing updated!");
    }*/
}
