package com.example.adaptivelayoutsample;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ProductRecyclerAdapter extends RecyclerView.Adapter<ProductRecyclerAdapter.ProductViewHolder> {

    List<Product> products;
    OnProductClickListener itemClickListener;

    public ProductRecyclerAdapter(List<Product> products, OnProductClickListener itemClickListener) {
        this.products = products;
        this.itemClickListener = itemClickListener;
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_item, parent, false);
        return new ProductViewHolder(view, itemClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        holder.txtName.setText(products.get(position).getName());
        holder.txtDescription.setText(products.get(position).getDescription());
    }

    @Override
    public int getItemCount() {
        return products.size();
    }


    public static class ProductViewHolder extends RecyclerView.ViewHolder {

        final public TextView txtName;
        final public TextView txtDescription;

        public ProductViewHolder(@NonNull View itemView, ProductRecyclerAdapter.OnProductClickListener listener) {
            super(itemView);
            txtName = itemView.findViewById(R.id.item_name);
            txtDescription = itemView.findViewById(R.id.item_description);
            itemView.setOnClickListener(v -> listener.onClick(txtName, txtDescription));
        }

    }
    public interface OnProductClickListener {
        public void onClick(TextView tvName, TextView tvDescription);
    }
}
