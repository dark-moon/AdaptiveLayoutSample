package com.example.adaptivelayoutsample.view;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.adaptivelayoutsample.R;
import com.example.adaptivelayoutsample.data.entities.Product;
import com.example.adaptivelayoutsample.data.ProductDao;
import com.example.adaptivelayoutsample.data.ProductsDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class AddProductActivity extends AppCompatActivity {

    private static final String TAG = "Main Activity";
    private EditText txtName, txtDescription;
    private Button btnSave, btnCancel;
    Product productToUpdate = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);
        initViews();

        ExecutorService background = Executors.newFixedThreadPool(3);


        Intent intent = getIntent();
        int categoryId = intent.getIntExtra("categoryId", -1);
        if (intent.getExtras().getString("productName") != null) {
            int id = intent.getIntExtra("productId", 0);
            String name = intent.getStringExtra("productName");
            String description = intent.getStringExtra("productDescription");
            Double price = intent.getDoubleExtra("price", 0.0);
            txtName.setText(name);
            txtDescription.setText(description);
            productToUpdate = new Product(id, name, description, price, categoryId);
        }


        ProductDao productDao = ProductsDatabase.getInstance(this).productDao();

        btnSave.setOnClickListener(view -> {
            AddProductActivity.this.startActivity(new Intent(AddProductActivity.this, ProductsActivity.class));
            background.execute(() -> {
                if (productToUpdate == null) {
                    Product product = new Product(txtName.getText().toString(), txtDescription.getText().toString());
                    product.setCategory_id(categoryId);
                    productDao.insert(product);
                } else {
                    productToUpdate.setName(txtName.getText().toString());
                    productToUpdate.setDescription(txtDescription.getText().toString());
                    productDao.update(productToUpdate);
                }
            });
            finish();
        });

    }

    private void initViews() {
        txtName = findViewById(R.id.txt_name);
        txtDescription = findViewById(R.id.txt_description);
        btnSave = findViewById(R.id.btn_save);
        btnCancel = findViewById(R.id.btnCancel);

        btnCancel.setOnClickListener(v -> {
            finish();
        });
    }
}