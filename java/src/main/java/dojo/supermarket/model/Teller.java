package dojo.supermarket.model;

import dojo.supermarket.model.offer.Offer;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

// TODO 3. Refactor the name, what does teller suppose to mean?
public class Teller {

    private final SupermarketCatalog catalog;
    // TODO. There can be an offer catalog.
    private final Map<Product, Offer> offers = new HashMap<>();

    public Teller(SupermarketCatalog catalog) {
        this.catalog = catalog;
    }

    public void addSpecialOffer(Product product, Offer offer) {
        offers.put(product, offer);
    }

    public Receipt checksOutArticlesFrom(ShoppingCart theCart) {
        Receipt receipt = new Receipt();
        // TODO 5. unnecessary local variable. (Replace temp with query)
        List<ProductQuantity> productQuantities = theCart.getItems();
        // TODO 4. extract below part as another method (Extract Method)
        for (ProductQuantity pq: productQuantities) {
            Product p = pq.getProduct();
            // TODO 5. unnecessary local variables. (Replace temp with query)
            double quantity = pq.getQuantity();
            double unitPrice = catalog.getUnitPrice(p);
            receipt.addProduct(p, quantity, unitPrice);
        }
        theCart.handleOffers(receipt, offers, catalog);

        return receipt;
    }
}
