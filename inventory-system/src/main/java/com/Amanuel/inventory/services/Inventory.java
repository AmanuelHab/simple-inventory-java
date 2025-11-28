package com.Amanuel.inventory.services;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.Amanuel.inventory.models.Item;
import com.Amanuel.inventory.models.Package;

public class Inventory {
    private Map<Integer, Item> allItems;
    private Map<Integer, Package> packageIndex;
    private Map<Integer, ArrayList<Package>> allPackages;

    public Inventory(){
        this.allItems = new HashMap<>();
        this.allPackages = new HashMap<>();
        this.packageIndex = new HashMap<>();
    }
    // --------- Methods for Items ----------
    public void addItem(String name){
        Item item = new Item(name);
        allItems.put(item.getItemId(), item);
    }
    public Item getItem(int itemId){
        return allItems.get(itemId);
    }
    public int getItemId(String name){
        for(Item item: allItems.values()){
            if(item.getName().equals(name)){
                return item.getItemId();
            }
        }
        return -1;
    }
    public int getItemId (Item item){
        for(int id: allItems.keySet()){
            if(item.equals(allItems.getOrDefault(id, null))){
                return id;
            }
        }
        return -1;
    }
    public boolean itemKnown(int itemId){
        return allItems.containsKey(itemId);
    }
    public void updateItemName(int index, String name){
        Item item = allItems.get(index);
        item.setName(name);
    }
    public boolean isEmpty(){
        return allItems.isEmpty();
    }
    public void displayItem(Item item){
        System.out.println("Name: " + item.getName());
        System.out.println("-----------------------");
    }
    public void displayAllItems(){
        if(isEmpty()){
            System.out.println("No items in inventory.");
            return;
        }
        System.out.println(" === All Items === ");
        for(Item item: allItems.values()){
            displayItem(item);
        }
        System.out.println();
    }
    // ----------  Methods for Packages  ----------
    public void addPackage(int itemId, Package newPackage){
        if(!allPackages.containsKey(itemId)){
            allPackages.put(itemId, new ArrayList<>());
        }
        allPackages.get(itemId).add(newPackage);
        packageIndex.put(newPackage.getPackageId(), newPackage);
    }
    public ArrayList<Package> getPackageList(int itemId){
        return allPackages.get(itemId);
    }
    public void updatePackagePricing(int index, Package.PricingStrategy newStrategy, float newPricingValue){
        Package pac = packageIndex.get(index);
        pac.setStrategy(newStrategy, newPricingValue);
    }
    public void displayPackage(int packageId){
        Package pac = packageIndex.get(packageId);
        System.out.println("Package Id: " + pac.getPackageId() + 
                        "\nItem: " + allItems.get(pac.getItemId()).getName() +
                        "\tQuantity: " + pac.getQuantity() + 
                        "\nCost: " + pac.getCost() + " ETB" +
                        "\tPrice: " + pac.getPrice() + " ETB" +
                        "\nPricing Strategy: " + pac.getStrategy() +
                        "\nProfit from one piece: " + pac.getProfit());
        System.out.println("-----------------------");
    }
    public void displayPackage(Package pac){
        System.out.println("Package Id: " + pac.getPackageId() + 
                        "\nItem: " + allItems.get(pac.getItemId()).getName() +
                        "\tQuantity: " + pac.getQuantity() + 
                        "\nCost: " + pac.getCost() + " ETB" +
                        "\tPrice: " + pac.getPrice() + " ETB" +
                        "\nPricing Strategy: " + pac.getStrategy() +
                        "\nProfit from one piece: " + pac.getProfit());
        System.out.println("-----------------------");
    }
    public void displayAllPackages(){
        if(packageIndex.isEmpty()){
            System.out.println("No packages in inventory.");
        }
        for(Package pac: packageIndex.values()){
            displayPackage(pac);
        }
    }
}
