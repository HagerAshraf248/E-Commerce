package products;
public class BasicProduct extends Product{
    public BasicProduct(String name, double price, int quantity) {
        super(name, price, quantity);
    }

    @Override
    public boolean isExpired() {
        return false;
    }

    @Override
    public boolean needsShipping() {
        return false;
    }
}
