package com.example.duanmau_ph39815.Dao;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.duanmau_ph39815.database.DbHelper;
import com.example.duanmau_ph39815.model.PhieuMuon;
import com.example.duanmau_ph39815.model.Sach;
import com.example.duanmau_ph39815.model.Top;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class PhieuMuonDAO {
    private SQLiteDatabase db;
    private Context context;
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");

    public PhieuMuonDAO(Context context) {
        this.context = context;
        DbHelper dbHelper = new DbHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    public long insert(PhieuMuon obj) {
        ContentValues values = new ContentValues();
        values.put("maTT", obj.getMaTT());
        values.put("maTV", obj.getMaTV());
        values.put("maSach", obj.getMaSach());
        values.put("ngayThue", sdf.format(obj.getNgayThue()));
        values.put("tienThue", obj.getTienThue());
        values.put("traSach", obj.getTraSach());
        values.put("gio",obj.getGio());
        return db.insert("PhieuMuon", null, values);
    }

    public int update(PhieuMuon obj) {
        ContentValues values = new ContentValues();
        values.put("maTT", obj.getMaTT());
        values.put("maTV", obj.getMaTV());
        values.put("maSach", obj.getMaSach());
        values.put("ngayThue", sdf.format(obj.getNgayThue()));
        values.put("tienThue", obj.getTienThue());
        values.put("traSach", obj.getTraSach());
        values.put("gio",obj.getGio());
        return db.update("PhieuMuon", values, "maPM=?", new String[]{String.valueOf(obj.getMaPM())});
    }

    public int delete(String id) {
        return db.delete("PhieuMuon", "maPM=?", new String[]{id});
    }

    //get tat ca data
    public List<PhieuMuon> getAll() {
        String sql = "select * from PhieuMuon";
        return getData(sql);
    }

    //get data theo ID
    public PhieuMuon getID(String id) {
        String sql = "select * from PhieuMuon where maPM=?";
        List<PhieuMuon> list = getData(sql, id);
        return list.get(0);
    }

    @SuppressLint("Range")
    private List<PhieuMuon> getData(String sql, String... selectionArgs) {
        List<PhieuMuon> list = new ArrayList<>();
        Cursor c = db.rawQuery(sql, selectionArgs);
        while (c.moveToNext()) {
            PhieuMuon obj = new PhieuMuon();
            obj.setMaPM(Integer.parseInt(c.getString(c.getColumnIndex("maPM"))));
            obj.setMaTT(c.getString(c.getColumnIndex("maTT")));
            obj.setMaTV(Integer.parseInt(c.getString(c.getColumnIndex("maTV"))));
            obj.setMaSach(Integer.parseInt(c.getString(c.getColumnIndex("maSach"))));
            try {
                obj.setNgayThue(sdf.parse(c.getString(c.getColumnIndex("ngayThue"))));
            } catch (Exception e) {
                e.printStackTrace();
            }
            obj.setTienThue(Integer.parseInt(c.getString(c.getColumnIndex("tienThue"))));
            obj.setTraSach(Integer.parseInt(c.getString(c.getColumnIndex("traSach"))));
            obj.setGio((c.getString(c.getColumnIndex("gio"))));
            list.add(obj);
        }
        return list;
    }

}
