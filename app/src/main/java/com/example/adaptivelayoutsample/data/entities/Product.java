package com.example.adaptivelayoutsample.data.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "table_products")
public class Product {
    @PrimaryKey(autoGenerate = true)
    private int _id;
    @ColumnInfo(name = "col_name")
    private String name;
    @ColumnInfo(name = "col_description")
    private String description;
    @ColumnInfo(name = "col_price")
    private double price;
    @ColumnInfo(name = "category_id")
    private int category_id;

    public Product() { }

    public Product(String name, String description) {
        this(0, name, description, 0.0, 0);
    }

    public Product(int _id, String name, String description) {
        this(_id, name, description, 0.0, 0);
    }

    public Product(int _id, String name, String description, double price) {
        this(_id, name, description, price, 0);
    }

    public Product(int _id, String name, String description, double price, int category_id) {
        this.set_id(_id);
        this.setName(name);
        this.setDescription(description);
        this.setPrice(price);
    }



    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getCategory_id() {
        return category_id;
    }

    public void setCategory_id(int category_id) {
        this.category_id = category_id;
    }
}
