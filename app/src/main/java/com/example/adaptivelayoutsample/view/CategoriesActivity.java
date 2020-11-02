package com.example.adaptivelayoutsample.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.adaptivelayoutsample.CategoriesAdapter;
import com.example.adaptivelayoutsample.R;
import com.example.adaptivelayoutsample.data.entities.Category;
import com.example.adaptivelayoutsample.databinding.ActivityCategoriesBinding;

import java.util.ArrayList;
import java.util.List;

public class CategoriesActivity extends AppCompatActivity {

    CategoryViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityCategoriesBinding binding =
                DataBindingUtil.setContentView(this, R.layout.activity_categories);

        viewModel =
                new ViewModelProvider(this, new ViewModelProvider.AndroidViewModelFactory(this.getApplication()))
                .get(CategoryViewModel.class);

        List<Category> testList = new ArrayList<>();
        testList.add(new Category("Test", "test"));

        binding.categoriesRecyclerView.setHasFixedSize(true);
        CategoriesAdapter adapter = new CategoriesAdapter();
        adapter.setCategories(testList);

        viewModel.categoriesLiveData.observe(this, adapter::setCategories);

        binding.categoriesFab.setOnClickListener(v -> {
            startActivity(new Intent(CategoriesActivity.this, AddCategoryActivity.class));
        });

        adapter.setOnCategoryClickListener(category -> {
            Intent intent = new Intent(CategoriesActivity.this, ProductsActivity.class);
            intent.putExtra("categoryId", category.getId());
            startActivity(intent);
        });

        binding.categoriesRecyclerView.setAdapter(adapter);
        viewModel.onLoadCategories();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        viewModel.onLoadCategories();
    }
}