package com.example.adaptivelayoutsample.view;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import com.example.adaptivelayoutsample.ProductRecyclerAdapter;
import com.example.adaptivelayoutsample.R;
import com.example.adaptivelayoutsample.data.entities.Product;
import com.example.adaptivelayoutsample.databinding.ActivityProductsBinding;

import java.util.ArrayList;
import java.util.List;

public class ProductsActivity extends AppCompatActivity {

    List<Product> products;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityProductsBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_products);

        ProductViewModel viewModel =
                new ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()))
                .get(ProductViewModel.class);

        int category_id = getIntent().getIntExtra("categoryId", -1);

        products = new ArrayList<>();

        binding.productRecycler.setHasFixedSize(true);
        ProductRecyclerAdapter productsAdapter = new ProductRecyclerAdapter(products);

        viewModel.productsLiveData.observe(this, productsAdapter::setProducts);


        binding.productsFab.setOnClickListener(v -> {
            Intent intent = new Intent(ProductsActivity.this, AddProductActivity.class);
            intent.putExtra("categoryId", category_id);
            startActivity(new Intent(intent));
        });

        binding.productRecycler.setAdapter(productsAdapter);
        viewModel.onLoadCategoryProducts(category_id);
    }
}