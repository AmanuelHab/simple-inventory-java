package javac;

public class Item {
    private String name;
    private int quantity;
    private float cost;
    private float price;
    private float pricingValue;
    private String details;
    private PricingStrategy strategy;

    // Pricing strategy enum
    public enum PricingStrategy {
        PROFIT_COEFFICIENT,  // price = cost * coefficient
        PROFIT_MARGIN,       // price = cost + fixed profit
        DIRECT_PRICE         // manual price setting
    }

    Item(String name, int quantity, float cost){
        this(name, quantity, cost, 1.0f, PricingStrategy.PROFIT_COEFFICIENT, "");
    }
    Item(String name, int quantity, float cost, float pricingValue){
        this(name, quantity, cost, pricingValue,PricingStrategy.PROFIT_COEFFICIENT, "");
    }
    Item(String name, int quantity, float cost, float pricingValue, PricingStrategy strategy, String details){
        this.name = name;
        this.quantity = quantity;
        this.cost = cost;
        this.pricingValue = pricingValue;
        this.details = details;
        this.strategy = strategy;
        setPricing();
    }
    public void setPricing(){
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
    public float getPricingValue() {
        return pricingValue;
    }
    public float getPrice() {
        return price;
    }
    public String getDetails() {
        return details;
    }
    public PricingStrategy getPricingStrategy(){
        return strategy;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    public void setCost(float cost) {
        this.cost = cost;
        recalculateValues();
    }
    public void setPricingStrategy(PricingStrategy newStrategy, float newPricingValue){
        this.strategy = newStrategy;
        this.pricingValue = newPricingValue;
        setPricing();
    }
    public void recalculateValues(){
        switch(this.strategy){
            case PROFIT_COEFFICIENT:
                this.price = this.pricingValue * this.cost;
                break;
            case PROFIT_MARGIN:
                this.price = this.pricingValue + this.cost;
                break;
            case DIRECT_PRICE:
                return;
        }
    }
    public void setCalculatedValues(int addedQuantity, float cost){
        float calculatedCost = ((this.quantity * this.cost) + (addedQuantity * cost))/(addedQuantity + quantity);
        this.cost = calculatedCost;
        this.quantity += addedQuantity;
        recalculateValues();
    }
    public void setDetails(String details) {
        this.details = details;
    }
    public String getProfit(){
        float profit = price - cost;
        float profitPercentage = (profit/cost) * 100;
        return String.format("Profit: %.2f ETB (%.1f%%)", profit, profitPercentage);
    }
}
