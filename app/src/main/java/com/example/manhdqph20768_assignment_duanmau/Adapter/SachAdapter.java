package com.example.manhdqph20768_assignment_duanmau.Adapter;

import android.app.Dialog;
import android.content.Context;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.manhdqph20768_assignment_duanmau.Dao.LoaiSachDAO;
import com.example.manhdqph20768_assignment_duanmau.Dao.SachDAO;
import com.example.manhdqph20768_assignment_duanmau.Dao.ThanhVienDAO;
import com.example.manhdqph20768_assignment_duanmau.Model.LoaiSach;
import com.example.manhdqph20768_assignment_duanmau.Model.PhieuMuon;
import com.example.manhdqph20768_assignment_duanmau.Model.Sach;
import com.example.manhdqph20768_assignment_duanmau.Model.ThanhVien;
import com.example.manhdqph20768_assignment_duanmau.R;

import java.util.ArrayList;
import java.util.List;

public class SachAdapter extends RecyclerView.Adapter<SachAdapter.ViewHolder> implements Filterable {
    private Context context;
    private List<Sach> list;
    private List<Sach> listOld;
    SachDAO dao;
    EditText edt_masach,edt_tensach,edt_giasach,edt_soluong;
    Spinner spn_loaisach;
    Button btn_save,btn_huy;
    LoaiSachDAO loaiSachDAO;
    LoaiSach loaiSach;
    SpinnerAdapter spinnerAdapter;
    List<LoaiSach> listLoaiSach;
    TextView tv_masach,tv_tensach,tv_giathue,tv_soluong,tv_loaisach;
    int maLoaiSach = 0;

    public SachAdapter(Context context) {
        this.context = context;
    }
    public void setData(List<Sach> list){
        this.list = list;
        this.listOld = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_listview_qlsach,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        dao = new SachDAO(context);
        loaiSachDAO = new LoaiSachDAO(context);

        Sach sach = list.get(position);
        loaiSach = loaiSachDAO.getID(String.valueOf(sach.getMaLoai()));

        holder.tv_masach.setText("Mã sách: "+sach.getMaSach());
        holder.tv_tensach.setText("Tên sách: "+sach.getTenSach());
        holder.tv_giathue.setText("Giá thuê: "+sach.getGiaThue());
        holder.tv_soluong.setText("Số lượng: "+sach.getSoLuong());
        holder.tv_loaisach.setText("Loại sách: "+loaiSach.getTenLoai());

        holder.img_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Sach sach1 = list.get(holder.getAdapterPosition());
                long check = dao.delete(String.valueOf(sach1.getMaSach()));
                if (check>0){
                    Toast.makeText(context, "Delete thành công", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(context, "Delete thất bại", Toast.LENGTH_SHORT).show();
                }
                list.remove(holder.getAdapterPosition());
                notifyDataSetChanged();
            }
        });
        holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog dialog = new Dialog(context);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.dialog_sachchitiet);
                dialog.show();
                Window window = dialog.getWindow();
                if (window == null){
                    return;
                }
                window.setBackgroundDrawable(null);
                tv_masach = dialog.findViewById(R.id.tv_masach_dialogchitiet);
                tv_tensach = dialog.findViewById(R.id.tv_masach_dialogchitiet);
                tv_giathue = dialog.findViewById(R.id.tv_masach_dialogchitiet);
                tv_soluong = dialog.findViewById(R.id.tv_masach_dialogchitiet);
                tv_loaisach = dialog.findViewById(R.id.tv_masach_dialogchitiet);

                tv_masach.setText("Mã sách: "+sach.getMaSach());
                tv_tensach.setText("Tên sách: "+sach.getTenSach());
                tv_giathue.setText("Giá thuê: "+sach.getGiaThue());
                tv_soluong.setText("Số lượng: "+sach.getSoLuong());
                tv_loaisach.setText("Loại sách: "+loaiSach.getTenLoai());
            }
        });
        holder.relativeLayout.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Sach item = list.get(holder.getAdapterPosition());
                int position = 0;

                final Dialog dialog = new Dialog(context);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.addsach_dialog);
                dialog.show();
                Window window =dialog.getWindow();
                if (window==null){
                    return false;
                }
                window.setGravity(Gravity.CENTER);
                window.setBackgroundDrawable(null);

                edt_masach = dialog.findViewById(R.id.edt_masach_dialogsach);
                edt_tensach = dialog.findViewById(R.id.edt_tensach_dialogsach);
                edt_giasach = dialog.findViewById(R.id.edt_giathuesach_dialogsach);
                edt_soluong =dialog.findViewById(R.id.edt_soluong_dialogsach);
                spn_loaisach = dialog.findViewById(R.id.spinner_dialogsach);
                listLoaiSach = new ArrayList<>();
                listLoaiSach = loaiSachDAO.getAll();
                spinnerAdapter = new SpinnerAdapter(context,R.layout.item_spinner_loaisach,listLoaiSach);
                spn_loaisach.setAdapter(spinnerAdapter);

                for (int i =0;i<listLoaiSach.size();i++){
                    if (item.getMaLoai()== listLoaiSach.get(i).getMaLoai()){
                        position = i;
                    }
                }
                spn_loaisach.setSelection(position);

                btn_save = dialog.findViewById(R.id.btn_luu_dialogsach);
                btn_huy = dialog.findViewById(R.id.btn_huy_dialogsach);

                edt_masach.setText(String.valueOf(item.getMaSach()));
                edt_tensach.setText(item.getTenSach());
                edt_giasach.setText(item.getGiaThue()+"");
                edt_soluong.setText(item.getSoLuong()+"");



                spn_loaisach.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        maLoaiSach = listLoaiSach.get(position).getMaLoai();
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });

                btn_huy.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
                btn_save.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (validate()>0){
                            item.setTenSach(edt_tensach.getText().toString());
                            item.setGiaThue(Integer.parseInt(edt_giasach.getText().toString()));
                            item.setMaLoai(maLoaiSach);
                            item.setSoLuong(Integer.parseInt(edt_soluong.getText().toString()));
                            long check = dao.update(item);
                            if (check>0){
                                Toast.makeText(context, "Update thành công", Toast.LENGTH_SHORT).show();
                                dialog.dismiss();
                            }else {
                                Toast.makeText(context, "Update thất bại", Toast.LENGTH_SHORT).show();
                            }
                            notifyDataSetChanged();
                        }
                    }
                });
                return true;
            }
        });
    }

    @Override
    public int getItemCount() {
        if (list != null){
            return list.size();
        }
        return 0;
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                String strSearch = constraint.toString();
                if (strSearch.isEmpty()){
                    list = listOld;
                }else {
                    List<Sach> listnew = new ArrayList<>();
                    for (Sach sach : listOld){
                        if (sach.getSoLuong() < Integer.parseInt(strSearch)){
                            listnew.add(sach);
                        }
                    }
                    list = listnew;
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = list;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                list = (List<Sach>) results.values;
            }
        };
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tv_masach,tv_tensach,tv_giathue,tv_loaisach,tv_soluong;
        private ImageView img_delete;
        private RelativeLayout relativeLayout;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_masach = itemView.findViewById(R.id.tv_masach_itemsach);
            tv_tensach = itemView.findViewById(R.id.tv_tensach_itemsach);
            tv_giathue = itemView.findViewById(R.id.tv_giathue_itemsach);
            tv_loaisach = itemView.findViewById(R.id.tv_loaisach_itemsach);
            img_delete = itemView.findViewById(R.id.img_delete_itemsach);
            relativeLayout = itemView.findViewById(R.id.layout_update_itemsach);
            tv_soluong = itemView.findViewById(R.id.tv_soluong_itemsach);
        }
    }
    public int validate(){
        int check = 1;
        if (edt_tensach.getText().length()==0|| edt_giasach.getText().length()==0){
            Toast.makeText(context,"Bạn phải nhập đầy đủ thông tin",Toast.LENGTH_SHORT).show();
            check = -1;
        }
        return check;
    }

}
