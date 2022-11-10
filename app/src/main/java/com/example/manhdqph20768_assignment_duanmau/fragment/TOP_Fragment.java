package com.example.manhdqph20768_assignment_duanmau.fragment;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.manhdqph20768_assignment_duanmau.Adapter.TopAdapter;
import com.example.manhdqph20768_assignment_duanmau.Dao.PhieuMuonDAO;
import com.example.manhdqph20768_assignment_duanmau.Model.Top;
import com.example.manhdqph20768_assignment_duanmau.R;

import java.util.ArrayList;
import java.util.List;

public class TOP_Fragment extends Fragment {
    ListView listView;
    TopAdapter adapter;
    List<Top> list ;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view =inflater.inflate(R.layout.fragment_t_o_p_, container, false);
        listView = view.findViewById(R.id.lv_top);
        PhieuMuonDAO dao = new PhieuMuonDAO(getContext());
        list = dao.getTop();
        adapter = new TopAdapter(getContext(),R.layout.item_listview_top,list);
        listView.setAdapter(adapter);
        return view;
    }
}