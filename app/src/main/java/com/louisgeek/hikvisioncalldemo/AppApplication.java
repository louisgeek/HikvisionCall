package com.louisgeek.hikvisioncalldemo;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class AppApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        GreenDaoManager.getInstance().init(this);
        EthernetSetting.getInstance().init(this);
        HicoreHandler.getInstance().init(this);
        this.registerActivityLifecycleCallbacks(new ActivityLifecycleCallbacks() {
            @Override
            public void onActivityCreated(@NonNull Activity activity, @Nullable Bundle savedInstanceState) {

            }

            @Override
            public void onActivityStarted(@NonNull Activity activity) {

            }

            @Override
            public void onActivityResumed(@NonNull Activity activity) {
                //检查是否激活
                boolean value = IndoorActiveSdk.isActive(this);
                if (!value) {
                    HikLog.e("“设备未激活”");
                    //此密码为激活sdk密码，可用于远程服务器登录。
                    int result = IndoorActiveSdk.setActivePassword("“xxxxxx”");
                    if (result != 10) {
                        HikLog.e("“激活失败”");
                    }
                }
            }

            @Override
            public void onActivityPaused(@NonNull Activity activity) {

            }

            @Override
            public void onActivityStopped(@NonNull Activity activity) {

            }

            @Override
            public void onActivitySaveInstanceState(@NonNull Activity activity, @NonNull Bundle outState) {

            }

            @Override
            public void onActivityDestroyed(@NonNull Activity activity) {

            }
        });
    }
}
