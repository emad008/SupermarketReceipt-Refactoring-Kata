package dojo.supermarket.model.offer;

import dojo.supermarket.model.Discount;
import dojo.supermarket.model.Product;

public class PercentOffer extends Offer {
    private final int percentage;

    public PercentOffer(Product product, int percentage) {
        super(product);
        this.percentage = percentage;
    }

    @Override
    public Discount offerDiscountOnPurchase(Product p, double unitPrice, double quantity) {
        // TODO. This if is repeated in all offer subclasses.
        if (!p.equals(getProduct()))
            return null;
        return new Discount(
            p,
            this,
            -quantity * unitPrice * percentage / 100.0
        );
    }

    @Override
    public String getDescription() {
        return percentage + "% off";
    }
}
