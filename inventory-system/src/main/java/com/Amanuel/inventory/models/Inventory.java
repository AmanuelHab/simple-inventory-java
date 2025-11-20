package com.Amanuel.inventory.models;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Inventory {
    private Map<Integer, Item> allItems;
    private Map<Integer, ArrayList<Package>> allPackages;
    private int nextItemId;

    public Inventory(){
        this.allItems = new HashMap<>();
        this.allPackages = new HashMap<>();
        this.nextItemId = 1;
    }
    public Item addItem(String name, String details){
        Item item = new Item(name, details);
        allItems.put(nextItemId, item);
        return allItems.get(nextItemId++);
    }
    public int getItemId(String name){
        for(int id: allItems.keySet()){
            if(name.equals(allItems.get(id).getName())){
                return id;
            }
        }
        return -1;
    }
    public Item getItem(int itemId){
        return allItems.get(itemId);
    }
    public boolean itemKnown(int itemId){
        return allItems.containsKey(itemId);
    }
    public void addPackage(int itemId, Package newPackage){
        if(!allPackages.containsKey(itemId)){
            allPackages.put(itemId, new ArrayList<>());
        }
        allPackages.get(itemId).add(newPackage);
    }
    public Item searchItemByName(String name){
        for (Item item: allItems.values()){
            if(name.equals(item.getName())){
                return item;
            }
        }
        return null;
    }
    public ArrayList<Package> getPackageList(int itemId){
        return allPackages.get(itemId);
    }
    public void displayItem(Item item){
        System.out.println("Name: " + item.getName() + 
        "\tDetails: " + (item.getDetails().equals(" ") || item.getDetails().isEmpty() ? "No details" : item.getDetails()));
        System.out.println("-----------------------");
    }
    /*
    public boolean isEmpty(){
        return allItems.size() == 0 ? true: false;
    }
    public void displayItem(int index){
        Item item = allItems.get(index);
        System.out.println("Name: " + item.getName() + 
                        "\tQuantity: " + item.getQuantity() + 
                        "\nCost: " + item.getCost() + " ETB" +
                        "\tPrice: " + item.getPrice() + " ETB" +
                        "\nPricing Strategy: " + item.getPricingStrategy() +
                        "\nProfit from one piece: " + item.getProfit() +
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
        System.out.println();
    }*/
}
