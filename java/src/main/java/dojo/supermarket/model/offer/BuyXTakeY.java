package dojo.supermarket.model.offer;

import dojo.supermarket.model.discount.Discount;
import dojo.supermarket.model.Product;
import dojo.supermarket.model.discount.NullDiscount;

public class BuyXTakeY extends Offer {
    private final double baseBuyQuantity;
    private final double takingQuantity;
    public BuyXTakeY(Product product, double baseBuyQuantity, double takingQuantity) {
        super(product);
        this.baseBuyQuantity = baseBuyQuantity;
        this.takingQuantity = takingQuantity;
    }

    @Override
    public Discount offerDiscountOnPurchase(Product product, double unitPrice, double quantity) {
        if (!product.equals(getProduct()))
            return new NullDiscount(product, this);

        double numberOfTakes = Math.floor(quantity / takingQuantity);
        double priceForAllTakes = numberOfTakes * baseBuyQuantity * unitPrice;
        double remainingQuantity = quantity - numberOfTakes * takingQuantity;
        double priceForRemainingQuantity = remainingQuantity * unitPrice;
        double price = priceForAllTakes + priceForRemainingQuantity;
        double discountAmount = quantity * unitPrice - price;
        return new Discount(product, this, -discountAmount);
    }

    @Override
    public String getDescription() {
        return takingQuantity + " for " + baseBuyQuantity;
    }
}
