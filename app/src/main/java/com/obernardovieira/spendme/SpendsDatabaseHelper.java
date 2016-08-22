package com.obernardovieira.spendme;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by obernardovieira
 * thanks to
 * https://github.com/codepath/android_guides/wiki/Local-Databases-with-SQLiteOpenHelper
 * for the amazing tutorial
 */
public class SpendsDatabaseHelper extends SQLiteOpenHelper {
    // Database Info
    private static final String DATABASE_NAME = "spendsDatabase";
    private static final int DATABASE_VERSION = 1;

    // Table Names
    private static final String TABLE_CATEGORIES = "categories";
    private static final String TABLE_SPENDS = "spends";

    // Category Table Columns
    private static final String KEY_CATEGORY_ID = "id";
    private static final String KEY_CATEGORY_NAME = "name";
    private static final String KEY_CATEGORY_CHAR = "char";

    // Spend Table Columns
    private static final String KEY_SPEND_ID = "id";
    private static final String KEY_SPEND_CATEGORY = "category";
    private static final String KEY_SPEND_VALUE = "value";

    public SpendsDatabaseHelper(Context context)
    {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Called when the database is created for the FIRST time.
    // If a database already exists on disk with the same DATABASE_NAME, this method will NOT be called.
    @Override
    public void onCreate(SQLiteDatabase db)
    {
        String CREATE_CATEGORIES_TABLE = "CREATE TABLE " + TABLE_CATEGORIES + "(" + KEY_CATEGORY_ID + " INTEGER PRIMARY KEY, " + KEY_CATEGORY_NAME + " TEXT, " + KEY_CATEGORY_CHAR + " TEXT)";
        String CREATE_SPENDS_TABLE = "CREATE TABLE " + TABLE_SPENDS + "(" + KEY_SPEND_ID + " INTEGER PRIMARY KEY, " + KEY_SPEND_CATEGORY + " INTEGER REFERENCES " + TABLE_CATEGORIES + ", " + KEY_SPEND_VALUE + "INTEGER)";

        db.execSQL(CREATE_CATEGORIES_TABLE);
        db.execSQL(CREATE_SPENDS_TABLE);
        Log.d("SpendsDatabaseHelper", "onCreate success!");
    }

    // Called when the database needs to be upgraded.
    // This method will only be called if a database already exists on disk with the same DATABASE_NAME,
    // but the DATABASE_VERSION is different than the version of the database that exists on disk.
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        Log.d("SpendsDatabaseHelper", "onCreate success!");
    }

    public boolean addCategory(String name, String character)
    {
        // Create and/or open the database for writing
        SQLiteDatabase db = getWritableDatabase();
        boolean success = false;

        // It's a good idea to wrap our insert in a transaction. This helps with performance and ensures
        // consistency of the database.
        db.beginTransaction();
        try
        {

            ContentValues values = new ContentValues();
            values.put(KEY_CATEGORY_NAME, name);
            values.put(KEY_CATEGORY_CHAR, character);

            // Notice how we haven't specified the primary key. SQLite auto increments the primary key column.
            db.insertOrThrow(TABLE_CATEGORIES, null, values);
            db.setTransactionSuccessful();
            success = true;
        }
        catch (Exception e)
        {
            Log.d("SpendsDatabaseHelper", "Error while trying to add post to database");
        }
        finally
        {
            db.endTransaction();
        }
        return success;
    }

    public List<String> getAllCategories()
    {
        List<String> categories = new ArrayList<String>();
        String tmp_name;
        String CATEGORIES_SELECT_QUERY = String.format("SELECT * FROM %s", TABLE_CATEGORIES);

        // "getReadableDatabase()" and "getWriteableDatabase()" return the same object (except under low
        // disk space scenarios)
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(CATEGORIES_SELECT_QUERY, null);

        try
        {
            if (cursor.moveToFirst())
            {
                do
                {
                    tmp_name = cursor.getString(cursor.getColumnIndex(KEY_CATEGORY_NAME));
                    categories.add(tmp_name);

                } while(cursor.moveToNext());
            }
        }
        catch (Exception e)
        {
            Log.d("SpendsDatabaseHelper", "Error while trying to get posts from database");
        }
        finally
        {
            if (cursor != null && !cursor.isClosed())
            {
                cursor.close();
            }
        }
        return categories;
    }
}