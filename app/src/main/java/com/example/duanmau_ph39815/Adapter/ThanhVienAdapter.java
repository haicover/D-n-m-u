package com.example.duanmau_ph39815.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.duanmau_ph39815.R;
import com.example.duanmau_ph39815.fragment.ThanhVienFragment;
import com.example.duanmau_ph39815.model.ThanhVien;

import java.util.ArrayList;

public class ThanhVienAdapter extends ArrayAdapter<ThanhVien> {
    Context context;
    ThanhVienFragment fragment;
    private ArrayList<ThanhVien> list;
    TextView maTV, tvTenTV, tvNamSinh;
    ImageView imgDel;

    public ThanhVienAdapter(Context context, ThanhVienFragment fragment, ArrayList<ThanhVien> list) {
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
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.thanh_vien_item, null);

        }

        final ThanhVien thanhVien = list.get(position);
        if (thanhVien != null) {
            maTV = v.findViewById(R.id.tvMaTV);
            maTV.setText("Mã thành viên: " + thanhVien.getMaTV());

            tvTenTV = v.findViewById(R.id.tvTenTV);
            tvTenTV.setText("Tên thành viên: " + thanhVien.getHoTenTV());

            tvNamSinh = v.findViewById(R.id.tvNamSinh);
            tvNamSinh.setText("Năm sinh: " + thanhVien.getNamSinh());

            imgDel = v.findViewById(R.id.imgDeleteTV);
        }
        imgDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragment.xoa(String.valueOf(thanhVien.getMaTV()));
            }
        });
        return v;
    }

}
