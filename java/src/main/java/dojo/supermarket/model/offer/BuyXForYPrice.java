package dojo.supermarket.model.offer;

import dojo.supermarket.model.discount.Discount;
import dojo.supermarket.model.Product;
import dojo.supermarket.model.discount.NullDiscount;

public class BuyXForYPrice extends Offer {
    private final double baseQuantity;
    private final double priceForBaseQuantity;
    public BuyXForYPrice(Product product, double baseQuantity, double priceForBaseQuantity) {
        super(product);
        this.baseQuantity = baseQuantity;
        this.priceForBaseQuantity = priceForBaseQuantity;
    }

    @Override
    public Discount offerDiscountOnPurchase(Product product, double unitPrice, double quantity) {
        if (!product.equals(getProduct()))
            return new NullDiscount(product, this);

        double numberOfBases = Math.floor(quantity / baseQuantity);
        double priceForAllTakes = numberOfBases * priceForBaseQuantity;
        double remainingQuantity = quantity - numberOfBases * baseQuantity;
        double priceForRemainingQuantity = remainingQuantity * unitPrice;
        double price = priceForAllTakes + priceForRemainingQuantity;
        double discountAmount = unitPrice * quantity - price;
        return new Discount(product, this, -discountAmount);
    }

    @Override
    public String getDescription() {
        return baseQuantity + " for " + priceForBaseQuantity;
    }
}
