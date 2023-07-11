package dojo.supermarket;

import dojo.supermarket.model.*;

import java.util.List;
import java.util.Locale;

// MAYBEATODO 3. Unusual class. I think we can fit in this class in our metaphor. I can think it as a some real printer who
// prints the receipt. Like a presentation layer.
public class ReceiptPrinter {

    private final int columns;

    public ReceiptPrinter() {
        this(40);
    }

    public ReceiptPrinter(int columns) {
        this.columns = columns;
    }

    // TODO 6. Unused method
    public String printReceipt(Receipt receipt) {
        StringBuilder result = new StringBuilder();
        // DONETODO 4. Extract method
        result.append(presentReceiptItems(receipt.getItems()));
        // DONETODO 4. Extract method
        result.append(presentDiscounts(receipt.getDiscounts()));
        result.append("\n");
        result.append(presentTotal(receipt));
        return result.toString();
    }

    private String presentReceiptItems(List<ReceiptItem> receiptItems) {
        StringBuilder result = new StringBuilder();
        for (ReceiptItem item : receiptItems)
            result.append(presentReceiptItem(item));
        return result.toString();
    }

    private String presentDiscounts(List<Discount> discounts) {
        StringBuilder result = new StringBuilder();
        for (Discount discount : discounts)
            result.append(presentDiscount(discount));
        return result.toString();
    }

    private String presentReceiptItem(ReceiptItem item) {
        String totalPricePresentation = presentPrice(item.getTotalPrice());
        String name = item.getProduct().getName();

        String line = formatLineWithWhitespace(name, totalPricePresentation);

        // TODO 7. Extract method or maybe use string builder
        if (item.getQuantity() != 1) {
            line += "  " + presentPrice(item.getPrice()) + " * " + presentQuantity(item) + "\n";
        }
        return line;
    }

    private String presentDiscount(Discount discount) {
        String name = discount.getDescription() + "(" + discount.getProduct().getName() + ")";
        String value = presentPrice(discount.getDiscountAmount());

        return formatLineWithWhitespace(name, value);
    }

    private String presentTotal(Receipt receipt) {
        String name = "Total: ";
        String value = presentPrice(receipt.getTotalPrice());
        return formatLineWithWhitespace(name, value);
    }

    private String formatLineWithWhitespace(String name, String value) {
        StringBuilder line = new StringBuilder();
        line.append(name);
        int whitespaceSize = this.columns - name.length() - value.length();
        // TODO 7. Use String.repeat
        for (int i = 0; i < whitespaceSize; i++) {
            line.append(" ");
        }
        line.append(value);
        line.append('\n');
        return line.toString();
    }

    private static String presentPrice(double price) {
        return String.format(Locale.UK, "%.2f", price);
    }

    private static String presentQuantity(ReceiptItem item) {
        // TODO. This is not seems correct. But I have no idea what to do
        return ProductUnit.EACH.equals(item.getProduct().getUnit())
                ? String.format("%d", (int)item.getQuantity())
                : String.format(Locale.UK, "%.3f", item.getQuantity());
    }
}
