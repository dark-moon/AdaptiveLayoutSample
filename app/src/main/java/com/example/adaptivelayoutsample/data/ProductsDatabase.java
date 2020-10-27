package com.example.adaptivelayoutsample.data;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(version = 1, entities = {Product.class}, exportSchema = false)
public abstract class ProductsDatabase extends RoomDatabase {

    private static volatile ProductsDatabase instance;
    private static Object lock = new Object();

    public ProductsDatabase() {}
    public static ProductsDatabase getInstance(Context context) {
        if (instance == null) {
            synchronized (lock) {
                if (instance == null) {
                    instance = Room.databaseBuilder(context.getApplicationContext(), ProductsDatabase.class, "products_database")
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return instance;
    }

    public abstract ProductDao productDao();
}
