package com.example.adaptivelayoutsample;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.adaptivelayoutsample.data.Product;

import java.util.List;

public class ProductRecyclerAdapter extends RecyclerView.Adapter<ProductRecyclerAdapter.ProductViewHolder> {

    List<Product> products;
    OnProductClickListener itemClickListener;

    public ProductRecyclerAdapter(List<Product> products) {
        this.products = products;
        this.itemClickListener = (id, name, description) -> { };
    }

    public void setOnProductClickListener(OnProductClickListener listener) {
        this.itemClickListener = listener;
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_item, parent, false);
        return new ProductViewHolder(view, itemClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        holder.txtName.setTag(products.get(position)._id);
        holder.txtName.setText(products.get(position).name);
        holder.txtDescription.setText(products.get(position).description);
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    public void setProducts(List<Product> products) {
        this.products = products;
        notifyDataSetChanged();
    }


    public static class ProductViewHolder extends RecyclerView.ViewHolder {

        final public TextView txtName;
        final public TextView txtDescription;

        public ProductViewHolder(@NonNull View itemView, ProductRecyclerAdapter.OnProductClickListener listener) {
            super(itemView);
            txtName = itemView.findViewById(R.id.item_name);
            txtDescription = itemView.findViewById(R.id.item_description);
            itemView.setOnClickListener(v -> listener.onClick((int) txtName.getTag(), txtName.getText().toString(), txtDescription.getText().toString()));
        }

    }
    public interface OnProductClickListener {
        public void onClick(int id, String name, String description);
    }
}
