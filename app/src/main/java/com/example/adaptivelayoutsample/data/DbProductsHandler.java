package com.example.adaptivelayoutsample.data;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.adaptivelayoutsample.Product;


public class DbProductsHandler extends SQLiteOpenHelper {
    private static final String DB_NAME = "db_products";
    private static final int DB_VERSION = 1;

    public static final String PRODUCT_TABLE_NAME = "Products";
    public static final String PRODUCT_COLUMN_ID = "_id";
    public static final String PRODUCT_COLUMN_NAME = "name";
    public static final String PRODUCT_COLUMN_DESC = "description";

    private SQLiteDatabase db;

    public DbProductsHandler(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createProducts = "CREATE TABLE " + PRODUCT_TABLE_NAME +
                "(" + PRODUCT_COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                PRODUCT_COLUMN_NAME + " TEXT, " +
                PRODUCT_COLUMN_DESC + " TEXT);";
        db.execSQL(createProducts);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
        this.db = db;
    }

    public void insetProduct(Product product) {
        StringBuilder insertStatement = new StringBuilder("INSERT INTO ")
                .append(DbProductsHandler.PRODUCT_TABLE_NAME).append(" (")
                .append(DbProductsHandler.PRODUCT_COLUMN_NAME).append(", ")
                .append(DbProductsHandler.PRODUCT_COLUMN_DESC)
                .append(") VALUES('")
                .append(product.getName())
                .append("', '")
                .append(product.getDescription())
                .append("');");

        db.execSQL(insertStatement.toString());
    }

    public void updateProduct(Product oldProduct, Product newProduct) {
        updateProduct(oldProduct, newProduct.getName(), newProduct.getDescription());
    }

    public void updateProduct(Product product, String name, String description) {
        StringBuilder updateStatement = new StringBuilder("UPDATE ")
                .append(DbProductsHandler.PRODUCT_TABLE_NAME)
                .append("SET " )
                .append(DbProductsHandler.PRODUCT_COLUMN_NAME)
                .append("= ").append(name)
                .append(", ").append(DbProductsHandler.PRODUCT_COLUMN_DESC)
                .append(" = ")
                .append(description)
                .append("WHERE ")
                .append(DbProductsHandler.PRODUCT_COLUMN_NAME).append(" = ")
                .append(product.getName());
        db.execSQL(updateStatement.toString());
    }

    public Cursor getAllProducts() {
        return db.rawQuery("SELECT * FROM " + DbProductsHandler.PRODUCT_TABLE_NAME, null);
    }
}
