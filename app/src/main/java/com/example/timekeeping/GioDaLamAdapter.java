package com.example.timekeeping;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class GioDaLamAdapter extends BaseAdapter {

    private Context context;
    private int layout;
    private List<GioDaLam> gioDaLamList;

    public GioDaLamAdapter(Context context, int layout, List<GioDaLam> gioDaLamList) {
        this.context = context;
        this.layout = layout;
        this.gioDaLamList = gioDaLamList;
    }

    @Override
    public int getCount() {
        return gioDaLamList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    private class ViewHolder {
        TextView txttenca;
        TextView txtgio;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if(convertView == null){
            holder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(layout, null);
            holder.txttenca = (TextView) convertView.findViewById(R.id.textView_ten_ca);
            holder.txtgio = (TextView) convertView.findViewById(R.id.textView_gio_da_lam);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }

        GioDaLam gioDaLam = gioDaLamList.get(position);
        holder.txttenca.setText(gioDaLam.getTen());
        holder.txtgio.setText(gioDaLam.getGiodalam());


        return convertView;
    }

}
