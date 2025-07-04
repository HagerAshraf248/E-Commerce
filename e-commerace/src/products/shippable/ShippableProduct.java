package products.shippable;
import products.Product;


public class ShippableProduct extends Product{
   private double weight;
    public ShippableProduct(String name, double price, int quantity, double weight){
        super(name, price, quantity);
        this.weight=weight;
    }
    public double getWeight() { return weight; }

    @Override
    public boolean isExpired() {
        return false; // As default Value
    }

    @Override
    public boolean needsShipping() {
        return true;
    }
}
