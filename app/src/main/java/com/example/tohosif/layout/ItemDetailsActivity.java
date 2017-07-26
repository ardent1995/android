package com.example.tohosif.layout;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.tohosif.recyclerview.R;


public class ItemDetailsActivity extends AppCompatActivity {
    TextView tv_details;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_details);

        tv_details = (TextView) findViewById(R.id.tv_details);

        try {
            PojoUser pojoUser = (PojoUser) getIntent().getExtras().getSerializable("user");
            tv_details.setText(pojoUser.getDetails());
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }
}
