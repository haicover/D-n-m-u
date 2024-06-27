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
import com.example.duanmau_ph39815.model.Sach;

import java.util.ArrayList;

public class SachSpinerAdapter extends ArrayAdapter<Sach> {
    private Context context;
    private ArrayList<Sach> list;
    TextView tvMaSach, tvTenSach;
    public SachSpinerAdapter(@NonNull Context context, ArrayList<Sach> list) {
        super(context, 0, list);
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v = convertView;
        if (v == null) {
            LayoutInflater inflater = (LayoutInflater)
                    context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.sach_item_spiner, null);
        }
        final Sach item = list.get(position);
        if (item != null) {
            tvMaSach = v.findViewById(R.id.tvMaSSp);
            tvMaSach.setText(item.maSach + ". ");
            tvTenSach = v.findViewById(R.id.tvTenSSp);
            tvTenSach.setText(item.tenSach);
        }
        return v;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v = convertView;
        if (v == null) {
            LayoutInflater inflater = (LayoutInflater)
                    context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.sach_item_spiner, null);
        }
        final Sach item = list.get(position);
        if (item != null) {
            tvMaSach = v.findViewById(R.id.tvMaSSp);
            tvMaSach.setText(item.maSach + ". ");
            tvTenSach = v.findViewById(R.id.tvTenSSp);
            tvTenSach.setText(item.tenSach);
        }
        return v;
    }
}
