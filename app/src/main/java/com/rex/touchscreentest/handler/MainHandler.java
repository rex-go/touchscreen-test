package com.rex.touchscreentest.handler;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Toast;

import com.rex.touchscreentest.R;
import com.rex.touchscreentest.view.TouchscreenActivity;

public class MainHandler {

    public void startTest(View view) {
        Context context = view.getContext();
        Intent intent = new Intent(context, TouchscreenActivity.class);
        context.startActivity(intent);
    }

    public void notReady(View view) {
        Toast.makeText(view.getContext(), R.string.toast_not_ready, Toast.LENGTH_SHORT).show();
    }

    public void exit(View view) {
        ((Activity) view.getContext()).finish();
    }
}
