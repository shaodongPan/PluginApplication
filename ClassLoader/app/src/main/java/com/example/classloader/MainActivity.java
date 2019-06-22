package com.example.classloader;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.plugnlib.PluginManager;
import com.example.plugnlib.ProxyActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        PluginManager.getInstance().init(this);

        findViewById(R.id.loadApk).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String apkPath = Utils.copyAssetAndWrite(MainActivity.this,"b.apk");
                PluginManager.getInstance().loadApk(apkPath);
            }
        });

        findViewById(R.id.goApk).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, ProxyActivity.class);
                intent.putExtra("className","com.example.bundle.BundleActivity");
                startActivity(intent);
            }
        });
    }

    private void LoadApk(String path) {

    }
}
