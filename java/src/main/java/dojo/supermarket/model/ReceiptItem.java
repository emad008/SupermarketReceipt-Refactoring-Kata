package dojo.supermarket.model;

import java.util.Objects;

// TODO. This is actually an order. It can have more complex logic with new features but for now, we can just think
// an order as just one line in a receipt
public class ReceiptItem {
    private final Product product;
    // TODO 8. I agree more with the unitPrice and price notation used in SupermarketCatalog
    private final double price;
    // DONETODO 2. This field can be represented is multiplication of quantity and price. (Redundancy)
    private final double quantity;

    ReceiptItem(Product p, double quantity, double price) {
        this.product = p;
        this.quantity = quantity;
        this.price = price;
    }

    public double getPrice() {
        return price;
    }

    public Product getProduct() {
        return product;
    }

    public double getQuantity() {
        return quantity;
    }

    public double getTotalPrice() {
        return getPrice() * getQuantity();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ReceiptItem)) return false;
        ReceiptItem that = (ReceiptItem) o;
        return Double.compare(that.price, price) == 0 &&
                Double.compare(that.quantity, quantity) == 0 &&
                Objects.equals(product, that.product);
    }

    @Override
    public int hashCode() {
        return Objects.hash(product, price, quantity);
    }
}
