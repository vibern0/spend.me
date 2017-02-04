package myself.obernardovieira.spendme.Core;

import android.app.Application;

public class SpendMeApp extends Application
{
    public boolean updateCategoriesList;
    public boolean updateSpendsList;
    @Override
    public void onCreate()
    {
        super.onCreate();
        updateCategoriesList = false;
        updateSpendsList = false;
    }
}
