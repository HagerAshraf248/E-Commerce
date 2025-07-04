package products.services;

import cart.CartItem;
import products.shippable.Shippable;

import java.util.List;

public class ShippingService {
    public void ship(List<CartItem> items) {
        double totalWeight = 0;

        System.out.println("** Shipment notice **");
        for (CartItem item : items) {
            if (item.getProduct() instanceof Shippable) {
                System.out.printf("%dx %s ", item.getQuantity(), item.getProduct().getName());
            }
        }
        for (CartItem item : items) {
            if (item.getProduct() instanceof Shippable) {
                Shippable shipItem = (Shippable) item.getProduct();
                for (int i = 0; i < item.getQuantity(); i++) {
                    System.out.printf("%.0fg\n", shipItem.getWeight());
                    totalWeight += shipItem.getWeight();
                }
            }
        }
        System.out.printf("Total package weight %.1fkg\n", totalWeight / 1000.0 );
    }
}
