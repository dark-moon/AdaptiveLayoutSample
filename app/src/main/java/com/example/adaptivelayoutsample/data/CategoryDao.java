package com.example.adaptivelayoutsample.data;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;

import com.example.adaptivelayoutsample.data.entities.Category;
import com.example.adaptivelayoutsample.data.entities.CategoyWithProducts;

import java.util.List;

@Dao
public interface CategoryDao {

    @Insert
    void insertCategory(Category category);

    @Insert
    void insertCategories(List<Category> categories);

    @Delete
    void clear(List<Category> categories);

    @Query("SELECT * FROM table_categories")
    List<Category> getAllCategories();

    @Transaction
    @Query("SELECT * FROM table_categories")
    List<CategoyWithProducts> getCategoriesWithProducts();

    @Transaction
    @Query("SELECT * FROM table_categories WHERE id = :categoryId")
    CategoyWithProducts getCategoryDetails(int categoryId);
}
