package com.example.adaptivelayoutsample.helper;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;

import androidx.annotation.NonNull;

public class BackgroundThread extends Thread {

    private static final String TAG = "Background thread";
    public static final int BACKGROUND_START_VOICE = 0;
    public static final int BACKGROUND_PAUSE_VOICE = 1;


    private MyHandler handler;

    @Override
    public void run() {
        Looper.prepare();

        handler = new MyHandler(Looper.myLooper());
        Looper.loop();
    }

    public MyHandler getHandler() {
        return handler;
    }

    public class MyHandler extends Handler {

        public MyHandler(Looper looper){
            super(looper);
        }
        @Override
        public void handleMessage(@NonNull Message msg) {
            switch (msg.arg1) {
                case BACKGROUND_START_VOICE:
                    Log.i(TAG, "handleMessage: Starting your voice");
                    break;
                case BACKGROUND_PAUSE_VOICE:
                    Log.i(TAG, "handleMessage: Paused");
                    break;
                default:
                    Log.i(TAG, "handleMessage: Undefined option!");
            }
        }
    }
}
