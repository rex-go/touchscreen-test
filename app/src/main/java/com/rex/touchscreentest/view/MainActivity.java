package com.rex.touchscreentest.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;

import com.rex.touchscreentest.R;
import com.rex.touchscreentest.databinding.ActivityMainBinding;
import com.rex.touchscreentest.handler.MainHandler;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        MainHandler mainHandler = new MainHandler();
        binding.setMainHandler(mainHandler);
    }
}
