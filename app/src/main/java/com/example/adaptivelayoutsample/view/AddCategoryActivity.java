package com.example.adaptivelayoutsample.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;

import com.example.adaptivelayoutsample.R;
import com.example.adaptivelayoutsample.data.CategoryDao;
import com.example.adaptivelayoutsample.data.ProductsDatabase;
import com.example.adaptivelayoutsample.data.entities.Category;
import com.example.adaptivelayoutsample.databinding.ActivityAddCategoryBinding;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class AddCategoryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityAddCategoryBinding binding =
                DataBindingUtil.setContentView(this, R.layout.activity_add_category);

        ExecutorService backgroundService = Executors.newFixedThreadPool(2);

        ProductsDatabase database = ProductsDatabase.getInstance(this);
        CategoryDao categoryDao = database.categoryDao();

        binding.btnCategorySave.setOnClickListener(view -> {
            backgroundService.execute(() -> {
                categoryDao.insertCategory(new Category(binding.etCategoryName.getText().toString()
                        , binding.etCategoryDescription.getText().toString()));
            });
            finish();
        });
    }
}