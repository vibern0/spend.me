package myself.obernardovieira.spendme;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

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
        lv.setOnItemClickListener(adapterItemListener);
        lv.setOnItemLongClickListener(adapterItemLongListener);
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

    AdapterView.OnItemClickListener adapterItemListener = new AdapterView.OnItemClickListener()
    {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l)
        {
            Intent intent = new Intent(MainActivity.this, SpendActivity.class);
            intent.putExtra("spend", i + 1);
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
            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
            builder.setTitle("Delete Spend");
            builder.setMessage("Do you want to delete this spend?");
            builder.setPositiveButton("Delete", new DialogInterface.OnClickListener()
            {
                public void onClick(DialogInterface dialog, int id)
                {
                    SpendDataTable.remove(clicked_id);
                    dialog.dismiss();
                    adapter.notifyDataSetChanged();
                    Toast.makeText(MainActivity.this, "Deleted!", Toast.LENGTH_SHORT).show();
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
