package com.example.timekeeping;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class CongViecAdapter extends BaseAdapter {

    private HomeActivity context;
    private int layout;
    private List<CongViec> congViecList;

    public CongViecAdapter(HomeActivity context, int layout, List<CongViec> congViecList) {
        this.context = context;
        this.layout = layout;
        this.congViecList = congViecList;
    }

    @Override
    public int getCount() {
        return congViecList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }




    private class ViewHolder{
        TextView txtTen;
        TextView txtNgayBatDau;
        TextView txtTongLuong;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if(convertView == null){

            holder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater)  context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(layout, null);
            holder.txtTen = (TextView) convertView.findViewById(R.id.textView_ten);
            holder.txtTongLuong = (TextView) convertView.findViewById(R.id.textView_tong_luong);
            holder.txtNgayBatDau =(TextView) convertView.findViewById(R.id.textView_ngay);
            convertView.setTag(holder);
        }
        else{
            holder = (ViewHolder) convertView.getTag();
        }

        CongViec congViec = congViecList.get(position);
        holder.txtTen.setText(congViec.getTenCV());
        holder.txtNgayBatDau.setText(congViec.getNgayBatDau());
        holder.txtTongLuong.setText(String.valueOf(congViec.getTongLuong()));




        return convertView;
    }
}
