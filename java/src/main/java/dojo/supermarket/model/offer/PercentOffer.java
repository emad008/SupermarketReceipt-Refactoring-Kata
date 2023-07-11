package dojo.supermarket.model.offer;

import dojo.supermarket.model.discount.Discount;
import dojo.supermarket.model.Product;
import dojo.supermarket.model.discount.NullDiscount;

public class PercentOffer extends Offer {
    private final int percentage;

    public PercentOffer(Product product, int percentage) {
        super(product);
        this.percentage = percentage;
    }

    @Override
    public Discount offerDiscountOnPurchase(Product product, double unitPrice, double quantity) {
        // TODO. This if is repeated in all offer subclasses.
        if (!product.equals(getProduct()))
            return new NullDiscount(product, this);
        return new Discount(
            product,
            this,
            -quantity * unitPrice * percentage / 100.0
        );
    }

    @Override
    public String getDescription() {
        return percentage + "% off";
    }
}
