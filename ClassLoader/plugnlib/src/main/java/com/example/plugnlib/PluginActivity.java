package com.example.plugnlib;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

public class PluginActivity extends Activity implements IPlugin {

    public Activity proxyActivity;

    public int FROM = FROM_IN;

    @Override
    public void onCreate(Bundle onSaveInstanceState) {
        if (onSaveInstanceState != null) {
            FROM = onSaveInstanceState.getInt("FROM");
        }

        if (FROM == FROM_IN) {
            super.onCreate(onSaveInstanceState);
            proxyActivity = this;
        }
    }

    @Override
    public void attach(Activity activity) {
        proxyActivity = activity;
    }

    @Override
    public void setContentView(int layoutResID) {
        if (FROM == FROM_IN) {
            super.setContentView(layoutResID);
        } else {
            proxyActivity.setContentView(layoutResID);
        }
    }

    @Override
    public void onPause() {
        if (FROM == FROM_IN) {
            super.onPause();
        }
    }

    @Override
    public void onDestroy() {
        if (FROM == FROM_IN) {
            super.onDestroy();
        }
    }
}
