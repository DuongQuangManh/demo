package com.example.manhdqph20768_assignment_duanmau.Adapter;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.manhdqph20768_assignment_duanmau.Dao.LoaiSachDAO;
import com.example.manhdqph20768_assignment_duanmau.Dao.PhieuMuonDAO;
import com.example.manhdqph20768_assignment_duanmau.Dao.SachDAO;
import com.example.manhdqph20768_assignment_duanmau.Dao.ThanhVienDAO;
import com.example.manhdqph20768_assignment_duanmau.Model.LoaiSach;
import com.example.manhdqph20768_assignment_duanmau.Model.PhieuMuon;
import com.example.manhdqph20768_assignment_duanmau.Model.Sach;
import com.example.manhdqph20768_assignment_duanmau.Model.ThanhVien;
import com.example.manhdqph20768_assignment_duanmau.R;
import com.example.manhdqph20768_assignment_duanmau.SpinnerAdapterTenSach;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PhieuMuonAdapter extends RecyclerView.Adapter<PhieuMuonAdapter.ViewHolder> {
    private Context context;
    private List<PhieuMuon> list;
    PhieuMuonDAO dao;

    EditText edt_mapm;
    Spinner spn_tenthanhvien,spn_tensach;
    TextView tv_ngaythue,tv_tienthue;
    Button btn_save,btn_huy;
    CheckBox cbo_trangthai;

    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    SpinnerAdapterTenSach spinnerAdapterTenSach;
    SachDAO sachDAO;
    List<Sach> sachList;
    Sach sach;
    int maSach, tienThue;

    SpinnerAdapterThanhVien spinnerAdapterThanhVien;
    ThanhVienDAO thanhVienDAO;
    List<ThanhVien> thanhVienList;
    ThanhVien thanhVien;
    int maThanhVien;


    public PhieuMuonAdapter(Context context) {
        this.context = context;
    }
    public void setData(List<PhieuMuon> list){
        this.list = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_listview_qlpm,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        dao = new PhieuMuonDAO(context);
        PhieuMuon phieuMuon = list.get(position);

        sachDAO = new SachDAO(context);
        Sach sach = sachDAO.getID(String.valueOf(phieuMuon.getMaSach()));
        thanhVienDAO = new ThanhVienDAO(context);
        ThanhVien thanhVien = thanhVienDAO.getID(String.valueOf(phieuMuon.getMaTV()));

        holder.tv_maphieu.setText("Mã phiếu: "+phieuMuon.getMaPM());
        holder.tv_tenthanhvien.setText("Thành viên: "+thanhVien.getHoTen());
        holder.tv_tensach.setText("Giá thuê: "+sach.getTenSach());
        holder.tv_giathue.setText("Giá thuê: "+phieuMuon.getTienThue());
        holder.tv_ngaythue.setText("Ngày thuê: "+sdf.format(phieuMuon.getNgay()));

        if (phieuMuon.getTraSach()==1){
            holder.tv_trangthai.setTextColor(Color.BLUE);
            holder.tv_trangthai.setText("Đã trả sách");
        }else {
            holder.tv_trangthai.setTextColor(Color.RED);
            holder.tv_trangthai.setText("Chưa trả sách");
        }


        holder.img_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PhieuMuon phieuMuon1 = list.get(holder.getAdapterPosition());
                long check = dao.delete(String.valueOf(phieuMuon1.getMaPM()));
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
                PhieuMuon item = list.get(holder.getAdapterPosition());
                int positionTV = 0,positionSach = 0;

                final Dialog dialog = new Dialog(context);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.addpm_dialog);
                dialog.show();
                Window window =dialog.getWindow();
                if (window==null){
                    return false;
                }
                window.setGravity(Gravity.CENTER);
                window.setBackgroundDrawable(null);

                edt_mapm = dialog.findViewById(R.id.edt_maphieumuon_dialogpm);
                edt_mapm.setEnabled(false);
                spn_tenthanhvien = dialog.findViewById(R.id.spinner_tenthanhvien_dialogpm);
                spn_tensach = dialog.findViewById(R.id.spinner_tensach_dialogpm);
                tv_ngaythue = dialog.findViewById(R.id.tv_ngaythue_dialogpm);
                tv_tienthue = dialog.findViewById(R.id.tv_tienthue_dialogpm);
                cbo_trangthai = dialog.findViewById(R.id.cbo_trangthai_dialogpm);
                btn_save = dialog.findViewById(R.id.btn_luu_dialogpm);
                btn_huy =dialog.findViewById(R.id.btn_huy_dialogpm);

                thanhVienList = new ArrayList<>();
                thanhVienList = thanhVienDAO.getAll();
                spinnerAdapterThanhVien = new SpinnerAdapterThanhVien(context,R.layout.item_spinner_loaisach,thanhVienList);
                spn_tenthanhvien.setAdapter(spinnerAdapterThanhVien);
                spn_tenthanhvien.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        maThanhVien = thanhVienList.get(position).getMaTV();
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });

                sachList = new ArrayList<>();
                sachList = sachDAO.getAll();
                spinnerAdapterTenSach = new SpinnerAdapterTenSach(context,R.layout.item_spinner_loaisach,sachList);
                spn_tensach.setAdapter(spinnerAdapterTenSach);
                spn_tensach.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        maSach = sachList.get(position).getMaSach();
                        tienThue = sachList.get(position).getGiaThue();


                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });

                tv_ngaythue.setText("Ngày thuê: "+sdf.format(item.getNgay()));
                tv_tienthue.setText("Tiền thuê: "+item.getTienThue());
                edt_mapm.setText("Mã phiếu: "+item.getMaPM());



                for (int i =0;i<thanhVienList.size();i++){
                    if (item.getMaTV()== thanhVienList.get(i).getMaTV()){
                        positionTV = i;
                    }
                }
                spn_tenthanhvien.setSelection(positionTV);
                for (int i =0;i<sachList.size();i++){
                    if (item.getMaSach()== sachList.get(i).getMaSach()){
                        positionSach = i;
                    }
                }
                spn_tenthanhvien.setSelection(positionSach);

                btn_save = dialog.findViewById(R.id.btn_luu_dialogpm);
                btn_huy = dialog.findViewById(R.id.btn_huy_dialogpm);

                btn_huy.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
                btn_save.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        item.setMaSach(maSach);
                        item.setMaTV(maThanhVien);
                        item.setTienThue(tienThue);
                        if (cbo_trangthai.isChecked()){
                            item.setTraSach(1);
                        }else {
                            item.setTraSach(0);
                        }
                        long check = dao.update(item);
                        if (check>0){
                            Toast.makeText(context, "Update thành công", Toast.LENGTH_SHORT).show();
                            dialog.dismiss();
                        }else {
                            Toast.makeText(context, "Update thất bại", Toast.LENGTH_SHORT).show();
                        }
                        notifyDataSetChanged();
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
        private TextView tv_maphieu,tv_tenthanhvien,tv_tensach,tv_giathue,tv_trangthai,tv_ngaythue;
        private ImageView img_delete;
        private RelativeLayout relativeLayout;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_maphieu = itemView.findViewById(R.id.tv_mapm_itempm);
            tv_tenthanhvien = itemView.findViewById(R.id.tv_tensinhvien_itempm);
            tv_tensach = itemView.findViewById(R.id.tv_tensach_itempm);
            tv_giathue = itemView.findViewById(R.id.tv_tienthue_itempm);
            tv_trangthai = itemView.findViewById(R.id.tv_trangthai_itempm);
            tv_ngaythue = itemView.findViewById(R.id.tv_ngaythue_itempm);
            img_delete = itemView.findViewById(R.id.img_delete_itempm);
            relativeLayout = itemView.findViewById(R.id.layout_update_itempm);
        }
    }

}
