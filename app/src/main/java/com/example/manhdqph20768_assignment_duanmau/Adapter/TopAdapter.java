package com.example.manhdqph20768_assignment_duanmau.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.manhdqph20768_assignment_duanmau.Model.Top;
import com.example.manhdqph20768_assignment_duanmau.R;

import java.util.List;

public class TopAdapter extends ArrayAdapter<Top> {
    private Context context;
    private int resource;
    private List<Top> objects;
    private LayoutInflater inflater;

    public TopAdapter(@NonNull Context context, int resource, @NonNull List<Top> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        this.objects = objects;
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView==null){
            viewHolder = new ViewHolder();
            convertView = inflater.inflate(R.layout.item_listview_top,null);
            viewHolder.tv_tenSach = convertView.findViewById(R.id.tvSach);
            viewHolder.tv_soLuong = convertView.findViewById(R.id.tvsl);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        Top top = objects.get(position);
        viewHolder.tv_tenSach.setText("Sách: "+top.getTenSach());
        viewHolder.tv_soLuong.setText("Số lượng: "+top.getSoLuong());
        return convertView;
    }
    public class ViewHolder{
        private TextView tv_tenSach,tv_soLuong;
    }
}
