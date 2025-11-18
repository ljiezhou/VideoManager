package com.ds.commonsdk.umeng

import android.app.Application
import android.content.pm.ApplicationInfo
import android.content.pm.PackageManager
import android.util.Log
import com.ds.commonsdk.BuildConfig

/**
 * @Author ljiezhou
 * @date 2023/6/28
 * @Description
 */

val uMHolder: UMHolder by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
    UMHolder.INSTANCE
}

class UMHolder {
    fun preInit(application: Application, channel: String) {
//        UMConfigure.preInit(application, BuildConfig.UM_APP_KEY, channel)
    }


    fun init(context: Application, channel: String) {
        Log.d(TAG, "init: Umeng")
//        // 打开统计SDK调试模式
//        UMConfigure.setLogEnabled(BuildConfig.DEBUG)
////        if (BuildConfig.DEBUG) getTestDeviceInfo(context)
//
//        /**
//         * 初始化common库
//         * 参数1:上下文，不能为空
//         * 参数2:设备类型，UMConfigure.DEVICE_TYPE_PHONE为手机、UMConfigure.DEVICE_TYPE_BOX为盒子，默认为手机
//         * 参数3:Push推送业务的secret
//         */
//        //获取渠道信息
//        UMConfigure.init(context, BuildConfig.UM_APP_KEY, channel, UMConfigure.DEVICE_TYPE_PHONE, "")
//
//        //设置友盟渠道号
//        var appInfo: ApplicationInfo? = null
//        try {
//            appInfo = context.packageManager.getApplicationInfo(context.packageName, PackageManager.GET_META_DATA)
//        } catch (e: PackageManager.NameNotFoundException) {
//            e.printStackTrace()
//        }
//        if (appInfo?.metaData != null) appInfo.metaData.putString("UMENG_CHANNEL", channel)
//        MobclickAgent.setPageCollectionMode(MobclickAgent.PageMode.AUTO)
//        // 打开统计SDK调试模式
//        UMConfigure.setLogEnabled(BuildConfig.DEBUG)
    }

    companion object {
        val INSTANCE: UMHolder by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
            UMHolder()
        }
        private const val TAG = "UMHolder"

    }
}