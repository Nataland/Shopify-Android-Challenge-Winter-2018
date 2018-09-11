package shopify.shopifymobiledevchallenge;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.net.URL;
import java.util.List;

/**
 * Created by natalie on 2018-09-10.
 */

public class CustomProductAdapter extends RecyclerView.Adapter<CustomProductAdapter.CustomViewHolder> {

    private List<Product> dataList;
    private Context context;

    public CustomProductAdapter(Context context, List<Product> dataList) {
        this.context = context;
        this.dataList = dataList;
    }

    class CustomViewHolder extends RecyclerView.ViewHolder {

        public final View view;

        TextView txtTitle;
        TextView quantityView;
        CardView cardView;
        ImageView productView;

        CustomViewHolder(View itemView) {
            super(itemView);
            view = itemView;
            txtTitle = view.findViewById(R.id.title);
            quantityView = view.findViewById(R.id.quantity);
            cardView = view.findViewById(R.id.card_view_friend);
            productView = view.findViewById(R.id.cover_image);
        }
    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.product_row, parent, false);
        return new CustomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final CustomViewHolder holder, final int position) {
        holder.txtTitle.setText(dataList.get(position).getTitle());
        holder.quantityView.setText("Quantity: " + getTotalQuantity(dataList.get(position).getVariants()));


        Thread thread = new Thread(new Runnable() {

            @Override
            public void run() {
                try {
                    URL url = new URL(dataList.get(position).getImage().getSrc());

                    Bitmap bmp = BitmapFactory.decodeStream(url.openConnection().getInputStream());
                    holder.productView.setImageBitmap(bmp);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        thread.start();
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    private Long getTotalQuantity(List<Variant> variants) {
        Long total = 0L;
        for (Variant variant : variants) {
            total += variant.getInventory_quantity();
        }
        return total;
    }

}
