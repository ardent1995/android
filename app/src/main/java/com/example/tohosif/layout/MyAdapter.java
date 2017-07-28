package com.example.tohosif.layout;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tohosif.recyclerview.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tohosif on 14-07-2017.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    //    List<Information> data = new ArrayList<>();
//    PojoUsers pojoUsers;
    List<UserFromDatabase> data = new ArrayList<>();
    private int[] icons = {R.drawable.img1, R.drawable.img2, R.drawable.img3, R.drawable.img4, R.drawable.img5, R.drawable.img6, R.drawable.img7, R.drawable.img8, R.drawable.img9, R.drawable.img10,
            R.drawable.img11, R.drawable.img12, R.drawable.img13, R.drawable.img1, R.drawable.img2, R.drawable.img3, R.drawable.img4, R.drawable.img5, R.drawable.img6, R.drawable.img7, R.drawable.img8, R.drawable.img9, R.drawable.img10,
            R.drawable.img11, R.drawable.img12, R.drawable.img13, R.drawable.img1, R.drawable.img2, R.drawable.img3, R.drawable.img4, R.drawable.img5, R.drawable.img6, R.drawable.img7, R.drawable.img8, R.drawable.img9, R.drawable.img10,
            R.drawable.img11, R.drawable.img12, R.drawable.img13, R.drawable.img1, R.drawable.img2, R.drawable.img3, R.drawable.img4, R.drawable.img5, R.drawable.img6, R.drawable.img7, R.drawable.img8, R.drawable.img9, R.drawable.img10,
            R.drawable.img11};
    private LayoutInflater inflater;
    private Context context;

    public MyAdapter(Context context, List<UserFromDatabase> data) {
        this.context = context;
        inflater = LayoutInflater.from(context);       //To get LayoutInflater in a Given Context
        this.data = data;
//        try {
//            InputStream is = context.getAssets().open("user.json");
//            Scanner scanner = new Scanner(is);
//            StringBuilder builder = new StringBuilder();
//            while (scanner.hasNextLine()) {
//                builder.append(scanner.nextLine());
//            }
//            JSONObject root = new JSONObject(builder.toString());
//            JSONArray users = root.getJSONArray("users");
//            for (int i = 0; i < icons.length; i++) {
//                data.add(new Information(users.getJSONObject(i % 4), icons[i]));
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }

//        Gson gson = new Gson();
//        BufferedReader br = null;
//        try {
//            InputStream is = context.getAssets().open("user.json");
//            br = new BufferedReader(new InputStreamReader(is));
//            pojoUsers = gson.fromJson(br, PojoUsers.class);
//
////            if(pojoUsers !=null){
////                for(PojoUser pojoUser: pojoUsers.getUsers()){
////                    Log.d("USER",""+pojoUser);
////                }
////            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.custom_row, parent, false); //LayoutParam???
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
//        final Information currentInformation = data.get(position);
//        final PojoUser pojoUser = pojoUsers.getUsers().get(position % 4);
        int size = data.size();
        final UserFromDatabase user = data.get(position % size);
        user.setIconId(icons[position]);
        holder.iv_img.setImageResource(user.getIconId());
        String middleName = "";
        if (user.getMiddleName() != null) {
            middleName = user.getMiddleName();
        }
        holder.tx_txt.setText(user.getFirstName() + " " + middleName + " " + user.getLastName());
        holder.tx_txt.setTextColor(Color.RED);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ItemDetailsActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("user", user);
                intent.putExtras(bundle);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return icons.length;
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tx_txt;
        ImageView iv_img;

        public MyViewHolder(View itemView) {
            super(itemView);
            tx_txt = (TextView) itemView.findViewById(R.id.txt);
            iv_img = (ImageView) itemView.findViewById(R.id.img);
        }
    }
}
