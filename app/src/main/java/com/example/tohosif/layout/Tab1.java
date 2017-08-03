package com.example.tohosif.layout;


import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tohosif.recyclerview.DatabaseHelper;
import com.example.tohosif.recyclerview.R;

import java.util.ArrayList;
import java.util.List;

import static com.example.tohosif.recyclerview.TableDataContract.TableInfo;


/**
 * A simple {@link Fragment} subclass.
 */
public class Tab1 extends Fragment {

    public DatabaseHelper db;
    public MyAdapter adapter;
    private RecyclerView recyclerView;
    private List<UserFromDatabase> data;
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
        data = new ArrayList<>();
        fetchData();
        fab = (FloatingActionButton) layout.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(getActivity(), registerActivity.class);
                startActivity(in);
            }
        });
//        DividerItemDecoration dividerItemDecoration=new DividerItemDecoration(this,layoutManager.getOrientation());
//        recyclerView.addItemDecoration(dividerItemDecoration);

        return layout;
    }

    public void fetchData() {
        db = new DatabaseHelper(getActivity());
        try {
            db.createDataBase();
            db.openDataBase();
        } catch (Exception e) {
            e.printStackTrace();
        }
        SQLiteDatabase sd = db.getReadableDatabase();
        Cursor cursor = sd.query(TableInfo.TABLE_NAME, null, null, null, null, null, null);
        while (cursor.moveToNext()) {
            data.add(new UserFromDatabase(cursor.getInt(cursor.getColumnIndex(TableInfo.ID)), cursor.getString(cursor.getColumnIndex(TableInfo.FIRST_NAME)), cursor.getString(cursor.getColumnIndex(TableInfo.MIDDLE_NAME)),
                    cursor.getString(cursor.getColumnIndex(TableInfo.LAST_NAME)), cursor.getString(cursor.getColumnIndex(TableInfo.GENDER)), cursor.getString(cursor.getColumnIndex(TableInfo.DOB)), cursor.getString(cursor.getColumnIndex(TableInfo.CITY)),
                    cursor.getString(cursor.getColumnIndex(TableInfo.EMAIL_ID)), cursor.getString(cursor.getColumnIndex(TableInfo.PHONE_NO))));
        }
        adapter = new MyAdapter(getActivity(), data, db);
        recyclerView.setAdapter(adapter);
    }
}