package com.example.adaptivelayoutsample.view;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.adaptivelayoutsample.data.CategoryDao;
import com.example.adaptivelayoutsample.data.entities.Product;
import com.example.adaptivelayoutsample.data.ProductDao;
import com.example.adaptivelayoutsample.data.ProductsDatabase;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ProductViewModel extends AndroidViewModel {

    private final ExecutorService background;
    private List<Product> products;
    private final MutableLiveData<List<Product>> _productsLiveData;
    public final LiveData<List<Product>> productsLiveData;
    private final ProductDao productDao;
    private final CategoryDao categoryDao;


    public ProductViewModel(@NonNull Application application) {
        super(application);
        background = Executors.newFixedThreadPool(2);
        products = new ArrayList<>();
        _productsLiveData = new MutableLiveData<>();
        productsLiveData = _productsLiveData;
        ProductsDatabase db = ProductsDatabase.getInstance(application);
        productDao = db.productDao();
        categoryDao = db.categoryDao();
    }

    public void onLoadProduct() {
        background.execute(() -> {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            products = productDao.getAllProducts();
            _productsLiveData.postValue(products);
        });
    }

    public void onLoadCategoryProducts(int categoryId) {
        background.execute(() -> {
            products = categoryDao.getCategoryDetails(categoryId).products;
            _productsLiveData.postValue(products);
        });
    }

}
