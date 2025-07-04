package products.services;

import cart.Cart;
import cart.CartItem;
import customer.Customer;
import products.Product;
import products.shippable.Shippable;

import java.util.ArrayList;
import java.util.List;

public class CheckoutService {
    private static final double SHIPPING_FEE = 30;

    public void checkout(Customer customer, Cart cart) throws Exception{
        if (cart.isEmpty()) {
            throw new Exception("Cart is empty");
        }

        double subtotal = 0;
        List<CartItem> shippableItems = new ArrayList<>();

        for (CartItem item : cart.getItems()) {
            Product product = item.getProduct();
            subtotal += item.getTotalPrice();

            if (product instanceof Shippable) {
                shippableItems.add(item);
            }
        }

        double shipping = shippableItems.isEmpty() ? 0 : SHIPPING_FEE;
        double total = subtotal + shipping;

        if (customer.getBalance() < total ) {
            throw new Exception("Customer's balance is insufficient.");
        }


        for (CartItem item : cart.getItems()) {
            item.getProduct().reduceQuantity(item.getQuantity());
        }

        customer.deduct(total);

        if (!shippableItems.isEmpty()) {
            new ShippingService().ship(shippableItems);
        }


        // Receipt
        System.out.println("** Checkout receipt **");
        for (CartItem item : cart.getItems()) {
            System.out.printf("%dx %-12s %.2f\n", item.getQuantity(), item.getProduct().getName(), item.getTotalPrice());
        }
        System.out.println("----------------------");
        System.out.println("Subtotal\t" + subtotal);
        System.out.println("Shipping\t" + shipping);
        System.out.println("Amount\t\t" + total);
        System.out.println("Customer Balance After Payment: " + customer.getBalance());

    }
}
