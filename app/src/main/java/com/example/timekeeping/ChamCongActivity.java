package com.example.timekeeping;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class ChamCongActivity extends AppCompatActivity {
    Database database;

    TextView txtTenCongViecChamCong;
    TextView txtLoaiChamCong;
    TextView txtNgayCham;
    TextView txtLuong;
    TextView textChamTheoNgay;
    TextView textChamTheoGio;
    Button btnXacNhanChamCong;
    Button btnNghiPhep;

    private int HinhThucChamCong;
    private int LuongTrongNgay;
    private  int TongLuong;
    private int IdNguoiDung;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cham_cong);



        database = new Database(this);
        txtTenCongViecChamCong = (TextView) findViewById(R.id.textView_cham_cong_ten);
        txtLoaiChamCong = (TextView) findViewById(R.id.textView_loai_cham_cong);
        txtNgayCham = (TextView) findViewById(R.id.textView_ngay_cham_cong);
        txtLuong = (TextView) findViewById(R.id.textView_luong);
        textChamTheoGio =(TextView) findViewById(R.id.text_chamtheogio);
        textChamTheoNgay = (TextView) findViewById(R.id.text_chamtheongay);
        btnXacNhanChamCong = (Button) findViewById(R.id.button_cham_cong);
        btnNghiPhep = (Button) findViewById(R.id.button_nghi);

        int idCongViec = getIntent().getIntExtra("ID_CONG_VIEC", -1);
        String NgayDuocChon = getIntent().getStringExtra("NGAY_DUOC_CHON");
        Toast.makeText(this, String.valueOf(idCongViec), Toast.LENGTH_SHORT).show();
        txtNgayCham.setText(NgayDuocChon);

        String[] selectionArgs = {String.valueOf(idCongViec)};
        Cursor cursor = database.GetData("SELECT * FROM tblCongViec WHERE Id = ?", selectionArgs);
        while (cursor.moveToNext()) {
            txtTenCongViecChamCong.setText(cursor.getString(1));
            if(Integer.parseInt(cursor.getString(2)) == 1){
                HinhThucChamCong = Integer.parseInt(cursor.getString(2));
                txtLoaiChamCong.setText("Chấm công theo ngày");
                textChamTheoNgay.setVisibility(View.VISIBLE);
                textChamTheoGio.setVisibility(View.GONE);
            } else if (Integer.parseInt(cursor.getString(2)) == 2) {
                HinhThucChamCong = Integer.parseInt(cursor.getString(2));
                txtLoaiChamCong.setText("Chấm công theo giờ");
                textChamTheoGio.setVisibility(View.VISIBLE);
                textChamTheoNgay.setVisibility(View.GONE);
            }
            txtLuong.setText(cursor.getString(5));
            LuongTrongNgay = Integer.parseInt(cursor.getString(5));
            TongLuong = Integer.parseInt(cursor.getString(6));


        }


        btnXacNhanChamCong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(HinhThucChamCong == 1){
//                    database.QueryData("CREATE TABLE IF NOT EXISTS tblThemGio(Id INTEGER PRIMARY KEY AUTOINCREMENT, TenCa VARCHAR(20),TongThoiGian DOUBLE)");
                    int LuongMoi = (TongLuong + LuongTrongNgay);
                    database.QueryData("INSERT INTO tblChamCong VALUES (null, '"+NgayDuocChon+"', '"+1+"','"+LuongTrongNgay+"','"+idCongViec+"')");
                    database.QueryData("UPDATE tblCongViec SET TongLuong = '"+ LuongMoi +"' WHERE Id = '"+idCongViec+"'");
                    Intent intent = new Intent(ChamCongActivity.this, ChiTietCongViecActivity.class);
                    overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                    startActivity(intent);
                } else if (HinhThucChamCong == 2) {
                    Intent intent = new Intent(ChamCongActivity.this, ThemGioLamActivity.class);
                    overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                    startActivity(intent);
                }
            }
        });


    }
}