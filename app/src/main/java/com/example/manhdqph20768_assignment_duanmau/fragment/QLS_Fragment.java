package com.example.manhdqph20768_assignment_duanmau.fragment;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.manhdqph20768_assignment_duanmau.Adapter.SachAdapter;
import com.example.manhdqph20768_assignment_duanmau.Adapter.SpinnerAdapter;
import com.example.manhdqph20768_assignment_duanmau.Adapter.ThanhVienAdapter;
import com.example.manhdqph20768_assignment_duanmau.Dao.LoaiSachDAO;
import com.example.manhdqph20768_assignment_duanmau.Dao.SachDAO;
import com.example.manhdqph20768_assignment_duanmau.Dao.ThanhVienDAO;
import com.example.manhdqph20768_assignment_duanmau.Model.LoaiSach;
import com.example.manhdqph20768_assignment_duanmau.Model.Sach;
import com.example.manhdqph20768_assignment_duanmau.Model.ThanhVien;
import com.example.manhdqph20768_assignment_duanmau.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;


public class QLS_Fragment extends Fragment {
    RecyclerView rcy;
    FloatingActionButton btn_add;
    List<Sach> list;
    SachDAO dao;
    SachAdapter adapter;
    Sach item;
    EditText edt_masach,edt_tensach,edt_giathue,edt_soluong,edt_search;
    Spinner spn_loaisach;
    Button btn_save,btn_huy;

    SpinnerAdapter spinnerAdapter;
    List<LoaiSach> listLoaiSach;
    LoaiSachDAO loaiSachDAO;
    int maLoaiSach;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_q_l_s_, container, false);
        rcy = view.findViewById(R.id.rcy_qls);
        edt_search = view.findViewById(R.id.edt_search);
        btn_add = view.findViewById(R.id.fabsach);
        dao = new SachDAO(getActivity());
        capNhat();
        edt_search.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH){
                    search(edt_search.getText().toString());
                }
                return false;
            }
        });

        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                opendialog(getContext());
            }
        });
        return view;
    }

    private void search(String search) {
        adapter.getFilter().filter(search);
        hideSoftKeyBroad();
    }

    private void opendialog(final Context context) {
        final Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.addsach_dialog);
        dialog.show();
        Window window =dialog.getWindow();
        if (window==null){
            return;
        }
        window.setGravity(Gravity.CENTER);
        window.setBackgroundDrawable(null);
        edt_masach = dialog.findViewById(R.id.edt_masach_dialogsach);
        edt_masach.setEnabled(false);
        edt_tensach = dialog.findViewById(R.id.edt_tensach_dialogsach);
        edt_giathue = dialog.findViewById(R.id.edt_giathuesach_dialogsach);
        edt_soluong = dialog.findViewById(R.id.edt_soluong_dialogsach);
        spn_loaisach = dialog.findViewById(R.id.spinner_dialogsach);
        listLoaiSach = new ArrayList<>();
        loaiSachDAO = new LoaiSachDAO(getContext());
        listLoaiSach = loaiSachDAO.getAll();
        spinnerAdapter = new SpinnerAdapter(getContext(),R.layout.item_spinner_loaisach,listLoaiSach);
        spn_loaisach.setAdapter(spinnerAdapter);

        spn_loaisach.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                maLoaiSach = listLoaiSach.get(position).getMaLoai();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        btn_save = dialog.findViewById(R.id.btn_luu_dialogsach);
        btn_huy = dialog.findViewById(R.id.btn_huy_dialogsach);

        btn_huy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                item = new Sach();
                item.setTenSach(edt_tensach.getText().toString());
                item.setGiaThue(Integer.parseInt(edt_giathue.getText().toString()));
                item.setSoLuong(Integer.parseInt(edt_soluong.getText().toString()));
                item.setMaLoai(maLoaiSach);
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
        adapter = new SachAdapter(getActivity());
        adapter.setData(list);
        LinearLayoutManager manager = new LinearLayoutManager(getActivity(),RecyclerView.VERTICAL,false);
        rcy.setLayoutManager(manager);
        rcy.setAdapter(adapter);
    }
    public int validate(){
        int check = 1;
        if (edt_giathue.getText().length()==0|| edt_tensach.getText().length()==0 || edt_soluong.getText().toString().length()==0){
            Toast.makeText(getActivity(),"Bạn phải nhập đầy đủ thông tin",Toast.LENGTH_SHORT).show();
            check = -1;
        }
        return check;
    }
    public void hideSoftKeyBroad(){
        InputMethodManager inputMethodManager = (InputMethodManager) getActivity().getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(getActivity().getCurrentFocus().getWindowToken(),0);
    }
}