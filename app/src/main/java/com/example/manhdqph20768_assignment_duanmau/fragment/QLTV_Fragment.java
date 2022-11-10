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
import android.widget.ListView;
import android.widget.Toast;

import com.example.manhdqph20768_assignment_duanmau.Adapter.ThanhVienAdapter;
import com.example.manhdqph20768_assignment_duanmau.Dao.ThanhVienDAO;
import com.example.manhdqph20768_assignment_duanmau.Model.ThanhVien;
import com.example.manhdqph20768_assignment_duanmau.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class QLTV_Fragment extends Fragment {
    RecyclerView rcy;
    FloatingActionButton btn_add;
    List<ThanhVien> list;
    ThanhVienDAO dao;
    ThanhVienAdapter adapter;
    ThanhVien item;
    EditText edt_matv,edt_tentv,edt_namsinh,edt_sotaikhoan;
    Button btn_save,btn_huy;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_q_l_t_v_, container, false);
        rcy = view.findViewById(R.id.rcy_qltv);
        btn_add = view.findViewById(R.id.fab);
        dao = new ThanhVienDAO(getActivity());
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
        dialog.setContentView(R.layout.addtv_dialog);
        dialog.show();
        Window window =dialog.getWindow();
        if (window==null){
            return;
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
        btn_huy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                item = new ThanhVien();
                item.setHoTen(edt_tentv.getText().toString());
                item.setNamSinh(edt_namsinh.getText().toString());
                item.setSoTaiKhoan(edt_sotaikhoan.getText().toString());
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
        adapter = new ThanhVienAdapter(getActivity());
        adapter.setData(list);
        LinearLayoutManager manager = new LinearLayoutManager(getActivity(),RecyclerView.VERTICAL,false);
        rcy.setLayoutManager(manager);
        rcy.setAdapter(adapter);
    }
    public int validate(){
        int check = 1;
        if (edt_tentv.getText().length()==0|| edt_namsinh.getText().length()==0 || edt_sotaikhoan.getText().toString().length() == 0){
            Toast.makeText(getActivity(),"Bạn phải nhập đầy đủ thông tin",Toast.LENGTH_SHORT).show();
            check = -1;
        }
        return check;
    }
}