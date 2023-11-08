package com.example.lamthem2.adapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lamthem2.R;
import com.example.lamthem2.dao.CongViecDao;
import com.example.lamthem2.model.CongViec;

import java.util.ArrayList;

public class CongViecAdapter extends RecyclerView.Adapter<CongViecAdapter.viewholder>{
    private final Context context;
    private final ArrayList<CongViec> list;
    CongViecDao congViecDao;

    public CongViecAdapter(Context context, ArrayList<CongViec> list) {
        this.context = context;
        this.list = list;
        congViecDao = new CongViecDao(context);
    }
    // Gán layout và tạo view
    @NonNull
    @Override
    public viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_cv, null);
        return new viewholder(view);
    }
    // Gán và cập nhật dữ liệu trên view
    @Override
    public void onBindViewHolder(@NonNull viewholder holder, int position) {
        holder.txtTitle.setText(list.get(position).getTieuDe());
        holder.txtContent.setText(list.get(position).getNoiDung());
        holder.txtDate.setText(list.get(position).getNgay());
        holder.txtType.setText(list.get(position).getLoai());
        if (list.get(position).getTrangThai() == 1){
            holder.chkCV.setChecked(true);
        } else {
            holder.chkCV.setChecked(false);
        }
        // Delete
        CongViec congViec = list.get(position); // Truy xuất đến đối tượng đang chọn tại vị trí position
        holder.imgBtnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context); // Tạo đối tượng
                builder.setIcon(R.drawable.warning); // set icon
                builder.setTitle("Cảnh báo");
                builder.setMessage("Bạn có chắc chắn muốn xóa không"); // set chuỗi thông báo
                // Tạo nút button yes, thực hiện xóa
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (congViecDao.delete(congViec.getId())) {
                            list.clear();
                            list.addAll(congViecDao.selectAll());
                            notifyDataSetChanged();
                            Toast.makeText(context, "Delete succ", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(context, "Delete fail", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                // Tạo nút button No
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(context, "Không xóa", Toast.LENGTH_SHORT).show();
                    }
                });
                // Show
                AlertDialog dialog = builder.create(); // tạo hộp thoại
                dialog.show();
            }
        });
        // Update
        holder.imgBtnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                opendialog(congViec);
            }
        });
    }
    // Trả về số lượng phần tử hiện thị trong RecyclerView
    @Override
    public int getItemCount() {
        return list.size();
    }


    // Tạo class tĩnh, ánh xạ các thành phần widget(1)
    public static class viewholder extends RecyclerView.ViewHolder{
        TextView txtTitle, txtContent, txtDate, txtType;
        CheckBox chkCV;
        ImageButton imgBtnUpdate, imgBtnDelete;

        public viewholder(@NonNull View itemView) {
            super(itemView);
            txtTitle = itemView.findViewById(R.id.txtTitle);
            txtContent = itemView.findViewById(R.id.txtContent);
            txtDate = itemView.findViewById(R.id.txtDate);
            txtType = itemView.findViewById(R.id.txtType);
            chkCV = itemView.findViewById(R.id.chkCV);
            imgBtnUpdate = itemView.findViewById(R.id.imgBtnUpdate);
            imgBtnDelete = itemView.findViewById(R.id.imgBtnDelete);
        }
    }
    // OpenDialog
    public void opendialog(CongViec congViec){
        AlertDialog.Builder builder = new AlertDialog.Builder(context); // Tạo đối tượng
        // Tạo view, gán layout
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_update, null); // gán layout
        builder.setView(view); // set view
        Dialog dialog = builder.create(); // tạo hộp thoại
        dialog.show(); // hiện thị hộp thoại
        // Ánh xạ
        EditText editTxtTitle = view.findViewById(R.id.editTxtTitle);
        EditText editTxtContent = view.findViewById(R.id.editTxtContent);
        EditText editTxtDate = view.findViewById(R.id.editTxtDate);
        EditText editTxtType = view.findViewById(R.id.editTxtType);
        Button btnSua = view.findViewById(R.id.btnSua);
        // Gán lại dữ liệu lên các thành phần giao diện
        editTxtTitle.setText(congViec.getTieuDe());
        editTxtContent.setText(congViec.getNoiDung());
        editTxtDate.setText(congViec.getNgay());
        editTxtType.setText(congViec.getLoai());
        // Xử lí event cho editTxtType
        editTxtType.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder1 = new AlertDialog.Builder(context);
                builder1.setTitle("Chọn giới tính");
                String [] loai = {"Nam", "Nữ",};
                builder1.setItems(loai, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        editTxtType.setText(loai[which]);
                    }
                });
                AlertDialog dialog1 = builder1.create(); // Tạo hộp thoại
                dialog1.show();
            }
        });
        // sử lí nút sửa
        btnSua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                congViec.setTieuDe(editTxtTitle.getText().toString());
                congViec.setNoiDung(editTxtContent.getText().toString());
                congViec.setNgay(editTxtDate.getText().toString());
                congViec.setLoai(editTxtType.getText().toString());
                if (congViecDao.update(congViec)){
                    list.clear();
                    list.addAll(congViecDao.selectAll());
                    notifyDataSetChanged();
                    dialog.dismiss(); // Đóng hộp thoại
                    Toast.makeText(context, "Cập nhật thành công", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(context, "Cập nhật thất bại", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
