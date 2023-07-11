package dojo.supermarket.model.offer;

import dojo.supermarket.model.Discount;
import dojo.supermarket.model.Product;

public class BuyXForYPrice extends Offer {
    private final double baseQuantity;
    private final double priceForBaseQuantity;
    public BuyXForYPrice(Product product, double baseQuantity, double priceForBaseQuantity) {
        super(product);
        this.baseQuantity = baseQuantity;
        this.priceForBaseQuantity = priceForBaseQuantity;
    }

    @Override
    public Discount offerDiscountOnPurchase(Product p, double unitPrice, double quantity) {
        if (!p.equals(getProduct()))
            return null;

        double numberOfBases = Math.floor(quantity / baseQuantity);
        double priceForAllTakes = numberOfBases * priceForBaseQuantity;
        double remainingQuantity = quantity - numberOfBases * baseQuantity;
        double priceForRemainingQuantity = remainingQuantity * unitPrice;
        double totalPrice = priceForAllTakes + priceForRemainingQuantity;
        double discountAmount = unitPrice * quantity - totalPrice;
        return new Discount(p, this, -discountAmount);
    }

    @Override
    public String getDescription() {
        return baseQuantity + " for " + priceForBaseQuantity;
    }
}
