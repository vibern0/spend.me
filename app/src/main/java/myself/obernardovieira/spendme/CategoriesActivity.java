package myself.obernardovieira.spendme;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import myself.obernardovieira.spendme.Core.SpendMeApp;

public class CategoriesActivity extends Activity {

    private SpendMeApp application;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catergories);
        application = (SpendMeApp)getApplication();
    }

    @Override
    protected void onResume()
    {
        super.onResume();
        if(application.updateCategoriesList)
        {
            updateCategoriesList();
        }
    }

    private void updateCategoriesList()
    {
        //
    }

    public void onClickButtonAddCategory(View view)
    {
        Intent intent = new Intent(this, CategoryActivity.class);
        startActivity(intent);
    }
}
