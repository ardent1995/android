package com.example.tohosif.fragment;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.support.v7.app.AlertDialog;
import android.support.v7.view.ActionMode;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tohosif.db.UserTable;
import com.example.tohosif.model.UserFromDatabase;
import com.example.tohosif.recyclerview.MainActivity;
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
    private UserTable userTable;
    private ActionMode actionMode;
    private boolean multiSelect = false;
    private ArrayList<UserFromDatabase> selectedItems = new ArrayList<>();
    private CallbackInterface mCallback;
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
            return false;
        }

        @Override
        public boolean onActionItemClicked(final ActionMode mode, MenuItem item) {

            /*for (UserFromDatabase user : selectedItems) {
                data.remove(user);
            }*/


            AlertDialog.Builder myAlert = new AlertDialog.Builder(context);
            if (selectedItems.size() == 1) {
                myAlert.setMessage("Do you want to delete that entry?");
            } else {
                myAlert.setMessage("Do you want to delete these " + selectedItems.size() + " entries?");
            }
            myAlert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    //delete from database
                    for (UserFromDatabase user : selectedItems) {
                        int rowsdeletedFromDatabase = userTable.deleteFromDatabase(user);
                        //delete from recyclerview
                        if (rowsdeletedFromDatabase > 0) {
                            removeitem(user);
                        }
                    }
                    Toast.makeText(context, selectedItems.size() + " entry deleted", Toast.LENGTH_LONG).show();
                    mode.finish();
                    dialog.dismiss();
                }
            })
                    .setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    })
                    .setTitle("Confirmation")
                    .create();
            myAlert.show();

            return true;
        }

        @Override
        public void onDestroyActionMode(ActionMode mode) {
            actionMode = null;
            multiSelect = false;
            selectedItems.clear();
            notifyDataSetChanged();
        }
    };

    public MyAdapter(Context context, List<UserFromDatabase> data, UserTable userTable) {
        this.context = context;
        inflater = LayoutInflater.from(context);       //To get LayoutInflater in a Given Context
        this.data = data;
        this.userTable = userTable;

        try {
            mCallback = (CallbackInterface) context;
        } catch (ClassCastException e) {

        }

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

        holder.update(user);
    }

    private void removeitem(UserFromDatabase user) {
        int CurrentPosition = data.indexOf(user);
        data.remove(CurrentPosition);
        notifyItemRemoved(CurrentPosition);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public interface CallbackInterface {
        void onHandleSelection(UserFromDatabase user);
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tx_txt;
        ImageView iv_img;

        public MyViewHolder(View itemView) {
            super(itemView);
            tx_txt = (TextView) itemView.findViewById(R.id.txt);
            iv_img = (ImageView) itemView.findViewById(R.id.img);
        }

        void selectItem(UserFromDatabase user) {
            if (actionMode == null) {
                actionMode = ((MainActivity) context).startSupportActionMode(actionModeCallbacks);
            }
            if (actionMode != null) {
                if (multiSelect) {
                    if (selectedItems.contains(user)) {
                        selectedItems.remove(user);
                        itemView.setBackgroundResource(R.drawable.recycler_view_item);
                        actionMode.setTitle(selectedItems.size() + " Selected");
                    } else {
                        selectedItems.add(user);
                        itemView.setBackgroundColor(Color.LTGRAY);
                        actionMode.setTitle(selectedItems.size() + " Selected");
                    }
                }
            }
        }

        void update(final UserFromDatabase user) {
            if (selectedItems.contains(user)) {
                itemView.setBackgroundColor(Color.LTGRAY);
            } else {
                itemView.setBackgroundResource(R.drawable.recycler_view_item);
            }
            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    selectItem(user);
                    return true;
                }
            });
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (!multiSelect) {
//                        Intent intent = new Intent(context, ItemDetailsActivity.class);
//                        Bundle bundle = new Bundle();
//                        bundle.putSerializable("user", user);
//                        intent.putExtras(bundle);
//                        context.startActivity(intent);
                        if (mCallback != null) {
                            mCallback.onHandleSelection(user);
                        }
//                        Intent intent = new Intent(context,RegisterAndUpdateActivity.class);
//                        Bundle bundle = new Bundle();
//                        bundle.putSerializable("user",user);
//                        intent.putExtras(bundle);
//                        context.startActivity(intent);
                    } else {
                        selectItem(user);
                    }
                }
            });
        }
    }
}
