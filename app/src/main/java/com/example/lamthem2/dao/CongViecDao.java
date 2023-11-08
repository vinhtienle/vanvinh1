package com.example.lamthem2.dao;

import static android.content.ContentValues.TAG;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.lamthem2.database.DbHelper;
import com.example.lamthem2.model.CongViec;

import java.util.ArrayList;

public class CongViecDao {
    private final DbHelper dbHelper;

    public CongViecDao(Context context) {
        dbHelper = new DbHelper(context);
    }
    // Select All: lấy toàn bộ dữ liệu từ bảng congviec, gán dữ liệu vào list
    public ArrayList<CongViec> selectAll(){
        ArrayList<CongViec> list = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        try{
            Cursor cursor = db.rawQuery("select * from congviec", null);
            if (cursor.getCount() > 0){
                cursor.moveToFirst(); // Đưa con trỏ về dòng đầu tiên
                while (!cursor.isAfterLast()){
                    CongViec congViec = new CongViec(); // Tạo đối tượng
                    // Nhập thông tin đối tượng
                    congViec.setId(cursor.getInt(0));
                    congViec.setTieuDe(cursor.getString(1));
                    congViec.setNoiDung(cursor.getString(2));
                    congViec.setNgay(cursor.getString(3));
                    congViec.setLoai(cursor.getString(4));
                    congViec.setTrangThai(cursor.getInt(5));
                    list.add(congViec); // Add đối tượng vào list
                    cursor.moveToNext(); // Next con trỏ dòng tiếp theo
                }
            }
        }
        catch (Exception e){
            Log.i(TAG, "Lỗi", e);
        }
        return list;
    }
    // Insert: chèn dữ liệu vào bảng
    public  boolean insert(CongViec congViec){
        SQLiteDatabase db = dbHelper.getWritableDatabase(); // getWrite thực hiện ghi dữ liệu
        ContentValues values = new ContentValues(); // ContentValues đưa dữ liệu vào bảng
        values.put("tieude", congViec.getTieuDe());
        values.put("noidung", congViec.getNoiDung());
        values.put("ngay", congViec.getNgay());
        values.put("loai", congViec.getLoai());
        values.put("trangthai", congViec.getTrangThai());
        // Nếu add dữ liệu thành công thì trả về giá trị tương ứng với số dòng được chèn vào
        long row = db.insert("congviec", null, values);
        return (row > 0);
    }

    // Update: update dữ liệu
    public boolean update(CongViec congViec){
        SQLiteDatabase db = dbHelper.getWritableDatabase(); // getWrite thực hiện ghi dữ liệu
        ContentValues values = new ContentValues(); // ContentValues đưa dữ liệu vào bảng
        values.put("tieude", congViec.getTieuDe());
        values.put("noidung", congViec.getNoiDung());
        values.put("ngay", congViec.getNgay());
        values.put("loai", congViec.getLoai());
        values.put("trangthai", congViec.getTrangThai());
        // Nếu add dữ liệu thành công thì trả về giá trị tương ứng với số dòng được chèn vào
        long row = db.update("congviec", values, "id=?", new String[]{String.valueOf(congViec.getId())});
        return (row > 0);
    }

    // Xóa
    public boolean delete(int id){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        long row = db.delete("congviec", "id=?", new String[]{String.valueOf(id)});
        return (row > 0);
    }
}
