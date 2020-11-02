package com.example.adaptivelayoutsample.data.entities;

import androidx.room.Embedded;
import androidx.room.Relation;

import java.util.List;

public class CategoyWithProducts {
    @Embedded
    public Category category;
    @Relation(parentColumn = "id", entityColumn = "category_id")
    public List<Product> products;
}
