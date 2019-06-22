package com.example.bundle;

import android.os.Bundle;

import com.example.plugnlib.PluginActivity;

public class BundleActivity extends PluginActivity {

    @Override
    public void onCreate(Bundle onSaveInstanceState) {
        super.onCreate(onSaveInstanceState);
        setContentView(R.layout.activity_bundle);
    }
}
