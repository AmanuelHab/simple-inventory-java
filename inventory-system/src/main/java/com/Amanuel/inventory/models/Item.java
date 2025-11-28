package com.Amanuel.inventory.models;

public class Item {
    private static int nextId = 1;

    private final int itemId;
    private String name;

    public Item(String name){
        setName(name);
        this.itemId = nextId++;
    }
    public int getItemId() {
        return itemId;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        if (name == null || name.trim().isEmpty()){
            throw new IllegalArgumentException("Item name cannot be null or empty.");
        }
        this.name = name;
    }
}
