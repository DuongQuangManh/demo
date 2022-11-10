package com.example.manhdqph20768_assignment_duanmau.Adapter;

import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.manhdqph20768_assignment_duanmau.Dao.LoaiSachDAO;
import com.example.manhdqph20768_assignment_duanmau.Dao.ThanhVienDAO;
import com.example.manhdqph20768_assignment_duanmau.Model.LoaiSach;
import com.example.manhdqph20768_assignment_duanmau.Model.ThanhVien;
import com.example.manhdqph20768_assignment_duanmau.R;

import java.util.List;

public class LoaiSachAdapter extends RecyclerView.Adapter<LoaiSachAdapter.ViewHolder> {
    private Context context;
    private List<LoaiSach> list;
    LoaiSachDAO dao;
    EditText edt_mals,edt_tenls;
    Button btn_save,btn_huy;

    public LoaiSachAdapter(Context context) {
        this.context = context;
    }
    public void setData(List<LoaiSach> list){
        this.list = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_listview_qlls,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        dao = new LoaiSachDAO(context);
        LoaiSach loaiSach = list.get(position);
        holder.tv_matv.setText("Mã sách: "+loaiSach.getMaLoai());
        holder.tv_tentv.setText("Tên sách: "+loaiSach.getTenLoai());
        holder.img_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoaiSach loaiSach1 = list.get(holder.getAdapterPosition());
                long check = dao.delete(String.valueOf(loaiSach1.getMaLoai()));
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
                LoaiSach item = list.get(holder.getAdapterPosition());


                final Dialog dialog = new Dialog(context);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.addloaisach_dialog);
                dialog.show();
                Window window =dialog.getWindow();
                if (window==null){
                    return false;
                }
                window.setGravity(Gravity.CENTER);
                window.setBackgroundDrawable(null);
                edt_mals = dialog.findViewById(R.id.edt_matv_dialogls);
                edt_mals.setEnabled(false);
                edt_tenls = dialog.findViewById(R.id.edt_tentv_dialogls);

                btn_save = dialog.findViewById(R.id.btn_luu_dialogls);
                btn_huy = dialog.findViewById(R.id.btn_huy_dialogls);

                edt_mals.setText(String.valueOf(item.getMaLoai()));
                edt_tenls.setText(item.getTenLoai());

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
                            item.setTenLoai(edt_tenls.getText().toString());
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
        private TextView tv_matv,tv_tentv;
        private ImageView img_delete;
        private RelativeLayout relativeLayout;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_matv = itemView.findViewById(R.id.tv_mals_item);
            tv_tentv = itemView.findViewById(R.id.tv_tenls_item);
            img_delete = itemView.findViewById(R.id.img_delete_itemls);
            relativeLayout = itemView.findViewById(R.id.layout_update_itemls);
        }
    }
    public int validate(){
        int check = 1;
        if (edt_tenls.getText().length()==0){
            Toast.makeText(context,"Bạn phải nhập đầy đủ thông tin",Toast.LENGTH_SHORT).show();
            check = -1;
        }
        return check;
    }

}
