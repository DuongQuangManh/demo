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
import com.example.manhdqph20768_assignment_duanmau.Model.ThanhVien;
import com.example.manhdqph20768_assignment_duanmau.R;

import java.util.List;

public class SpinnerAdapterThanhVien extends ArrayAdapter<ThanhVien> {
    private Context context;
    private int resource;
    private List<ThanhVien> list;
    private LayoutInflater layoutInflater;

    public SpinnerAdapterThanhVien(@NonNull Context context, int resource, @NonNull List objects) {
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
            viewHolder.tv_matv = convertView.findViewById(R.id.tv_masach_spinner);
            viewHolder.tv_tentv = convertView.findViewById(R.id.tv_tensach_spinner);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        ThanhVien tv = list.get(position);
        viewHolder.tv_matv.setText(tv.getMaTV()+"");
        viewHolder.tv_tentv.setText(tv.getHoTen());
        return convertView;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView ==null){
            viewHolder = new ViewHolder();
            convertView = layoutInflater.inflate(resource,null);
            viewHolder.tv_matv = convertView.findViewById(R.id.tv_masach_spinner);
            viewHolder.tv_tentv = convertView.findViewById(R.id.tv_tensach_spinner);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        ThanhVien tv = list.get(position);
        viewHolder.tv_matv.setText(tv.getMaTV()+"");
        viewHolder.tv_tentv.setText(tv.getHoTen());
        return convertView;
    }
    public class ViewHolder{
        private TextView tv_matv,tv_tentv;
    }
}
