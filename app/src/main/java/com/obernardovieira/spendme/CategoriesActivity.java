package com.obernardovieira.spendme;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class CategoriesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories);
    }

    public void goNewCategory(View view) {
        Intent intent = new Intent(CategoriesActivity.this, NewCategoryActivity.class);
        CategoriesActivity.this.startActivity(intent);
    }
}
