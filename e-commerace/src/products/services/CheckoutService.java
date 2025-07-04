package products.services;

import cart.*;
import customer.*;
import products.*;
import products.shippable.*;

import java.util.ArrayList;
import java.util.List;

public class CheckoutService {
    private static final double SHIPPING_FEE = 30;

    public void checkout(Customer customer, Cart cart) throws Exception {
        if (cart.isEmpty()) {
            throw new Exception("Cart is empty");
        }

        double subtotal = 0;
        List<Shippable> toShip = new ArrayList<>();

        for (CartItem item : cart.getItems()) {
            Product product = item.getProduct();

            if (product.isExpired()) {
                System.out.println(product.getName() + " is expired.");
                return;
            }

            if (!product.isAvailable(item.getQuantity())) {
                System.out.println(product.getName() + " is out of stock.");
                return;
            }

            subtotal += item.getTotalPrice();
            if (product instanceof Shippable) {
                toShip.add((Shippable) product);
            }
        }

        double total = subtotal + (toShip.isEmpty() ? 0 : SHIPPING_FEE);

        if (customer.getBalance() < total) {
            throw new Exception("Insufficient balance.");
        }


        for (CartItem item : cart.getItems()) {
            item.getProduct().reduceQuantity(item.getQuantity());
        }

        customer.deduct(total);
        if (cart.isEmpty()) {
            throw new Exception("Cart is empty.");
        }
        //info
        if (!toShip.isEmpty()) {
            new ShippingService().ship(toShip);
        }

        //Receipt
        System.out.println("** Checkout receipt **");
        for (CartItem item : cart.getItems()) {
            System.out.printf("%dx %-12s %.2f\n", item.getQuantity(),
                    item.getProduct().getName(), item.getTotalPrice());
        }
        System.out.println("----------------------");
        System.out.println("Subtotal\t" + subtotal);
        System.out.println("Shipping\t" + (toShip.isEmpty() ? 0 : SHIPPING_FEE));
        System.out.println("Amount\t\t" + total);
        System.out.println("Customer Balance After Payment: " + customer.getBalance());
    }
}
