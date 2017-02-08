package myself.obernardovieira.spendme.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import myself.obernardovieira.spendme.Database.SpendMeContract.*;

public class SpendMeDBHelper extends SQLiteOpenHelper
{

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "spendme.db";
    //
    private static final String SQL_CREATE_SPENDS_TABLES =
            "CREATE TABLE " + SpendsTable.TABLE_NAME + " (" +
                    SpendsTable._ID + " INTEGER PRIMARY KEY," +
                    SpendsTable.COLUMN_CATEGORY + " TEXT, " +
                    SpendsTable.COLUMN_VALUE + " INT, " +
                    SpendsTable.COLUMN_DESCRIPTION + " TEXT)";

    private static final String SQL_DELETE_SPENDS_TABLES =
            "DROP TABLE IF EXISTS " + SpendsTable.TABLE_NAME;
    //
    private static final String SQL_CREATE_CATEGORIES_TABLES =
            "CREATE TABLE " + CategoriesTable.TABLE_NAME + " (" +
                    CategoriesTable._ID + " INTEGER PRIMARY KEY," +
                    CategoriesTable.COLUMN_COLOR+ " INT, " +
                    CategoriesTable.COLUMN_NAME + " TEXT, " +
                    CategoriesTable.COLUMN_CHARACTER + " TEXT)";

    private static final String SQL_DELETE_CATEGORIES_TABLES =
            "DROP TABLE IF EXISTS " + CategoriesTable.TABLE_NAME;

    public SpendMeDBHelper(Context context)
    {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    public void onCreate(SQLiteDatabase db)
    {
        db.execSQL(SQL_CREATE_SPENDS_TABLES);
        db.execSQL(SQL_CREATE_CATEGORIES_TABLES);
    }
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        db.execSQL(SQL_DELETE_SPENDS_TABLES);
        db.execSQL(SQL_DELETE_CATEGORIES_TABLES);
        onCreate(db);
    }
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        onUpgrade(db, oldVersion, newVersion);
    }
}
