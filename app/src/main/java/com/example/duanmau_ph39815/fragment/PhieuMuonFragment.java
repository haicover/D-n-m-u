package com.example.duanmau_ph39815.fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.duanmau_ph39815.Adapter.PhieuMuonAdapter;
import com.example.duanmau_ph39815.Adapter.SachSpinerAdapter;
import com.example.duanmau_ph39815.Adapter.ThanhVienSpinerAdapter;
import com.example.duanmau_ph39815.Dao.PhieuMuonDAO;
import com.example.duanmau_ph39815.Dao.SachDAO;
import com.example.duanmau_ph39815.Dao.ThanhVienDAO;
import com.example.duanmau_ph39815.R;
import com.example.duanmau_ph39815.model.PhieuMuon;
import com.example.duanmau_ph39815.model.Sach;
import com.example.duanmau_ph39815.model.ThanhVien;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class PhieuMuonFragment extends Fragment {

    ListView lvPhieuMuon;
    ArrayList<PhieuMuon> list;
    FloatingActionButton fab;
    Dialog dialog;
    EditText edMaPM;
    Spinner spTV, spSach;
    TextView tvNgay, tvTienThue,tvTimePM;
    CheckBox chkTraSach;
    Button btnSave, btnCancel;
    static PhieuMuonDAO dao;
    PhieuMuonAdapter adapter;
    PhieuMuon item;
    ThanhVienSpinerAdapter thanhVienSpinerAdapter;
    ArrayList<ThanhVien> listThanhVien;
    ThanhVienDAO thanhVienDAO;
    ThanhVien thanhVien;
    int maThanhVien;
    SachSpinerAdapter sachSpinerAdapter;
    ArrayList<Sach> listSach;
    SachDAO sachDAO;
    Sach sach;
    int maSach, tienThue;
    int positionTV, positionSach;
    PhieuMuonDAO phieuMuonDAO;
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");

    EditText edSearch;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_phieu_muon, container, false);
        lvPhieuMuon = v.findViewById(R.id.lvPhieuMuon);
        fab = v.findViewById(R.id.fabPm);
        list = (ArrayList<PhieuMuon>) phieuMuonDAO.getAll();
        dao = new PhieuMuonDAO(getActivity());
        capNhatLV();
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog(getActivity(), 0);
            }
        });
        lvPhieuMuon.setOnItemLongClickListener((adapterView, view, i, l) -> {
            item = list.get(i);
            openDialog(getActivity(), 1);
            return false;
        });
        edSearch = v.findViewById(R.id.edSearch);
        edSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                list.clear();
                for (PhieuMuon pm:list){
                    if (String.valueOf(pm.getMaTT()).contains(charSequence.toString())){
                        list.add(pm);
                    }
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        return v;
    }

    protected void openDialog(final Context context, final int type) {
        dialog = new Dialog(context);
        dialog.setContentView(R.layout.phieu_muon_dialog);
        edMaPM = dialog.findViewById(R.id.edMaPM);
        spTV = dialog.findViewById(R.id.spMaTv);
        spSach = dialog.findViewById(R.id.spMaS);
        tvNgay = dialog.findViewById(R.id.tvNgay);
        tvTimePM = dialog.findViewById(R.id.tvTimePM);
        tvTienThue = dialog.findViewById(R.id.tvTienThuePM);
        chkTraSach = dialog.findViewById(R.id.chkTraSach);
        btnCancel = dialog.findViewById(R.id.btnCancelPM);
        btnSave = dialog.findViewById(R.id.btnSavePM);
        thanhVienDAO = new ThanhVienDAO(context);
        listThanhVien = new ArrayList<ThanhVien>();
        listThanhVien = (ArrayList<ThanhVien>) thanhVienDAO.getAll();
        thanhVienSpinerAdapter = new ThanhVienSpinerAdapter(context, listThanhVien);
        spTV.setAdapter(thanhVienSpinerAdapter);
        //lấy mã loại sách
        spTV.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // TODO Auto-generated method stub
                maThanhVien = listThanhVien.get(position).maTV;
                Toast.makeText(context, "Chọn " + listThanhVien.get(position).hoTenTV, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub
            }
        });
        sachDAO = new SachDAO(context);
        listSach = new ArrayList<>();
        listSach = (ArrayList<Sach>) sachDAO.getAll();
        sachSpinerAdapter = new SachSpinerAdapter(context, listSach);
        spSach.setAdapter(sachSpinerAdapter);
        //lấy mã loại sách
        spSach.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // TODO Auto-generated method stub
                maSach = listSach.get(position).maSach;
                tienThue = listSach.get(position).giaThue;
                Toast.makeText(context, "Chọn " + listSach.get(position).tenSach, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub
            }
        });
        edMaPM.setEnabled(false);
        //edit set data len form
        if (type != 0) {
            edMaPM.setText(String.valueOf(item.getMaPM()));
            for (int i = 0; i < listThanhVien.size(); i++) {
                if (item.getMaTV() == (listThanhVien.get(i).maTV)) {
                    positionTV = i;
                }
                spTV.setSelection(positionTV);
            }

            for (int i = 0; i < listSach.size(); i++) {
                if (item.getMaSach() == (listSach.get(i).maSach)) {
                    positionTV = i;
                }
                spSach.setSelection(positionSach);
                tvNgay.setText("Ngày thuê: " + sdf.format(item.getNgayThue()));
                Sach sach = sachDAO.getID(String.valueOf(item.getMaSach()));
                tvTienThue.setText("Tiền thuê: " + item.getTienThue());
            }

            if (item.getTraSach() == 1) {
                chkTraSach.setChecked(true);
            } else {
                chkTraSach.setChecked(false);
            }

        }
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                item = new PhieuMuon();
                item.setMaSach(maSach);
                item.setMaTV(maThanhVien);
                item.setNgayThue(new Date());
                item.setTienThue(tienThue);
                Calendar calendar = Calendar.getInstance();
                int hour = calendar.get(Calendar.HOUR_OF_DAY);
                int minute = calendar.get(Calendar.MINUTE);
                item.setGio(hour+":"+minute);
                if (chkTraSach.isChecked()) {
                    item.setTraSach(1);
                } else {
                    item.setTraSach(0);
                }

                if (validate() > 0) {
                    if (type == 0) {
                        if (dao.insert(item) > 0) {
                            Toast.makeText(context, "Thêm thành công", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(context, "Thêm thất bại", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        item.setMaPM(Integer.parseInt(edMaPM.getText().toString()));
                        if (dao.update(item) > 0) {
                            Toast.makeText(context, "Sửa thành công", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(context, "Sửa thất bại", Toast.LENGTH_SHORT).show();
                        }
                    }
                    capNhatLV();
                    dialog.dismiss();
                }
            }
        });
        dialog.show();
    }

    public void xoa(final String Id) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Delete");
        builder.setMessage("Bạn có muốn xóa không");
        builder.setCancelable(true);
        builder.setPositiveButton(
                "Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dao.delete(Id);
                        capNhatLV();
                        dialog.cancel();
                    }
                }
        );
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        AlertDialog alertDialog = builder.create();
        builder.show();

    }

    void capNhatLV() {
        list = (ArrayList<PhieuMuon>) dao.getAll();
        adapter = new PhieuMuonAdapter(getActivity(), this, list);
        lvPhieuMuon.setAdapter(adapter);
    }

    public int validate() {
        int check = 1;
        return check;
    }
}