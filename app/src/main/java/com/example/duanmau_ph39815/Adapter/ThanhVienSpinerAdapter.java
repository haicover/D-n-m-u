package com.example.duanmau_ph39815.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.duanmau_ph39815.R;
import com.example.duanmau_ph39815.model.ThanhVien;

import java.util.ArrayList;

public class ThanhVienSpinerAdapter extends ArrayAdapter<ThanhVien> {
    Context context;
    private ArrayList<ThanhVien> list;
    TextView tvMaTV, tvTenTV;
    public ThanhVienSpinerAdapter(Context context, ArrayList<ThanhVien> list){
        super(context,0,list);
        this.context  = context;
        this.list = list;

    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = convertView;
        if (view == null){
            LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.thanh_vien_item_spiner, null);
        }

        final ThanhVien thanhVien = list.get(position);
        if (thanhVien!=null){
            tvMaTV = view.findViewById(R.id.tvMaTVSp);
            tvMaTV.setText(thanhVien.getMaTV()+".");

            tvTenTV = view.findViewById(R.id.tvTenTVSp);
            tvTenTV.setText(thanhVien.getHoTenTV());
        }
        return view;
    }

    @Override
    public View getDropDownView(int position,  View convertView, @NonNull ViewGroup parent) {
        View view = convertView;
        if (view == null){
            LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.thanh_vien_item_spiner, null);
        }

        final ThanhVien thanhVien = list.get(position);
        if (thanhVien!=null){
            tvTenTV = view.findViewById(R.id.tvTenTVSp);
            tvTenTV.setText(thanhVien.getHoTenTV());
        }
        return view
                ;
    }
}
