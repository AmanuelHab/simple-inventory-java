package javac;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Inventory inventory = new Inventory();
        Scanner console = new Scanner(System.in);
        
        while(true){
        System.out.println("=== Inventory Managment System ===");
        System.out.println("1. Add item\n2. Search item\n3. Display all\n4. Exit");
        int choice = console.nextInt();
            switch (choice) {
                case 1:
                    System.out.println("== Add Item ==");
                    System.out.print("Enter name: ");
                    String name = console.next();
                    int index = inventory.findItemByName(name);
                    System.out.print("Enter quantity: ");
                    int quantity = console.nextInt();
                    System.out.println("Enter cost: ");
                    float cost = console.nextFloat();
                    // Update the item if it already exists
                    if(index != -1){
                        inventory.updateItem(index, quantity, cost);
                    }else{
                        System.out.print("Enter profit coefficient: ");
                        float profitCoefficient = console.nextFloat();
                        System.out.print("Enter detail: ");
                        String detail = console.next();
                        Item item = new Item(name, quantity, cost, profitCoefficient, detail);
                        inventory.addItem(item);
                    }
                    break;
                case 2:
                    System.out.println("== Search Item ==");
                    System.out.print("Enter name: ");
                    String search = console.next();
                    int i = inventory.findItemByName(search);
                    if(i == -1){
                        System.out.println("Item not Found!");
                        break;
                    } 
                    inventory.displayItem(i);
                    break;
                case 3:
                    System.out.println("== Display All ==");
                    break;
                case 4:
                    return;
                default:
                    break;
            }
        }
    console.close();
    }
}
