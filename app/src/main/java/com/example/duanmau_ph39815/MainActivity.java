package com.example.duanmau_ph39815;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
import android.view.View;

import com.example.duanmau_ph39815.Dao.ThuThuDAO;
import com.example.duanmau_ph39815.fragment.AddUserFragment;
import com.example.duanmau_ph39815.fragment.ChangePassFragment;
import com.example.duanmau_ph39815.fragment.DoanhThuFragment;
import com.example.duanmau_ph39815.fragment.LoaiSachFragment;
import com.example.duanmau_ph39815.fragment.PhieuMuonFragment;
import com.example.duanmau_ph39815.fragment.SachFragment;
import com.example.duanmau_ph39815.fragment.ThanhVienFragment;
import com.example.duanmau_ph39815.fragment.TopFragment;
import com.example.duanmau_ph39815.model.ThuThu;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {
    DrawerLayout drawerLayout;
    Toolbar toolbar;
    View mHeaderView;
    ThuThuDAO thuThuDAO;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        NavigationView nav = findViewById(R.id.nav_view);

        drawerLayout = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        mHeaderView = nav.getHeaderView(0);

        FragmentManager manager = getSupportFragmentManager();
        PhieuMuonFragment phieuMuonFragment = new PhieuMuonFragment();
        manager.beginTransaction().replace(R.id.framer, phieuMuonFragment).commit();

        nav.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int itemId = item.getItemId();
                if (itemId == R.id.nav_PhieuMuon) {
                    setTitle("Quản lý phiếu mượn");
                    PhieuMuonFragment phieuMuonFragment = new PhieuMuonFragment();
                    manager.beginTransaction().replace(R.id.framer,phieuMuonFragment).commit();
                } else if (itemId == R.id.nav_LoaiSach) {
                    setTitle("Quản lý loại sách");
                    LoaiSachFragment loaiSachFragment = new LoaiSachFragment();
                    manager.beginTransaction().replace(R.id.framer,loaiSachFragment).commit();
                } else if (itemId == R.id.nav_Sach) {
                    setTitle("Quản lý sách");
                    SachFragment sachFragment = new SachFragment();
                    manager.beginTransaction().replace(R.id.framer,sachFragment ).commit();
                } else if (itemId == R.id.nav_ThanhVien) {
                    setTitle("Quản lý thành viên");
                    ThanhVienFragment thanhVienFragment = new ThanhVienFragment();
                    manager.beginTransaction().replace(R.id.framer,thanhVienFragment).commit();
                } else if (itemId == R.id.sub_Top) {
                    setTitle("Top 10 sách mượn nhiều nhất");
                    TopFragment topFragment = new TopFragment();
                    manager.beginTransaction().replace(R.id.framer,topFragment).commit();
                } else if (itemId == R.id.sub_DoanhThu) {
                    setTitle("Doanh thu");
                    DoanhThuFragment doanhThuFragment = new DoanhThuFragment();
                    manager.beginTransaction().replace(R.id.framer,doanhThuFragment).commit();
                } else if (itemId == R.id.sub_Pass) {
                    setTitle("Đổi mật khẩu");
                    ChangePassFragment changePassFragment = new ChangePassFragment();
                    manager.beginTransaction().replace(R.id.framer,changePassFragment).commit();
                } else if (itemId == R.id.sub_Logout) {
                    startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                    finish();
                }
                drawerLayout.closeDrawers();
                return false;
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id==android.R.id.home)
            drawerLayout.openDrawer(GravityCompat.START);
        return super.onOptionsItemSelected(item);
    }
}