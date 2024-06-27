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
import android.widget.Toast;

import com.example.duanmau_ph39815.Adapter.ThanhVienAdapter;
import com.example.duanmau_ph39815.Dao.ThanhVienDAO;
import com.example.duanmau_ph39815.R;
import com.example.duanmau_ph39815.model.ThanhVien;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class ThanhVienFragment extends Fragment {
    ListView lv;
    ArrayList<ThanhVien> list;
    FloatingActionButton fab;
    Dialog dialog;
    EditText edTenTV, edNamSinh, edMaTV;
    Button btn_luu, btn_huy;

    static ThanhVienDAO dao;
    ThanhVienAdapter adapter;
    ThanhVien thanhVien;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_thanh_vien, container, false);
        lv = view.findViewById(R.id.lvThanhVien);
        fab = view.findViewById(R.id.fab);
        dao = new ThanhVienDAO(getActivity());
        capNhatLV();
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog(getActivity(),0);
            }
        });

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                thanhVien = list.get(position);
                openDialog(getActivity(), 1);
            }
        });
        return view;
    }
    protected void openDialog(final Context context, final int type) {
        dialog = new Dialog(context);
        dialog.setContentView(R.layout.thanh_vien_dialog);
        edTenTV = dialog.findViewById(R.id.edTenTV);
        edNamSinh = dialog.findViewById(R.id.edNamSinh);
        edMaTV = dialog.findViewById(R.id.edMaTV);
        btn_huy = dialog.findViewById(R.id.btnCancelTV);
        btn_luu = dialog.findViewById(R.id.btnSaveTV);

        edMaTV.setEnabled(false);
        if (type!= 0){
            edMaTV.setText(String.valueOf(thanhVien.getMaTV()));
            edTenTV.setText(thanhVien.getHoTenTV());
            edNamSinh.setText(thanhVien.getNamSinh());

        }
        btn_huy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        btn_luu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                thanhVien = new ThanhVien();
                thanhVien.setHoTenTV(edTenTV.getText().toString());
                thanhVien.setNamSinh(edNamSinh.getText().toString());

                if (validate()>0){
                    if (type==0){

                        if (dao.insert(thanhVien)>0){
                            Toast.makeText(context, "Thêm thành công", Toast.LENGTH_LONG).show();
                        }else {
                            Toast.makeText(context, "Thêm thất bại",Toast.LENGTH_LONG).show();
                        }
                    }else {

                        thanhVien.setMaTV(Integer.parseInt(edMaTV.getText().toString()));
                        if (dao.update(thanhVien)>0){
                            Toast.makeText(context, "Sửa thành công", Toast.LENGTH_LONG).show();

                        }else {
                            Toast.makeText(context, "Sửa thất bại", Toast.LENGTH_LONG).show();
                        }
                    }
                    capNhatLV();
                    dialog.dismiss();
                }

            }
        });
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();
    }

    public void xoa(final String id){
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Delete");
        builder.setMessage("Bạn có muốn xóa không?");
        builder.setCancelable(true);
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getContext(), "Xóa thành công", Toast.LENGTH_LONG).show();
                dao.delete(id);
                capNhatLV();
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

    void capNhatLV(){
        list = (ArrayList<ThanhVien>)dao.getAll();
        adapter = new ThanhVienAdapter(getActivity(), this, list);
        lv.setAdapter(adapter);
    }

    public  int validate(){
        int check = 1;
        if (edTenTV.getText().length()==0 ||edNamSinh.getText().length()==0 ){
            Toast.makeText(getContext(),"Bạn phải nhập đầy đủ thông tin", Toast.LENGTH_LONG).show();
            check=-1;
        }else if (Integer.parseInt(edNamSinh.getText().toString())>2021 || Integer.parseInt(edNamSinh.getText().toString())<1800){
            Toast.makeText(getContext(),"Năm sinh không hợp lệ",Toast.LENGTH_SHORT).show();
            check = -1;
        }else if (edTenTV.getText().length()>70){
            Toast.makeText(getContext(),"Quá 70 kí tự, mời nhập lại",Toast.LENGTH_SHORT).show();
        }
        return check;
    }
}