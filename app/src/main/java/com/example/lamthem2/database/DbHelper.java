package com.example.lamthem2.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DbHelper extends SQLiteOpenHelper {
    public static final String Db_name = "QLCV";
    public DbHelper(Context context) {
        super(context, Db_name, null, 1);
    }
    // Tạo bảng
    @Override
    public void onCreate(SQLiteDatabase db) {
        String tb_congviec = "create table congviec(id integer primary key autoincrement, tieude text, noidung text, ngay text, loai text, trangthai integer)";
        db.execSQL(tb_congviec);

        // Chèn dữ liệu
        String data = "insert into congviec values (0, 'PH35610', 'Vịnh Xuân Quyền', '2002-04-10', 'NAM', 1)";
        db.execSQL(data);
    }
    // Được gọi khi nâng cấp version
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion != newVersion){
            db.execSQL("DROP TABLE IF EXISTS congviec");
            onCreate(db);
        }
    }
}
