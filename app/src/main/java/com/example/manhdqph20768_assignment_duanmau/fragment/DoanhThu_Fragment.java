package com.example.manhdqph20768_assignment_duanmau.fragment;

import android.app.DatePickerDialog;
import android.os.Bundle;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.manhdqph20768_assignment_duanmau.Dao.PhieuMuonDAO;
import com.example.manhdqph20768_assignment_duanmau.R;
import com.google.android.material.textfield.TextInputLayout;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class DoanhThu_Fragment extends Fragment {
    Button btn_doanhthu;
    TextInputLayout txt_fromNgay,txt_toNgay;
    EditText edt_fromNgay,edt_toNgay;
    TextView tv_doanhthu;
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
    Calendar lich = Calendar.getInstance();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_doanh_thu_, container, false);
        btn_doanhthu = view.findViewById(R.id.btn_doanhthu);
        txt_fromNgay = view.findViewById(R.id.tip_fromNgay);
        txt_toNgay = view.findViewById(R.id.tip_toNgay);
        edt_fromNgay = view.findViewById(R.id.edt_fromNgay_doanhthu);
        edt_toNgay = view.findViewById(R.id.edt_toNgay_doanhthu);
        tv_doanhthu = view.findViewById(R.id.tvDoanhThu);
        txt_fromNgay.setStartIconOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int day = lich.get(Calendar.DAY_OF_MONTH);
                int month = lich.get(Calendar.MONTH);
                int year = lich.get(Calendar.YEAR);
                DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        edt_fromNgay.setText(dayOfMonth+"-"+(month+1)+"-"+year);
                    }
                },year,month,day);
                datePickerDialog.show();
            }
        });
        txt_toNgay.setStartIconOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int day = lich.get(Calendar.DAY_OF_MONTH);
                int month = lich.get(Calendar.MONTH);
                int year = lich.get(Calendar.YEAR);
                DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        edt_toNgay.setText(dayOfMonth+"-"+(month+1)+"-"+year);
                    }
                },year,month,day);
                datePickerDialog.show();
            }
        });
        btn_doanhthu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String strfromNgay = edt_fromNgay.getText().toString();
                Log.d("ngay",strfromNgay);
                String strtoNgay = edt_toNgay.getText().toString();
                Log.d("ngay",strtoNgay);
                PhieuMuonDAO dao = new PhieuMuonDAO(getActivity());
                tv_doanhthu.setText("Doanh thu: "+dao.getDanhThu(strfromNgay,strtoNgay)+" VNĐ");
            }
        });
        return  view;
    }
}