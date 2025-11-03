package javac;
import java.util.ArrayList;

public class Inventory {
    private ArrayList<Item> allItems = new ArrayList<>();

    public void addItem(Item item){
        allItems.add(item);
    }
    public int findItemByName(String name){
        for (int i = 0; i < allItems.size(); i++){
            if(allItems.get(i).getName().equals(name)){
                return i;
            }
        }
        return -1;
    }
    public void displayItem(int index){
        Item item = allItems.get(index);
        System.out.println("Name: " + item.getName() + " Quantity: " + item.getQuantity() + "\nCost: " + item.getCost() + " Price: " + item.getPrice() + "\nDetail: " + item.getDetails());
    }
    public void updateItem(int index, int quantity, float cost){
        Item item = allItems.get(index);
        item.setCalculatedValues(quantity, cost);
    }
}
