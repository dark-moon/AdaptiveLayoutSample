package com.example.adaptivelayoutsample.data;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.adaptivelayoutsample.data.entities.Category;
import com.example.adaptivelayoutsample.data.entities.CategoyWithProducts;
import com.example.adaptivelayoutsample.data.entities.Product;

import java.util.List;

@Dao
public interface ProductDao {

    @Insert
    void insert(Product product);

    @Insert
    void insert(List<Product> products);

    @Update
    void update(Product product);

    @Delete
    void delete(Product product);

    @Query("SELECT * FROM table_products")
    List<Product> getAllProducts();

    @Query("SELECT * FROM table_products WHERE _id = :id")
    Product getProductById(int id);

    @Query("SELECT * FROM table_products WHERE col_price = :maxPrice")
    List<Product> getProduct(double maxPrice);

}
