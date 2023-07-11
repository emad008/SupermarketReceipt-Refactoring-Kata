package dojo.supermarket.model.offer;

import dojo.supermarket.model.Discount;
import dojo.supermarket.model.Product;

public class BuyXTakeY extends Offer {
    private final double baseBuyQuantity;
    private final double takingQuantity;
    public BuyXTakeY(Product product, double baseBuyQuantity, double takingQuantity) {
        super(product);
        this.baseBuyQuantity = baseBuyQuantity;
        this.takingQuantity = takingQuantity;
    }

    @Override
    public Discount offerDiscountOnPurchase(Product p, double unitPrice, double quantity) {
        if (!p.equals(getProduct()))
            return null;

        double numberOfTakes = Math.floor(quantity / takingQuantity);
        double priceForAllTakes = numberOfTakes * baseBuyQuantity * unitPrice;
        double remainingQuantity = quantity - numberOfTakes * takingQuantity;
        double priceForRemainingQuantity = remainingQuantity * unitPrice;
        double totalPrice = priceForAllTakes + priceForRemainingQuantity;
        double discountAmount = quantity * unitPrice - totalPrice;
        return new Discount(p, takingQuantity + " for " + baseBuyQuantity, -discountAmount);
    }
}
