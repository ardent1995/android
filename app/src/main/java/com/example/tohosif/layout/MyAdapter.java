package com.example.tohosif.layout;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.view.ActionMode;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tohosif.recyclerview.DatabaseHelper;
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
    private DatabaseHelper db;

    private boolean multiSelect = false;
    private ArrayList<UserFromDatabase> selectedItems = new ArrayList<>();
    private ActionMode.Callback actionModeCallbacks = new ActionMode.Callback() {
        @Override
        public boolean onCreateActionMode(ActionMode mode, Menu menu) {
            multiSelect = true;
            mode.getMenuInflater().inflate(R.menu.menu_action_mode, menu);
            notifyDataSetChanged();
            return true;
        }

        @Override
        public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
            mode.setTitle(selectedItems.size() + " Selected");
            return false;
        }

        @Override
        public boolean onActionItemClicked(ActionMode mode, MenuItem item) {

            for (UserFromDatabase user : selectedItems) {
                data.remove(user);
            }

            Log.d("SIZE", "" + selectedItems.size());

//            AlertDialog.Builder myAlert = new AlertDialog.Builder(context);
//                myAlert.setMessage("Do you want to delete these entry?")
//                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialog, int which) {
//                                //delete from database
//                                    for(UserFromDatabase user: selectedItems){
//                                        data.remove(user);
//
////                                        int id = user.getId();
////                                        int rowsdeletedFromDatabase = db.deleteFromDatabase(id);
//                                    //delete from recyclerview
////                                    if (rowsdeletedFromDatabase>0) {
////                                        removeitem(user);
////                                    }
//                                }
//                                dialog.dismiss();
//                                Toast.makeText(context, selectedItems.size()+" entry deleted", Toast.LENGTH_LONG).show();
//                            }
//                        })
//                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialog, int which) {
//                                dialog.cancel();
//                            }
//                        })
//                        .setTitle("Confirmation")
//                        .create();
//                myAlert.show();

            mode.finish();
            return true;
        }

        @Override
        public void onDestroyActionMode(ActionMode mode) {
            multiSelect = false;
            selectedItems.clear();
            notifyDataSetChanged();
        }
    };


    public MyAdapter(Context context, List<UserFromDatabase> data, DatabaseHelper db) {
        this.context = context;
        inflater = LayoutInflater.from(context);       //To get LayoutInflater in a Given Context
        this.data = data;
        this.db = db;

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
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
//        final Information currentInformation = data.get(position);
//        final PojoUser pojoUser = pojoUsers.getUsers().get(position % 4);
        final UserFromDatabase user = data.get(position);
        user.setIconId(icons[position]);
        holder.iv_img.setImageResource(user.getIconId());
        String middleName = "";
        if (user.getMiddleName() != null) {
            middleName = user.getMiddleName();
        }
        holder.tx_txt.setText(user.getFirstName() + " " + middleName + " " + user.getLastName());
        holder.tx_txt.setTextColor(Color.RED);

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                ((AppCompatActivity) v.getContext()).startSupportActionMode(actionModeCallbacks);
                holder.selectItem(user);
                return true;
            }
        });
        holder.update(user);

//        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
//            @Override
//            public boolean onLongClick(View v) {
//                AlertDialog.Builder myAlert = new AlertDialog.Builder(context);
//                myAlert.setMessage("Do you want to delete this entry?")
//                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialog, int which) {
//                                //delete from database
//                                int id = user.getId();
//                                int rowsdeletedFromDatabase = db.deleteFromDatabase(id);
//                                //delete from recyclerview
//                                if (rowsdeletedFromDatabase>0) {
//                                    removeitem(user,position);
//                                }
//                                dialog.dismiss();
//                                Toast.makeText(context, "Entry deleted", Toast.LENGTH_LONG).show();
//                            }
//                        })
//                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialog, int which) {
//                                dialog.cancel();
//                            }
//                        })
//                        .setTitle("Confirmation")
//                        .create();
//                myAlert.show();
//                return true;
//            }
//        });
    }

//    private void removeitem(UserFromDatabase user) {
//        int CurrentPosition = data.indexOf(user);
//        data.remove(CurrentPosition);
//        notifyItemRemoved(CurrentPosition);
//    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tx_txt;
        ImageView iv_img;
        CardView cardView;

        public MyViewHolder(View itemView) {
            super(itemView);
            tx_txt = (TextView) itemView.findViewById(R.id.txt);
            iv_img = (ImageView) itemView.findViewById(R.id.img);
            cardView = (CardView) itemView.findViewById(R.id.card_view);
        }

        void selectItem(UserFromDatabase user) {
            if (multiSelect) {
                if (selectedItems.contains(user)) {
                    selectedItems.remove(user);
                    cardView.setBackgroundColor(Color.WHITE);
                } else {
                    selectedItems.add(user);
                    cardView.setBackgroundColor(Color.LTGRAY);
                }
            }
        }

        void update(final UserFromDatabase user) {
            if (selectedItems.contains(user)) {
                cardView.setBackgroundColor(Color.LTGRAY);
            } else {
                cardView.setBackgroundColor(Color.WHITE);
            }
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (!multiSelect) {
                        Intent intent = new Intent(context, ItemDetailsActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putSerializable("user", user);
                        intent.putExtras(bundle);
                        context.startActivity(intent);
                    } else {
                        selectItem(user);
                    }
                }
            });
        }
    }
}
