package com.example.manhdqph20768_assignment_duanmau.fragment;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.manhdqph20768_assignment_duanmau.Adapter.LoaiSachAdapter;
import com.example.manhdqph20768_assignment_duanmau.Adapter.ThanhVienAdapter;
import com.example.manhdqph20768_assignment_duanmau.Dao.LoaiSachDAO;
import com.example.manhdqph20768_assignment_duanmau.Dao.ThanhVienDAO;
import com.example.manhdqph20768_assignment_duanmau.Model.LoaiSach;
import com.example.manhdqph20768_assignment_duanmau.Model.ThanhVien;
import com.example.manhdqph20768_assignment_duanmau.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;


public class QLLS_Fragment extends Fragment {
    RecyclerView rcy;
    FloatingActionButton btn_add;
    List<LoaiSach> list;
    LoaiSachDAO dao;
    LoaiSachAdapter adapter;
    LoaiSach item;
    EditText edt_mals,edt_tenls;
    Button btn_save,btn_huy;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_q_l_l_s_, container, false);
        rcy = view.findViewById(R.id.rcy_qlls);
        btn_add = view.findViewById(R.id.fabloaisach);
        dao = new LoaiSachDAO(getActivity());
        capNhat();

        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                opendialog(getContext());
            }
        });
        return view;
    }
    private void opendialog(final Context context) {
        final Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.addloaisach_dialog);
        dialog.show();
        Window window =dialog.getWindow();
        if (window==null){
            return;
        }
        window.setGravity(Gravity.CENTER);
        window.setBackgroundDrawable(null);
        edt_mals = dialog.findViewById(R.id.edt_matv_dialogls);
        edt_mals.setEnabled(false);
        edt_tenls = dialog.findViewById(R.id.edt_tentv_dialogls);
        btn_save = dialog.findViewById(R.id.btn_luu_dialogls);
        btn_huy = dialog.findViewById(R.id.btn_huy_dialogls);
        btn_huy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                item = new LoaiSach();
                item.setTenLoai(edt_tenls.getText().toString());
                if (validate()>0){
                    if (dao.insert(item)>0){
                        Toast.makeText(context, "insert thành công", Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(context, "insert thất bại", Toast.LENGTH_SHORT).show();
                    }
                    capNhat();
                    dialog.dismiss();
                }
            }
        });

    }
    public void capNhat(){
        list = dao.getAll();
        adapter = new LoaiSachAdapter(getActivity());
        adapter.setData(list);
        LinearLayoutManager manager = new LinearLayoutManager(getActivity(),RecyclerView.VERTICAL,false);
        rcy.setLayoutManager(manager);
        rcy.setAdapter(adapter);
    }
    public int validate(){
        int check = 1;
        if (edt_tenls.getText().length()==0){
            Toast.makeText(getActivity(),"Bạn phải nhập đầy đủ thông tin",Toast.LENGTH_SHORT).show();
            check = -1;
        }
        return check;
    }
}