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
import com.example.duanmau_ph39815.fragment.TopFragment;
import com.example.duanmau_ph39815.model.Top;

import java.util.ArrayList;

public class TopAdapter extends ArrayAdapter<Top> {
    private Context context;
    ArrayList<Top> list;
    TextView tvSach, tvSoLuong;
    public TopAdapter(@NonNull Context context, ArrayList<Top> list) {
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
            v = inflater.inflate(R.layout.top_item, null);
        }
        final Top item = list.get(position);
        if(item != null){
            tvSach = v.findViewById(R.id.tvTopSach);
            tvSach.setText("Sách: " + item.tenSach);
            tvSoLuong = v.findViewById(R.id.tvTopSl);
            tvSoLuong.setText("Số Lượng: " + item.soLuong);
        }
        return v;
    }
}
