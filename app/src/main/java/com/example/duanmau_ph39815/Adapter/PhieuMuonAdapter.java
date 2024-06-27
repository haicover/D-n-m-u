package com.example.duanmau_ph39815.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.duanmau_ph39815.Dao.PhieuMuonDAO;
import com.example.duanmau_ph39815.Dao.SachDAO;
import com.example.duanmau_ph39815.Dao.ThanhVienDAO;
import com.example.duanmau_ph39815.R;
import com.example.duanmau_ph39815.fragment.PhieuMuonFragment;
import com.example.duanmau_ph39815.model.PhieuMuon;
import com.example.duanmau_ph39815.model.Sach;
import com.example.duanmau_ph39815.model.ThanhVien;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class PhieuMuonAdapter extends ArrayAdapter<PhieuMuon> {
    private Context context;
    PhieuMuonFragment fragment;
    private ArrayList<PhieuMuon> list;
    TextView tvMaPM, tvTenTV, tvTenSach, tvTienThue, tvNgay, tvTraSach,tvTime;
    ImageView imgDel;
    SachDAO sachDAO;
    ThanhVienDAO thanhVienDAO;
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");

    public PhieuMuonAdapter(@NonNull Context context, PhieuMuonFragment fragment, ArrayList<PhieuMuon> list) {
        super(context, 0, list);
        this.context = context;
        this.list = list;
        this.fragment = fragment;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v = convertView;
        if (v == null) {
            LayoutInflater inflater = (LayoutInflater)
                    context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.phieu_muon_item, null);
        }
        final PhieuMuon item = list.get(position);
        if (item != null) {
            tvMaPM = v.findViewById(R.id.tvMaPM);
            tvMaPM.setText("Mã Phiếu: " + item.getMaPM());

            sachDAO = new SachDAO(context);
            Sach sach = sachDAO.getID(String.valueOf(item.getMaSach()));
            tvTenSach = v.findViewById(R.id.tvTenSach);
            tvTenSach.setText("Tên Sách: " + sach.tenSach);

            thanhVienDAO = new ThanhVienDAO(context);
            ThanhVien thanhVien = thanhVienDAO.getID(String.valueOf(item.getMaTV()));
            tvTenTV = v.findViewById(R.id.tvTenTVv);
            tvTenTV.setText("Thành Viên: " + thanhVien.hoTenTV);
            tvTienThue = v.findViewById(R.id.tvTienThue);
            tvTienThue.setText("Tiền Thuê: " + item.getTienThue());
            tvNgay = v.findViewById(R.id.tvNgayPM);
            tvNgay.setText("Ngày thuê: " + sdf.format(item.getNgayThue()));
            tvTraSach = v.findViewById(R.id.tvTraSach);
            if (item.getTraSach() == 1) {
                tvTraSach.setTextColor(Color.BLUE);
                tvTraSach.setText("Đã trả sách");
            } else {
                tvTraSach.setTextColor(Color.RED);
                tvTraSach.setText("Chưa trả sách");
            }
            imgDel = v.findViewById(R.id.imgDeletePM);
            tvTime = v.findViewById(R.id.tvTime);
            tvTime.setText("Giờ thuê: "+item.getGio());
            if (item.getTienThue() <5000) {
                tvTienThue.setTextColor(Color.BLUE);
            } else {
                tvTienThue.setTextColor(Color.RED);
            }
            if (item.getTraSach() == 1) {
                tvTraSach.setTextColor(Color.BLUE);
                tvTraSach.setText("Đã trả sách");
            } else {
                tvTraSach.setTextColor(Color.RED);
                tvTraSach.setText("Chưa trả sách");
            }

        }
        imgDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragment.xoa(String.valueOf(item.getMaPM()));
                notifyDataSetChanged();
            }
        });
        return v;

    }
}
