package com.example.plugnlib;

import android.content.pm.PackageInfo;
import android.content.res.AssetManager;
import android.content.res.Resources;

import dalvik.system.DexClassLoader;

public class PluginApk {

    public PackageInfo mPackageInfo;

    public DexClassLoader mClassLoader;

    public AssetManager mAssetManager;

    public Resources mResources;

    public PluginApk(PackageInfo mPackageInfo, DexClassLoader mClassLoader, Resources mResources) {
        this.mPackageInfo = mPackageInfo;
        this.mClassLoader = mClassLoader;
        this.mAssetManager = mResources.getAssets();
        this.mResources = mResources;
    }
}
