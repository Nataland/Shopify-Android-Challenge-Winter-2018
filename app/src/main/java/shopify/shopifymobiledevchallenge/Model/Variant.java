package shopify.shopifymobiledevchallenge.Model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by natalie on 2018-09-11.
 */

public class Variant implements Serializable {
    @SerializedName("inventory_quantity")
    private Long inventory_quantity;

    public Variant(Long inventory_quantity) {
        this.inventory_quantity = inventory_quantity;
    }

    public Long getInventory_quantity() {
        return inventory_quantity;
    }
}
