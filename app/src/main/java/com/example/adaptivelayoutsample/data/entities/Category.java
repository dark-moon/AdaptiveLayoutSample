package com.example.adaptivelayoutsample.data.entities;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "table_categories")
public class Category {
    @ColumnInfo(name = "id")
    @PrimaryKey(autoGenerate = true)
    private int id;
    @NonNull
    @ColumnInfo(name = "col_name")
    private String name;
    @ColumnInfo(name = "col_description")
    private String description;

    public Category() {
        this("");
    }

    public Category(@NonNull String name) {
        this.setName(name);
        this.setDescription("");
    }

    public Category(@NonNull String name, String description) {
        this.setName(name);
        this.setDescription(description);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @NonNull
    public String getName() {
        return name;
    }

    public void setName(@NonNull String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
