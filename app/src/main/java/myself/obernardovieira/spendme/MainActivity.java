package myself.obernardovieira.spendme;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import myself.obernardovieira.spendme.Core.DataObjects.Category;
import myself.obernardovieira.spendme.Core.DataObjects.Spend;
import myself.obernardovieira.spendme.Core.SpendMeApp;
import myself.obernardovieira.spendme.Database.CategoryDataTable;
import myself.obernardovieira.spendme.Database.SpendDataTable;

public class MainActivity extends Activity {

    private SpendMeApp application;
    private ArrayList<Spend> spends;
    private MySpendsAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //
        CategoryDataTable.init(this);
        SpendDataTable.init(this);
        //
        ListView lv = (ListView) findViewById(R.id.lv_spends);
        application = (SpendMeApp)getApplication();
        spends = SpendDataTable.getAll();
        if(spends == null)
        {
            spends = new ArrayList<>();
        }
        adapter = new MySpendsAdapter();
        lv.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    @Override
    protected void onResume()
    {
        super.onResume();
        if(application.updateSpendsList)
        {
            application.updateSpendsList = false;
            spends = SpendDataTable.getAll();
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onMenuItemSelected(int featureId, MenuItem item)
    {
        if(item.getItemId() == R.id.item_categories)
        {
            Intent intent = new Intent(this, CategoriesActivity.class);
            startActivity(intent);
            return true;
        }
        super.onMenuItemSelected(featureId, item);
        return false;
    }

    public void onClickButtonAddSpend(View view)
    {
        Intent intent = new Intent(this, SpendActivity.class);
        startActivity(intent);
    }

    class MySpendsAdapter extends BaseAdapter
    {

        @Override
        public int getCount() {
            return spends.size();
        }

        @Override
        public Object getItem(int i) {
            return spends.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup)
        {
            View layout = getLayoutInflater().inflate(R.layout.element_spend_layout,null);

            float value = spends.get(i).getValue();
            Category category = spends.get(i).getCategory();
            String description = spends.get(i).getDescription();

            TextView category_char = (TextView)layout.findViewById(R.id.tv_spend_category_char);
            category_char.setText(category.getCharacter());
            category_char.setBackgroundColor(category.getColor());

            ((TextView)layout.findViewById(R.id.tv_spend_value)).setText(value + "€");
            if(description.length() > 0)
            {
                ((TextView)layout.findViewById(R.id.tv_spend_description)).setText(description);
            }
            else
            {
                ((TextView)layout.findViewById(R.id.tv_spend_description)).setText(" ");
            }
            return layout;
        }
    }
}
