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

import com.example.duanmau_ph39815.R;
import com.example.duanmau_ph39815.fragment.LoaiSachFragment;
import com.example.duanmau_ph39815.model.LoaiSach;

import java.util.ArrayList;

public class LoaiSachAdapter extends ArrayAdapter<LoaiSach> {
    private Context context;
    private LoaiSachFragment loaiSachFragment;
    private ArrayList<LoaiSach> list;
    TextView tvMaLS, tvTenLS;
    ImageView imgDel;



    public LoaiSachAdapter(@NonNull Context context, LoaiSachFragment loaiSachFragment, ArrayList<LoaiSach> list) {
        super(context, 0, list);
        this.context = context;
        this.loaiSachFragment = loaiSachFragment;
        this.list = list;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v = convertView;
        if (v == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.loai_sach_item, null);

        }
        final LoaiSach item = list.get(position);
        if (item != null) {
            tvMaLS = v.findViewById(R.id.tvMaLS);
            tvMaLS.setText("Mã Loại Sách: " + item.maLoai);
            tvTenLS = v.findViewById(R.id.tvTenLS);
            tvTenLS.setText("Tên Loại Sách: " + item.tenLoai);

            imgDel = v.findViewById(R.id.imgDeleteLS);
            imgDel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    loaiSachFragment.xoa(String.valueOf(item.maLoai));
                }
            });
            if (item.getTenLoai().contains("N")==true){
                tvMaLS.setTextColor(Color.RED);

            }else if (item.getTenLoai().contains("A")==true){
                tvMaLS.setTextColor(Color.GREEN);

            }else {
                tvMaLS.setTextColor(Color.BLACK);
            }

        }
        return v;
    }
}
