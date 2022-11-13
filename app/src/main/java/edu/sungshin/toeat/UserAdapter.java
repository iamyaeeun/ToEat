package edu.sungshin.toeat;

import static android.content.ContentValues.TAG;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.EditText;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class UserAdapter {
    private Context mContext;
    private static SQLiteDatabase mDb;
    private UserDBActivityHelper mDbHelper;
    private String tableName="FoodData";

    public UserAdapter(Context context)   //다른 클래스에서 Activity를 Context를 활용해 호출
    {
        this.mContext = context;
        mDbHelper = new UserDBActivityHelper(mContext);
    }

    public UserAdapter createDatabase() throws SQLException  //DB 생성 코드
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

    public UserAdapter open() throws SQLException  //DB를 여는 코드
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
        mDb.insert("FoodData",null,value);  //사용자 DB에 사용자값 insert
    }

    public void delete(int no){
        mDb=mDbHelper.getWritableDatabase();
        mDb.delete("FoodData","no=?",new String[]{String.valueOf(no)});  //사용자 DB에 사용자값 delete
    }

    public static void update(EditText foodName, EditText expiration, EditText num, EditText market, EditText memo) {
        mDb.execSQL("UPDATE myTable SET foodName="+foodName+"expirationDate="+expiration+"amount="+num+"market="+market+"memo="+memo);
    }

    public ArrayList getTableData(){
        try{
            String sql="SELECT * FROM "+tableName;  //table 이름을 통해 사용자DB 불러옴
            ArrayList userList=new ArrayList();  //모델을 넣을 리스트 생성
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
