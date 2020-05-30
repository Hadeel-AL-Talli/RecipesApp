package com.example.recipesapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

public class Categories extends AppCompatActivity {
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    CategoriesAdapter categoriesAdapter;
    int [] arr = {R.drawable.breakfast
            ,R.drawable.chicken
            ,R.drawable.cookies
            ,R.drawable.pasta
            ,R.drawable.dinner
            ,R.drawable.seafood
            ,R.drawable.steak};



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories);
        setTitle("Categories");

         recyclerView = findViewById(R.id.recycler_view_categories);
        layoutManager = new GridLayoutManager(this, 2 );
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
         categoriesAdapter = new CategoriesAdapter(arr);
         recyclerView.setAdapter(categoriesAdapter);
         recyclerView.setHasFixedSize(true);


    }
}
