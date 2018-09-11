package shopify.shopifymobiledevchallenge;

import java.io.Serializable;
import java.util.List;

/**
 * Created by natalie on 2018-09-10.
 */

public class Tag implements Serializable {
    private String tagName;
    private List<Product> products;

    public Tag(String tagName, List<Product> products) {
        this.tagName = tagName;
        this.products = products;
    }

    public String getTagName() {
        return tagName;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void addProduct(Product product) {
        this.products.add(product);
    }
}
