package com.example.manhdqph20768_assignment_duanmau.fragment;

import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.fragment.app.Fragment;

import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.manhdqph20768_assignment_duanmau.Dao.ThuThuDAO;
import com.example.manhdqph20768_assignment_duanmau.Model.ThuThu;
import com.example.manhdqph20768_assignment_duanmau.R;

public class DoiMatKhau_Fragment extends Fragment {
    EditText edt_mkcu,edt_mkmoi,edt_mkmoi2;
    Button btn_doimatkhau;
    ImageView img_show1,img_show2;
    ThuThuDAO thuThuDAO;
    float v = 0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_doi_mat_khau_, container, false);

        edt_mkcu = view.findViewById(R.id.edt_mkcu_doimatkhau);
        edt_mkmoi = view.findViewById(R.id.edt_mk_doimatkhau);
        edt_mkmoi2 = view.findViewById(R.id.edt_mk2_doimatkhau);
        btn_doimatkhau = view.findViewById(R.id.btn_doimatkhau);
        img_show1 = view.findViewById(R.id.img_showpass_doimatkhau);
        img_show2 = view.findViewById(R.id.img_showpass2_doimatkhau);
        thuThuDAO = new ThuThuDAO(getContext());

        animationShow();

        img_show1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(edt_mkmoi.getInputType() == InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD) {
                    edt_mkmoi.setInputType( InputType.TYPE_CLASS_TEXT |
                            InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    img_show1.setImageResource(R.drawable.ic_eye_off);
                }else {
                    edt_mkmoi.setInputType( InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD );
                    img_show1.setImageResource(R.drawable.ic_eye_show);
                }
                edt_mkmoi.setSelection(edt_mkmoi.getText().length());
            }
        });
        img_show2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(edt_mkmoi2.getInputType() == InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD) {
                    edt_mkmoi2.setInputType( InputType.TYPE_CLASS_TEXT |
                            InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    img_show2.setImageResource(R.drawable.ic_eye_off);
                }else {
                    edt_mkmoi2.setInputType( InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD );
                    img_show2.setImageResource(R.drawable.ic_eye_show);
                }
                edt_mkmoi2.setSelection(edt_mkmoi2.getText().length());
            }
        });
        btn_doimatkhau.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences pref = getActivity().getSharedPreferences("USER_FILE",Context.MODE_PRIVATE);
                String user = pref.getString("USERNAME","");
                if (validate()>0){
                    ThuThu thuThu = thuThuDAO.getID(user);
                    thuThu.setMatkhau(edt_mkmoi.getText().toString());
                    thuThuDAO.updatePass(thuThu);
                    if(thuThuDAO.updatePass(thuThu)>0){
                        Toast.makeText(getContext(), "Thay đổi mật khẩu thành công", Toast.LENGTH_SHORT).show();
                        edt_mkcu.setText(" ");
                        edt_mkmoi.setText(" ");
                        edt_mkmoi2.setText(" ");
                    }else {
                        Toast.makeText(getContext(), "Thay đổi mật khẩu thất bại", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        return view;
    }

    private void animationShow() {
        edt_mkcu.setTranslationX(800);
        edt_mkmoi.setTranslationX(800);
        edt_mkmoi2.setTranslationX(800);
        btn_doimatkhau.setTranslationX(800);

        edt_mkcu.setAlpha(v);
        edt_mkmoi.setAlpha(v);
        edt_mkmoi2.setAlpha(v);
        btn_doimatkhau.setAlpha(v);

        edt_mkcu.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(300).start();
        edt_mkmoi.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(500).start();
        edt_mkmoi2.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(700).start();
        btn_doimatkhau.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(900).start();
    }
    public int validate(){
        int check = 1;
        if(edt_mkcu.getText().length()==0|| edt_mkmoi.getText().length()==0||edt_mkmoi2.getText().length()==0){
            Toast.makeText(getContext(), "Bạn phải nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
            check = -1;
        }else {
            SharedPreferences pref = getActivity().getSharedPreferences("USER_FILE", Context.MODE_PRIVATE);
            String mkcu = pref.getString("PASSWORD","");
            String mk = edt_mkmoi.getText().toString();
            String mkmoi = edt_mkmoi2.getText().toString();
            if (!mkcu.equals(edt_mkcu.getText().toString())){
                Toast.makeText(getContext(), "Mật khẩu cũ sai", Toast.LENGTH_SHORT).show();
            }
            if (!mk.equals(mkmoi)){
                Toast.makeText(getContext(), "Mật khẩu không trùng khớp", Toast.LENGTH_SHORT).show();
                check = -1;
            }
        }
        return check;
    }
}