package com.example.tohosif.layout;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.tohosif.recyclerview.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

/**
 * A simple {@link Fragment} subclass.
 */
public class Tab3 extends Fragment {
    TextView tv_data;
    Button btn_fetchdata;

    public Tab3() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View layout = inflater.inflate(R.layout.fragment_tab3, container, false);
        btn_fetchdata = (Button) layout.findViewById(R.id.btn_fetchdata);
        tv_data = (TextView) layout.findViewById(R.id.tv_data);
        btn_fetchdata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getData(v);
            }
        });

        return layout;
    }

    public void getData(View view) {
        try {
            InputStream is = getActivity().getAssets().open("user.json");
            Scanner scanner = new Scanner(is);
            StringBuilder builder = new StringBuilder();
            while (scanner.hasNextLine()) {
                builder.append(scanner.nextLine());
            }
            parseJson(builder.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void parseJson(String s) {
        StringBuilder builder = new StringBuilder();

        try {
            JSONObject root = new JSONObject(s);
            JSONArray users = root.getJSONArray("users");
            for (int i = 0; i < users.length(); i++) {
                JSONObject user = users.getJSONObject(i);
                builder.append("Name : ")
                        .append(user.getString("first_name"))
                        .append(" ")
                        .append(user.getString("middle_name"))
                        .append(" ")
                        .append(user.getString("last_name"))
                        .append("\n");
                builder.append("Gender : ").append(user.getString("gender")).append("\n");
                builder.append("City : ").append(user.getString("city")).append("\n");
                builder.append("Email id : ").append(user.getString("email_id")).append("\n");
                builder.append("Phone No. : ").append(user.getString("phone_no.")).append("\n\n\n");
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        tv_data.setText(builder.toString());
    }
    }
