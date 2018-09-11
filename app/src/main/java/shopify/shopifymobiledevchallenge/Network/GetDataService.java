package shopify.shopifymobiledevchallenge.Network;

import retrofit2.Call;
import retrofit2.http.GET;
import shopify.shopifymobiledevchallenge.Model.ProductPage;

/**
 * Created by natalie on 2018-09-10.
 */

public interface GetDataService {
    @GET("/admin/products.json?page=1&access_token=c32313df0d0ef512ca64d5b336a0d7c6")
    Call<ProductPage> getAllProducts();
}
