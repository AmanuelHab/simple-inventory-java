package javac;

public class Item {
    private String name;
    private int quantity;
    private float cost;
    private float price;
    private float profitCoefficient;
    private String details;

    Item(String name, int quantity, float cost){
        this(name, quantity, cost, 1.0f, "");
    }
    Item(String name, int quantity, float cost, float profitCoefficient){
        this(name, quantity, cost, profitCoefficient, "");
    }
    Item(String name, int quantity, float cost, float profitCoefficient, String details){
        this.name = name;
        this.quantity = quantity;
        this.cost = cost;
        this.profitCoefficient = profitCoefficient;
        this.price = cost * profitCoefficient;
        this.details = details;
    }
    public String getName() {
        return name;
    }
    public int getQuantity() {
        return quantity;
    }
    public float getCost() {
        return cost;
    }
    public float getProfitCoefficient() {
        return profitCoefficient;
    }
    public float getPrice() {
        return price;
    }
    public String getDetails() {
        return details;
    }
}
