package com.example.manhdqph20768_assignment_duanmau.Adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.manhdqph20768_assignment_duanmau.Create_Fragment;
import com.example.manhdqph20768_assignment_duanmau.Login_Fragment;


public class ViewPagerAdapterLogin extends FragmentStateAdapter {


    public ViewPagerAdapterLogin(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 1:
                return new Create_Fragment();
            default: return new Login_Fragment();
        }
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
