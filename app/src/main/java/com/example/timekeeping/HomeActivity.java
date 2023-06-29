package com.example.timekeeping;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity {
    Database database;

    ImageView imgLogOut;
    ImageView imgTaoCongViec;

    

    ListView lvCongViec;
    ArrayList<CongViec> arrayCongViec;
    CongViecAdapter adapter;
    


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        database = new Database(this);
        
        imgLogOut = (ImageView) findViewById(R.id.image_dang_xuat);
        imgTaoCongViec = (ImageView) findViewById(R.id.image_tao_cong_viec);
        
        imgLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                database.QueryData("DELETE FROM tblKiemTraDangNhap ");
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                startActivity(intent);
                finish();
            }
        });

        imgTaoCongViec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, FormTaoCongViecActivity.class);
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                startActivity(intent);
            }
        });

        lvCongViec = (ListView) findViewById(R.id.list_cong_viec);
        arrayCongViec = new ArrayList<>();
        adapter = new CongViecAdapter(this, R.layout.dong_cong_viec, arrayCongViec);
        lvCongViec.setAdapter(adapter);
        getDataCongViec();

        lvCongViec.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                CongViec layIdCongViec = arrayCongViec.get(position);
                int chonIdCongViec = layIdCongViec.getIdCongViec();
                Intent intent = new Intent(HomeActivity.this, ChiTietCongViecActivity.class);
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                intent.putExtra("ID_CONG_VIEC",chonIdCongViec);
                startActivity(intent);

            }
        });


    }

    private void getDataCongViec(){
        Cursor cursor = database.GetData("SELECT * FROM tblCongViec",null);
        while(cursor.moveToNext()){
            String s = cursor.getString(0);
            int id = Integer.parseInt(s);
            String tenCV = cursor.getString(1);
            String ngayBD = cursor.getString(4);
            String Stongluong = cursor.getString(6);
            int tongLuong = Integer.parseInt(Stongluong);

            arrayCongViec.add(new CongViec(id,tenCV,0,0,ngayBD,0,tongLuong,0,0,""));
        }
        adapter.notifyDataSetChanged();
    }

//    private void LayTenNguoiDung (){
//        Cursor cursor = database.GetData("SELECT * FROM tblNguoiDung",null);
//        while(cursor.moveToNext()){
//
//        }
//    }
}