package com.Amanuel.inventory.models;

public class Item {
    private String name;
    private String details;

    public Item(String name){
        this(name, "");
    }
    public Item(String name, String details){
        this.name = name;
        this.details = details;
    }
    public String getName() {
        return name;
    }
    public String getDetails() {
        return details;
    }
    public void setName(String name) {
        if (name == null || name.trim().isEmpty()){
            throw new IllegalArgumentException("Item name cannot be null or empty.");
        }
        this.name = name;
    }
    public void setDetails(String details) {
        this.details = details != null ? details : "";
    }
}
