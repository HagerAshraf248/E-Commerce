import products.*;
import products.expirable.ExpirableProduct;
import products.shippable.ShippableProduct;
import customer.Customer;
import cart.Cart;
import products.services.CheckoutService;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        try {
        Product cheese = new ExpirableProduct("Cheese", 100, 5, LocalDate.now().plusDays(15));
        Product biscuits = new ShippableProduct("Biscuits", 150, 3, 700); // 700g
        Product scratchCard = new BasicProduct("ScratchCard", 50, 10);


        Customer customer = new Customer("Hager", 600);
        Cart cart = new Cart();
            cart.add(cheese, 1);
            cart.add(biscuits, 100);
            cart.add(scratchCard, 1);

            CheckoutService checkout = new CheckoutService();
            checkout.checkout(customer, cart);

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
