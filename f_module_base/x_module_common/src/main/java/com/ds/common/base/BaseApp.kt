package com.ds.common.base

import android.app.Application
import android.content.Context
import androidx.lifecycle.ViewModelProvider
import androidx.multidex.MultiDex
import com.blankj.utilcode.util.LogUtils
import com.ds.appname.AppNameConst
import com.ds.common.util.ChannelUtil

//import com.ds.commonsdk.umeng.uMHolder

/**
 * 作者　: hegaojian
 * 时间　: 2019/12/14
 * GetViewModelExt类的getAppViewModel方法
 */
abstract class BaseApp : Application() {

    private var mFactory: ViewModelProvider.Factory? = null

    override fun onCreate() {
        super.onCreate()
        ChannelUtil.channel(this)
        LogUtils.getConfig().globalTag = getString(AppNameConst.APP_NAME_ID)
//        uMHolder.preInit(this, ChannelUtil.channel)

        if (!com.ds.common.util.ProcessUtil.isAppMainProcess(applicationContext)) {
            return
        }
        _onCreate()
    }

    abstract fun _onCreate()
    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        MultiDex.install(base)
    }

    private fun getAppFactory(): ViewModelProvider.Factory {
        if (mFactory == null) {
            mFactory = ViewModelProvider.AndroidViewModelFactory.getInstance(this)
        }
        return mFactory as ViewModelProvider.Factory
    }

    companion object {
        private const val TAG = "BaseApp"
    }
}
