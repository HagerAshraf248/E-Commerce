package products.expirable;
import products.Product;

import java.time.LocalDate;

public class ExpirableProduct extends Product{
    private LocalDate expirationDate;
    public ExpirableProduct(String name, double price, int quantity, LocalDate expirationDate){
        super(name, price, quantity);
        this.expirationDate = expirationDate;
    }

    @Override
    public boolean isExpired() {
        return LocalDate.now().isAfter(expirationDate);
    }

    @Override
    public boolean needsShipping() {
        return true; // As default Value
    }
}
