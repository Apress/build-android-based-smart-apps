/**
 * Created by Chinmoy_M on 4/10/2017.
 */
public class example1_DataHelper {
    private static final String DATABASE_NAME = "example1";
    private static final int DATABASE_VERSION = 4;
    private static final String TABLE_REG1 = "reg1";
    private Context context;
    private SQLiteDatabase db;
    public example1_DataHelper(Context context) {
        this.context = context;
        OpenHelper openHelper = new OpenHelper(this.context);
        this.db = openHelper.getWritableDatabase();
    }
    public long insert_reg1(String values)
    {
        long returnValue = 1;
        String executeString = "insert into " + TABLE_REG1 + " values(" + values +");";
        Log.d("insert",executeString);
        try {
            db.execSQL(executeString);
        }
        catch(SQLiteException e) {
            Log.e("Database error while inserting",e.toString());
            returnValue = 0;
        }
        return returnValue;
    }
    public long update_reg1(String pk, String Age, String Weight, String Gender, String Area, String Pincode)
    {
        long returnValue = 1;
        String executeString = "update " + TABLE_REG1 + " set "+ "Age = '" + Age + "'," + "Weight = '" + Weight + "'," + "Gender = '" + Gender + "'," + "Area = '" + Area + "'," + "Pincode = '" + Pincode + "'"  + " where Id = '" + pk + "' ;";
        Log.d("update",executeString);
        try {
            db.execSQL(executeString);
        }
        catch(SQLiteException e) {
            Log.e("Database error while updating",e.toString());
            returnValue = 0;
        }
        return returnValue;
    }
    public String getName_reg1(String pk)
    {
        String str = "";
        Cursor cursor = this.db.query (TABLE_REG1, new String[] {"Name"}, "Id" + "="+"?", new String[]{pk}, null, null, "ID desc");
        if (cursor.moveToFirst())
        {
            str = cursor.getString(0);
        }
        if (cursor != null && !cursor.isClosed()) {
            cursor.close();
        }
        return str;
    }
    public void deleteAll_reg1()
    {
        this.db.delete(TABLE_REG1, null, null);
    }
    public List<String> selectAll_reg1()
    {
        List<String>list = new ArrayList<String>();
        Cursor cursor = this.db.query (TABLE_REG1, new String[] { "Id" }, null, null, null, null, "ID desc");
        if (cursor.moveToFirst()) {
            do {
                list.add(cursor.getString(0));
            } while (cursor.moveToNext());
        }
        if (cursor != null && !cursor.isClosed()) {
            cursor.close();
        }
        return list;
    }
    public void prune(String tableName, String condition)
    {
        String sql="delete from "+ tableName + " where " + condition;
        db.rawQuery(sql, null).moveToFirst();
    }
    public void pruneAll(String[] tableName, String[] condition)
    {
        for(int i=0;i<tableName.length;i++){
            prune(tableName[i],condition[i]);
        }
    }

}
