package dojo.supermarket.model.discount;

import dojo.supermarket.model.Product;
import dojo.supermarket.model.offer.Offer;

public class NullDiscount extends Discount {
    public NullDiscount(
        Product product,
        Offer offer
    ) {
        super(product, offer, 0);
    }
}
