package com.example.bundle;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import com.example.plugnlib.IPlugin;
import com.example.plugnlib.PluginApk;
import com.example.plugnlib.PlugnManager;

public class ProxyActivity extends Activity {

    private String className;

    private PluginApk plugnApk;

    private IPlugin mPlugin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        className = getIntent().getExtras().getString("className");
        plugnApk = PlugnManager.getInstance().getPlugnApk();
        launchActivity();
    }

    private void launchActivity() {
        if (plugnApk == null) {
            Log.e("ProxyActivity", "xxx加载错误");
        }

        try {
            Class<?> clazz = plugnApk.mClassLoader.loadClass(className);
            Object object = clazz.newInstance();
            if (object instanceof IPlugin) {
                mPlugin = (IPlugin) object;
                mPlugin.attach(this);
                Bundle bundle = new Bundle();
                bundle.putInt("FROM", IPlugin.FROM_IN);
                mPlugin.onCreate(bundle);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
