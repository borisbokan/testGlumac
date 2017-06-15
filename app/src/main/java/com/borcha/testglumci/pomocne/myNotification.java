package com.borcha.testglumci.pomocne;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Build;
import android.support.v7.app.NotificationCompat;


/**
 * Created by borcha on 28.05.17..
 */

public class myNotification extends  NotificationCompat.Builder{

    Context cont;

    public myNotification(Context cont, String _titl, String _text) {
        super(cont);
        this.cont= cont;
        setContentTitle(_titl);
        setContentText(_text);

    }


 }
