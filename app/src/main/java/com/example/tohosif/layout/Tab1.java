package com.example.tohosif.layout;


import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
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


/**
 * A simple {@link Fragment} subclass.
 */
public class Tab1 extends Fragment {

    public DatabaseHelper db;
    public MyAdapter adapter;
    private RecyclerView recyclerView;
    private List<UserFromDatabase> data;

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
        Cursor cursor = sd.query("user_table", null, null, null, null, null, null);
        while (cursor.moveToNext()) {
            data.add(new UserFromDatabase(cursor.getInt(cursor.getColumnIndex("Id")), cursor.getString(cursor.getColumnIndex("First_Name")), cursor.getString(cursor.getColumnIndex("Middle_Name")),
                    cursor.getString(cursor.getColumnIndex("Last_Name")), cursor.getString(cursor.getColumnIndex("Gender")), cursor.getString(cursor.getColumnIndex("DOB")), cursor.getString(cursor.getColumnIndex("City")),
                    cursor.getString(cursor.getColumnIndex("Email_id")), cursor.getString(cursor.getColumnIndex("Phone_No"))));
        }
        adapter = new MyAdapter(getActivity(), data, db);
        recyclerView.setAdapter(adapter);
    }
}