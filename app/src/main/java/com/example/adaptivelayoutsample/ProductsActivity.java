package com.example.adaptivelayoutsample;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.adaptivelayoutsample.data.DbProductsHandler;

import java.util.ArrayList;
import java.util.List;

public class ProductsActivity extends AppCompatActivity {

    List<Product> products;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products);
        products = new ArrayList<>();

        DbProductsHandler handler = new DbProductsHandler(this);
        handler.getWritableDatabase();

        Cursor cursor = handler.getAllProducts();
        while (cursor.moveToNext()) {
            products.add(new Product(cursor.getString(1), cursor.getString(2)));
        }

        RecyclerView productRecyclerView = findViewById(R.id.product_recycler);
        productRecyclerView.setHasFixedSize(true);

        productRecyclerView.setAdapter(new ProductRecyclerAdapter(products, (tvName, tvDescription) -> {
                Log.i("products activity", "onClick: Item clicked: " + tvName.getText().toString());
        }));


    }
}