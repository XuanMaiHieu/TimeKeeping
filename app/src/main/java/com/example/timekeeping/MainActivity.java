package com.example.timekeeping;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.Toolbar;



public class MainActivity extends AppCompatActivity {

    Database database;
    Button btnSignup;
    EditText edtTenDangNhap;
    EditText edtMatKhau;
    Button btnLogin;

    private String Email;
    private String Password;
    public int LayIdNguoiDung;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        database = new Database(this);

        btnSignup= (Button) findViewById(R.id.btnSignup);
        edtTenDangNhap = (EditText) findViewById(R.id.editText);
        edtMatKhau = (EditText) findViewById(R.id.editText2);
        btnLogin = (Button) findViewById(R.id.button3);

        database.QueryData("CREATE TABLE IF NOT EXISTS tblKiemTraDangNhap(Id INTEGER PRIMARY KEY AUTOINCREMENT,IdNguoiDung INT ,TenDangNhap VARCHAR(200), MatKhau VARCHAR(200))");

        Cursor cursor = database.GetData("SELECT * FROM tblKiemTraDangNhap",null);
        if(cursor.getCount() == 0){
            btnLogin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Email = edtTenDangNhap.getText().toString();
                    Password = edtMatKhau.getText().toString();
                    if(Email == "" || Password == ""){
                        Toast.makeText(MainActivity.this, "Yêu cầu nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                    }
                    else if(Email != "" && Password != ""){
                        Boolean check = checkEmailPassword(Email, Password);
                        if (check == true) {
                            Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                            startActivity(intent);
                            finish();
                            database.QueryData("INSERT INTO tblKiemTraDangNhap VALUES( null, '" + LayIdNguoiDung + "'  ,'" + Email + "'   , '" + Password + "' )  ");
                        } else {
                            Toast.makeText(MainActivity.this, "Sai thông tin đăng nhập! Yêu cầu nhập lại", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            });
        }
        else{
            Intent intent = new Intent(MainActivity.this, HomeActivity.class);
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            startActivity(intent);
            finish();
        }
        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, DangKyActivity.class);
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                startActivity(intent);
            }
        });

    }

    public Boolean checkEmailPassword (String email, String pass){
        Cursor cursor = database.GetData("SELECT * FROM tblNguoiDung WHERE TenDangNhap = ? AND MatKhau = ?", new String[]{email,pass});
        if(cursor.getCount() > 0){
            while(cursor.moveToNext()){
                String id =cursor.getString(0);
                LayIdNguoiDung = Integer.parseInt(id);
            }
            return true;
        }
        else{
            return false;
        }
    }


}