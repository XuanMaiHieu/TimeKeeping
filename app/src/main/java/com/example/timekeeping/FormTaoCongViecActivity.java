package com.example.timekeeping;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class FormTaoCongViecActivity extends AppCompatActivity {
    Database database;

    EditText edtTenCV;
    LinearLayout linearLayoutHinhThuc;
    RadioGroup radioGroupHinhThuc;
    RadioButton radioButtonNgay;
    RadioButton radioButtonGio;
    LinearLayout linearLayoutNgay;
    EditText edtLuongNgay;
    LinearLayout linearLayoutGio;
    EditText edtLuongGio;
    EditText edtDate;
    EditText edtTime;
    EditText edtGhiChu;
    Button btnXacNhan;

    private String LayGio;
    private String LayPhut;

    private int HinhThuc;

    private int HinhThucLuong;

    private String IdDangNhap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_tao_cong_viec);

        database = new Database(this);
        database.QueryData("CREATE TABLE IF NOT EXISTS tblCongViec (Id INTEGER PRIMARY KEY AUTOINCREMENT,TenCV VARCHAR(255),HinhThuc INT,IdNguoiDung INT, NgayBatDau VARCHAR(255),TienLuong INT,TongLuong INT,GioThongBao INT, PhutThongBao INT,GhiChu NVARCHAR(255),FOREIGN KEY (IdNguoiDung) REFERENCES tblNguoiDung(Id))");

        edtTenCV = (EditText) findViewById(R.id.editText_ten_cong_viec);
        linearLayoutHinhThuc = (LinearLayout) findViewById(R.id.linearLayout_hinh_thuc);
        radioGroupHinhThuc = (RadioGroup) findViewById(R.id.radioGroup_hinh_thuc);
        radioButtonNgay = (RadioButton) findViewById(R.id.radioButtonTheoNgay);
        radioButtonGio = (RadioButton) findViewById(R.id.radioButtonTheoGio);
        linearLayoutNgay = (LinearLayout) findViewById(R.id.linearLayout_ngay);
        linearLayoutGio = (LinearLayout) findViewById(R.id.linearLayout_gio);
        edtLuongGio = (EditText) findViewById(R.id.editText_luong_gio);
        edtLuongNgay = (EditText) findViewById(R.id.editText_luong_ngay);
        edtDate = (EditText) findViewById(R.id.editText_date);
        edtTime = (EditText) findViewById(R.id.editText_time);
        edtGhiChu = (EditText) findViewById(R.id.editText_ghi_chu);
        btnXacNhan = (Button) findViewById(R.id.button_xac_nhan);



        //bat su kien an hien cua radioGroup
        radioGroupHinhThuc.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                if(checkedId == R.id.radioButtonTheoNgay){
                    linearLayoutNgay.setVisibility(View.VISIBLE);
                    linearLayoutGio.setVisibility(View.GONE);
                    ChieuCaoMoiLinearLayout();
                    HinhThuc =1;
                } else if (checkedId == R.id.radioButtonTheoGio) {
                    linearLayoutNgay.setVisibility(View.GONE);
                    linearLayoutGio.setVisibility(View.VISIBLE);
                    ChieuCaoMoiLinearLayout();
                    HinhThuc =2;

                }
            }
        });

        //chon ngay
        edtDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ChonNgay(edtDate);
            }
        });

        //chon gio
        edtTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ChonGio(edtTime);
            }
        });
        // button xac nhan insert du lieu vao table
        btnXacNhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tenCV = edtTenCV.getText().toString();
                String ngayBatDau = edtDate.getText().toString();
                String GioNhacNho = edtTime.getText().toString();
                String GhiChu = edtGhiChu.getText().toString();
                layTienLuong();
                getId();

                boolean CheckTenCV = checkTenCongViec(tenCV);
                if(CheckTenCV == true){
                    Toast.makeText(FormTaoCongViecActivity.this, "Ten cong viec da ton tai", Toast.LENGTH_SHORT).show();
                }
                else{
                    database.QueryData("INSERT INTO tblCongViec VALUES (null, '"+tenCV+"','"+HinhThuc+"','"+IdDangNhap+"','"+ngayBatDau+"','"+HinhThucLuong+"','"+0+"','"+LayGio+"','"+LayPhut+"','"+GhiChu+"' ) ");
                }

                Intent intent = new Intent(FormTaoCongViecActivity.this, HomeActivity.class);
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                startActivity(intent);
            }
        });

    }

    //Ham chon ngay
    private void ChonNgay(EditText edt){
        Calendar calendar = Calendar.getInstance();
        int ngay = calendar.get(Calendar.DATE);
        int thang = calendar.get(Calendar.MONTH);
        int nam = calendar.get(Calendar.YEAR);
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                calendar.set(year,month,dayOfMonth);
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
                edt.setText(simpleDateFormat.format(calendar.getTime()));
            }
        },nam,thang,ngay);
        datePickerDialog.show();
    }


    //ham chon gio
    private void ChonGio(EditText edt){
        Calendar calendar = Calendar.getInstance();
        int gio = calendar.get(Calendar.HOUR_OF_DAY);
        int phut = calendar.get(Calendar.MINUTE);
        TimePickerDialog timePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm");
                calendar.set(0,0,0,hourOfDay,minute);
                edt.setText(simpleDateFormat.format(calendar.getTime()));
                LayGio = String.valueOf(hourOfDay);
                LayPhut = String.valueOf(minute);
            }
        },gio, phut, true);
        timePickerDialog.show();

    }


    //ham tang chieu cao cua linearLayout chinh
    private void ChieuCaoMoiLinearLayout (){
        int ChieuCaoMoi = 500;
        linearLayoutHinhThuc.getLayoutParams().height = ChieuCaoMoi;
        linearLayoutHinhThuc.requestLayout();
    }

    //ham lay tien luong

    private void layTienLuong (){
        if(HinhThuc == 1){
            String s = edtLuongNgay.getText().toString();
            HinhThucLuong = Integer.parseInt(s);
        } else if (HinhThuc == 2) {
            String s = edtLuongGio.getText().toString();
            HinhThucLuong = Integer.parseInt(s);

        }
    }

    //Ham lay id nguoi dung
    private void getId (){
        Cursor cursor = database.GetData("SELECT * FROM tblKiemTraDangNhap",null);
        while (cursor.moveToNext()){
            IdDangNhap = cursor.getString(1);
        }
    }
    //Ham check trung ten cong viec
    public Boolean checkTenCongViec (String tenCongViec){
        Cursor cursor = database.GetData("SELECT * FROM tblCongViec WHERE TenCV = ?",new String[]{tenCongViec});
        if(cursor.getCount() > 0 ){
            return true;
        }
        else{
            return false;
        }
    }

}