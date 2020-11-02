package com.example.adaptivelayoutsample;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.adaptivelayoutsample.data.entities.Product;
import com.example.adaptivelayoutsample.databinding.ProductItemBinding;

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
        ProductItemBinding binding =
                DataBindingUtil.inflate(
                        LayoutInflater.from(parent.getContext()),
                        R.layout.product_item,
                        parent,
                        false);
        return new ProductViewHolder(binding, itemClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        holder.binding.setProduct(products.get(position));
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

        final ProductItemBinding binding;

        public ProductViewHolder(@NonNull ProductItemBinding binding, ProductRecyclerAdapter.OnProductClickListener listener) {
            super(binding.getRoot());
            this.binding = binding;
            itemView.setOnClickListener(v -> listener.onClick(binding.getProduct().get_id(), binding.getProduct().getName(), binding.getProduct().getDescription()));
        }

    }
    public interface OnProductClickListener {
        public void onClick(int id, String name, String description);
    }
}
