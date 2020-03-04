/**
 * Created by Chinmoy_M on 4/10/2017.
 */
private static class OpenHelper extends SQLiteOpenHelper {
    OpenHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }@Override
    public void onCreate(SQLiteDatabase db) {
        try {
            String execStr;
            execStr = "CREATE TABLE " + TABLE_REG1 + " (Id varchar(60) not null, Name varchar(60) not null, Age int(3) not null, Weight int(3) not null, Gender varchar(60) not null, Area varchar(60) not null, Pincode int(6) not null, PRIMARY KEY (Id) )";
            Log.d("example1_DataHelper \n",execStr);
            db.execSQL(execStr);
        }catch(SQLiteException e) {
            Log.e("Database error",e.toString());
        }
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w("Example", "Upgrading database, this will drop tables and recreate.");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_REG1);
        onCreate(db);
    }
}

