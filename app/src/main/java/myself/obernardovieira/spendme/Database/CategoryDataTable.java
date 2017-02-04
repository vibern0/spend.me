package myself.obernardovieira.spendme.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import myself.obernardovieira.spendme.Core.DataObjects.Category;
import myself.obernardovieira.spendme.Database.SpendMeContract.*;

public class CategoryDataTable {

    private static SpendMeDBHelper mDbHelper;

    public static void init(Context context)
    {
        mDbHelper = new SpendMeDBHelper(context);
    }

    public static Category get(int id)
    {
        SQLiteDatabase db = mDbHelper.getReadableDatabase();

        String[] projection = {
                CategoriesTable.COLUMN_COLOR,
                CategoriesTable.COLUMN_NAME,
                CategoriesTable.COLUMN_CHARACTER
        };

        String selection = CategoriesTable._ID + " = ?";
        String[] selectionArgs = { Integer.toString(id) };

        Cursor c = db.query(
                CategoriesTable.TABLE_NAME,
                projection,
                selection,
                selectionArgs,
                null,
                null,
                null
        );

        c.moveToFirst();
        int color = c.getInt(
                c.getColumnIndexOrThrow(CategoriesTable.COLUMN_COLOR)
        );
        String name = c.getString(
                c.getColumnIndexOrThrow(CategoriesTable.COLUMN_NAME)
        );
        String character = c.getString(
                c.getColumnIndexOrThrow(CategoriesTable.COLUMN_CHARACTER)
        );

        return new Category(color, name, character);
    }

    public static List<Category> getAll()
    {
        List<Category> categories = new ArrayList<>();
        SQLiteDatabase db = mDbHelper.getReadableDatabase();

        String[] projection = {
                CategoriesTable.COLUMN_COLOR,
                CategoriesTable.COLUMN_NAME,
                CategoriesTable.COLUMN_CHARACTER
        };

        Cursor c = db.query(
                CategoriesTable.TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                null
        );

        c.moveToFirst();
        do
        {
            int color = c.getInt(
                    c.getColumnIndexOrThrow(CategoriesTable.COLUMN_COLOR)
            );
            String name = c.getString(
                    c.getColumnIndexOrThrow(CategoriesTable.COLUMN_NAME)
            );
            String character = c.getString(
                    c.getColumnIndexOrThrow(CategoriesTable.COLUMN_CHARACTER)
            );

            categories.add(new Category(color, name, character));
        } while(c.moveToNext());

        return categories;
    }

    public static boolean add(int color, String name, String character)
    {
        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(CategoriesTable.COLUMN_COLOR, color);
        values.put(CategoriesTable.COLUMN_NAME, name);
        values.put(CategoriesTable.COLUMN_CHARACTER, character);
        long newRowId = db.insert(CategoriesTable.TABLE_NAME, null, values);

        return (newRowId != -1);
    }

    public static boolean remove(int id)
    {
        SQLiteDatabase db = mDbHelper.getReadableDatabase();
        int _deleted;

        String selection = CategoriesTable._ID + " = ?";
        String[] selectionArgs = { Integer.toString(id) };
        _deleted = db.delete(CategoriesTable.TABLE_NAME, selection, selectionArgs);

        return (_deleted != 0);
    }
}
