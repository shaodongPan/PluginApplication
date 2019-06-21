package com.example.plugnlib;

import android.app.Activity;
import android.os.Bundle;

public interface IPlugin {

    int FROM_IN = 0;

    int FROM_OUT = 1;

    void onCreate(Bundle onSaveInstanceState);

    void attach(Activity activity);

    void onPause();

    void onDestroy();

}
