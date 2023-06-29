package com.example.timekeeping;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class ThemGioLamActivity extends AppCompatActivity {
    Database database;

    ListView lvGioLam;
    Button btnChamCong2;
    Button btnThemCa;

    private int LayGioVaoCa;
    private int LayPhutVaoCa;
    private int LayGioHetCa;
    private int LayPhutHetCa;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_them_gio_lam);

        database = new Database(this);
        btnThemCa = (Button) findViewById(R.id.button_them_gio);
        btnThemCa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogThem();
            }
        });
    }

    private void DialogThem(){
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog_them_gio);


        TextView txtGioVaoCa = (TextView) dialog.findViewById(R.id.textView_gio_vao_ca);
        TextView txtGioHetCa = (TextView) dialog.findViewById(R.id.textView_gio_het_ca);
        TextView txtTongThoiGian = (TextView) dialog.findViewById(R.id.textView_tong_thoi_gian);
        Button btnThemCaVaoListView = (Button) dialog.findViewById(R.id.button_them_vao_listView);

        txtGioVaoCa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                int gio = calendar.get(Calendar.HOUR_OF_DAY);
                int phut = calendar.get(Calendar.MINUTE);
                TimePickerDialog timePickerDialog = new TimePickerDialog(ThemGioLamActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm");
                        calendar.set(0,0,0,hourOfDay,minute);
                        txtGioVaoCa.setText(simpleDateFormat.format(calendar.getTime()));
                        LayGioVaoCa = hourOfDay;
                        LayPhutVaoCa = minute;
                    }
                },gio, phut, true);
                timePickerDialog.show();
            }
        });

        txtGioHetCa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                int gio = calendar.get(Calendar.HOUR_OF_DAY);
                int phut = calendar.get(Calendar.MINUTE);
                TimePickerDialog timePickerDialog = new TimePickerDialog(ThemGioLamActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm");
                        calendar.set(0,0,0,hourOfDay,minute);
                        txtGioHetCa.setText(simpleDateFormat.format(calendar.getTime()));
                        LayGioHetCa = hourOfDay;
                        LayPhutHetCa = minute;
                    }
                },gio, phut, true);
                timePickerDialog.show();
            }
        });

        dialog.show();

//
        btnThemCaVaoListView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double tongthoigian = TinhGio();
                Toast.makeText(ThemGioLamActivity.this, String.valueOf(tongthoigian), Toast.LENGTH_SHORT).show();
//
//               database.QueryData("CREATE TABLE IF NOT EXISTS tblThemGio(Id INTEGER PRIMARY KEY AUTOINCREMENT, TenCa VARCHAR(20),DOUBLE )");
            }
        });

        dialog.show();


    }// ham tinh tong thoi gian
    private Double TinhGio(){
        int gio = LayGioHetCa - LayGioVaoCa;
        int phut = LayPhutHetCa - LayPhutVaoCa;

        if (phut < 0) { // Nếu phút bắt đầu lớn hơn phút kết thúc
            gio--; // Trừ đi 1 giờ và chuyển phút về dương
            phut += 60;
        }

        double thoiGianCuoi = gio + (phut / 60.0);
        thoiGianCuoi = Math.round(thoiGianCuoi *10.0)/10.0;

        return thoiGianCuoi;
    }
}


