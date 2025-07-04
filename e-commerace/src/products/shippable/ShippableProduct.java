package products.shippable;

import products.Product;

public class ShippableProduct extends Product implements Shippable {
    private double weight;

    public ShippableProduct(String name, double price, int quantity, double weight) {
        super(name, price, quantity);
        this.weight = weight;
    }

    public double getWeight() {
        return weight;
    }

    @Override
    public boolean isExpired() {
        return false;
    }

    @Override
    public boolean needsShipping() {
        return true;
    }
}
