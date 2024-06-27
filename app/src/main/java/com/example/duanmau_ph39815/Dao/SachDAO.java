package com.example.duanmau_ph39815.Dao;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.duanmau_ph39815.database.DbHelper;
import com.example.duanmau_ph39815.model.Sach;
import com.example.duanmau_ph39815.model.ThanhVien;

import java.util.ArrayList;
import java.util.List;

public class SachDAO {

    private static DbHelper dbHelper;

    public SachDAO(Context context) {
        dbHelper = new DbHelper(context);
    }

    public static ArrayList<Sach> getAll() {
        ArrayList<Sach> list = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        try {
            Cursor cursor = db.rawQuery("SELECT * FROM Sach", null);
            if (cursor.getCount() > 0) {
                cursor.moveToFirst();
                while (cursor.moveToNext()) {
                    Sach sach = new Sach();
                    sach.setMaSach(cursor.getInt(0));
                    sach.setTenSach(cursor.getString(1)) ;
                    sach.setGiaThue(cursor.getInt(2));
                    sach.setMaLoai(cursor.getInt(3));
                    list.add(sach);
                }
                db.setTransactionSuccessful();
            }
        } catch (Exception e) {
            Log.i(TAG, "Lỗi", e);
        }
        return list;
    }
    public Sach getID(String id){
        String sql = "SELECT * FROM Sach where maSach=?";
        List<Sach> list = getAll();
        return list.get(0);
    }

    public long insert(Sach sach) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("tenSach", sach.getTenSach());
        values.put("giaThue", sach.getGiaThue());
        values.put("maLoai",sach.getMaLoai());
        return db.insert("Sach", null, values);
    }

    public int update(Sach sach) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("tenSach", sach.getTenSach());
        values.put("giaThue", sach.getGiaThue());
        values.put("maLoai",sach.getMaLoai());
        return db.update("Sach", values, "maSach=?", new String[]{String.valueOf(sach.getMaSach())});

    }

    public int delete(String id) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        return db.delete("Sach", "maSach=?"
                , new String[]{String.valueOf(id)});
    }
}
