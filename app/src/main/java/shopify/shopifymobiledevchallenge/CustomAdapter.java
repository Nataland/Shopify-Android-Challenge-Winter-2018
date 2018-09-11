package shopify.shopifymobiledevchallenge;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.io.Serializable;
import java.util.List;

/**
 * Created by natalie on 2018-09-10.
 */

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.CustomViewHolder> {
    private List<Tag> dataList;
    private Context context;

    public CustomAdapter(Context context, List<Tag> dataList) {
        this.context = context;
        this.dataList = dataList;
    }

    class CustomViewHolder extends RecyclerView.ViewHolder {

        public final View view;

        TextView txtTitle;
        CardView cardView;

        CustomViewHolder(View itemView) {
            super(itemView);
            view = itemView;
            txtTitle = view.findViewById(R.id.title);
            cardView = view.findViewById(R.id.card_view_friend);
        }
    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.custom_row, parent, false);
        return new CustomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CustomViewHolder holder, final int position) {
        holder.txtTitle.setText(dataList.get(position).getTagName());

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ProductsListPageActivity.class);

                intent.putExtra("LIST", (Serializable) dataList.get(position).getProducts());
                intent.putExtra("NAME", dataList.get(position).getTagName());

                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }
}
