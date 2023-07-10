package dojo.supermarket.model;


// TODO 3. This is not a valid class with roots in reality or metaphor. At least the name does not mean anything
public class ProductQuantity {

    private final Product product;
    private final double quantity;

    public ProductQuantity(Product product, double weight) {
        this.product = product;
        this.quantity = weight;
    }

    public Product getProduct() {
        return product;
    }

    public double getQuantity() {
        return quantity;
    }
}
