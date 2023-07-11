package dojo.supermarket.model.offer;

import dojo.supermarket.model.Discount;
import dojo.supermarket.model.Product;
import dojo.supermarket.model.SpecialOfferType;

public class Offer {
    private final Product product;
    double argument;

    public Offer(Product product) {
        this.product = product;
    }

    protected Product getProduct() {
        return product;
    }

    public Discount offerDiscountOnPurchase(Product p, double unitPrice, double quantity) {
        return null;
    }
}
