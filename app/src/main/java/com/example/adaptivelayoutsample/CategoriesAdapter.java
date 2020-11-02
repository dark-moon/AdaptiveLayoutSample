package com.example.adaptivelayoutsample;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.adaptivelayoutsample.data.entities.Category;
import com.example.adaptivelayoutsample.databinding.CategoryItemBinding;

import java.util.ArrayList;
import java.util.List;

public class CategoriesAdapter extends RecyclerView.Adapter<CategoriesAdapter.CategoriesViewHolder> {

    private List<Category> categories;
    private OnCategoryClickListener listener;
    public CategoriesAdapter() {
        categories = new ArrayList<>();
    }

    @NonNull
    @Override
    public CategoriesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CategoryItemBinding binding =
                DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.category_item, parent, false);

        return new CategoriesViewHolder(binding, listener);
    }

    public void setOnCategoryClickListener(OnCategoryClickListener listener) {
        this.listener = listener;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(@NonNull CategoriesViewHolder holder, int position) {
        holder.binding.setCategory(categories.get(position));

    }

    @Override
    public int getItemCount() {
        return categories.size();
    }

    class CategoriesViewHolder extends RecyclerView.ViewHolder {

        final CategoryItemBinding binding;
        private OnCategoryClickListener listener;

        public CategoriesViewHolder(@NonNull CategoryItemBinding binding, OnCategoryClickListener listener) {
            super(binding.getRoot());
            this.binding = binding;
            this.listener = listener;
            binding.getRoot().setOnClickListener((view) -> listener.onCategoryClicked(binding.getCategory()));
        }
    }

    public interface OnCategoryClickListener {
        public void onCategoryClicked(Category category);
    }
}
