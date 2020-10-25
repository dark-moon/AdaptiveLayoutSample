package com.example.adaptivelayoutsample;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.app.LauncherActivity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.adaptivelayoutsample.data.DbProductsHandler;
import com.example.adaptivelayoutsample.helper.BackgroundThread;
import com.google.android.material.textview.MaterialTextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "Main Activity";
    private EditText txtName, txtDescription;
    private Button btnSave, btnCancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();

        BackgroundThread backgroundThread = new BackgroundThread();
        backgroundThread.start();

        DbProductsHandler databaseHandler = new DbProductsHandler(MainActivity.this);
        SQLiteDatabase db = databaseHandler.getWritableDatabase();


        btnSave.setOnClickListener(view -> {
            BackgroundThread.MyHandler handler = backgroundThread.getHandler();
            handler.post(new Runnable() {
                @Override
                public void run() {
                    databaseHandler.insetProduct(new Product(txtName.getText().toString(), txtDescription.getText().toString()));
                }
            });
            MainActivity.this.startActivity(new Intent(MainActivity.this, ProductsActivity.class));
        });
    }

    private void initViews() {
        txtName = findViewById(R.id.txt_name);
        txtDescription = findViewById(R.id.txt_description);
        btnSave = findViewById(R.id.btn_save);
        btnCancel = findViewById(R.id.btnCancel);
    }
}