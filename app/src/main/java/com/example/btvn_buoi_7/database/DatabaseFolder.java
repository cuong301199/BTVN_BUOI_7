package com.example.btvn_buoi_7.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import com.example.btvn_buoi_7.folder.FolderModel;

import java.util.ArrayList;
import java.util.List;

public class DatabaseFolder extends SQLiteOpenHelper {


    private static final String DATABASE_NAME = "myfolder.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "folder";
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_DISCRIPTION = "discription";

    public DatabaseFolder(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sql = "CREATE TABLE IF NOT EXISTS "+TABLE_NAME +"("+
                KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"+
                KEY_NAME +" VACHAR(200),"+
                KEY_DISCRIPTION +"  VACHAR(200) )";


        sqLiteDatabase.execSQL(sql);

        Log.d("AAA", "create table");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
    }

    public List<FolderModel> getAllFolder(){
        List<FolderModel> listFolder = new ArrayList<>();

        SQLiteDatabase db = getReadableDatabase();
        Cursor data = db.rawQuery("SELECT * FROM "+ TABLE_NAME +"",null);
        while (data.moveToNext()){
            int id = data.getInt(0);
            String name = data.getString(1);
            String discription = data.getString(2);
            listFolder.add(new FolderModel(id,name,discription));
        }
        return listFolder;
    }

    public void insertFolder(String name, String discription){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("INSERT INTO "+TABLE_NAME+"(name,discription) VALUES (?,?)",new String[]{name,discription});
        Log.d("AAA","thêm mới dữ liệu thành công");

    }

    public void updateFolder(FolderModel folder){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("UPDATE "+TABLE_NAME+" SET "+
                KEY_NAME+" = ?,"+
                KEY_DISCRIPTION+" = ? WHERE "+
                KEY_ID+" = ?", new String[]{folder.getName(),folder.getDescription(), folder.getId()+""});
    }

    public void deleteFolder(FolderModel folder){
        SQLiteDatabase db = getWritableDatabase();
        String sql = "DELETE FROM "+TABLE_NAME+" WHERE "+KEY_ID+"=? ";
        db.execSQL(sql,new String[]{folder.getId()+""});
    }
}
