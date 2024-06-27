package com.example.duanmau_ph39815.Dao;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.duanmau_ph39815.database.DbHelper;
import com.example.duanmau_ph39815.model.LoaiSach;
import com.example.duanmau_ph39815.model.Sach;


import java.util.ArrayList;
import java.util.List;

public class LoaiSachDAO {

    Context context;

    private static DbHelper dbHelper;

    public LoaiSachDAO(Context context) {
        dbHelper = new DbHelper(context);
    }

    public static ArrayList<LoaiSach> getAll() {
        ArrayList<LoaiSach> list = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        try {
            Cursor cursor = db.rawQuery("SELECT * FROM LoaiSach", null);
            if (cursor.getCount() > 0) {
                cursor.moveToFirst();
                while (!cursor.isAfterLast()) {
                    LoaiSach loaiSach = new LoaiSach();
                    loaiSach.setMaLoai(cursor.getInt(0));
                    loaiSach.setTenLoai(cursor.getString(1)); ;
                    list.add(loaiSach);
                    cursor.moveToNext();
                }
                db.setTransactionSuccessful();
            }
        } catch (Exception e) {
            Log.i(TAG, "Lỗi", e);
        }
        return list;
    }
    public LoaiSach getID(String id){
        String sql = "SELECT * FROM LoaiSach where maLoai=?";
        List<LoaiSach> list = getAll();
        return list.get(0);
    }

    public long insert(LoaiSach loaiSach) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("tenLoai", loaiSach.getTenLoai());
        return db.insert("LoaiSach", null, values);
    }

    public int update(LoaiSach loaiSach) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("tenLoai", loaiSach.getTenLoai());
        return db.update("LoaiSach", values, "maLoai=?", new String[]{String.valueOf(loaiSach.getMaLoai())});

    }

    public int delete(String id) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        return db.delete("LoaiSach", "maLoai=?"
                , new String[]{String.valueOf(id)});
    }


}
