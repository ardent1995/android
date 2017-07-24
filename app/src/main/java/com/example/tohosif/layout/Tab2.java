package com.example.tohosif.layout;


import android.app.ActivityManager;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.NotificationCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RemoteViews;

import com.example.tohosif.recyclerview.MainActivity;
import com.example.tohosif.recyclerview.R;

import static android.content.Context.NOTIFICATION_SERVICE;

/**
 * A simple {@link Fragment} subclass.
 */
public class Tab2 extends Fragment {

    Button startbtn, stopbtn;
    private NotificationCompat.Builder notification;
    private NotificationManager nm;
    private int uniqueId;
    private RemoteViews remoteViews;
    private Context context;

    public Tab2() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        context = getActivity();
        nm = (NotificationManager) context.getSystemService(NOTIFICATION_SERVICE);
        remoteViews = new RemoteViews(context.getPackageName(), R.layout.custom_notification);

        remoteViews.setImageViewResource(R.id.notification_img, R.mipmap.ic_launcher_round);
        remoteViews.setTextViewText(R.id.notification_title, "This is my Notification");
        remoteViews.setTextViewText(R.id.notification_text, "You are looking at my notification.");

        uniqueId = (int) System.currentTimeMillis();
        Intent buttonIntent = new Intent("ButtonClicked");
        buttonIntent.putExtra("id", uniqueId);

        PendingIntent buttonPendingIntent = PendingIntent.getBroadcast(context, 1, buttonIntent, 0);
        remoteViews.setOnClickPendingIntent(R.id.notification_button, buttonPendingIntent);

        View layout = inflater.inflate(R.layout.fragment_tab2, container, false);
        startbtn = (Button) layout.findViewById(R.id.Startbtn);
        stopbtn = (Button) layout.findViewById(R.id.stopbtn);
        if (isMyServiceRunning(MyServices.class)) {
            startbtn.setVisibility(View.GONE);
            stopbtn.setVisibility(View.VISIBLE);
        } else {
            stopbtn.setVisibility(View.GONE);
            startbtn.setVisibility(View.VISIBLE);
        }
        startbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(context, MainActivity.class);
                PendingIntent pendIntent = PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);

                notification = new NotificationCompat.Builder(context);
                notification.setSmallIcon(R.mipmap.ic_launcher)
                        .setAutoCancel(true)
                        .setCustomBigContentView(remoteViews)
                        .setContentIntent(pendIntent);

                nm.notify(uniqueId, notification.build());
                context.startService(new Intent(context, MyServices.class));
                startbtn.setVisibility(View.GONE);
                stopbtn.setVisibility(View.VISIBLE);
            }
        });
        stopbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.stopService(new Intent(context, MyServices.class));
                stopbtn.setVisibility(View.GONE);
                startbtn.setVisibility(View.VISIBLE);
            }
        });
        return layout;
    }

    private boolean isMyServiceRunning(Class<?> serviceClass) {
        ActivityManager manager = (ActivityManager) getActivity().getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {
            if (serviceClass.getName().equals(service.service.getClassName())) {
                return true;
            }
        }
        return false;
    }
}
