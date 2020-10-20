package com.example.adaptivelayoutsample;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.ListView;

public class ProductsActivity extends AppCompatActivity {

    Product[] products = {
            new Product("HP Pavillion", "HP Laptop"),
            new Product("MacBook Pro 13`", "Apple notebook"),
            new Product("MacBook Pro 13`", "Apple notebook"),
            new Product("MacBook Pro 13`", "Apple notebook"),
            new Product("MacBook Pro 13`", "Apple notebook"),
            new Product("MacBook Pro 13`", "Apple notebook"),
            new Product("MacBook Pro 13`", "Apple notebook"),
            new Product("MacBook Pro 13`", "Apple notebook"),
            new Product("MacBook Pro 13`", "Apple notebook"),
            new Product("MacBook Pro 13`", "Apple notebook"),
            new Product("MacBook Pro 13`", "Apple notebook"),
            new Product("MacBook Pro 13`", "Apple notebook"),
            new Product("Microsoft Mouse", "Wireless mouse from Microsoft"),
            new Product("Microsoft Mouse", "Wireless mouse from Microsoft"),
            new Product("Microsoft Mouse", "Wireless mouse from Microsoft"),
            new Product("Microsoft Mouse", "Wireless mouse from Microsoft"),
            new Product("Microsoft Mouse", "Wireless mouse from Microsoft"),
            new Product("Microsoft Mouse", "Wireless mouse from Microsoft"),
            new Product("Microsoft Mouse", "Wireless mouse from Microsoft"),
            new Product("Microsoft Mouse", "Wireless mouse from Microsoft"),
            new Product("Microsoft Mouse", "Wireless mouse from Microsoft"),
            new Product("Magic Mouse", "Apple magic mouse"),
    } ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products);

        RecyclerView productRecyclerView = findViewById(R.id.product_recycler);
        productRecyclerView.setHasFixedSize(true);

        productRecyclerView.setAdapter(new ProductRecyclerAdapter(products));
    }
}