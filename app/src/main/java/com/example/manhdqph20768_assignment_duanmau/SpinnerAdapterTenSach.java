package com.example.manhdqph20768_assignment_duanmau;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.manhdqph20768_assignment_duanmau.Model.Sach;
import com.example.manhdqph20768_assignment_duanmau.Model.ThanhVien;

import java.util.List;

public class SpinnerAdapterTenSach extends ArrayAdapter<Sach> {
    private Context context;
    private int resource;
    private List<Sach> list;
    private LayoutInflater layoutInflater;

    public SpinnerAdapterTenSach(@NonNull Context context, int resource, @NonNull List objects) {
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
            viewHolder.tv_masach = convertView.findViewById(R.id.tv_masach_spinner);
            viewHolder.tv_tensach = convertView.findViewById(R.id.tv_tensach_spinner);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        Sach sach = list.get(position);
        viewHolder.tv_masach.setText(sach.getMaSach()+"");
        viewHolder.tv_tensach.setText(sach.getTenSach());
        return convertView;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView ==null){
            viewHolder = new ViewHolder();
            convertView = layoutInflater.inflate(resource,null);
            viewHolder.tv_masach = convertView.findViewById(R.id.tv_masach_spinner);
            viewHolder.tv_tensach = convertView.findViewById(R.id.tv_tensach_spinner);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        Sach sach = list.get(position);
        viewHolder.tv_masach.setText(sach.getMaSach()+"");
        viewHolder.tv_tensach.setText(sach.getTenSach());
        return convertView;
    }
    public class ViewHolder{
        private TextView tv_masach,tv_tensach;
    }
}
