package dojo.supermarket.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Receipt {

    private final List<ReceiptItem> items = new ArrayList<>();
    private final List<Discount> discounts = new ArrayList<>();

    private double getItemsTotalPrice() {
        return items.stream().mapToDouble(ReceiptItem::getTotalPrice).sum();
    }

    private double getTotalDiscountAmount() {
        return discounts.stream().mapToDouble(Discount::getDiscountAmount).sum();
    }

    public double getTotalPrice() {
        // DONETODO 4. Extract the two methods. (Extract Method)
        return getItemsTotalPrice() + getTotalDiscountAmount();
    }

    public void addProduct(Product p, double quantity, double price) {
        items.add(new ReceiptItem(p, quantity, price));
    }

    public List<ReceiptItem> getItems() {
        return Collections.unmodifiableList(items);
    }

    public void addDiscount(Discount discount) {
        discounts.add(discount);
    }

    public List<Discount> getDiscounts() {
        return discounts;
    }
}
