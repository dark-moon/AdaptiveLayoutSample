package com.example.adaptivelayoutsample.view;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.adaptivelayoutsample.R;
import com.example.adaptivelayoutsample.data.Product;
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
        if (intent.getExtras() != null) {
            int id = intent.getIntExtra("productId", 0);
            String name = intent.getStringExtra("productName");
            String description = intent.getStringExtra("productDescription");
            txtName.setText(name);
            txtDescription.setText(description);
            productToUpdate = new Product(id, name, description);
        }

        ProductDao productDao = ProductsDatabase.getInstance(this).productDao();

        btnSave.setOnClickListener(view -> {
            AddProductActivity.this.startActivity(new Intent(AddProductActivity.this, ProductsActivity.class));
            background.execute(() -> {
                if (productToUpdate == null) {
                    productDao.insert(new Product(txtName.getText().toString(), txtDescription.getText().toString()));
                } else {
                    productToUpdate.name = txtName.getText().toString();
                    productToUpdate.description = txtDescription.getText().toString();
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
    }
}