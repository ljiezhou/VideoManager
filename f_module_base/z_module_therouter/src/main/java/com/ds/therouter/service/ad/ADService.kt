package com.ds.therouter.service.ad

import android.app.Application
import android.content.Context
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity

/**
 * @Author ljiezhou
 * @date 2024/6/17
 * @Description
 */
interface ADService {
    fun init(application: Application, channel: String)

    fun loadSplashAd(activity: AppCompatActivity, container: FrameLayout, loadSplashAdListener: LoadSplashAdListener)

    fun loadNativeAd(context: Context, container: FrameLayout)
}
