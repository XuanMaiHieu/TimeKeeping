package com.example.timekeeping;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class DangKyActivity extends AppCompatActivity {
    Database database;
    Button btnLogin;
    Button btnSignup;
    EditText edtConfirm;
    EditText edtPass;
    EditText edtEmail;
    EditText edtTenNguoiDung;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_ky);

        database = new Database(this);

        database.QueryData("CREATE TABLE IF NOT EXISTS tblNguoiDung(Id INTEGER PRIMARY KEY AUTOINCREMENT, TenNguoiDung VARCHAR(200) ,TenDangNhap VARCHAR(200), MatKhau VARCHAR(200))");

        edtTenNguoiDung =(EditText) findViewById(R.id.editTextTenNguoiDung);
        edtEmail = (EditText) findViewById(R.id.editText);
        edtPass = (EditText) findViewById(R.id.editText2);
        edtConfirm = (EditText) findViewById(R.id.editText3);
        btnSignup =(Button) findViewById(R.id.button3);

        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = edtTenNguoiDung.getText().toString();
                String email = edtEmail.getText().toString().toLowerCase();
                String password = edtPass.getText().toString();
                String confirm = edtConfirm.getText().toString();

                if(email.equals("") || password.equals("") || confirm.equals("")){
                    Toast.makeText(DangKyActivity.this, "Bắt buộc điền đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                }
                else{
                    if(password.equals(confirm)){
                        boolean checkEmailUser = checkEmail(email);
                        if(checkEmailUser == false){
                            database.QueryData("INSERT INTO tblNguoiDung VALUES ( null, '"+name+"'  ,'"+email+"'   , '"+password+"' )  ");
                            Toast.makeText(DangKyActivity.this, "Da tao thanh cong User", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(DangKyActivity.this, MainActivity.class);
                            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                            startActivity(intent);
                        }
                        else {
                            Toast.makeText(DangKyActivity.this, "Tai khoan da ton tai, vui long dang nhap", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else{
                        Toast.makeText(DangKyActivity.this, "Nhap lai confirm", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        btnLogin = (Button) findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DangKyActivity.this, MainActivity.class);
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                startActivity(intent);
            }
        });
    }
    public Boolean checkEmail (String email){
        Cursor cursor = database.GetData("SELECT * FROM tblNguoiDung WHERE TenDangNhap = ?",new String[]{email});
        if(cursor.getCount() > 0 ){
            return true;
        }
        else{
            return false;
        }
    }
}