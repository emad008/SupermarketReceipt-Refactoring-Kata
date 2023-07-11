package dojo.supermarket;

import dojo.supermarket.model.*;
import dojo.supermarket.model.discount.Discount;
import dojo.supermarket.model.discount.NullDiscount;

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

    // DONETODO 6. Unused method
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
        StringBuilder result = new StringBuilder();

        result.append(
            formatLineWithWhitespace(
                item.getProduct().getName(),
                presentPrice(item.getPrice())
            )
        );

        // DONETODO 7. Extract method or maybe use string builder
        if (item.getQuantity() != 1) {
            result
                .append("  ")
                .append(presentPrice(item.getUnitPrice()))
                .append(" * ")
                .append(presentQuantity(item))
                .append("\n");
        }
        return result.toString();
    }

    private String presentDiscount(Discount discount) {
        if (discount instanceof NullDiscount)
            return "";
        String name = discount.getDescription() + "(" + discount.getProduct().getName() + ")";
        String value = presentPrice(discount.getDiscountAmount());

        return formatLineWithWhitespace(name, value);
    }

    private String presentTotal(Receipt receipt) {
        String name = "Total: ";
        String value = presentPrice(receipt.getPrice());
        return formatLineWithWhitespace(name, value);
    }

    private String formatLineWithWhitespace(String name, String value) {
        StringBuilder line = new StringBuilder();
        line.append(name);
        int whitespaceSize = this.columns - name.length() - value.length();
        // DONETODO 7. Use String.repeat
        line.append(" ".repeat(Math.max(0, whitespaceSize)));
        line.append(value);
//        line.append('\n');
        return line.toString();
    }

    private static String presentPrice(double price) {
        return String.format(Locale.UK, "%.2f", price);
    }

    private static String presentQuantity(ReceiptItem item) {
        // IGNOREDTODO. This is not seems correct. But I have no idea what to do
        return ProductUnit.EACH.equals(item.getProduct().getUnit())
                ? String.format("%d", (int)item.getQuantity())
                : String.format(Locale.UK, "%.3f", item.getQuantity());
    }
}
