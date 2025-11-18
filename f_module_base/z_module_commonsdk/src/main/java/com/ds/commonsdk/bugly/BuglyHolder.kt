package com.ds.commonsdk.bugly

import android.app.Application
import com.ds.commonsdk.BuildConfig
import com.tencent.bugly.crashreport.CrashReport
import com.tencent.bugly.crashreport.CrashReport.UserStrategy

/**
 * @Author ljiezhou
 * @date 2023/6/28
 * @Description
 */
val buglyHolder: BuglyHolder by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
    BuglyHolder.INSTANCE
}

class BuglyHolder {

    fun init(context: Application, channel: String) {
        val strategy = UserStrategy(context)
        strategy.appChannel = channel;  //设置渠道
        strategy.appVersion = BuildConfig.APPLICATION_VERSION_NAME      //App的版本
        strategy.appPackageName = BuildConfig.APPLICATION_ID  //App的包名
        CrashReport.setIsDevelopmentDevice(context, BuildConfig.DEBUG)
        CrashReport.initCrashReport(context, BuildConfig.BUGLY_APPID, BuildConfig.DEBUG)
    }

    companion object {
        val INSTANCE: BuglyHolder by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
            BuglyHolder()
        }
    }
}