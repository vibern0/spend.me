package myself.obernardovieira.spendme.Database;

import android.provider.BaseColumns;

public class SpendMeContract {

    public SpendMeContract() { }

    public static class SpendsTable implements BaseColumns
    {
        public static final String TABLE_NAME = "spends";
        public static final String COLUMN_CATEGORY = "category";
        public static final String COLUMN_VALUE = "value";
        public static final String COLUMN_DESCRIPTION = "description";
    }

    public static class CategoriesTable implements BaseColumns
    {
        public static final String TABLE_NAME = "categories";
        public static final String COLUMN_COLOR = "color";
        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_CHARACTER = "character";
    }
}
