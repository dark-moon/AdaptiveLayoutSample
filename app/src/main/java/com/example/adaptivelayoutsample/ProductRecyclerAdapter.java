package com.example.adaptivelayoutsample;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ProductRecyclerAdapter extends RecyclerView.Adapter<ProductRecyclerAdapter.ProductViewHolder> {

    Product[] products;

    public ProductRecyclerAdapter(Product[] products) {
        this.products = products;
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_item, parent, false);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        holder.txtName.setText(products[position].getName());
        holder.txtDescription.setText(products[position].getDescription());
    }

    @Override
    public int getItemCount() {
        return products.length;
    }

    public class ProductViewHolder extends RecyclerView.ViewHolder {

        final public TextView txtName;
        final public TextView txtDescription;

        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);
            txtName = itemView.findViewById(R.id.item_name);
            txtDescription = itemView.findViewById(R.id.item_description);
        }
    }
}
