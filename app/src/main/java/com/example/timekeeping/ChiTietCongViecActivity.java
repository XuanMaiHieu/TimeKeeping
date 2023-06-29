package com.example.timekeeping;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.CalendarView;
import android.widget.TextView;
import android.widget.Toast;

public class ChiTietCongViecActivity extends AppCompatActivity {

    Database database;
    TextView txtChiTietTen;
    TextView txtChiTietLuong;
    CalendarView calendarViewChamCong;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet_cong_viec);

        database = new Database(this);
        database.QueryData("CREATE TABLE IF NOT EXISTS tblChamCong (Id INTEGER PRIMARY KEY AUTOINCREMENT, NgayChamCong VARCHAR(20), TinhTrang INT, LuongTrongNgay INT, IdCongViec INT, FOREIGN KEY (IdCongViec) REFERENCES tblCongViec(Id) )");

        txtChiTietTen = (TextView) findViewById(R.id.textView_chi_tiet_ten);
        txtChiTietLuong = (TextView) findViewById(R.id.textView_chi_tiet_luong);
        calendarViewChamCong = (CalendarView) findViewById(R.id.calendarView);

        //lay ra ten va tong so luong trong bang congviec
        int idCongViec = getIntent().getIntExtra("ID_CONG_VIEC", -1);
        String[] selectionArgs = {String.valueOf(idCongViec)};
        Cursor cursor = database.GetData("SELECT * FROM tblCongViec WHERE Id = ?", selectionArgs);
        while (cursor.moveToNext()) {
            txtChiTietTen.setText(cursor.getString(1));
            txtChiTietLuong.setText(cursor.getString(6));
        }

        calendarViewChamCong.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                String selectedDate = dayOfMonth + "/" + (month + 1) + "/" + year;
                Intent intent = new Intent(ChiTietCongViecActivity.this, ChamCongActivity.class);
                intent.putExtra("ID_CONG_VIEC",idCongViec);
                intent.putExtra("NGAY_DUOC_CHON",selectedDate);
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                startActivity(intent);
            }
        });

    }
}