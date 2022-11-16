package edu.sungshin.toeat;

import static android.content.ContentValues.TAG;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.io.IOException;
import java.util.ArrayList;

public class UserAdapter3 {
    private Context mContext;
    private static SQLiteDatabase mDb;
    private UserDBActivityHelper3 mDbHelper;
    private String tableName="FoodDB3";

    public UserAdapter3(Context context)   //다른 클래스에서 Activity를 Context를 활용해 호출
    {
        this.mContext = context;
        mDbHelper = new UserDBActivityHelper3(mContext);
    }

    public UserAdapter3 createDatabase() throws SQLException  //DB 생성 코드
    {
        try
        {
            mDbHelper.createDataBase();
        }
        catch (IOException mIOException)  //예외 발생시 Log 출력
        {
            Log.e(TAG, mIOException.toString() + "  UnableToCreateDatabase");
            throw new Error("UnableToCreateDatabase");
        }
        return this;
    }

    public UserAdapter3 open() throws SQLException  //DB를 여는 코드
    {
        try
        {
            mDbHelper.openDataBase();
            mDbHelper.close();
            mDb = mDbHelper.getReadableDatabase();
        }
        catch (SQLException mSQLException)  //예외 발생시 처리 코드
        {
            Log.e(TAG, "open >>"+ mSQLException.toString());
            throw mSQLException;
        }
        return this;
    }

    public void close()
    {
        mDbHelper.close();
    }   //DB가 닫히도록 하는 코드

    public void insert(String no, String foodName, String expirationDate, String amount, String market, String memo){  //Insert
        mDb=mDbHelper.getWritableDatabase();
        ContentValues value=new ContentValues();
        value.put("no",no);
        value.put("foodName",foodName);
        value.put("expirationDate",expirationDate);
        value.put("amount",amount);
        value.put("market",market);
        value.put("memo",memo);
        mDb.insert("FoodDB3",null,value);  //사용자 DB에 사용자값 insert
    }

    public void delete(String foodName){
        mDb=mDbHelper.getWritableDatabase();
        mDb.delete("FoodDB3","foodName=?",new String[]{String.valueOf(foodName)});  //사용자 DB에 사용자값 delete

        ArrayList<Food> foodList= getTableData();

    }

    public void update(String foodName, String expirationDate, String amount, String market, String memo) {
        mDb=mDbHelper.getWritableDatabase();
        ContentValues value=new ContentValues();
        value.put("foodName",foodName);
        value.put("expirationDate",expirationDate);
        value.put("amount",amount);
        value.put("market",market);
        value.put("memo",memo);
        mDb.update("FoodDB3",value,"foodName=?",new String[]{String.valueOf(foodName)});
    }

    public ArrayList getTableData(){
        try{
            String sql="SELECT * FROM "+tableName;  //table 이름을 통해 사용자DB 불러옴
            ArrayList <Food> userList=new ArrayList<Food>();  //모델을 넣을 리스트 생성
            Food user=null;  //모델 선언
            Cursor mCur=mDb.rawQuery(sql,null);
            if(mCur!=null){
                while(mCur.moveToNext()){  //컬럼의 마지막까지로 커서 설정
                    user=new Food(mCur.getString(0),mCur.getString(1),mCur.getString(2), mCur.getString(3), mCur.getString(4), mCur.getString(5));
                    userList.add(user);  //리스트에 사용자 값 넣기
                }
            }
            return userList;
        }catch (SQLException mSQLException){
            Log.e(TAG,"getTestData >>"+mSQLException.toString());
            throw mSQLException;
        }
    }
}
