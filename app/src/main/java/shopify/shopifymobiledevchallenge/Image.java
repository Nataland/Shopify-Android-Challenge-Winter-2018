package shopify.shopifymobiledevchallenge;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by natalie on 2018-09-11.
 */

public class Image implements Serializable {
    @SerializedName("src")
    private String src;

    public Image(String src) {
        this.src = src;
    }

    public String getSrc() {
        return src;
    }
}
