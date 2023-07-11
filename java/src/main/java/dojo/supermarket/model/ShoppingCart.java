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

    List<ProductQuantity> getItems() {
        return productQuantities.keySet().stream().map(
            p -> new ProductQuantity(
                p,
                productQuantities.get(p)
            )
        ).toList();
    }

    // TODO 6. Unused method
    void addItem(Product product) {
        addItemQuantity(product, 1.0);
    }

    Map<Product, Double> productQuantities() {
        return Collections.unmodifiableMap(productQuantities);
    }

    public void addItemQuantity(Product product, double quantity) {
        // TODO 7. Use get or default.
        if (productQuantities.containsKey(product)) {
            productQuantities.put(product, productQuantities.get(product) + quantity);
        } else {
            productQuantities.put(product, quantity);
        }
    }

    // TODO. Maybe there are more than one offer for a product. Why use map?
    void handleOffers(Receipt receipt, Map<Product, Offer> offers, SupermarketCatalog catalog) {
        for (Product p: productQuantities().keySet()) {
            double quantity = productQuantities.get(p);
            // DONETODO 1. These should be handled through inheritance. (Replace Conditional with Polymorphism)
            if (offers.containsKey(p)) {
                Offer offer = offers.get(p);
                // TODO 5. Make discount a method. (Replace temp with query)
                Discount discount = offer.offerDiscountOnPurchase(
                    p,
                    catalog.getUnitPrice(p),
                    quantity
                );

                // TODO. I am not sure about this one but it can be a (Introduce Null Object)
                if (discount != null)
                    receipt.addDiscount(discount);
            }
        }
    }
}
