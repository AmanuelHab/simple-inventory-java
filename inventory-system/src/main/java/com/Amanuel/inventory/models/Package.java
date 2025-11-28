package com.Amanuel.inventory.models;

public class Package {
    private static int nextId = 1;
    private final int packageId;
    private int itemId;
    private int quantity;
    private float cost;
    private float price;
    private float pricingValue;
    private String details;
    private PricingStrategy strategy;

    public enum PricingStrategy{
        PROFIT_COEFFICIENT,
        PROFIT_MARGIN,
        DIRECT_PRICE
    }
    public Package(int itemId, int quantity, float cost, float price, float pricingValue, String details,
        PricingStrategy strategy) {
        if(quantity <= 0) throw new IllegalArgumentException("Quantity must be positive.");
        if(cost < 0) throw new IllegalArgumentException("Cost cannot be negative.");
        this.packageId = nextId++;
        this.itemId = itemId;
        this.quantity = quantity;
        this.cost = cost;
        this.price = price;
        this.pricingValue = pricingValue;
        this.details = details;
        this.strategy = strategy;
    }
    public int getPackageId() {
        return packageId;
    }
    public int getItemId() {
        return itemId;
    }
    public int getQuantity() {
        return quantity;
    }
    public float getCost() {
        return cost;
    }
    public PricingStrategy getStrategy() {
        return strategy;
    }
    public float getPricingValue() {
        return pricingValue;
    }
    public float getPrice() {
        return price;
    }
    public String getDetails() {
        return details;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    public void setCost(float cost) {
        this.cost = cost;
        setPrice();
    }
    public void setStrategy(PricingStrategy newStrategy, float newPricingValue){
        this.strategy = newStrategy;
        this.pricingValue = newPricingValue;
        setPrice();
    }
    public void setPricingValue(float pricingValue) {
        switch(this.strategy){
            case PROFIT_COEFFICIENT:
                if(pricingValue <= 0) throw new IllegalArgumentException("Profit coefficient must be positive.");
                this.pricingValue = pricingValue;
                break;
            case PROFIT_MARGIN:
                this.pricingValue = pricingValue;
                break;
            case DIRECT_PRICE: 
                if(pricingValue < 0) throw new IllegalArgumentException("Price cannot be negative.");
                break;
        }
        setPrice();
    }
    public void setPrice(){
        switch(this.strategy){
            case PROFIT_COEFFICIENT:
                this.price = this.pricingValue * cost;
                break;
            case PROFIT_MARGIN:
                this.price = this.pricingValue + cost;
                break;
            case DIRECT_PRICE:
                this.price = this.pricingValue;
                break;
        }
        if(this.price < this.cost){
            System.out.println("The cost is above the profit!");
        }
    }
    
    public void setDetails(String details) {
        this.details = details;
    }
    public float getTotalValue(){
        return price * quantity;
    }
    public float getTotalCost(){
        return cost * quantity;
    }
        return (price - cost) * quantity;
    }
    public void mergePackages(Package package1, Package package2){
        int quantity1 = package1.getQuantity();
        int quantity2 = package2.getQuantity();
        float calculatedCost = ((package1.getCost() * quantity1) + (package2.getCost() * quantity2)) / (quantity1 + quantity2);
        package1.setCost(calculatedCost);
        package2.setCost(calculatedCost);
    }
}
