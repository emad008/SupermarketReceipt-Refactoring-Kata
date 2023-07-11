package dojo.supermarket.model;

import dojo.supermarket.model.offer.Offer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ShoppingCart {
    // DONETODO 2. Why we store same things in two different ways? (Redundancy)
    private final Map<Product, Double> productQuantities = new HashMap<>();

    List<Product> getProducts() {
        return productQuantities.keySet().stream().toList();
    }

    // DONETODO 6. Unused method
//    void addItem(Product product) {
//        addItemQuantity(product, 1.0);
//    }

    double getQuantity(Product product) {
        return productQuantities.get(product);
    }

    public void addItemQuantity(Product product, double quantity) {
        // DONETODO 7. Use get or default.
        productQuantities.put(product, productQuantities.getOrDefault(product, (double) 0) + quantity);
    }

//    void handleOffers(Receipt receipt, Map<Product, Offer> offers, SupermarketCatalog catalog) {
//        for (Product p: productQuantities.keySet()) {
//            double quantity = productQuantities.get(p);
//            // DONETODO 1. These should be handled through inheritance. (Replace Conditional with Polymorphism)
//            if (offers.containsKey(p)) {
//                Offer offer = offers.get(p);
//                // DONETODO 5. Make discount a method. (Replace temp with query)
//
//            }
//        }
//    }
}
