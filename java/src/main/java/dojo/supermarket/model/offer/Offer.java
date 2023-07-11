package dojo.supermarket.model.offer;

import dojo.supermarket.model.discount.Discount;
import dojo.supermarket.model.Product;
import dojo.supermarket.model.discount.NullDiscount;

public class Offer {
    private final Product product;
    double argument;

    public Offer(Product product) {
        this.product = product;
    }

    protected Product getProduct() {
        return product;
    }

    public Discount offerDiscountOnPurchase(Product product, double unitPrice, double quantity) {
        return new NullDiscount(product, this);
    }

    public String getDescription() {
        return "offer";
    }
}
