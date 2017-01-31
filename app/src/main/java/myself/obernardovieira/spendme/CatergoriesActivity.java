package myself.obernardovieira.spendme;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class CatergoriesActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catergories);
    }

    public void onClickButtonAddCategory(View view)
    {
        Intent intent = new Intent(this, CategoryActivity.class);
        startActivity(intent);
    }
}
