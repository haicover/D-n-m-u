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

import com.example.duanmau_ph39815.Dao.LoaiSachDAO;
import com.example.duanmau_ph39815.R;
import com.example.duanmau_ph39815.fragment.SachFragment;
import com.example.duanmau_ph39815.model.LoaiSach;
import com.example.duanmau_ph39815.model.Sach;

import java.util.ArrayList;
import java.util.List;

public class SachAdapter extends ArrayAdapter<Sach> {
    private Context context;
    SachFragment sachFragment;
    private ArrayList<Sach> list;
    TextView tvMaSach, tvTenSach, tvGiaThue, tvLoai;
    ImageView imgDel;


    public SachAdapter(@NonNull Context context, SachFragment sachFragment, ArrayList<Sach> list) {
        super(context, 0, list);
        this.context = context;
        this.list = list;
        this.sachFragment = sachFragment;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v = convertView;
        if (v == null) {
            LayoutInflater inflater = (LayoutInflater)
                    context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.sach_item, null);
        }
        final Sach item = list.get(position);
        if (item != null) {
            LoaiSachDAO loaiSachDAO = new LoaiSachDAO(context);
            LoaiSach loaiSach = loaiSachDAO.getID((String.valueOf(item.getMaLoai())));

            tvMaSach = v.findViewById(R.id.tvMaS);
            tvMaSach.setText("Mã Sách: " + item.getMaSach());

            tvTenSach = v.findViewById(R.id.tvTenS);
            tvTenSach.setText("Tên Sách: " + item.getTenSach());

            tvGiaThue = v.findViewById(R.id.tvGiaThue);
            tvGiaThue.setText("Giá Thuê: " + item.getGiaThue());

            tvLoai = v.findViewById(R.id.tvLoai);
            tvLoai.setText("Loại sách: " + loaiSach.getTenLoai());

            imgDel = v.findViewById(R.id.imgDeleteS);
        }
        imgDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sachFragment.xoa(String.valueOf(item.maSach));
            }
        });
        return v;
    }
}
