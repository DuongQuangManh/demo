package com.example.manhdqph20768_assignment_duanmau;

import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.manhdqph20768_assignment_duanmau.Dao.ThuThuDAO;


public class Login_Fragment extends Fragment {
    EditText tk,mk;
    Button login;
    ImageView img_eyeshow;
    float v = 0;
    CheckBox ckb;
    String strUser,strPass;
    ThuThuDAO dao;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup view = (ViewGroup) inflater.inflate(R.layout.fragment_login_, container, false);
        tk = view.findViewById(R.id.edt_tk_login);
        mk = view.findViewById(R.id.edt_mk_login);
        login = view.findViewById(R.id.btn_dangnhap_login);
        img_eyeshow = view.findViewById(R.id.img_showpass_login);
        ckb = view.findViewById(R.id.ckb_luumatkhau);
        dao = new ThuThuDAO(getContext());
        animateLogin();

        SharedPreferences preferences = getActivity().getSharedPreferences("USER_FILE", Context.MODE_PRIVATE);
        String user = preferences.getString("USERNAME","");
        String pass =preferences.getString("PASSWORD","");
        Boolean rem = preferences.getBoolean("REMEMBER",false);

        tk.setText(user);
        mk.setText(pass);
        ckb.setChecked(rem);


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkLogin();
            }
        });
        img_eyeshow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mk.getInputType() == InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD) {
                    mk.setInputType( InputType.TYPE_CLASS_TEXT |
                            InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    img_eyeshow.setImageResource(R.drawable.ic_eye_off);
                }else {
                    mk.setInputType( InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD );
                    img_eyeshow.setImageResource(R.drawable.ic_eye_show);
                }
                mk.setSelection(mk.getText().length());
            }
        });
        return view;
    }
    public void animateLogin(){
        tk.setTranslationX(800);
        mk.setTranslationX(800);
        login.setTranslationX(800);

        tk.setAlpha(v);
        mk.setAlpha(v);
        login.setAlpha(v);

        tk.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(300).start();
        mk.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(500).start();
        login.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(700).start();
    }
    public void rememberUser(String u,String p,boolean status){
        SharedPreferences pref = getActivity().getSharedPreferences("USER_FILE",Context.MODE_PRIVATE);
        SharedPreferences.Editor edt = pref.edit();
        if (!status){
            edt.clear();
        }else {
            edt.putString("USERNAME",u);
            edt.putString("PASSWORD",p);
            edt.putBoolean("REMEMBER",status);
        }
        edt.commit();
    }
    public void checkLogin(){
        strUser = tk.getText().toString();
        strPass = mk.getText().toString();
        if (strUser.isEmpty()|| strPass.isEmpty()){
            Toast.makeText(getContext(), "Tên đăng nhập và mật khẩu không được bỏ trống", Toast.LENGTH_SHORT).show();
        }else {
            if (dao.checkLogin(strUser,strPass)>0){
                Toast.makeText(getContext(), "Login thành công", Toast.LENGTH_SHORT).show();
                rememberUser(strUser,strPass,ckb.isChecked());
                Intent i = new Intent(getContext(),MainActivity.class);
                i.putExtra("user",strUser);
                startActivity(i);
                getActivity().finish();
            }else {
                Toast.makeText(getContext(), "Login thất bại", Toast.LENGTH_SHORT).show();
            }
        }
    }
}