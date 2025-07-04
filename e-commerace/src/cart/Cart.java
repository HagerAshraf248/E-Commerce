package cart;
import products.Product;

import java.util.ArrayList;
import java.util.List;

public class Cart {
    List<CartItem> items = new ArrayList<>();

    public void add(Product product, int quantity) throws Exception {
        if (!product.isAvailable(quantity)) {
            throw new Exception("Not enough stock for "+product.getName()+".");
        }

        if(product.isExpired()){
            throw new Exception(product.getName()+" is expired.");
        }
        items.add(new CartItem(product, quantity));
    }

    public List<CartItem> getItems() {
        return items;
    }

    public boolean isEmpty() {
        return items.isEmpty();
    }

    public void clear() {
        items.clear();
    }
}
