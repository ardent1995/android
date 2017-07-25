package com.example.tohosif.layout;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tohosif.recyclerview.R;


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
        View layout = inflater.inflate(R.layout.fragment_tab1, container, false);
        recyclerView = (RecyclerView) layout.findViewById(R.id.recycler_view);
        adapter = new MyAdapter(getActivity());
        recyclerView.setAdapter(adapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
//        DividerItemDecoration dividerItemDecoration=new DividerItemDecoration(this,layoutManager.getOrientation());
//        recyclerView.addItemDecoration(dividerItemDecoration);

        return layout;
    }


}
