package com.example.plugnlib;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.content.res.Resources;

import java.io.File;
import java.lang.reflect.Method;

import dalvik.system.DexClassLoader;

public class PlugnManager {

    private Context mContext;

    private static PlugnManager instance;

    public PluginApk getPlugnApk() {
        return plugnApk;
    }

    private PluginApk plugnApk;

    public static PlugnManager getInstance() {
        if (instance == null) {
            return new PlugnManager();
        }
        return instance;
    }

    public void init(Context context) {
        mContext = context;
    }

    /**
     * 加载apk
     */
    public void loadApk(String apkPath) {
        PackageInfo packageInfo = createPackageInfo(apkPath);
        DexClassLoader loader = createClassLoader(apkPath);
        AssetManager assetManager = createAssetManager(apkPath);
        Resources resources = createResources(assetManager);

        plugnApk = new PluginApk(packageInfo, loader, resources);
    }

    private Resources createResources(AssetManager assetManager) {
        Resources resources = mContext.getResources();
        return new Resources(assetManager, resources.getDisplayMetrics(), resources.getConfiguration());
    }

    //加载assetManager
    private AssetManager createAssetManager(String apkPath) {
        try {
            AssetManager assetManager = AssetManager.class.newInstance();
            Method method = assetManager.getClass().getDeclaredMethod("addAssetPath", String.class);
            method.invoke(assetManager, apkPath);
            return assetManager;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private DexClassLoader createClassLoader(String apkPath) {
        File file = mContext.getDir(apkPath, Context.MODE_PRIVATE);
        return new DexClassLoader("dex", file.getAbsolutePath()
                , null, mContext.getClassLoader());
    }

    private PackageInfo createPackageInfo(String apkPath) {
        PackageInfo packageInfo = mContext.getPackageManager().getPackageArchiveInfo(apkPath,
                PackageManager.GET_ACTIVITIES | PackageManager.GET_SERVICES);
        return packageInfo;
    }
}
