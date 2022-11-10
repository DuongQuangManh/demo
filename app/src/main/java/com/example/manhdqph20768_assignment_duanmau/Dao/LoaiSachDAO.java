package com.example.manhdqph20768_assignment_duanmau.Dao;

import android.annotation.SuppressLint;
import android.companion.WifiDeviceFilter;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import androidx.recyclerview.widget.DiffUtil;

import com.example.manhdqph20768_assignment_duanmau.Database.Database;
import com.example.manhdqph20768_assignment_duanmau.Model.LoaiSach;
import com.example.manhdqph20768_assignment_duanmau.Model.Sach;
import com.example.manhdqph20768_assignment_duanmau.Model.ThanhVien;
import com.example.manhdqph20768_assignment_duanmau.Model.ThuThu;

import org.intellij.lang.annotations.JdkConstants;

import java.util.ArrayList;
import java.util.List;

public class LoaiSachDAO {
    private SQLiteDatabase db;
    SachDAO dao;
    List<Sach> list;
    int position = 0;


    public LoaiSachDAO(Context context) {
        Database database = new Database(context);
        db = database.getWritableDatabase();
        dao = new SachDAO(context);
        list = dao.getAll();
    }

    public long insert(LoaiSach obj){
        ContentValues values = new ContentValues();
        values.put("tenLoai", obj.getTenLoai());
        return db.insert("LoaiSach",null,values);
    }

    public int update(LoaiSach obj){
        ContentValues values = new ContentValues();
        values.put("tenLoai",obj.getTenLoai());
        return db.update("LoaiSach",values,"maLoai=?",new String[]{String.valueOf(obj.getMaLoai())});
    }
    public int delete(String id){
        int check =0;
        for (int i=0;i<list.size();i++){
            if (Integer.valueOf(id) == Integer.valueOf(list.get(i).getMaLoai())){
               int checkdeletesach = dao.delete(String.valueOf(list.get(i).getMaSach()));
               if (checkdeletesach<0){
               }else {
                   check = db.delete("LoaiSach","maLoai=?",new String[]{id});
               }
            }else {
                check = db.delete("LoaiSach","maLoai=?",new String[]{id});
            }
        }
        return check;
    }

    @SuppressLint("Range")
    public List<LoaiSach> getData(String sql, String...selectionArgs){
        List<LoaiSach> list = new ArrayList<>();
        Cursor c = db.rawQuery(sql,selectionArgs);
        while (c.moveToNext()){
            LoaiSach obj = new LoaiSach();
            obj.setMaLoai(Integer.parseInt(c.getString(c.getColumnIndex("maLoai"))));
            obj.setTenLoai(c.getString(c.getColumnIndex("tenLoai")));
            list.add(obj);
        }
        return list;
    }

    public List<LoaiSach> getAll(){
        String sql = "SELECT *FROM LoaiSach";
        return getData(sql);
    }

    public LoaiSach getID(String id){
        String sql = "SELECT * FROM LoaiSach WHERE maLoai=?";
        List<LoaiSach> list = getData(sql,id);
        return list.get(0);
    }


}
