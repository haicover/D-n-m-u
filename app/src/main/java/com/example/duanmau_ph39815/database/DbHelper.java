package com.example.duanmau_ph39815.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {
    static final String dbname = "PNLIB";
    static final int dbVersion = 2;

    public DbHelper(@Nullable Context context) {
        super(context, dbname, null, dbVersion);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //Tạo bảng thủ thư
        String createtableThuThu =
                "CREATE TABLE ThuThu (" +
                        "       maTT    TEXT PRIMARY KEY," +
                        "       hoTenTT   TEXT NOT NULL," +
                        "       matKhau TEXT NOT NULL)";
        db.execSQL(createtableThuThu);
        createtableThuThu =
                "Insert into ThuThu(MaTT, hoTenTT, MatKhau) values" +
                        "('admin','Dat Admin','admin')," +
                        "('datlt','Nguyen dat','123456')," +
                        "('teonv','Nguyen van teo','2345')," +
                        "('not','Nguyen Vdmin','admin')";
        db.execSQL(createtableThuThu);
        //Tạo bảng Thành Viên
        String createtableThanhVien =
                "CREATE TABLE ThanhVien (" +
                        "    maTV    INTEGER PRIMARY KEY AUTOINCREMENT," +
                        "    hoTenTV   TEXT NOT NULL," +
                        "    namSinh TEXT NOT NULL)";
        db.execSQL(createtableThanhVien);
        createtableThanhVien =
                "Insert into thanhvien(hoTenTV,namSinh) VALUES" +
                        "('Nguyen Van A','2003')," +
                        "('Nguyen Van B','2002')," +
                        "('Nguyen Van C','2004')," +
                        "('Nguyen Van D','2005')," +
                        "('Nguyen Van E','2006')," +
                        "('Nguyen Van F','2007')";
        db.execSQL(createtableThanhVien);
        //Tạo bảng Loại Sách
        String createtableLoaiSach =
                "CREATE TABLE LoaiSach (" +
                        "    maLoai  INTEGER PRIMARY KEY AUTOINCREMENT," +
                        "    tenLoai TEXT    NOT NULL)";
        db.execSQL(createtableLoaiSach);
        createtableLoaiSach =
                "insert into LoaiSach(tenLoai) VALUES" +
                        "('Lập trình cơ bản')," +
                        "('Lập trình nâng cao')," +
                        "('Tiếng Anh')," +
                        "('Lập trình mobile')," +
                        "('Tiếng Pháp')," +
                        "('Lập trình web')," +
                        "('Tiếng Đức')," +
                        "('Photoshop')";
        db.execSQL(createtableLoaiSach);
        //Tạo bảng Sách
        String createtableSach =
                "CREATE TABLE Sach (" +
                        "    maSach  INTEGER PRIMARY KEY AUTOINCREMENT," +
                        "    tenSach TEXT    NOT NULL," +
                        "    giaThue INTEGER NOT NULL," +
                        "    maLoai  INTEGER REFERENCES LoaiSach (maLoai))";
        db.execSQL(createtableSach);
        createtableSach =
                "INSERT INTO Sach(tenSach,giaThue,maLoai) VALUES" +
                        "('Lập trình cơ bản','2000','2')," +
                        "('Tiếng Anh','4000','2')," +
                        "('Lập trình cơ bản','5000','1')," +
                        "('Photoshop','3000','2')," +
                        "('Lập trình nâng cao','2000','3')," +
                        "('Lập trình cơ bản','2000','3')," +
                        "('Tiếng Anh','3000','2')," +
                        "('Photoshop','4000','4')," +
                        "('Tiếng Anh','6000','3')";
        db.execSQL(createtableSach);
        //Tạo bảng phiếu mượn
        String createtablePhieuMuon =
                "CREATE TABLE PhieuMuon (" +
                        "    maPM     INTEGER PRIMARY KEY AUTOINCREMENT," +
                        "    maTT     TEXT REFERENCES ThuThu (maTT)," +
                        "    maTV     INTEGER REFERENCES ThanhVien (maTV)," +
                        "    maSach   INTEGER REFERENCES Sach (maSach)," +
                        "    ngayThue     DATE    NOT NULL," +
                        "    gioMuon   TIME  NOT NULL,"+
                        "    tienThue INTEGER NOT NULL," +
                        "    traSach  INTEGER NOT NULL)";
        db.execSQL(createtablePhieuMuon);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        if (i<i1){
//            String dropThuThu = "drop table if exists ThuThu";
//            db.execSQL(dropThuThu);
//            String dropThanhVien = "drop table if exists ThanhVien";
//            db.execSQL(dropThanhVien);
//            String dropLoaiSach = "drop table if exists LoaiSach";
//            db.execSQL(dropLoaiSach);
//            String dropSach = "drop table if exists Sach";
//            db.execSQL(dropSach);
//            String dropPhieuMuon = "drop table if exists PhieuMuon";
//            db.execSQL(dropPhieuMuon);
//            onCreate(db);
            String dropPhieuMuon = "drop table if exists PhieuMuon";
            db.execSQL(dropPhieuMuon);
            String createtablePhieuMuon =
                    "CREATE TABLE PhieuMuon (" +
                            "    maPM     INTEGER PRIMARY KEY AUTOINCREMENT," +
                            "    maTT     TEXT REFERENCES ThuThu (maTT)," +
                            "    maTV     INTEGER REFERENCES ThanhVien (maTV)," +
                            "    maSach   INTEGER REFERENCES Sach (maSach)," +
                            "    ngayThue     DATE    NOT NULL," +
                            "    gioMuon   TIME  NOT NULL,"+
                            "    tienThue INTEGER NOT NULL," +
                            "    traSach  INTEGER NOT NULL)";
            db.execSQL(createtablePhieuMuon);
        }
    }
}
