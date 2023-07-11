package dojo.supermarket.model;

import dojo.supermarket.model.offer.Offer;

public class Discount {
    // DONETODO 5. Its better to use the offer for generating the description. (Replace temp with query)
    private final double discountAmount;
    private final Product product;
    private final Offer offer;

    public Discount(Product product, Offer offer, double discountAmount) {
        this.product = product;
        this.offer = offer;
        this.discountAmount = discountAmount;
    }

    public String getDescription() {
        return offer.getDescription();
    }

    public double getDiscountAmount() {
        return discountAmount;
    }

    public Product getProduct() {
        return product;
    }
}
