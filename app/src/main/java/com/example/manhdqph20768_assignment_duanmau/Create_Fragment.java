package com.example.manhdqph20768_assignment_duanmau;

import android.os.Bundle;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.manhdqph20768_assignment_duanmau.Dao.ThuThuDAO;
import com.example.manhdqph20768_assignment_duanmau.Model.ThuThu;

import java.util.regex.Pattern;

public class Create_Fragment extends Fragment {
    EditText tk,mk,mk2,hoten;
    Button taotk;
    ImageView img_show1,img_show2;
    float v = 0;
    ThuThuDAO dao;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup view = (ViewGroup) inflater.inflate(R.layout.fragment_create_, container, false);
        tk = view.findViewById(R.id.edt_tk_create);
        mk = view.findViewById(R.id.edt_mk_create);
        mk2 = view.findViewById(R.id.edt_mk2_create);
        taotk = view.findViewById(R.id.btn_taotk_create);
        img_show1 = view.findViewById(R.id.img_showpass_create);
        img_show2 = view.findViewById(R.id.img_showpass2_create);
        hoten = view.findViewById(R.id.edt_hoten_create);

        dao = new ThuThuDAO(getContext());

        tk.setTranslationX(800);
        mk.setTranslationX(800);
        taotk.setTranslationX(800);
        mk2.setTranslationX(800);
        hoten.setTranslationX(800);

        tk.setAlpha(v);
        mk.setAlpha(v);
        taotk.setAlpha(v);
        mk2.setAlpha(v);
        hoten.setAlpha(v);

        tk.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(300).start();
        hoten.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(500).start();
        mk.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(700).start();
        mk2.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(900).start();
        taotk.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(1100).start();
        img_show1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mk.getInputType() == InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD) {
                    mk.setInputType( InputType.TYPE_CLASS_TEXT |
                            InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    img_show1.setImageResource(R.drawable.ic_eye_off);
                }else {
                    mk.setInputType( InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD );
                    img_show1.setImageResource(R.drawable.ic_eye_show);
                }
                mk.setSelection(mk.getText().length());
            }
        });
        img_show2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mk2.getInputType() == InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD) {
                    mk2.setInputType( InputType.TYPE_CLASS_TEXT |
                            InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    img_show2.setImageResource(R.drawable.ic_eye_off);
                }else {
                    mk2.setInputType( InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD );
                    img_show2.setImageResource(R.drawable.ic_eye_show);
                }
                mk2.setSelection(mk2.getText().length());
            }
        });
        taotk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = tk.getText().toString().trim();
                String name = hoten.getText().toString().trim();
                String pass = mk.getText().toString().trim();
                if (checkValidate()){
                    ThuThu thuThu = new ThuThu();
                    thuThu.setMaTT(user);
                    thuThu.setHoTen(name);
                    thuThu.setMatkhau(pass);
                    long check = dao.insert(thuThu);
                    if (check== -1){
                        Toast.makeText(getContext(), "Insert thất bại", Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(getContext(),"Insert thành công",Toast.LENGTH_SHORT).show();
                        tk.setText("");
                        hoten.setText("");
                        mk.setText("");
                        mk2.setText("");
                    }
                }

            }
        });

        return view;
    }
    public boolean checkValidate(){
        String user = tk.getText().toString().trim();
        String name = hoten.getText().toString().trim();
        String pass = mk.getText().toString().trim();
        String pass2 = mk2.getText().toString().trim();
        String email = "^\\w@\\w.\\w";
        Pattern pattern = Pattern.compile(email);
        if (user.isEmpty() || name.isEmpty() || pass.isEmpty() || pass2.isEmpty()){
            Toast.makeText(getContext(), "Vui lòng không bỏ trống thông tin ", Toast.LENGTH_SHORT).show();
            return false;
        }else {
            if (pattern.matcher(user).find()){
                if (pass.equals(pass2)){
                    return true;
                }else {
                    Toast.makeText(getContext(), "Mật khẩu không trùng khớp", Toast.LENGTH_SHORT).show();
                    return false;
                }
            }else {
                Toast.makeText(getContext(), "Vui lòng nhập đúng định dạng email", Toast.LENGTH_SHORT).show();
                return false;
            }
        }
    }
}