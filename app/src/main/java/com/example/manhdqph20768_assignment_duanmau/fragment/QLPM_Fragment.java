package com.example.manhdqph20768_assignment_duanmau.fragment;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.manhdqph20768_assignment_duanmau.Adapter.PhieuMuonAdapter;
import com.example.manhdqph20768_assignment_duanmau.Adapter.SachAdapter;
import com.example.manhdqph20768_assignment_duanmau.Adapter.SpinnerAdapter;
import com.example.manhdqph20768_assignment_duanmau.Adapter.SpinnerAdapterThanhVien;
import com.example.manhdqph20768_assignment_duanmau.Dao.LoaiSachDAO;
import com.example.manhdqph20768_assignment_duanmau.Dao.PhieuMuonDAO;
import com.example.manhdqph20768_assignment_duanmau.Dao.SachDAO;
import com.example.manhdqph20768_assignment_duanmau.Dao.ThanhVienDAO;
import com.example.manhdqph20768_assignment_duanmau.Model.PhieuMuon;
import com.example.manhdqph20768_assignment_duanmau.Model.Sach;
import com.example.manhdqph20768_assignment_duanmau.Model.ThanhVien;
import com.example.manhdqph20768_assignment_duanmau.R;
import com.example.manhdqph20768_assignment_duanmau.SpinnerAdapterTenSach;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class QLPM_Fragment extends Fragment {
    RecyclerView rcy;
    FloatingActionButton btn_add;

    List<PhieuMuon> list;
    PhieuMuonDAO dao;
    PhieuMuonAdapter adapter;
    PhieuMuon item;

    EditText edt_mapm;
    Spinner spn_tenthanhvien,spn_tensach;
    TextView tv_ngaythue,tv_tienthue;
    CheckBox cbo_trangthai;
    Button btn_save,btn_huy;

    SpinnerAdapterThanhVien spinnerAdapterThanhVien;
    List<ThanhVien> thanhVienList;
    ThanhVienDAO thanhVienDAO;
    ThanhVien thanhVien;
    int maThanhVien;

    SpinnerAdapterTenSach spinnerAdapterTenSach;
    List<Sach> sachList;
    SachDAO sachDAO;
    Sach sach;
    int maSach, tienThue;

    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_q_l_p_m_, container, false);
        rcy = view.findViewById(R.id.rcy_qlpm);
        btn_add = view.findViewById(R.id.fabpm);
        dao = new PhieuMuonDAO(getActivity());
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
        dialog.setContentView(R.layout.addpm_dialog);
        dialog.show();
        Window window =dialog.getWindow();
        if (window==null){
            return;
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

        thanhVienDAO = new ThanhVienDAO(getContext());
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

        sachDAO = new SachDAO(getContext());
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

        tv_ngaythue.setText("Ngày thuê: "+sdf.format(new Date()));
        tv_tienthue.setText("Tiền thuê: "+tienThue);



        btn_huy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                item = new PhieuMuon();
                item.setMaSach(maSach);
                item.setMaTV(maThanhVien);
                item.setNgay(new Date());
                item.setTienThue(tienThue);
                if (cbo_trangthai.isChecked()){
                    item.setTraSach(1);
                }else {
                    item.setTraSach(0);
                }

                if (dao.insert(item)>0){
                    Toast.makeText(context, "insert thành công", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(context, "insert thất bại", Toast.LENGTH_SHORT).show();
                }
                Log.d("ngay",new Date()+"");
                capNhat();
                dialog.dismiss();
            }
        });

    }

    public void capNhat(){
        list = dao.getAll();
        adapter = new PhieuMuonAdapter(getActivity());
        adapter.setData(list);
        LinearLayoutManager manager = new LinearLayoutManager(getActivity(),RecyclerView.VERTICAL,false);
        rcy.setLayoutManager(manager);
        rcy.setAdapter(adapter);
    }
}