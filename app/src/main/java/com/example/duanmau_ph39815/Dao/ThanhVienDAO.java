package com.example.duanmau_ph39815.Dao;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.duanmau_ph39815.database.DbHelper;
import com.example.duanmau_ph39815.model.ThanhVien;

import java.util.ArrayList;
import java.util.List;

public class ThanhVienDAO {

    private SQLiteDatabase db;
    private static DbHelper dbHelper;

    public ThanhVienDAO(Context context) {
        dbHelper = new DbHelper(context);
    }

    public static ArrayList<ThanhVien> getAll() {
        ArrayList<ThanhVien> list = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        try {
            Cursor cursor = db.rawQuery("SELECT * FROM ThanhVien", null);
            if (cursor.getCount() > 0) {
                cursor.moveToFirst();
                while (cursor.moveToNext()) {
                    ThanhVien thanhVien = new ThanhVien();
                    thanhVien.setMaTV(cursor.getInt(0));
                    thanhVien.setHoTenTV(cursor.getString(1));
                    thanhVien.setNamSinh(cursor.getString(2));
                    list.add(thanhVien);
                }
                db.setTransactionSuccessful();
            }
        } catch (Exception e) {
            Log.i(TAG, "Lỗi", e);
        }

        return list;
    }
    public ThanhVien getID(String id){
        String sql = "SELECT * FROM ThanhVien where maTV=?";
        List<ThanhVien> list = getAll();
        return list.get(0);
    }

    public long insert(ThanhVien thanhVien) {
        ContentValues values = new ContentValues();
        values.put("hoTenTV", thanhVien.getHoTenTV());
        values.put("namSinh", thanhVien.getNamSinh());
        return db.insert("ThanhVien", null, values);
    }

    public int update(ThanhVien thanhVien) {
        ContentValues values = new ContentValues();
        values.put("hoTenTV", thanhVien.getHoTenTV());
        values.put("namSinh", thanhVien.getNamSinh());
        return db.update("ThanhVien", values, "maTV=?", new String[]{String.valueOf(thanhVien.getMaTV())});

    }

    public int delete(String id) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        return db.delete("ThanhVien", "maTV=?", new String[]{String.valueOf(id)});
    }
}
