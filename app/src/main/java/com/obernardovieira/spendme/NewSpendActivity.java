package com.obernardovieira.spendme;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class NewSpendActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_spend);
    }

    public void addNewSpend(View view)
    {
        int category, value;
        TextView tv_option;

        Spinner s_category = (Spinner) findViewById(R.id.spinner_categories);
        EditText et_value = (EditText) findViewById(R.id.et_value);

        value = Integer.parseInt(et_value.getText().toString());
        tv_option = (TextView)s_category.getSelectedItem();
        category = Integer.parseInt(tv_option.getText().toString());

        //
    }
}
