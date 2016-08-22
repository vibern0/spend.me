package com.obernardovieira.spendme;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class NewCategoryActivity extends AppCompatActivity {

    private SpendsDatabaseHelper database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_category);

        database = new SpendsDatabaseHelper(this);
    }

    public void addNewCategory(View view) {

        String category_name;
        EditText et_cname;
        boolean success;
        Intent intent;

        et_cname = (EditText) findViewById(R.id.et_cname);
        category_name = et_cname.getText().toString();

        //
        success = database.addCategory(category_name, "A");
        if(success)
        {
            Toast.makeText(this, "Added successfully!", Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(this, "An error ocurred!", Toast.LENGTH_SHORT).show();
        }
        intent = new Intent(NewCategoryActivity.this, CategoriesActivity.class);
        NewCategoryActivity.this.startActivity(intent);
    }
}
