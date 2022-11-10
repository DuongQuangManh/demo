package com.example.manhdqph20768_assignment_duanmau.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.manhdqph20768_assignment_duanmau.Model.ThuThu;

public class Database extends SQLiteOpenHelper {

    public Database(Context context) {
        super(context, "ASSIGNMENT", null, 11);
    }

    static final String CREATE_TABLE_THU_THU =
            "create table ThuThu(maTT TEXT PRIMARY KEY, " +
            "hoTen TEXT NOT NULL, " +
            "matKhau TEXT NOT NULL)";
    static final String CREATE_TABLE_THANH_VIEN =
            "create table ThanhVien(maTV INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "hoTen TEXT NOT NULL, " +
                    "namSinh TEXT NOT NULL," +
                    "soTaiKhoan TEXT NOT NULL)";
    static final String CREATE_TABLE_LOAI_SACH =
            "create table LoaiSach(maLoai INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "tenLoai TEXT NOT NULL)";
    static final String CREATE_TABLE_SACH =
            "create table Sach(maSach INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "tenSach TEXT NOT NULL, " +
                    "giaThue INTEGER NOT NULL," +
                    "soLuong INTEGER NOT NULL, " +
                    "maLoai INTEGER REFERENCES LoaiSach(maLoai))";
    static final String CREATE_TABLE_PHIEU_MUON =
            "create table PhieuMuon(" +
                    "maPM INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "maTT TEXT REFERENCES ThuThu(maTT), " +
                    "maTV INTEGER REFERENCES ThanhVien(maTV), " +
                    "maSach INTEGER REFERENCES Sach(maSach), " +
                    "tienThue INTEGER NOT NULL, " +
                    "ngay DATE NOT NULL, " +
                    "traSach INTEGER NOT NULL )";


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_THU_THU);
        db.execSQL(CREATE_TABLE_THANH_VIEN);
        db.execSQL(CREATE_TABLE_LOAI_SACH);
        db.execSQL(CREATE_TABLE_SACH);
        db.execSQL(CREATE_TABLE_PHIEU_MUON);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS ThuThu");
        db.execSQL("DROP TABLE IF EXISTS ThanhVien");
        db.execSQL("DROP TABLE IF EXISTS LoaiSach");
        db.execSQL("DROP TABLE IF EXISTS Sach");
        db.execSQL("DROP TABLE IF EXISTS PhieuMuon");
        onCreate(db);
    }

}
