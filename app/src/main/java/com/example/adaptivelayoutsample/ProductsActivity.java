package com.example.adaptivelayoutsample;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

public class ProductsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products);

//        String[] products = {"HP Pavillion",
//                "MacBook Pro 13`",
//                "Microsoft Mouse",
//                "Magic Mouse"
//        };

        Product[] products = {
                new Product("HP Pavillion", "HP Laptop"),
                new Product("MacBook Pro 13`", "Apple notebook"),
                new Product("Microsoft Mouse", "Wireless mouse from Microsoft"),
                new Product("Magic Mouse", "Apple magic mouse"),
        } ;

        CustomAdapter productAdapter = new CustomAdapter(this, products);

        ListView lvProducts = findViewById(R.id.list_products);
        lvProducts.setAdapter(productAdapter);

    }
}