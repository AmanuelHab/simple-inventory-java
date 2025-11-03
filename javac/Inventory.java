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
}
