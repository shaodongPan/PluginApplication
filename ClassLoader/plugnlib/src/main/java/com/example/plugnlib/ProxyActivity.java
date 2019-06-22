package com.example.plugnlib;

import android.app.Activity;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;

public class ProxyActivity extends Activity {

    private String className;

    private PluginApk pluginApk;

    private IPlugin mPlugin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        className = getIntent().getExtras().getString("className");
        pluginApk = PluginManager.getInstance().getPluginApk();
        launchActivity();
    }

    private void launchActivity() {
        if (pluginApk == null) {
            Log.e("ProxyActivity", "xxx加载错误");
        }

        try {
            Class<?> clazz = pluginApk.mClassLoader.loadClass(className);
            Object object = clazz.newInstance();
            if (object instanceof IPlugin) {
                mPlugin = (IPlugin) object;
                mPlugin.attach(this);
                Bundle bundle = new Bundle();
                bundle.putInt("FROM", IPlugin.FROM_OUT);
                mPlugin.onCreate(bundle);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Override
    public Resources getResources() {
        return pluginApk != null ? pluginApk.mResources : super.getResources();
    }

    @Override
    public AssetManager getAssets() {
        return pluginApk != null ? pluginApk.mAssetManager : super.getAssets();
    }

    @Override
    public ClassLoader getClassLoader() {
        return pluginApk != null ? pluginApk.mClassLoader : super.getClassLoader();
    }
}
