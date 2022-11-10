package com.example.manhdqph20768_assignment_duanmau.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.manhdqph20768_assignment_duanmau.Model.LoaiSach;
import com.example.manhdqph20768_assignment_duanmau.R;

import java.util.List;

public class SpinnerAdapter extends ArrayAdapter<LoaiSach> {
    private Context context;
    private int resource;
    private List<LoaiSach> list;
    private LayoutInflater layoutInflater;

    public SpinnerAdapter(@NonNull Context context, int resource, @NonNull List objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        this.list = objects;
        this.layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView ==null){
            viewHolder = new ViewHolder();
            convertView = layoutInflater.inflate(resource,null);
            viewHolder.tv_maloai = convertView.findViewById(R.id.tv_masach_spinner);
            viewHolder.tv_loaisach = convertView.findViewById(R.id.tv_tensach_spinner);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        LoaiSach loaiSach = list.get(position);
        viewHolder.tv_maloai.setText(String.valueOf(loaiSach.getMaLoai()));
        viewHolder.tv_loaisach.setText(loaiSach.getTenLoai());
        return convertView;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView ==null){
            viewHolder = new ViewHolder();
            convertView = layoutInflater.inflate(resource,null);
            viewHolder.tv_maloai = convertView.findViewById(R.id.tv_masach_spinner);
            viewHolder.tv_loaisach = convertView.findViewById(R.id.tv_tensach_spinner);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        LoaiSach loaiSach = list.get(position);
        viewHolder.tv_maloai.setText(String.valueOf(loaiSach.getMaLoai()));
        viewHolder.tv_loaisach.setText(loaiSach.getTenLoai());
        return convertView;
    }
    public class ViewHolder{
        private TextView tv_maloai,tv_loaisach;
    }
}
