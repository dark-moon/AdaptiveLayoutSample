package com.example.adaptivelayoutsample.view;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.adaptivelayoutsample.data.ProductsDatabase;
import com.example.adaptivelayoutsample.data.entities.Category;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CategoryViewModel extends AndroidViewModel {

    private final MutableLiveData<List<Category>> _categoriesLiveData;
    public final LiveData<List<Category>> categoriesLiveData;
    final ProductsDatabase database;
    ExecutorService background = Executors.newFixedThreadPool(2);

    public CategoryViewModel(@NonNull Application application) {
        super(application);
        _categoriesLiveData = new MutableLiveData<>();
        categoriesLiveData = _categoriesLiveData;
        database = ProductsDatabase.getInstance(application);
    }

    public void onLoadCategories() {
        background.execute(() -> {
            List<Category> categories = database.categoryDao().getAllCategories();
            _categoriesLiveData.postValue(categories);
        });
    }

    private List<Category> createDummyCategories() {
        List<Category> categoryList = new ArrayList<>();
        for (int i = 0; i <10; i++) {
            categoryList.add(new Category("Category " + (i + 1), "description " + i));
        }
        return categoryList;
    }



}
