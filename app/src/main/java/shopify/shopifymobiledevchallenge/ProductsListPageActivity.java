package shopify.shopifymobiledevchallenge;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;

import java.util.List;

/**
 * Created by natalie on 2018-09-10.
 */

public class ProductsListPageActivity extends AppCompatActivity {
    private CustomProductAdapter adapter;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products_list_page);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_action_close);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Fetch products and tag name from previous activity
        Intent intent = getIntent();
        List<Product> products = (List<Product>) intent.getSerializableExtra("LIST");
        String tagName = intent.getStringExtra("NAME");

        getSupportActionBar().setTitle(tagName);
        generateDataList(products);
    }

    // Method to generate List of data using RecyclerView with custom adapter
    private void generateDataList(List<Product> products) {
        recyclerView = findViewById(R.id.customRecyclerView);
        adapter = new CustomProductAdapter(this, products);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(ProductsListPageActivity.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home/back button
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
