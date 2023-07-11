package dojo.supermarket.model;

import dojo.supermarket.model.offer.Offer;

import java.util.HashMap;
import java.util.Map;

// DONETODO 3. Refactor the name, what does teller suppose to mean?
public class CashRegister {
    private final SupermarketCatalog catalog;
    // TODO. There can be an offer catalog.
    // TODO. Maybe there are more than one offer for a product. Why use map?
    private final Map<Product, Offer> offers = new HashMap<>();

    public CashRegister(SupermarketCatalog catalog) {
        this.catalog = catalog;
    }

    public void addSpecialOffer(Product product, Offer offer) {
        offers.put(product, offer);
    }

    public void addProductsToReceipt(ShoppingCart theCart, Receipt receipt) {
        for (Product product: theCart.getProducts()) {
            // DONETODO 5. unnecessary local variables. (Replace temp with query)
            receipt.addProduct(product, theCart.getQuantity(product), catalog.getUnitPrice(product));
        }
    }

    public void addDiscountsToReceipt(ShoppingCart theCart, Receipt receipt) {
        for (Product product: theCart.getProducts()) {
            if (!offers.containsKey(product))
                continue;

            Offer offer = offers.get(product);
            Discount discount = offer.offerDiscountOnPurchase(
                product,
                catalog.getUnitPrice(product),
                theCart.getQuantity(product)
            );

            // TODO. I am not sure about this one but it can be a (Introduce Null Object)
            if (discount != null)
                receipt.addDiscount(discount);
        }
    }

    public Receipt checksOutArticlesFrom(ShoppingCart theCart) {
        Receipt receipt = new Receipt();
        // DONETODO 5. unnecessary local variable. (Replace temp with query)
        // DONETODO 4. extract below part as another method (Extract Method)
        addProductsToReceipt(
            theCart,
            receipt
        );
        addDiscountsToReceipt(
            theCart,
            receipt
        );

        return receipt;
    }
}
