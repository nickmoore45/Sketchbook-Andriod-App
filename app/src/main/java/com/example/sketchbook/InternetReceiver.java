package com.example.sketchbook;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class InternetReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        String status = CheckInternet.getNetworkInfo(context);
        if (status.equals("connected")){
            Toast.makeText(context.getApplicationContext(), "Connected to the internet", Toast.LENGTH_LONG).show();

        }
        else if (status.equals("disconnected")){
            Toast.makeText(context.getApplicationContext(), "Not connected to the internet", Toast.LENGTH_LONG).show();
        }
    }
}
