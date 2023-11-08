package com.example.lamthem2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.lamthem2.adapter.CongViecAdapter;
import com.example.lamthem2.dao.CongViecDao;
import com.example.lamthem2.model.CongViec;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    RecyclerView rcvCongViec;
    FloatingActionButton fltAdd;
    private ArrayList<CongViec> list = new ArrayList<>();
    CongViecAdapter adapter;
    CongViecDao congViecDao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rcvCongViec = findViewById(R.id.rcvCongViec);
        fltAdd = findViewById(R.id.fltAdd);
        congViecDao = new CongViecDao(this);
        list = congViecDao.selectAll(); // lấy toàn bộ dữ từ bảng congviec, thêm dữ liệu vào list
        // Set layout cho RecyclerView
        LinearLayoutManager layout = new LinearLayoutManager(this);
        rcvCongViec.setLayoutManager(layout);
        // Đổ dữ liệu lên RecyclerView
        adapter = new CongViecAdapter(this, list);
        rcvCongViec.setAdapter(adapter);
        // Xử lí event cho nút add
        fltAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                opendialogThem();
            }
        });
    }
    // Phương thức opendialog thêm
    public void opendialogThem(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this); // Tạo đối tượng
        // Tạo view, gán layout
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.item_add, null); // gán layout
        builder.setView(view); // set view
        Dialog dialog = builder.create(); // tạo hộp thoại
        dialog.show(); // hiện thị hộp thoại
        // Ánh xạ
        EditText txttieudea = view.findViewById(R.id.txttieudea);
        EditText txtnoidunga = view.findViewById(R.id.txtnoidunga);
        EditText txtngaya = view.findViewById(R.id.txtngaya);
        EditText txtloaia = view.findViewById(R.id.txtloaia);
        Button btnThem = view.findViewById(R.id.btnThem);

        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tieuDe = txttieudea.getText().toString();
                String noiDung = txtnoidunga.getText().toString();
                String ngay = txtngaya.getText().toString();
                String loai = txtloaia.getText().toString();
                CongViec congViec = new CongViec(tieuDe, noiDung, ngay, loai);
                if (congViecDao.insert(congViec)) {
                    list.clear();
                    list.addAll(congViecDao.selectAll());
                    adapter.notifyDataSetChanged();
                    dialog.dismiss();
                    Toast.makeText(MainActivity.this, "Thêm thành công", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "Thêm thất bại", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}