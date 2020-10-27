package com.example.adaptivelayoutsample.data;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "table_products")
public class Product {
    @PrimaryKey(autoGenerate = true)
    public int _id;
    @ColumnInfo(name = "col_name")
    public String name;
    @ColumnInfo(name = "col_description")
    public String description;
    @ColumnInfo(name = "col_price")
    public double price;

    public Product() { }

    public Product(String name, String description) {
        this(0, name, description, 0.0);
    }

    public Product(int _id, String name, String description) {
        this(_id, name, description, 0.0);
    }

    public Product(int _id, String name, String description, double price) {
        this._id = _id;
        this.name = name;
        this.description = description;
        this.price = price;
    }
}
