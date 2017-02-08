package myself.obernardovieira.spendme.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;

import myself.obernardovieira.spendme.Core.DataObjects.Spend;
import myself.obernardovieira.spendme.Database.SpendMeContract.*;

/**
 * Created by bernardovieira on 07-02-2017.
 */

public class SpendDataTable {

    private static SpendMeDBHelper mDbHelper;

    public static void init(Context context)
    {
        mDbHelper = new SpendMeDBHelper(context);
    }

    public static Spend get(int id)
    {
        SQLiteDatabase db = mDbHelper.getReadableDatabase();

        String[] projection = {
                SpendsTable.COLUMN_VALUE,
                SpendsTable.COLUMN_CATEGORY,
                SpendsTable.COLUMN_DESCRIPTION
        };

        String selection = SpendsTable._ID + " = ?";
        String[] selectionArgs = { Integer.toString(id) };

        Cursor c = db.query(
                SpendsTable.TABLE_NAME,
                projection,
                selection,
                selectionArgs,
                null,
                null,
                null
        );

        c.moveToFirst();
        float value = c.getFloat(
                c.getColumnIndexOrThrow(SpendsTable.COLUMN_VALUE)
        );
        int id_category = c.getInt(
                c.getColumnIndexOrThrow(SpendsTable.COLUMN_CATEGORY)
        );
        String description = c.getString(
                c.getColumnIndexOrThrow(SpendsTable.COLUMN_DESCRIPTION)
        );

        return new Spend(value, CategoryDataTable.get(id_category), description);
    }

    public static ArrayList<Spend> getAll()
    {
        ArrayList<Spend> spends = new ArrayList<>();
        SQLiteDatabase db = mDbHelper.getReadableDatabase();

        String[] projection = {
                SpendsTable.COLUMN_VALUE,
                SpendsTable.COLUMN_CATEGORY,
                SpendsTable.COLUMN_DESCRIPTION
        };

        Cursor c = db.query(
                SpendsTable.TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                null
        );

        if(!c.moveToFirst())
        {
            return null;
        }

        do
        {
            float value = c.getFloat(
                    c.getColumnIndexOrThrow(SpendsTable.COLUMN_VALUE)
            );
            int id_category = c.getInt(
                    c.getColumnIndexOrThrow(SpendsTable.COLUMN_CATEGORY)
            );
            String description = c.getString(
                    c.getColumnIndexOrThrow(SpendsTable.COLUMN_DESCRIPTION)
            );

            spends.add(new Spend(value, CategoryDataTable.get(id_category), description));

        } while(c.moveToNext());

        return spends;
    }

    public static boolean add(float value, String category_name, String description)
    {
        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(SpendsTable.COLUMN_VALUE, value);
        values.put(SpendsTable.COLUMN_CATEGORY, CategoryDataTable.get(category_name));
        values.put(SpendsTable.COLUMN_DESCRIPTION, description);
        long newRowId = db.insert(SpendsTable.TABLE_NAME, null, values);

        return (newRowId != -1);
    }

    public static void remove(int id)
    {
        //
    }
}