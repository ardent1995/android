package com.example.tohosif.layout;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tohosif.recyclerview.R;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class Tab1 extends Fragment {
    private RecyclerView recyclerView;
    private MyAdapter adapter;

    public Tab1() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View layout=inflater.inflate(R.layout.fragment_tab1, container, false);
        recyclerView = (RecyclerView) layout.findViewById(R.id.recycler_view);
        adapter = new MyAdapter(getActivity(), getData());
        recyclerView.setAdapter(adapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
//        DividerItemDecoration dividerItemDecoration=new DividerItemDecoration(this,layoutManager.getOrientation());
//        recyclerView.addItemDecoration(dividerItemDecoration);

        return layout;
    }
    public static List<Information> getData() {
        List<Information> data = new ArrayList<>();
        int[] icons = {R.drawable.img1, R.drawable.img2, R.drawable.img3, R.drawable.img4, R.drawable.img5, R.drawable.img6, R.drawable.img7, R.drawable.img8, R.drawable.img9, R.drawable.img10,
                R.drawable.img11, R.drawable.img12, R.drawable.img13, R.drawable.img1, R.drawable.img2, R.drawable.img3, R.drawable.img4, R.drawable.img5, R.drawable.img6, R.drawable.img7, R.drawable.img8, R.drawable.img9, R.drawable.img10,
                R.drawable.img11, R.drawable.img12, R.drawable.img13, R.drawable.img1, R.drawable.img2, R.drawable.img3, R.drawable.img4, R.drawable.img5, R.drawable.img6, R.drawable.img7, R.drawable.img8, R.drawable.img9, R.drawable.img10,
                R.drawable.img11, R.drawable.img12, R.drawable.img13, R.drawable.img1, R.drawable.img2, R.drawable.img3, R.drawable.img4, R.drawable.img5, R.drawable.img6, R.drawable.img7, R.drawable.img8, R.drawable.img9, R.drawable.img10,
                R.drawable.img11};
        /*String[] titles={"ABC","DEF","GHI","JKL","MNO","PQR","STU","VWX","YZ1","BDF","FGH","DFE","ASD","ABC","DEF","GHI","JKL","MNO","PQR","STU","VWX","YZ1","BDF","FGH","DFE","ASD","ABC","DEF","GHI","JKL","MNO","PQR","STU","VWX",
                "YZ1","BDF","FGH","DFE","ASD","ABC","DEF","GHI","JKL","MNO","PQR","STU","VWX","YZ1","BDF","FGH"};*/
        Log.d("LENGTH",""+icons.length);
        for (int i = 0; i < icons.length; i++) {
            Information current = new Information();
            current.iconId = icons[i];
            current.title = i;
            data.add(current);
        }
        return data;
    }

}
