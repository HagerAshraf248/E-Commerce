package products.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import products.shippable.Shippable;

public class ShippingService {
    public void ship(List<Shippable> items) {
        Map<String, Double> itemWeightMap = new HashMap<>();
        Map<String, Integer> itemCountMap = new HashMap<>();
        double totalWeight = 0;

        for (Shippable item : items) {
            String name = item.getName();
            double weight = item.getWeight();

            itemWeightMap.put(name, weight); // weight per unit
            itemCountMap.put(name, itemCountMap.getOrDefault(name, 0) + 1);
            totalWeight += weight;
        }

        System.out.println("** Shipment notice **");
        for (String name : itemCountMap.keySet()) {
            int count = itemCountMap.get(name);
            double weight = itemWeightMap.get(name);
            System.out.printf("%dx %-12s %4.0fg\n", count, name, weight);
        }

        System.out.printf("Total package weight %.1fkg\n\n", totalWeight / 1000.0);
    }
}
