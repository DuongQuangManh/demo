package com.example.manhdqph20768_assignment_duanmau;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;


import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.example.manhdqph20768_assignment_duanmau.Dao.LoaiSachDAO;
import com.example.manhdqph20768_assignment_duanmau.Dao.ThuThuDAO;
import com.example.manhdqph20768_assignment_duanmau.Model.LoaiSach;
import com.example.manhdqph20768_assignment_duanmau.Model.ThuThu;
import com.example.manhdqph20768_assignment_duanmau.fragment.DoanhThu_Fragment;
import com.example.manhdqph20768_assignment_duanmau.fragment.DoiMatKhau_Fragment;
import com.example.manhdqph20768_assignment_duanmau.fragment.QLLS_Fragment;
import com.example.manhdqph20768_assignment_duanmau.fragment.QLPM_Fragment;
import com.example.manhdqph20768_assignment_duanmau.fragment.QLS_Fragment;
import com.example.manhdqph20768_assignment_duanmau.fragment.QLTV_Fragment;
import com.example.manhdqph20768_assignment_duanmau.fragment.TOP_Fragment;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {

    private static final int FRAGMENT_QLPM = 0;
    private static final int FRAGMENT_QLLS = 1;
    private static final int FRAGMENT_QLS = 2;
    private static final int FRAGMENT_QLTV = 3;
    private static final int FRAGMENT_TOP10 = 4;
    private static final int FRAGMENT_DOANHTHU = 5;
    private static final int FRAGMENT_DOIMATKHAU = 6;
    private int CurrentFragment = FRAGMENT_QLPM;


    Toolbar toolbar;
    FrameLayout frameLayout;
    DrawerLayout drawerLayout;
    NavigationView navigationView;;
    ThuThuDAO thuThuDAO;
    View headerNav;
    TextView userHeader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = findViewById(R.id.toolbar);
        frameLayout = findViewById(R.id.content_layout);
        drawerLayout = findViewById(R.id.drawerlayout);
        navigationView = findViewById(R.id.nav_view);
        headerNav = navigationView.getHeaderView(0);
        userHeader = headerNav.findViewById(R.id.tv_name_header);

        setSupportActionBar(toolbar);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.nav_drawer_open,R.string.nav_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        Intent i = getIntent();
        String user = i.getStringExtra("user");
        thuThuDAO = new ThuThuDAO(this);
        ThuThu thuThu = thuThuDAO.getID(user);
        String username = thuThu.getHoTen();
        userHeader.setText("Welcome "+username+" !");


        replaceFragment(new QLPM_Fragment());
        navigationView.getMenu().findItem(R.id.qlpm_nav).setChecked(true);

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                if (id == R.id.qlpm_nav){
                    if (CurrentFragment != FRAGMENT_QLPM){
                        replaceFragment(new QLPM_Fragment());
                        CurrentFragment = FRAGMENT_QLPM;
                    }
                }else if (id == R.id.qlls_nav){
                    if (CurrentFragment != FRAGMENT_QLLS){
                        replaceFragment(new QLLS_Fragment());
                        CurrentFragment = FRAGMENT_QLLS;
                    }
                }else if (id == R.id.qls_nav){
                    if (CurrentFragment != FRAGMENT_QLS){
                        replaceFragment(new QLS_Fragment());
                        CurrentFragment = FRAGMENT_QLS;
                    }
                }else if (id == R.id.qltv_nav){
                    if (CurrentFragment != FRAGMENT_QLTV){
                        replaceFragment(new QLTV_Fragment());
                        CurrentFragment = FRAGMENT_QLTV;
                    }
                }else if (id == R.id.top10_nav){
                    if (CurrentFragment != FRAGMENT_TOP10){
                        replaceFragment(new TOP_Fragment());
                        CurrentFragment = FRAGMENT_TOP10;
                    }
                }else if (id == R.id.doanhthu_nav){
                    if (CurrentFragment != FRAGMENT_DOANHTHU){
                        replaceFragment(new DoanhThu_Fragment());
                        CurrentFragment = FRAGMENT_DOANHTHU;
                    }
                }else if (id == R.id.doimatkhau_nav){
                    if (CurrentFragment != FRAGMENT_DOIMATKHAU){
                        replaceFragment(new DoiMatKhau_Fragment());
                        CurrentFragment = FRAGMENT_DOIMATKHAU;
                    }
                }else if (id == R.id.dangxuat_nav){
                    finish();
                }
                drawerLayout.closeDrawer(GravityCompat.START);
                return true;
            }
        });

    }

    private void replaceFragment(Fragment fragment){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.content_layout,fragment);
        transaction.commit();
    }
    @Override
    public void onBackPressed() {
        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }else {
            super.onBackPressed();
        }
    }

}