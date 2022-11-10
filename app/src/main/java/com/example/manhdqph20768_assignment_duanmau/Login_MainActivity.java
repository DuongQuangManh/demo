package com.example.manhdqph20768_assignment_duanmau;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.viewpager2.widget.ViewPager2;

import com.example.manhdqph20768_assignment_duanmau.Adapter.ViewPagerAdapterLogin;
import com.example.manhdqph20768_assignment_duanmau.Dao.LoaiSachDAO;
import com.example.manhdqph20768_assignment_duanmau.Dao.PhieuMuonDAO;
import com.example.manhdqph20768_assignment_duanmau.Dao.SachDAO;
import com.example.manhdqph20768_assignment_duanmau.Dao.ThanhVienDAO;
import com.example.manhdqph20768_assignment_duanmau.Dao.ThuThuDAO;
import com.example.manhdqph20768_assignment_duanmau.Model.LoaiSach;
import com.example.manhdqph20768_assignment_duanmau.Model.PhieuMuon;
import com.example.manhdqph20768_assignment_duanmau.Model.Sach;
import com.example.manhdqph20768_assignment_duanmau.Model.ThanhVien;
import com.example.manhdqph20768_assignment_duanmau.Model.ThuThu;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.Date;

public class Login_MainActivity extends AppCompatActivity {

    ImageView bg,logo;
    ViewPager2 viewPager2;
    TabLayout tabLayout;
    ViewPagerAdapterLogin viewPagerAdapter;
    String[] tab = {"Đăng nhập","Đăng ký"};
    FloatingActionButton face,gg,twitter;
    ThuThuDAO thuThuDAO;
    SachDAO sachDAO;
    LoaiSachDAO loaiSachDAO;
    PhieuMuonDAO phieuMuonDAO;
    ThanhVienDAO thanhVienDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_main);
        viewPager2 = findViewById(R.id.view_pager);
        tabLayout = findViewById(R.id.tab_layout);
        face = findViewById(R.id.fltbtn_face);
        gg = findViewById(R.id.fltbtn_gg);
        twitter = findViewById(R.id.fltbtn_twitter);
        viewPagerAdapter = new ViewPagerAdapterLogin(this);
        viewPager2.setAdapter(viewPagerAdapter);
        new TabLayoutMediator(tabLayout,viewPager2,(tab1, position) -> tab1.setText(tab[position])).attach();
        thuThuDAO = new ThuThuDAO(this);
        sachDAO = new SachDAO(this);
        loaiSachDAO = new LoaiSachDAO(this);
        thanhVienDAO = new ThanhVienDAO(this);
        phieuMuonDAO = new PhieuMuonDAO(this);
//
//        ThuThu thuThu = new ThuThu("admin","Dương Quang Mạnh","123");
//        thuThuDAO.insert(thuThu);
//
//        LoaiSach loaiSach = new LoaiSach();
//        loaiSach.setTenLoai("CNTT");
//        loaiSachDAO.insert(loaiSach);
//
//        Sach sach = new Sach();
//        sach.setTenSach("JAVA1");
//        sach.setGiaThue(2000);
//        sach.setMaLoai(loaiSach.getMaLoai());
//        sachDAO.insert(sach);
//
//        ThanhVien thanhVien = new ThanhVien();
//        thanhVien.setHoTen("Duong Quang Manh");
//        thanhVien.setNamSinh("2003");
//        thanhVienDAO.insert(thanhVien);
//
//        PhieuMuon phieuMuon = new PhieuMuon();
//        phieuMuon.setMaTV(thanhVien.getMaTV());
//        phieuMuon.setMaSach(sach.getMaSach());
//        phieuMuon.setNgay(new Date());
//        phieuMuon.setTienThue(sach.getGiaThue());
//        phieuMuon.setTraSach(0);

    }
}