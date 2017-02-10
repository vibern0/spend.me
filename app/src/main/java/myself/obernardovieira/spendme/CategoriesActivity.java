package myself.obernardovieira.spendme;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import myself.obernardovieira.spendme.Core.DataObjects.Category;
import myself.obernardovieira.spendme.Core.SpendMeApp;
import myself.obernardovieira.spendme.Database.CategoryDataTable;
import myself.obernardovieira.spendme.Database.SpendMeContract;

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
        if(categories == null)
        {
            categories = new ArrayList<>();
        }
        adapter = new MyCategoriesAdapter();
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(adapterItemListener);
        lv.setOnItemLongClickListener(adapterItemLongListener);
        adapter.notifyDataSetChanged();
    }

    @Override
    protected void onResume()
    {
        super.onResume();
        if(application.updateCategoriesList)
        {
            application.updateCategoriesList = false;
            categories = CategoryDataTable.getAll();
            adapter.notifyDataSetChanged();
        }
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

    AdapterView.OnItemClickListener adapterItemListener = new AdapterView.OnItemClickListener()
    {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l)
        {
            Intent intent = new Intent(CategoriesActivity.this, CategoryActivity.class);
            intent.putExtra("category", i + 1);
            startActivity(intent);
        }
    };
    AdapterView.OnItemLongClickListener adapterItemLongListener = new AdapterView.OnItemLongClickListener()
    {
        @Override
        public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l)
        {
            final int clicked_id = i + 1;
            //
            AlertDialog.Builder builder = new AlertDialog.Builder(CategoriesActivity.this);
            builder.setTitle("Delete Category");
            builder.setMessage("Do you want to delete this category?");
            builder.setPositiveButton("Delete", new DialogInterface.OnClickListener()
            {
                public void onClick(DialogInterface dialog, int id)
                {
                    CategoryDataTable.remove(clicked_id);
                    dialog.dismiss();
                    adapter.notifyDataSetChanged();
                    Toast.makeText(CategoriesActivity.this, "Deleted!", Toast.LENGTH_SHORT).show();
                }
            });
            builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener()
            {
                public void onClick(DialogInterface dialog, int id)
                {
                    dialog.dismiss();
                }
            });
            AlertDialog alertDialog = builder.create();
            alertDialog.show();
            return true;
        }
    };
}
