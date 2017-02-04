package myself.obernardovieira.spendme;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import myself.obernardovieira.spendme.Core.DataObjects.Category;
import myself.obernardovieira.spendme.Core.SpendMeApp;
import myself.obernardovieira.spendme.Database.CategoryDataTable;

public class CategoriesActivity extends Activity {

    private SpendMeApp application;
    private ArrayList<Category> categories;
    private MyCategoriesAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catergories);
        ListView lv = (ListView) findViewById(R.id.lv_categories);
        application = (SpendMeApp)getApplication();
        categories = CategoryDataTable.getAll();
        adapter = new MyCategoriesAdapter();
        lv.setAdapter(adapter);
        updateCategoriesList();
    }

    @Override
    protected void onResume()
    {
        super.onResume();
        if(application.updateCategoriesList)
        {
            application.updateCategoriesList = false;
            categories = CategoryDataTable.getAll();
            updateCategoriesList();
        }
    }

    private void updateCategoriesList()
    {
        adapter.notifyDataSetChanged();
    }

    public void onClickButtonAddCategory(View view)
    {
        Intent intent = new Intent(this, CategoryActivity.class);
        startActivity(intent);
    }

    class MyCategoriesAdapter extends BaseAdapter
    {

        @Override
        public int getCount() {
            return categories.size();
        }

        @Override
        public Object getItem(int i) {
            return categories.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup)
        {
            View layout = getLayoutInflater().inflate(R.layout.element_category_layout,null);

            int color = categories.get(i).getColor();
            String name = categories.get(i).getName();
            String character = categories.get(i).getCharacter();

            ((TextView)layout.findViewById(R.id.tv_category_char)).setText(character);
            ((TextView)layout.findViewById(R.id.tv_category_char)).setBackgroundColor(color);
            ((TextView)layout.findViewById(R.id.tv_category_name)).setText(name);
            return layout;
        }
    }
}
