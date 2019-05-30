package com.tomjung.fakegrpcserver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class Receiver extends BroadcastReceiver {
    private static final String TAG = Receiver.class.getSimpleName();

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d(TAG, "Action : " + intent.getAction());

        switch (intent.getAction()) {
            case Intent.ACTION_BOOT_COMPLETED:
                Intent serverServiceIntent = new Intent(context, ServerService.class);
                context.startService(serverServiceIntent);
                break;

            default:
                Log.e(TAG, "Error default case");
                break;
        }
    }
}
