package edu.sungshin.toeat;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import androidx.core.app.NotificationCompat;

public class AlertReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        NotificationHelper notificationHelper=new NotificationHelper(context);
        NotificationCompat.Builder nb=notificationHelper.getChannel1Notification("유통기한 임박 알림","유통기한이 임박한 식품이 있습니다.");
        notificationHelper.getManager().notify(1,nb.build());
    }
}
