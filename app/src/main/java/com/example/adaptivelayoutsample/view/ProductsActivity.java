package com.example.adaptivelayoutsample.view;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.example.adaptivelayoutsample.ProductRecyclerAdapter;
import com.example.adaptivelayoutsample.R;
import com.example.adaptivelayoutsample.data.Product;
import com.example.adaptivelayoutsample.databinding.ActivityProductsBinding;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class ProductsActivity extends AppCompatActivity {

    List<Product> products;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_products);
        ActivityProductsBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_products);

        ProductViewModel viewModel =
                new ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()))
                .get(ProductViewModel.class);

        products = new ArrayList<>();
        products.add(new Product("Sample item", "Just to test the updates"));

        binding.productRecycler.setHasFixedSize(true);
        ProductRecyclerAdapter productsAdapter = new ProductRecyclerAdapter(products);

        viewModel.productsLiveData.observe(this, productsAdapter::setProducts);

        productsAdapter.setOnProductClickListener((id, name, description) -> {
            Intent intent = new Intent(ProductsActivity.this, AddProductActivity.class);
            intent.putExtra("productId", id);
            intent.putExtra("productName", name);
            intent.putExtra("productDescription", description);
            startActivity(intent);
        });

        binding.productsFab.setOnClickListener(v -> startActivity(new Intent(ProductsActivity.this, AddProductActivity.class)));

        binding.productRecycler.setAdapter(productsAdapter);
        viewModel.onLoadProduct();

    }
}