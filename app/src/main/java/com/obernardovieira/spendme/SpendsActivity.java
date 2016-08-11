package com.obernardovieira.spendme;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class SpendsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spends);

        String[] mobileArray = {"Android","IPhone","WindowsMobile","Blackberry","WebOS","Ubuntu","Windows7","Max OS X",
                "Android","IPhone","WindowsMobile","Blackberry","WebOS","Ubuntu","Windows7","Max OS X"};
        ArrayAdapter adapter = new ArrayAdapter<String>(this, R.layout.activity_listview_spends, mobileArray);

        ListView listView = (ListView) findViewById(R.id.lv_spends);
        listView.setAdapter(adapter);
    }

    public void goNewSpend(View view) {
        Intent intent = new Intent(SpendsActivity.this, NewSpendActivity.class);
        SpendsActivity.this.startActivity(intent);
    }
}
