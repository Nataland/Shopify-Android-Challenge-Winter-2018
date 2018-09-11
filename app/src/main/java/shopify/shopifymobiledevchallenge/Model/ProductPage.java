package shopify.shopifymobiledevchallenge.Model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

import shopify.shopifymobiledevchallenge.Model.Product;

/**
 * Created by natalie on 2018-09-10.
 */

public class ProductPage implements Serializable {

    @SerializedName("products")
    private List<Product> products;

    public ProductPage(List<Product> products) {
        this.products = products;
    }

    public List<Product> getProducts() {
        return products;
    }
}
