package edu.sungshin.myapplication;

import android.provider.BaseColumns;

public final class DataBases {


        public static final class CreateDB implements BaseColumns {
            public static final String FoodName = "foodname";
            public static final Integer ExpirationDate = Integer.valueOf("expirationDate");
            public static final Integer Amount = Integer.valueOf("amount");
            public static final String Market = "market";
            public static final String Memo = "memo";
            public static final String _TABLENAME0 = "foodtable";
            public static final String _CREATE0 = "create table if not exists " + _TABLENAME0 + "("
                    + _ID + " integer primary key autoincrement, "
                    + FoodName + " text not null , "
                    + ExpirationDate + " integer not null , "
                    + Amount + " integer not null , "
                    + Market + " text not null , "
                    + Memo + " text not null );";
        }

}
