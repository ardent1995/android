package com.example.tohosif.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tohosif.db.DatabaseHelper;
import com.example.tohosif.db.UserTable;
import com.example.tohosif.model.UserFromDatabase;
import com.example.tohosif.recyclerview.MainActivity;
import com.example.tohosif.recyclerview.R;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class Tab1 extends Fragment {

    public DatabaseHelper db;
    public UserTable userTable;
    public MyAdapter adapter;
    public List<UserFromDatabase> data;
    private RecyclerView recyclerView;
    private FloatingActionButton fab;

    public Tab1() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View layout = inflater.inflate(R.layout.fragment_tab1, container, false);

        recyclerView = (RecyclerView) layout.findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

        db = new DatabaseHelper(getActivity());
        data = new ArrayList<>();
        fetchData();

        fab = (FloatingActionButton) layout.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(getActivity(), RegisterAndUpdateActivity.class);
                startActivityForResult(in, 1);
            }
        });

        ((MainActivity) getActivity()).setFragmentRefreshListener(new MainActivity.FragmentRefreshListener() {
            @Override
            public void onRefresh() {
                data.clear();
                fetchData();
            }
        });

//        DividerItemDecoration dividerItemDecoration=new DividerItemDecoration(this,layoutManager.getOrientation());
//        recyclerView.addItemDecoration(dividerItemDecoration);

        return layout;
    }

    public void fetchData() {
        try {
            db.createDataBase();
            db.openDataBase();
        } catch (Exception e) {
            e.printStackTrace();
        }
        userTable = new UserTable(db);
        data = userTable.getUserFromDatabaseList();

        adapter = new MyAdapter(getActivity(), data, userTable);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            this.data.clear();
            fetchData();
        }
    }
}