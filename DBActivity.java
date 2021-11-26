package com.example.phonebook;

import androidx.appcompat.app.AppCompatActivity;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Toast;

public class DBActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dbactivity);
    }

    protected  interface OnQuerySuccess{
        public void onSuccess();
    }

    protected void ExecSQL(String SQL, Object[] args, OnQuerySuccess onQuerySuccess) throws Exception{
        SQLiteDatabase db = SQLiteDatabase.openOrCreateDatabase(getFilesDir().getPath()+"/PhoneBook.db",null);
        if (args != null)
            db.execSQL(SQL, args);
        else
            db.execSQL(SQL);
        db.close();
        onQuerySuccess.onSuccess();
    }

    protected  void InitDB() throws Exception{
        ExecSQL("CREATE TABLE IF NOT EXISTS PHONEBOOK(" +
                "ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                "FIRSTNAME TEXT NOT NULL," +
                "LASTNAME TEXT NULL," +
                "PHONE_NUM TEXT NOT NULL," +
                "DATE_OF_BIRTH TEXT  NULL," +
                "EMAIL TEXT NULL," +
                "UNIQUE(NAME, PHONE_NUM))",null, () -> Toast.makeText(getApplicationContext(),"DB Init successful", Toast.LENGTH_LONG).show());
    }
}