package com.crg.installtest;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import java.io.File;

public class MainActivity extends AppCompatActivity {
    private Button mButton;
    private File mApkFile;
    private Uri mUri;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        mApkFile = new File(getExternalFilesDir("apk"), "com.ss.android.article.news.1708231512.apk");
        mApkFile = new File(this.getExternalFilesDir("aloe"), "com.tencent.mobileqq.1708161007.apk");
        mButton = (Button) findViewById(R.id.button);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent installApkIntent = new Intent(Intent.ACTION_VIEW);
                installApkIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                installApkIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION | Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    mUri = FileProvider.getUriForFile(MainActivity.this, "com.crg.installtest.fileprovider", mApkFile);
                } else {
                    mUri = Uri.fromFile(mApkFile);
                }
                installApkIntent.setDataAndType(mUri, "application/vnd.android.package-archive");
                startActivity(installApkIntent);
            }
        });
    }
}
