package shopify.shopifymobiledevchallenge.Model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by natalie on 2018-09-10.
 */

public class Product implements Serializable {

    @SerializedName("id")
    private Long id;

    @SerializedName("title")
    private String title;

    @SerializedName("tags")
    private String tags;

    @SerializedName("variants")
    private List<Variant> variants;

    @SerializedName("image")
    private Image image;

    public Product(Long id, String title, String tags, List<Variant> variants, Image image) {
        this.id = id;
        this.title = title;
        this.tags = tags;
        this.variants = variants;
        this.image = image;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getTags() {
        return tags;
    }

    public List<Variant> getVariants() {
        return variants;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Image getImage() {
        return image;
    }
}
