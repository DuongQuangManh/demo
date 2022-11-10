package com.example.manhdqph20768_assignment_duanmau.Adapter;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Typeface;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.manhdqph20768_assignment_duanmau.Dao.ThanhVienDAO;
import com.example.manhdqph20768_assignment_duanmau.Model.ThanhVien;
import com.example.manhdqph20768_assignment_duanmau.R;
import com.example.manhdqph20768_assignment_duanmau.fragment.QLTV_Fragment;

import java.util.List;

public class ThanhVienAdapter extends RecyclerView.Adapter<ThanhVienAdapter.ViewHolder> {
    private Context context;
    private List<ThanhVien> list;
    ThanhVienDAO dao;
    EditText edt_matv,edt_tentv,edt_namsinh,edt_sotaikhoan;
    Button btn_save,btn_huy;

    public ThanhVienAdapter(Context context) {
        this.context = context;
    }
    public void setData(List<ThanhVien> list){
        this.list = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_listview_qltv,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        dao = new ThanhVienDAO(context);
        ThanhVien thanhVien = list.get(position);
        String stk = thanhVien.getSoTaiKhoan();
        String clearstk = stk.substring(stk.length()-1,stk.length());
        if (Integer.parseInt(clearstk) == 0 || Integer.parseInt(clearstk) == 5){
            holder.tv_sotaikhoan.setTypeface(null,Typeface.BOLD);
        }else {
            holder.tv_sotaikhoan.setTypeface(null,Typeface.NORMAL);

        }
        Log.d("clear",clearstk + "stk");
        holder.tv_matv.setText("Mã thành viên: "+thanhVien.getMaTV());
        holder.tv_tentv.setText("Họ tên: "+thanhVien.getHoTen());
        holder.tv_namsinh.setText("Năm sinh: "+thanhVien.getNamSinh());
        holder.tv_sotaikhoan.setText("Số tài khoản: "+thanhVien.getSoTaiKhoan());
        holder.img_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ThanhVien thanhVien1 = list.get(holder.getAdapterPosition());
                long check = dao.delete(String.valueOf(thanhVien1.getMaTV()));
                if (check>0){
                    Toast.makeText(context, "Delete thành công", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(context, "Delete thất bại", Toast.LENGTH_SHORT).show();
                }
                list.remove(holder.getAdapterPosition());
                notifyDataSetChanged();
            }
        });
        holder.relativeLayout.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                ThanhVien item = list.get(holder.getAdapterPosition());


                final Dialog dialog = new Dialog(context);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.addtv_dialog);
                dialog.show();
                Window window =dialog.getWindow();
                if (window==null){
                    return false;
                }
                window.setGravity(Gravity.CENTER);
                window.setBackgroundDrawable(null);
                edt_matv = dialog.findViewById(R.id.edt_matv_dialogtv);
                edt_matv.setEnabled(false);
                edt_tentv = dialog.findViewById(R.id.edt_tentv_dialogtv);
                edt_namsinh = dialog.findViewById(R.id.edt_namsinh_dialogtv);
                edt_sotaikhoan = dialog.findViewById(R.id.edt_sotaikhoan_dialogtv);
                btn_save = dialog.findViewById(R.id.btn_luu_dialogtv);
                btn_huy = dialog.findViewById(R.id.btn_huy_dialogtv);
                edt_matv.setText(String.valueOf(item.getMaTV()));
                edt_tentv.setText(item.getHoTen());
                edt_namsinh.setText(item.getNamSinh());

                edt_sotaikhoan.setText(item.getSoTaiKhoan());

                btn_huy.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
                btn_save.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (validate()>0){
                            item.setHoTen(edt_tentv.getText().toString());
                            item.setNamSinh(edt_namsinh.getText().toString());
                            item.setSoTaiKhoan(edt_sotaikhoan.getText().toString());
                            long check = dao.update(item);
                            if (check>0){
                                Toast.makeText(context, "Update thành công", Toast.LENGTH_SHORT).show();
                                dialog.dismiss();
                            }else {
                                Toast.makeText(context, "Update thất bại", Toast.LENGTH_SHORT).show();
                            }
                            notifyDataSetChanged();
                        }
                    }
                });
                return true;
            }
        });
    }

    @Override
    public int getItemCount() {
        if (list != null){
            return list.size();
        }
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tv_matv,tv_tentv,tv_namsinh,tv_sotaikhoan;
        private ImageView img_delete;
        private RelativeLayout relativeLayout;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_matv = itemView.findViewById(R.id.tv_matv_item);
            tv_tentv = itemView.findViewById(R.id.tv_tentv_item);
            tv_namsinh = itemView.findViewById(R.id.tv_namsinh_item);
            img_delete = itemView.findViewById(R.id.img_delete_item);
            relativeLayout = itemView.findViewById(R.id.layout_update_item);
            tv_sotaikhoan =itemView.findViewById(R.id.tv_sotaikhoan_item);
        }
    }
    public int validate(){
        int check = 1;
        if (edt_tentv.getText().length()==0|| edt_namsinh.getText().length()==0 || edt_sotaikhoan.getText().toString().length()==0){
            Toast.makeText(context,"Bạn phải nhập đầy đủ thông tin",Toast.LENGTH_SHORT).show();
            check = -1;
        }
        return check;
    }

}
