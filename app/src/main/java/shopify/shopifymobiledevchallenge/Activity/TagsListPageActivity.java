package shopify.shopifymobiledevchallenge.Activity;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import retrofit2.Call;
import retrofit2.Callback;

import android.support.v7.widget.LinearLayoutManager;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import retrofit2.Response;
import shopify.shopifymobiledevchallenge.Adapter.CustomAdapter;
import shopify.shopifymobiledevchallenge.Model.Product;
import shopify.shopifymobiledevchallenge.Model.ProductPage;
import shopify.shopifymobiledevchallenge.Model.Tag;
import shopify.shopifymobiledevchallenge.Network.GetDataService;
import shopify.shopifymobiledevchallenge.Network.RetrofitClientInstance;
import shopify.shopifymobiledevchallenge.R;

public class TagsListPageActivity extends AppCompatActivity {
    private CustomAdapter adapter;
    private RecyclerView recyclerView;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setTitle("Tags List Page");
        setContentView(R.layout.activity_tags_list_page);

        progressDialog = new ProgressDialog(TagsListPageActivity.this);
        progressDialog.setMessage("Loading....");
        progressDialog.show();

        // Create handle for the RetrofitInstance interface
        GetDataService service = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);

        Call<ProductPage> call = service.getAllProducts();
        call.enqueue(new Callback<ProductPage>() {

            @Override
            public void onResponse(Call<ProductPage> call, Response<ProductPage> response) {
                progressDialog.dismiss();
                generateDataList(response.body());
            }

            @Override
            public void onFailure(Call<ProductPage> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(TagsListPageActivity.this, "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    // Method to generate List of data using RecyclerView with custom adapter
    private void generateDataList(ProductPage photoList) {
        List<Product> products = photoList.getProducts();
        List<Tag> tags = new ArrayList<>();

        for (Product product : products) {
            // For every product, get the list of tags that it has, separated by a comma and a space
            List<String> tagsList = new ArrayList<>(Arrays.asList(product.getTags().split(", ")));

            // For every tag that a product has, if the tag is not created already, create the tag and add the product to the product list of the tag
            // If the tag is created, just add the product to the product list of the tag
            for (String tag : tagsList) {
                int exist = getTagExists(tag, tags);
                if (exist != -1) {
                    tags.get(exist).addProduct(product);
                } else {
                    tags.add(new Tag(tag, new ArrayList<Product>(Collections.singletonList(product))));
                }
            }
        }

        recyclerView = findViewById(R.id.customRecyclerView);
        adapter = new CustomAdapter(this, tags);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(TagsListPageActivity.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }

    // Check if a tag exists in a list of tags, if exist: return its position in the list, else return -1
    private int getTagExists(String tag, List<Tag> tagsList) {
        for (int i = 0; i < tagsList.size(); ++i) {
            if (tagsList.get(i).getTagName().equals(tag)) {
                return i;
            }
        }
        return -1;
    }
}
