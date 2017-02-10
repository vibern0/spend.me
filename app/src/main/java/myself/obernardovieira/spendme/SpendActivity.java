package myself.obernardovieira.spendme;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import myself.obernardovieira.spendme.Core.DataObjects.Category;
import myself.obernardovieira.spendme.Core.DataObjects.Spend;
import myself.obernardovieira.spendme.Core.SpendMeApp;
import myself.obernardovieira.spendme.Database.CategoryDataTable;
import myself.obernardovieira.spendme.Database.SpendDataTable;

public class SpendActivity extends Activity {

    private SpendMeApp application;
    private int editing_id;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spend);

        application = (SpendMeApp) getApplication();
        if(savedInstanceState == null)
        {
            Bundle extras = getIntent().getExtras();
            //
            ArrayList<Category> categories = CategoryDataTable.getAll();
            if(categories == null)
            {
                Toast.makeText(this, "There is no categories!", Toast.LENGTH_LONG).show();
            }
            else
            {
                loadSpinner(categories);
            }
            //
            if(extras == null)
            {
                getIntent().removeExtra("spend");
                editing_id = -1;
            }
            else
            {
                editing_id = extras.getInt("spend");

                Spend spend = SpendDataTable.get(editing_id);
                String description;
                //
                ((TextView)findViewById(R.id.et_spend_value)).
                        setText(String.valueOf(spend.getValue()));
                ((Spinner)findViewById(R.id.spinner_spend_category)).
                        setSelection(CategoryDataTable.get(spend.getCategory().getName()) - 1);
                description = spend.getDescription();
                if(description.length() > 0)
                {
                    ((TextView)findViewById(R.id.et_spend_description)).setText(description);
                }
                ((Button)findViewById(R.id.button_add)).setText("Update");
            }
        }
    }

    private void loadSpinner(ArrayList<Category> categories)
    {
        // Spinner element
        Spinner spinner = (Spinner) findViewById(R.id.spinner_spend_category);

        // Spinner click listener
        spinner.setOnItemSelectedListener(new SelectItemListener());

        // Spinner Drop down elements
        List<String> categories_name = new ArrayList<>();

        for(Category cs : categories)
        {
            categories_name.add(cs.getName());
        }

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, categories_name);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spinner.setAdapter(dataAdapter);
    }

    class SelectItemListener implements AdapterView.OnItemSelectedListener
    {
        @Override
        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l)
        {
            //
        }

        @Override
        public void onNothingSelected(AdapterView<?> adapterView)
        {
            //
        }
    }

    public void addSpend(View view)
    {
        EditText value = (EditText) findViewById(R.id.et_spend_value);
        Spinner spinner = (Spinner) findViewById(R.id.spinner_spend_category);
        EditText description = (EditText) findViewById(R.id.et_spend_description);

        if(editing_id == -1)
        {
            SpendDataTable.add(
                    Float.parseFloat(value.getText().toString()),
                    spinner.getSelectedItem().toString(),
                    description.getText().toString()
            );
        }
        else
        {
            SpendDataTable.update(editing_id,
                    Float.parseFloat(value.getText().toString()),
                    spinner.getSelectedItem().toString(),
                    description.getText().toString()
            );
        }
        application.updateSpendsList = true;
        finish();
        //
    }
}
