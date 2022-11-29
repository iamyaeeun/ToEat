package edu.sungshin.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DbOpenHelper {
    private static final String DATABASE_NAME = "InnerDatabase(SQLite).db";
    private static final int DATABASE_VERSION = 1;
    public static SQLiteDatabase mDB;
    private DatabaseHelper mDBHelper;
    private Context mCtx;

    public DbOpenHelper(DbOpenHelper dbOpenHelper) {
        DbOpenHelper mDbOpenHelper = new DbOpenHelper(this);
        mDbOpenHelper.open();
        mDbOpenHelper.create();

        Cursor iCursor = mDbOpenHelper.selectColumns();
        while(iCursor.moveToNext()){
            String tempFoodName = iCursor.getString(iCursor.getColumnIndex("foodname"));
            Integer tempExpirationDate = iCursor.getInt(iCursor.getColumnIndex("expirationDate"));
            Integer tempAmount = iCursor.getInt(iCursor.getColumnIndex("amount"));
            String tempMarket = iCursor.getString(iCursor.getColumnIndex("market"));
            String tempMemo = iCursor.getString(iCursor.getColumnIndex("memo"));
            if(tempFoodName.equals("아맛나")){
                String Result = tempFoodName + "," +tempExpirationDate + "," + tempAmount + "," + tempMarket+ "," + tempMemo;
            }
        }
    }

    private class DatabaseHelper extends SQLiteOpenHelper {

        public DatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
            super(context, name, factory, version);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(DataBases.CreateDB._CREATE0);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + DataBases.CreateDB._TABLENAME0);
            onCreate(db);
        }
    }

    public DbOpenHelper(Context context) {
        this.mCtx = context;
    }

    public DbOpenHelper open() throws SQLException {
        mDBHelper = new DatabaseHelper(mCtx, DATABASE_NAME, null, DATABASE_VERSION);
        mDB = mDBHelper.getWritableDatabase();
        return this;
    }

    public void create() {
        mDBHelper.onCreate(mDB);
    }

    public void close() {
        mDB.close();
    }

    public long insertColumn(String foodname, Integer expirationDate, Integer amount, String market, String memo) {
        ContentValues values = new ContentValues();
        values.put(DataBases.CreateDB.FoodName, foodname);
        values.put(String.valueOf(DataBases.CreateDB.ExpirationDate), expirationDate);
        values.put(String.valueOf(DataBases.CreateDB.Amount), amount);
        values.put(DataBases.CreateDB.Market, market);
        values.put(DataBases.CreateDB.Memo, memo);
        return mDB.insert(DataBases.CreateDB._TABLENAME0, null, values);
    }

    public Cursor selectColumns() {
        return mDB.query(DataBases.CreateDB._TABLENAME0, null, null, null, null, null, null);
    }

    public Cursor sortColumn(String sort){
        Cursor c = mDB.rawQuery( "SELECT * FROM foodtable ORDER BY " + sort + ";", null);
        return c;
    }

    public boolean updateColumn(long id, String foodname, Integer expirationDate, Integer amount , String market, String memo){
        ContentValues values = new ContentValues();
        values.put(DataBases.CreateDB.FoodName, foodname);
        values.put(String.valueOf(DataBases.CreateDB.ExpirationDate), expirationDate);
        values.put(String.valueOf(DataBases.CreateDB.Amount), amount);
        values.put(DataBases.CreateDB.Market, market);
        values.put(DataBases.CreateDB.Memo, memo);
        return mDB.update(DataBases.CreateDB._TABLENAME0, values, "_id=" + id, null) > 0;
    }

    // Delete All
    public void deleteAllColumns() {
        mDB.delete(DataBases.CreateDB._TABLENAME0, null, null);
    }

    // Delete Column
    public boolean deleteColumn(long id){
        return mDB.delete(DataBases.CreateDB._TABLENAME0, "_id="+id, null) > 0;
    }
}


