package myself.obernardovieira.spendme;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
            Intent intent = new Intent(this, CatergoriesActivity.class);
            startActivity(intent);
            return true;
        }
        super.onMenuItemSelected(featureId, item);
        return false;
    }
}
