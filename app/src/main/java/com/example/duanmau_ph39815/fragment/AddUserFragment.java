package com.example.duanmau_ph39815.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.duanmau_ph39815.Dao.ThuThuDAO;
import com.example.duanmau_ph39815.R;
import com.example.duanmau_ph39815.model.ThuThu;
import com.google.android.material.textfield.TextInputEditText;


public class AddUserFragment extends Fragment {

    TextInputEditText edUser, edHoTen, edPass, edRePass;
    Button btnSave, btnCancel;
    ThuThuDAO dao;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View v =  inflater.inflate(R.layout.fragment_add_user, container, false);

        edUser = v.findViewById(R.id.edUser);
        edHoTen = v.findViewById(R.id.edHoTen);
        edPass = v.findViewById(R.id.edPass);
        edRePass = v.findViewById(R.id.edRePass);
        btnSave = v.findViewById(R.id.btnSaveUser);
        btnCancel =v.findViewById(R.id.btnCancelUser);

        dao = new ThuThuDAO(getActivity());
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                edUser.setText("");
                edHoTen.setText("");
                edPass.setText("");
                edRePass.setText("");
            }
        });
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ThuThu thuThu = new ThuThu();
                thuThu.setMaTT(edUser.getText().toString());
                thuThu.setHoTenTT(edHoTen.getText().toString());
                thuThu.setMatKhau(edPass.getText().toString());
                if (validate()>0){
                    Toast.makeText(getActivity(), "Lưu thành công", Toast.LENGTH_SHORT).show();
                    edUser.setText("");
                    edHoTen.setText("");
                    edPass.setText("");
                    edRePass.setText("");
                }
                else {
                    Toast.makeText(getActivity(), "Lưu thất bại", Toast.LENGTH_SHORT).show();
                }
            }
        });
        return v;
    }
    public int validate(){
        String regex = "^[A-Z].*";
        int check = 1;
        if (edUser.getText().length()== 0 || edHoTen.getText().length() ==0 || edPass.getText().length()==0 || edRePass.getText().length()==0){
            Toast.makeText(getContext(), "Bạn phải nhập đủ thông tin", Toast.LENGTH_SHORT).show();
            check = -1;
        }else if (edUser.getText().toString().length()<5){
            Toast.makeText(getContext(), "Độ dài ngắn hơn 5, mời bạn nhập lại", Toast.LENGTH_SHORT).show();
            check = -1;
        }else if (edUser.getText().toString().length()>15){
            Toast.makeText(getContext(), "Quá 15 kí tự, mời bạn nhập lại", Toast.LENGTH_SHORT).show();
            check = -1;
        }else {
            String pass = edPass.getText().toString();
            String rePass = edRePass.getText().toString();

            if (!pass.equals(rePass)){
                Toast.makeText(getContext(),"Mật khẩu không trùng khớp", Toast.LENGTH_SHORT).show();
                check = -1;
            }
        }

        if(edUser.getText().toString().matches(regex)== false){
            Toast.makeText(getContext(), "Chữ cái đầu phải viết hoa", Toast.LENGTH_SHORT).show();
            check = -1;
        }


        return check;
    }
}