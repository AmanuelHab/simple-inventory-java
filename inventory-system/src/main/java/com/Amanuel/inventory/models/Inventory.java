package com.Amanuel.inventory.models;
import java.util.ArrayList;

public class Inventory {
    private ArrayList<Item> allItems = new ArrayList<>();

    public void addItem(Item item){
        allItems.add(item);
    }
    public int findItemByName(String name){
        for (int i = 0; i < allItems.size(); i++){
            if(allItems.get(i).getName().equalsIgnoreCase(name)){
                return i;
            }
        }
        return -1;
    }
    public boolean isEmpty(){
        return allItems.size() == 0 ? true: false;
    }
    public void displayItem(int index){
        Item item = allItems.get(index);
        System.out.println("Name: " + item.getName() + 
                        "\tQuantity: " + item.getQuantity() + 
                        "\nCost: " + item.getCost() + "ETB" +
                        "\tPrice: " + item.getPrice() + "ETB" +
                        "\nPricing Strategy: " + item.getPricingStrategy() +
                        "\tProfit from one piece: " + item.getProfit() +
                        "\nDetails: " + (item.getDetails().equals(" ") || item.getDetails().isEmpty() ? "No details" : item.getDetails()));
        System.out.println("-----------------------");
    }
    public void updateItem(int index, int quantity, float cost){
        Item item = allItems.get(index);
        item.setCalculatedValues(quantity, cost);
    }
    public void updateItemName(int index, String name){
        Item item = allItems.get(index);
        item.setName(name);
    }
    public void updateItemDetails(int index, String details){
        Item item = allItems.get(index);
        item.setDetails(details);
    }
    public void updateItemPricing(int index, Item.PricingStrategy newStrategy, float newPricingValue){
        Item item = allItems.get(index);
        item.setPricingStrategy(newStrategy, newPricingValue);
    }
    public void displayAllItems(){
        if(isEmpty()){
            System.out.println("No items in inventory.");
            return;
        }
        System.out.println(" === All Items === ");
        for(int i = 0; i < allItems.size(); i++){
            displayItem(i);
        }
    }
}
