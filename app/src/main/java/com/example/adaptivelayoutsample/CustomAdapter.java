package com.example.adaptivelayoutsample;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.ListAdapter;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;

import java.util.List;
import java.util.Map;

public class CustomAdapter extends ArrayAdapter {

    Context context;

    public CustomAdapter(@NonNull Context context, @NonNull Product[] objects) {
        super(context, R.layout.product_item, objects);
        this.context = context;
    }

    @Override
    public int getCount() {
        return super.getCount();
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(context);
        ConstraintLayout view = (ConstraintLayout) inflater.inflate(R.layout.product_item, parent, false);
        TextView tvItem = view.findViewById(R.id.item_name);
        TextView tvDescription = view.findViewById(R.id.item_description);

        tvItem.setText(((Product) getItem(position)).getName());
        tvDescription.setText(((Product) getItem(position)).getDescription());
        return view;
    }
}
