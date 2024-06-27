package com.example.duanmau_ph39815.fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.duanmau_ph39815.Adapter.LoaiSachAdapter;
import com.example.duanmau_ph39815.Dao.LoaiSachDAO;
import com.example.duanmau_ph39815.R;
import com.example.duanmau_ph39815.model.LoaiSach;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class LoaiSachFragment extends Fragment {
    ListView lvLoaiSach;
    ArrayList<LoaiSach> list;
    FloatingActionButton fab;
    Dialog dialog;
    EditText edMaLs, edTenLs;
    TextView titleLS;
    Button btnSave, btnCancel;
    static LoaiSachDAO dao;
    LoaiSachAdapter adapter;
    LoaiSach item;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.fragment_loai_sach, container, false);
        lvLoaiSach = v.findViewById(R.id.lvLoaiSach);
        fab = v.findViewById(R.id.fabLs);
        dao = new LoaiSachDAO(getActivity());
        capNhapLV();
        fab.setOnClickListener((view) -> {
            openDialog(getActivity(),0);
        });
        lvLoaiSach.setOnItemLongClickListener((adapterView, view, i, l) -> {
            item = list.get(i);
            openDialog(getActivity(),1);
            return false;
        });
        return v;
    }
    protected void openDialog(final Context context, final int type){
        //custom dialog
        dialog = new Dialog(context);
        dialog.setContentView(R.layout.loai_sach_dialog);
        edMaLs = dialog.findViewById(R.id.edMaLS);
        edTenLs = dialog.findViewById(R.id.edTenLS);
        btnCancel = dialog.findViewById(R.id.btnCancelS);
        btnSave = dialog.findViewById(R.id.btnSaveLS);
        //kiem tra type insert 0 hay update 1
        edMaLs.setEnabled(false);
        if (type!=0){
            edMaLs.setText(String.valueOf(item.maLoai));
            edTenLs.setText(item.tenLoai);
        }

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                item = new LoaiSach();
                item.tenLoai = edTenLs.getText().toString();
                if (validate()>0){
                    if (type==0){
                        //type = 0 (insert)

                        if (dao.insert(item)>0){
                            Toast.makeText(context, "Thêm thành công", Toast.LENGTH_SHORT).show();
                        }
                        else {
                            Toast.makeText(context, "Thêm thất bại", Toast.LENGTH_SHORT).show();
                        }
                    }else {
                        //type = 1 (update)

                        item.maLoai = Integer.parseInt(edMaLs.getText().toString());
                        if (dao.update(item)>0){
                            Toast.makeText(context, "Sửa thành công", Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(context, "Sửa thất bại", Toast.LENGTH_SHORT).show();
                        }
                    }
                    capNhapLV();
                    dialog.dismiss();
                }
            }
        });
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();
    }
    public void xoa(final String Id) {
        //sử dụng Alert
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Delete");
        builder.setMessage("Bạn có muốn xóa không");
        builder.setCancelable(true);

        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                Toast.makeText(getContext(), "Xóa thành công", Toast.LENGTH_SHORT).show();
                //gọi function delete
                dao.delete(Id);
                //cập nhật listview
                capNhapLV();
                dialog.cancel();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        AlertDialog alertDialog = builder.create();
        builder.show();
    }
    void capNhapLV() {
        list = (ArrayList<LoaiSach>) dao.getAll();
        adapter = new LoaiSachAdapter(getActivity(), this, list);
        lvLoaiSach.setAdapter(adapter);
    }

    public int validate(){
        int check = 1;
        if (edTenLs.getText().length()==0){
            Toast.makeText(getContext(), "Bạn phải nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
        }
        return check;
    }

}