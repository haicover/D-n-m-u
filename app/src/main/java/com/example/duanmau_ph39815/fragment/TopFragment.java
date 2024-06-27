package com.example.duanmau_ph39815.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.duanmau_ph39815.Adapter.TopAdapter;
import com.example.duanmau_ph39815.Dao.ThongKeDao;
import com.example.duanmau_ph39815.R;
import com.example.duanmau_ph39815.model.Top;

import java.util.ArrayList;

public class TopFragment extends Fragment {

    ListView lv;
    ArrayList<Top> list;
    TopAdapter adapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_top, container, false);
        lv = v.findViewById(R.id.lvTop);
        ThongKeDao thongKeDao = new ThongKeDao(getActivity());
        list = (ArrayList<Top>) thongKeDao.getTop();
        adapter = new TopAdapter(getActivity(),  list);
        lv.setAdapter(adapter);
        return v;
    }
}