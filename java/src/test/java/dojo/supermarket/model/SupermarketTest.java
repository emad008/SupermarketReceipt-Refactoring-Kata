package dojo.supermarket.model;

import dojo.supermarket.model.offer.BuyXForYPrice;
import dojo.supermarket.model.offer.BuyXTakeY;
import dojo.supermarket.model.offer.PercentOffer;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SupermarketTest {
    // Todo: test all kinds of discounts are applied properly

    @Test
    public void fiveFor7Discount() {
        SupermarketCatalog catalog = new FakeCatalog();
        Product toothbrush = new Product("toothbrush", ProductUnit.EACH);
        catalog.addProduct(toothbrush, 0.99);
        Product apples = new Product("apples", ProductUnit.KILO);
        catalog.addProduct(apples, 1.99);

        CashRegister teller = new CashRegister(catalog);
        teller.addSpecialOffer(
            apples,
            new BuyXForYPrice(
                apples,
                5,
                6.99
            )
        );

        ShoppingCart cart = new ShoppingCart();
        cart.addItemQuantity(apples, 7.0);

        // ACT
        Receipt receipt = teller.checksOutArticlesFrom(cart);

        // ASSERT
        assertEquals(6.99 + 2 * 1.99, receipt.getTotalPrice());
        assertEquals(1, receipt.getDiscounts().size());
        assertEquals(1, receipt.getItems().size());
        ReceiptItem receiptItem = receipt.getItems().get(0);
        assertEquals(apples, receiptItem.getProduct());
        assertEquals(1.99, receiptItem.getPrice());
        assertEquals(7.0 * 1.99, receiptItem.getTotalPrice());
        assertEquals(7.0, receiptItem.getQuantity());
    }

    @Test
    public void threeForTwoDiscount() {
        SupermarketCatalog catalog = new FakeCatalog();
        Product toothbrush = new Product("toothbrush", ProductUnit.EACH);
        catalog.addProduct(toothbrush, 0.99);
        Product apples = new Product("apples", ProductUnit.KILO);
        catalog.addProduct(apples, 1.99);

        CashRegister teller = new CashRegister(catalog);
        teller.addSpecialOffer(
            toothbrush,
            new BuyXTakeY(
                toothbrush,
                2,
                3
            )
        );

        ShoppingCart cart = new ShoppingCart();
        cart.addItemQuantity(toothbrush, 4.0);

        // ACT
        Receipt receipt = teller.checksOutArticlesFrom(cart);

        // ASSERT
        assertEquals(2 * 0.99 + 0.99, receipt.getTotalPrice());
        assertEquals(1, receipt.getDiscounts().size());
        assertEquals(1, receipt.getItems().size());
        ReceiptItem receiptItem = receipt.getItems().get(0);
        assertEquals(toothbrush, receiptItem.getProduct());
        assertEquals(0.99, receiptItem.getPrice());
        assertEquals(4.0 * 0.99, receiptItem.getTotalPrice());
        assertEquals(4.0, receiptItem.getQuantity());
    }

    @Test
    public void tenPercentDiscount() {
        SupermarketCatalog catalog = new FakeCatalog();
        Product toothbrush = new Product("toothbrush", ProductUnit.EACH);
        catalog.addProduct(toothbrush, 0.99);
        Product apples = new Product("apples", ProductUnit.KILO);
        catalog.addProduct(apples, 1.99);

        CashRegister teller = new CashRegister(catalog);
        teller.addSpecialOffer(
            apples,
            new PercentOffer(
                apples,
                10
            )
        );

        // TODO. These are not even testing the discount
        ShoppingCart cart = new ShoppingCart();
        cart.addItemQuantity(apples, 2.5);
        
        // ACT
        Receipt receipt = teller.checksOutArticlesFrom(cart);

        // ASSERT
        assertEquals(2.5 * 1.99 * 0.9, receipt.getTotalPrice(), 0.01);
        assertEquals(1, receipt.getDiscounts().size());
        assertEquals(1, receipt.getItems().size());
        ReceiptItem receiptItem = receipt.getItems().get(0);
        assertEquals(apples, receiptItem.getProduct());
        assertEquals(1.99, receiptItem.getPrice());
        assertEquals(2.5 * 1.99, receiptItem.getTotalPrice());
        assertEquals(2.5, receiptItem.getQuantity());
    }
}
