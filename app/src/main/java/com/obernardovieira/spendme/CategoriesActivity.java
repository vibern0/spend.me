package com.obernardovieira.spendme;

import android.content.Intent;
import android.opengl.Visibility;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class CategoriesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories);


        String[] categoryArray = { };

        ListView lv = (ListView) findViewById(R.id.lv_categories);
        TextView tv = (TextView) findViewById(R.id.tv_no_categories);

        if(categoryArray.length == 0) {
            lv.setVisibility(View.GONE);
            tv.setVisibility(View.VISIBLE);
        }
        else {
            ArrayAdapter adapter = new ArrayAdapter<String>(this, R.layout.activity_listview_categories, categoryArray);
            lv.setAdapter(adapter);

            lv.setVisibility(View.VISIBLE);
            tv.setVisibility(View.GONE);
        }

    }

    public void goNewCategory(View view) {
        Intent intent = new Intent(CategoriesActivity.this, NewCategoryActivity.class);
        CategoriesActivity.this.startActivity(intent);
    }
}
