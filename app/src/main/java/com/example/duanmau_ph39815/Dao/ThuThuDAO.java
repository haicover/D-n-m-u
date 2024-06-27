package com.example.duanmau_ph39815.Dao;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.duanmau_ph39815.database.DbHelper;
import com.example.duanmau_ph39815.model.ThuThu;

import java.util.ArrayList;
import java.util.List;

public class ThuThuDAO {
    private SQLiteDatabase db;


    public ThuThuDAO(Context context) {
        DbHelper dbHelper = new DbHelper(context);
        db = dbHelper.getWritableDatabase();
    }
    public long insert(ThuThu thuThu){
        ContentValues values = new ContentValues();
        values.put("maTT",thuThu.getMaTT());
        values.put("hoTenTT",thuThu.getHoTenTT());
        values.put("matKhau",thuThu.getMatKhau());
        return  db.insert("ThuThu",null,values);
    }

    public int update(ThuThu thuThu){
        ContentValues values = new ContentValues();
        values.put("maTT",thuThu.getMaTT());
        values.put("hoTenTT",thuThu.getHoTenTT());
        values.put("matKhau",thuThu.getMatKhau());
        return db.update("ThuThu",values,"maTT=?",new String[]{String.valueOf(thuThu.getMaTT())});

    }

    public int delete(String id){
        return db.delete("ThuThu","maTT=?",new String[]{id});
    }


    public List<ThuThu> getAll(){
        String sql = "SELECT * FROM ThuThu";
        return getData(sql);
    }

    public ThuThu getID(String id){
        String sql = "SELECT * FROM ThuThu where maTT=?";
        List<ThuThu> list = getData(sql,id);
        return list.get(0);
    }


    private List<ThuThu> getData(String sql, String...selectionArgs){
        List<ThuThu> list = new ArrayList<ThuThu>();

        Cursor cursor = db.rawQuery(sql,selectionArgs);
        while (cursor.moveToNext()){
            ThuThu thuThu = new ThuThu();
            thuThu.setMaTT(cursor.getString(0));
            thuThu.setHoTenTT(cursor.getString(1));
            thuThu.setMatKhau(cursor.getString(2));
            list.add(thuThu);
        }
        cursor.close();
        return list;

    }

    //update pass
    public int updatePass(ThuThu thuThu){
        ContentValues values = new ContentValues();
        values.put("hoTenTT",thuThu.getHoTenTT());
        values.put("matKhau",thuThu.getMatKhau());
        return db.update("ThuThu",values,"maTT=?", new String[]{String.valueOf(thuThu.getMaTT())});

    }

    //check login
    public int checkLogin(String id, String password){
        String sql = "SELECT * FROM ThuThu WHERE maTT=? AND matKhau=?";
        List<ThuThu> list = getData(sql, id,password);
        if (list.size()==0){
            return -1;
        }
        return 1;

    }
}
